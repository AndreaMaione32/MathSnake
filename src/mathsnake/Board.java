/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author edoardocossentino
 */
public abstract class Board extends JPanel implements Runnable{
    protected STATE state;
    
    protected Image ball;
    protected Image head;
    protected Image shield_small;
    protected Image yellow_dot;
    protected Image small_coins;
    protected Image grey_dot;
    protected CoinsSaver coinsSaver = new CoinsSaver();
    protected Snake snake = new Snake();
    protected double snakeSpeed = 300;
    protected boolean leftPressed = false;
    protected boolean rightPressed = false;
    protected double downSpeed = Environment.getInstance().STARTDOWNSPEED; //define velocity of block and power ups
    protected int gameBest = Environment.getInstance().STARTLIFEPOINTS;
    protected ConstructorThread constructorThread;
    protected Thread CThread = new Thread(constructorThread);
    protected boolean stop = false;
    protected boolean pause = false;
    protected Background background;
    
    public Image getBall() {
        return ball;
    }

    public Image getHead() {
        return head;
    }

    public Snake getSnake() {
        return snake;
    }
    
    protected void initBoard() {
        background = new Background(Environment.getInstance().PATHBACKGROUND);
        setFocusable(true);
        setPreferredSize(new Dimension(Environment.getInstance().JP_WIDTH, Environment.getInstance().JP_HEIGHT));
        setLayout(new GridBagLayout());
        loadImages();
        initGame();
        addListeners();
    }
    
    protected void loadImages() {
        ball = snake.loadImage(Environment.getInstance().PATHSKIN);
        shield_small = snake.loadImage(Environment.getInstance().PATHIMAGES + "shield_small.png");
        yellow_dot = snake.loadImage(Environment.getInstance().PATHIMAGES + "yellow_dot.png");
        small_coins = snake.loadImage(Environment.getInstance().PATHIMAGES + "small_retro_coins.png");
        grey_dot = snake.loadImage(Environment.getInstance().PATHIMAGES + "grey_dot.png");
    }
    
    protected void initGame() {
        // Viene inizializzato il timer necessario per i repaint
        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    protected void doDrawing(Graphics g) {
        double[] xVector = snake.getX();
        double[] yVector = snake.getY();
        //DRAWING BACKGROUND
        this.background.drawBackground(g);
        switch (state) {
            case IN_GAME:
                //The rendering hints are used to make the drawing smooth
                RenderingHints rh= new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                ((Graphics2D) g).setRenderingHints(rh);
                //DRAWING SNAKE
                for (int z = 0; z < Environment.getInstance().DOT_NUM - 1; z++){
                    if(snake.isSpeedUped())
                        g.drawImage(this.yellow_dot, (int)xVector[z], (int)yVector[z], this);
                    if(snake.isShielded()){
                      g.drawImage(this.grey_dot, (int)xVector[z], (int)yVector[z], this);
                       g.drawImage(this.shield_small, (int)xVector[z], (int)yVector[z], this);
                    }
                    if(!(snake.isShielded() || snake.isSpeedUped()))
                        g.drawImage(this.ball, (int)xVector[z], (int)yVector[z], this);
                }
                if(snake.isShielded())
                    g.drawImage(grey_dot, (int)xVector[Environment.getInstance().DOT_NUM-1], (int)yVector[Environment.getInstance().DOT_NUM - 1], this);
                if(snake.isSpeedUped())
                    g.drawImage(yellow_dot, (int)xVector[Environment.getInstance().DOT_NUM-1], (int)yVector[Environment.getInstance().DOT_NUM - 1], this);
                if(!(snake.isShielded() || snake.isSpeedUped()))
                    g.drawImage(ball, (int)xVector[Environment.getInstance().DOT_NUM-1], (int)yVector[Environment.getInstance().DOT_NUM - 1], this);
                g.setFont(new Font("Arial", Font.BOLD, 15));
                g.setColor(Environment.getInstance().WRITECOLOR);
                g.drawString(Integer.toString(snake.getLife()), (int)xVector[Environment.getInstance().DOT_NUM-1] + 20, (int)yVector[Environment.getInstance().DOT_NUM - 1] + 10);
                //DRAW ELEMENT
                ElementManager elementManager = ElementManager.getInstance();
                for(DownElement e : elementManager){
                    e.draw(g);
                }
                Font font = new Font("Arial", Font.BOLD, 16);
                FontMetrics metrics = g.getFontMetrics(font);
                g.setFont(font);
                g.setColor(Environment.getInstance().WRITECOLOR);                    
                String text = "SCORE : " + Integer.toString(gameBest);
                g.setFont(font);
                g.setColor(Environment.getInstance().WRITECOLOR);
                int textX = Environment.getInstance().JP_WIDTH - (10 + metrics.stringWidth(text));
                int textY = 20 + metrics.getHeight();
                g.drawString(text, textX, textY);
                String textCoin = "x"+coinsSaver.getCurrentCoins();
                font = new Font("Arial", Font.BOLD, 18);
                g.setFont(font);
                metrics = g.getFontMetrics(font);
                int textCoinX = Environment.getInstance().JP_WIDTH - (10 + metrics.stringWidth(textCoin));
                int imgX = textCoinX - this.small_coins.getWidth(null);;
                int imgY = textY + 10;
                int textCoinY = imgY + ((this.small_coins.getHeight(null) - metrics.getHeight()) / 2) + metrics.getAscent();;
                g.drawString(textCoin, textCoinX, textCoinY);
                g.drawImage(this.small_coins, imgX, imgY, null);
                break;
            case GAMEOVER:
                gameOver();
                break;
            default:
                break;
        }
    }
    
    protected void gameOver() {
        constructorThread.stopThread();
        long endTime = System.currentTimeMillis() + 1000;
        while(System.currentTimeMillis() != endTime) {
            // Wait 1 second
        }
        //RESTART BACKGROUND
        background = new Background(Environment.getInstance().PATHBACKGROUND);
        snake = new Snake();
    }
    
    protected void checkCollision(){
        ElementManager elementManager = ElementManager.getInstance();
        if (state == STATE.IN_GAME){
            for(DownElement e : elementManager){
                if(snake.collide(e.getAssociatedRectangle())){     //check if snake's head collide with block's rectangle
                    e.collsionAction(this);
                    elementManager.removeElement(e);
                }
                if(e.getY() > Environment.getInstance().JP_HEIGHT){   //check if the block is visibile on the screen, if is not the block is removed
                    elementManager.removeElement(e);
                }
            }
        }        
    }
    
    protected void initialState() {
        ElementManager.getInstance().flush();
        loadImages();
        
        this.leftPressed = false;
        this.rightPressed = false;
        coinsSaver = new CoinsSaver();
        snake.setLife(10);
        gameBest = 0;
    }
    
    protected void moveElements(double ds){
        ElementManager em = ElementManager.getInstance();
        for(DownElement e : em){
            e.move(ds);
        }
    }
    
    protected abstract void addListeners();
    
    public int getGameBest() {
        return gameBest;
    }
    
    public STATE getState() {
        return state;
    }

    public void setState(STATE state) {
        this.state = state;
    }

    public void setGameBest(int gameBest) {
        this.gameBest = gameBest;
    }
    
    public void stop(){
        this.stop = true;
    }
    
    public enum STATE {
        COUNTDOWN,
        IN_GAME,
        PAUSE,
        GAMEOVER
    }
    
    public CoinsSaver getCoinsSaver() {
        return coinsSaver;
    }

    public void setCoinsSaver(CoinsSaver coinsSaver) {
        this.coinsSaver = coinsSaver;
    }
    
    
}

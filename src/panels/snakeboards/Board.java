package panels.snakeboards;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JPanel;
import background.Background;
import constructors.ConstructorThread;
import downelements.DownElement;
import elementmanager.ElementManager;
import environment.Environment;
import iofiles.CoinsSaver;
import snake.Snake;

public abstract class Board extends JPanel implements Runnable{
    protected STATE state;
    
    protected Image ball;
    protected Image head;
    protected Image shield_small;
    protected Image yellow_dot;
    protected Image small_coins;
    protected Image grey_dot;
    protected CoinsSaver coinsSaver;
    protected Snake snake;
    protected double snakeSpeed;
    protected boolean leftPressed;
    protected boolean rightPressed;
    protected double downSpeed; //define velocity of block and power ups
    protected int gameBest;
    protected ConstructorThread constructorThread;
    protected Thread CThread;
    protected boolean stop;
    protected boolean pause;
    protected Background background;
    
    protected void initBoard() {
        coinsSaver = new CoinsSaver();
        snake = new Snake();
        snakeSpeed = 300;
        leftPressed = false;
        rightPressed = false;
        downSpeed = Environment.getInstance().STARTDOWNSPEED;
        gameBest = Environment.getInstance().STARTLIFEPOINTS;
        CThread = new Thread(constructorThread);
        stop = true;
        pause = false;
        background = new Background(Environment.getInstance().PATHBACKGROUND);
        setFocusable(true);
        setPreferredSize(new Dimension(Environment.getInstance().JP_WIDTH, Environment.getInstance().JP_HEIGHT));
        setLayout(new GridBagLayout());
        loadImages();
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
        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    protected void doDrawing(Graphics g) {
        //DRAWING BACKGROUND
        this.background.drawBackground(g);
        switch (state) {
            case IN_GAME:
                drawAll(g);
                if(pause)
                    drawPause(g);
                break;
            case GAMEOVER:
                gameOver();
                break;
            default:
                break;
        }
    }
    
    private void drawPause(Graphics g){
        g.setColor(new Color(0,0,0,180));
        int rectX = Environment.getInstance().JP_WIDTH/2 - 160;
        g.fillRect(rectX, 80, 320, 320);
        Font font = new Font("Arial", Font.BOLD, 30);
        FontMetrics metrics = g.getFontMetrics(font);
        String text = "PAUSE";
        g.setFont(font);
        g.setColor(Environment.getInstance().WRITECOLOR);
        int textX = (Environment.getInstance().JP_WIDTH - metrics.stringWidth(text)) / 2;
        g.drawString(text, textX, 50);
        g.setColor(Color.WHITE);
        font = new Font("Arial", Font.BOLD, 25);
        g.setFont(font);
        metrics = g.getFontMetrics(font);
        text = "PRESS \"R\" TO RESUME";
        textX = rectX + (320 - metrics.stringWidth(text)) / 2;
        g.drawString(text, textX, 160);
        text = "PRESS \"Q\" TO QUIT";
        textX = rectX + (320 - metrics.stringWidth(text)) / 2;
        g.drawString(text, textX, 320);
    }
    
    private void drawAll(Graphics g){
        double[] xVector = snake.getX();
        double[] yVector = snake.getY();
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
        String textCoin = " "+Integer.toString(coinsSaver.getCurrentCoins());
        font = new Font("Arial", Font.BOLD, 18);
        g.setFont(font);
        metrics = g.getFontMetrics(font);
        int textCoinX = Environment.getInstance().JP_WIDTH - (10 + metrics.stringWidth(textCoin));
        int imgX = textCoinX - this.small_coins.getWidth(null);;
        int imgY = textY + 10;
        int textCoinY = imgY + ((this.small_coins.getHeight(null) - metrics.getHeight()) / 2) + metrics.getAscent();;
        g.drawString(textCoin, textCoinX, textCoinY);
        g.drawImage(this.small_coins, imgX, imgY, null);
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
                    e.collisionAction(this);
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
    
    protected void addListeners(){
        addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e) {
                if(stop){
                background = new Background(Environment.getInstance().PATHBACKGROUND);
                Board b = (Board)e.getSource();
                new Thread((Board)e.getSource()).start();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                requestFocusInWindow();
            }
        });
    }
    
    public Image getBall() {
        return ball;
    }

    public Image getHead() {
        return head;
    }

    public Snake getSnake() {
        return snake;
    }
    
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
    
    public CoinsSaver getCoinsSaver() {
        return coinsSaver;
    }

    public void setCoinsSaver(CoinsSaver coinsSaver) {
        this.coinsSaver = coinsSaver;
    }
    
    public enum STATE {
        COUNTDOWN,
        IN_GAME,
        GAMEOVER
    }

}

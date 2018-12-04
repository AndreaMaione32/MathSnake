package mathsnake;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeBoard extends JPanel implements Runnable {
    
    private STATE state = STATE.COUNTDOWN;
    
    private Timer countdownTimer;
    private int secondsLeft = 3;
    private JLabel countdown = new JLabel(Integer.toString(secondsLeft));
    private Image ball;
    private Image head;
    private Image shield_small;
    private Image yellow_dot;
    private Image small_coins;
    private Image grey_dot;
    private CoinsSaver coinsSaver = new CoinsSaver();
    private final Snake snake = new Snake();
    private double snakeSpeed = 300;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private double downSpeed = Environment.STARTDOWNSPEED; //define velocity of block and power ups
    private int gameBest = Environment.STARTLIFEPOINTS;
    private ConstructorThread constructorThread = new ConstructorThread(snake);
    private Thread CThread = new Thread(constructorThread);
    private boolean stop = false;
    private boolean pause = false;
    private Background background;
    
    public SnakeBoard() {
        initSnakeBoard();
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
    
    private void initSnakeBoard() {
        setBackground(Color.WHITE);
        background = new Background(Environment.PATHBACKGROUND);
        setFocusable(true);
        setPreferredSize(new Dimension(Environment.JP_WIDTH, Environment.JP_HEIGHT));
        setLayout(new GridBagLayout());
        loadImages();
        initGame();
        countdown();
        addListeners();
    }
   
    private void loadImages() {
        ball = snake.loadImage(Environment.PATHIMAGES + "dot.png");
        shield_small = snake.loadImage(Environment.PATHIMAGES + "shield_small.png");
        head = snake.loadImage(Environment.PATHIMAGES + "smiling.png");
        yellow_dot = snake.loadImage(Environment.PATHIMAGES + "yellow_dot.png");
        small_coins = snake.loadImage(Environment.PATHIMAGES+"small_retro_coins.png");
        grey_dot = snake.loadImage(Environment.PATHIMAGES+"grey_dot.png");
    }
    
    private void initGame() {
        // Viene inizializzato il timer necessario per i repaint
        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    @Override
    public void run(){
        long beforeTime, delta, sleep; 
        beforeTime = System.currentTimeMillis();
        while(!stop){
            if(!pause){
                if (hasFocus() && state == STATE.COUNTDOWN) {
                if(!countdownTimer.isRunning()) {
                    countdown();
                    countdownTimer.start();
                }
            }
            if (hasFocus() && state == STATE.IN_GAME) {
                if(!CThread.isAlive()){
                    initialState();
                    constructorThread = new ConstructorThread(snake);
                    CThread = new Thread(constructorThread);
                    CThread.start();
                }
            }
            if(state == STATE.IN_GAME){
                checkCollision();
                snake.move();
                double ds = determineDownSpeed();
                this.moveBlocks(ds);
                this.movePowerUps(ds);
                this.moveCoins(ds);
                background.move(ds/2);
                snake.setHorizontalMovement(0);	
                if ((leftPressed) && (!rightPressed)) {
                    snake.setHorizontalMovement(-snakeSpeed);
                } else if ((rightPressed) && (!leftPressed)) {
                    snake.setHorizontalMovement(snakeSpeed);
                }
            }
            repaint();
            }
            delta = System.currentTimeMillis() - beforeTime;
            sleep = Environment.DELAY - delta;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }            
            beforeTime = System.currentTimeMillis();
        }
    }
    
    private void doDrawing(Graphics g) {
        double[] xVector = snake.getX();
        double[] yVector = snake.getY();
        //DRAWING BACKGROUND
        this.background.drawBackground(g);
        switch (state) {
            case COUNTDOWN:
                countdown.setText(Integer.toString(secondsLeft));
                checkCountdown();
                break;
            case IN_GAME:
                //The rendering hints are used to make the drawing smooth
                RenderingHints rh= new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                ((Graphics2D) g).setRenderingHints(rh);
                //DRAWING SNAKE
                for (int z = 0; z < Environment.DOT_NUM - 1; z++){
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
                    g.drawImage(grey_dot, (int)xVector[Environment.DOT_NUM-1], (int)yVector[Environment.DOT_NUM - 1], this);
                if(snake.isSpeedUped())
                    g.drawImage(yellow_dot, (int)xVector[Environment.DOT_NUM-1], (int)yVector[Environment.DOT_NUM - 1], this);
                if(!(snake.isShielded() || snake.isSpeedUped()))
                    g.drawImage(ball, (int)xVector[Environment.DOT_NUM-1], (int)yVector[Environment.DOT_NUM - 1], this);
                g.setFont(new Font("Arial", Font.BOLD, 15));
                g.setColor(Color.black);
                g.drawString(Integer.toString(snake.getLife()), (int)xVector[Environment.DOT_NUM-1] + 20, (int)yVector[Environment.DOT_NUM - 1] + 10);
                //DRAWING BLOCK
                BlocksManager blocksManager = BlocksManager.getInstance();
                for(int i = 0; i < blocksManager.numBlocks(); i++){
                    Block b = blocksManager.getBlock(i);
                    b.printBlock(g);
                }
                //DRAWING POWER UPS
                PowerUpsManager powerUpsManager= PowerUpsManager.getInstance();
                for(int i = 0; i<powerUpsManager.powerUpsnums(); i++){
                    PowerUps p = powerUpsManager.getPowerUps(i);
                    p.drawPowerUps(g);
                }
                //DRAWING COINS
                CoinsManager coinsManager = CoinsManager.getInstance();
                for(int i = 0; i<coinsManager.numCoins(); i++){
                    Coin c = coinsManager.getCoin(i);
                    c.drawCoin(g);
                }
                Font font = new Font("Arial", Font.BOLD, 14);
                FontMetrics metrics = g.getFontMetrics(font);
                g.setFont(font);
                g.setColor(Color.black);
                String text = "CURRENT BEST : NULL"; //Dovrà mostrare il miglior punteggio della Scoreboard
                g.drawString("CURRENT BEST : NULL", Environment.JP_WIDTH - (10 + metrics.stringWidth(text)), 20); 

                text = "GAME BEST : " + Integer.toString(gameBest);
                g.setFont(font);
                g.setColor(Color.black);
                int textX = Environment.JP_WIDTH - (10 + metrics.stringWidth(text));
                int textY = 20 + metrics.getHeight();
                g.drawString("GAME BEST : " + Integer.toString(gameBest), textX, textY);
                String textCoin = "x"+coinsSaver.getCurrentCoins();
                font = new Font("Arial", Font.BOLD, 18);
                g.setFont(font);
                metrics = g.getFontMetrics(font);
                int textCoinX = Environment.JP_WIDTH - (10 + metrics.stringWidth(textCoin));
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

    private void gameOver() {
        constructorThread.stopThread();
        long endTime = System.currentTimeMillis() + 1000;
        while(System.currentTimeMillis() != endTime) {
            // Wait 1 second
        }
        coinsSaver.saveCoins();
        state = STATE.COUNTDOWN;
        //RESTART BACKGROUND
        background = new Background(Environment.PATHBACKGROUND);
        CardLayout cl = MathSnake.getInstance().getCardLayout();
        cl.show(MathSnake.getInstance().getCardsJPanel(), "gameOver");
    }
    
    private void countdown() {
        countdown.setFont(new Font("Arial", Font.BOLD, 100));
        countdown.setForeground(Color.BLACK);
        add(countdown);
        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsLeft--;
            }
        });
    }
    
    private void checkCountdown() {
        if (secondsLeft == 0) {
            remove(countdown);
            countdownTimer.stop();
            state = STATE.IN_GAME;
        }
    }
    
    private void checkCollision(){
        BlocksManager blocksManager = BlocksManager.getInstance();
        PowerUpsManager powerUpsManager = PowerUpsManager.getInstance();
        CoinsManager coinsManager = CoinsManager.getInstance();
        Block b;
        PowerUps p;
        Coin c;
           
        if (state == STATE.IN_GAME){
            //COLLISIONS WITH BLOCK
            for(int y=0; y<blocksManager.numBlocks(); y++){
                b = blocksManager.getBlock(y);
                if(snake.collide(b.getAssociatedRectangle())){     //check if snake's head collide with block's rectangle
                    changeLife(b.getStrOp(), b.getValue(), snake);
                    blocksManager.removeBlock(b);
                    y--; //decrease y by one because when a block is eliminated the other blocks in list are shifted by one to left, so the next element to check is y again
                }
                if(b.getY() > Environment.JP_HEIGHT){   //check if the block is visibile on the screen, if is not the block is removed
                    blocksManager.removeBlock(b);
                    y--;
                }
            }
            //COLLISIONS WITH POWER UPS
            for(int y=0; y<powerUpsManager.powerUpsnums(); y++){
                p = powerUpsManager.getPowerUps(y);
                if(snake.collide(p.getAssociatedRectangle())){
                    p.action(snake);
                    powerUpsManager.removePowerUps(p);
                    y--;
                }
                if(p.getY() > Environment.JP_HEIGHT){
                    powerUpsManager.removePowerUps(p);
                    y--;
                }
            }
            //COLLISION WITH COINS
            for(int y = 0; y<coinsManager.numCoins(); y++){
                c = coinsManager.getCoin(y);
                if(snake.collide(c.getAssociatedRectangle())){
                    coinsSaver.setCurrentCoins(coinsSaver.getCurrentCoins()+1);
                    coinsManager.removeCoin(c);
                    y--;
                }
                if(c.getY() > Environment.JP_HEIGHT){
                    coinsManager.removeCoin(c);
                    y--;
                }
            }
        }        
    }
    
    private void changeLife(String op, int value, Snake snake){
        int actualLife = snake.getLife();
        

        if (op.equals("+"))
            snake.setLife(actualLife + value);
        if (op.equals("x"))
            snake.setLife(actualLife * value);
        if (op.equals("-"))
            snake.setLife(actualLife - value);
        if (op.equals("/"))
            snake.setLife(actualLife / value);
        
        actualLife = snake.getLife();
        if (actualLife < 0)
            snake.setLife(0);
        else {
            if (actualLife > gameBest)
                gameBest = actualLife;
        }
        if (snake.getLife() == 0)
            state = STATE.GAMEOVER;
        
    }
    
    private void initialState() {
        BlocksManager.getInstance().flush();
        PowerUpsManager.getInstance().flush();
        this.leftPressed = false;
        this.rightPressed = false;
        coinsSaver = new CoinsSaver();
        snake.setLife(10);
        gameBest = 0;
        secondsLeft = 3;
    }
    
    private void moveBlocks(double ds){
        BlocksManager bm = BlocksManager.getInstance();
        for(int i=0; i<bm.numBlocks(); i++){
            bm.getBlock(i).move(ds);
        }
    }  
    
    private void movePowerUps(double ds){
        PowerUpsManager pm = PowerUpsManager.getInstance();
        for(int i=0; i< pm.powerUpsnums(); i++){
            pm.getPowerUps(i).move(ds);
        }
    }
    
    private void moveCoins(double ds){
        CoinsManager cm = CoinsManager.getInstance();
        for(int i = 0; i< cm.numCoins(); i++){
            cm.getCoin(i).move(ds);
        }
    }
    
    private double determineDownSpeed(){
        if (snake.getLife() < Environment.LIFEINCREASING){
            return this.downSpeed;
        }
        else {
            int actualShift = (snake.getLife())/Environment.LIFEINCREASING;
            if (actualShift > (Environment.MAXINCREMENT)){
                return this.downSpeed + Environment.MAXVELOCITYSHIFT;
            }
            else {
                int actualDown = (Environment.MAXVELOCITYSHIFT/Environment.MAXINCREMENT)*actualShift;
                return this.downSpeed + actualDown;
            }
        }
    }
    
    public void stop(){
        this.stop = true;
    }
    
    private void addListeners() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (e.getKeyCode() == KeyEvent.VK_LEFT && state == STATE.IN_GAME) {
                    leftPressed = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && state == STATE.IN_GAME) {
                    rightPressed = true;
                }
                if (key == KeyEvent.VK_P)
                    if(state == STATE.IN_GAME){
                        state = STATE.PAUSE;
                        pause = true;
                        constructorThread.stopThread();
                    }
                    else if(state == STATE.PAUSE){
                        state = STATE.IN_GAME;
                        pause = false;
                        constructorThread = new ConstructorThread(snake);
                        CThread = new Thread(constructorThread);
                        CThread.start();
                    }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if ((key == KeyEvent.VK_LEFT) && state == STATE.IN_GAME){
                    leftPressed = false;   
                }
                if ((key == KeyEvent.VK_RIGHT) && state == STATE.IN_GAME){
                    rightPressed = false;   
                }
                
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                requestFocusInWindow();
            }
        });
    }
    
    private enum STATE {
        COUNTDOWN,
        IN_GAME,
        PAUSE,
        GAMEOVER
    }
}
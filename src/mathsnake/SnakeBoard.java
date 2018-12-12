package mathsnake;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.Timer;

public class SnakeBoard extends Board {
    
    private Timer countdownTimer;
    private int secondsLeft = 3;
    private JLabel countdown = new JLabel(Integer.toString(secondsLeft));
    private final SnakeBoard instance;
    
    public SnakeBoard() {
        initSnakeBoard();
        this.instance = this;
        super.state = STATE.COUNTDOWN;
    }
    
    private void initSnakeBoard() {
        super.initBoard();
        countdown();
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
                background = new Background(Environment.getInstance().PATHBACKGROUND);
            }
            if (hasFocus() && state == STATE.IN_GAME) {
                if(!CThread.isAlive()){
                    super.initialState();
                    constructorThread = new ConstructorThreadSnakeBoard(instance);
                    CThread = new Thread(constructorThread);
                    CThread.start();
                }
            }
            if(hasFocus() && state == STATE.IN_GAME){
                super.checkCollision();
                snake.move();
                double ds = determineDownSpeed();
                super.moveElements(ds);
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
            sleep = Environment.getInstance().DELAY - delta;

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
    
    @Override
    protected void doDrawing(Graphics g) {
        super.doDrawing(g);
        if(state == STATE.COUNTDOWN){
            countdown.setText(Integer.toString(secondsLeft));
            checkCountdown();
        }
    }

    @Override
    protected void gameOver() {
        super.gameOver();
        CardLayout cl = MathSnake.getInstance().getCardLayout();
        this.secondsLeft = 3;
        cl.show(MathSnake.getInstance().getCardsJPanel(), "gameOver");
        coinsSaver.saveCoins();
        state = STATE.COUNTDOWN;
    }
    
    private void countdown() {
        countdown.setFont(new Font("Arial", Font.BOLD, 100));
        countdown.setForeground(Environment.getInstance().WRITECOLOR);
        add(countdown);
        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hasFocus() && secondsLeft > 0)
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
    
    private double determineDownSpeed(){
        if (this.gameBest < Environment.getInstance().LIFEINCREASING){
            return this.downSpeed;
        }
        else {
            int actualShift = (this.gameBest)/Environment.getInstance().LIFEINCREASING;
            if (actualShift > (Environment.getInstance().MAXINCREMENT)){
                return this.downSpeed + Environment.getInstance().MAXVELOCITYSHIFT;
            }
            else {
                int actualDown = (Environment.getInstance().MAXVELOCITYSHIFT/Environment.getInstance().MAXINCREMENT)*actualShift;
                return this.downSpeed + actualDown;
            }
        }
    }
    
    @Override
    protected void addListeners() {
        
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
                        constructorThread = new ConstructorThreadSnakeBoard(instance);
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
    
}
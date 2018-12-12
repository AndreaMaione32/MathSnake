/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 *
 * @author edoardocossentino
 */
public class DemoBoard extends Board {
    private double movementSecond = 25.0;
    private Timer countdownMovement = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(hasFocus() && movementSecond > 0)
                movementSecond = movementSecond - 0.5;
            }
        });
    private final DemoBoard instance;
     
    public DemoBoard() {
        initDemoBoard();
        this.instance = this;
        super.state = STATE.IN_GAME;
    }
    
    private void initDemoBoard() {
        super.initBoard();
    }
    
    @Override
    public void run(){
        long beforeTime, delta, sleep; 
        beforeTime = System.currentTimeMillis();
        while(!stop){
            if(!pause){
            if (hasFocus() && state == STATE.IN_GAME) {
                if(!CThread.isAlive()){
                    initialState();
                    constructorThread = new ConstructorThreadDemoBoard(instance);
                    CThread = new Thread(constructorThread);
                    CThread.start();
                }
            }
            if(hasFocus() && state == STATE.IN_GAME){
                super.checkCollision();
                snake.move();
                double ds = determineDownSpeed();
                super.moveElements(determineDownSpeed());
                background.move(ds/2);
                snake.setHorizontalMovement(0);	
                if ((leftPressed) && (!rightPressed)) {
                    snake.setHorizontalMovement(-snakeSpeed);
                } else if ((rightPressed) && (!leftPressed)) {
                    snake.setHorizontalMovement(snakeSpeed);
                }
                checkMovement();
                if(!countdownMovement.isRunning()) {
                    countdownMovement.start();
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
    protected void gameOver() {
        super.gameOver();
        CardLayout cl = MathSnake.getInstance().getCardLayout();
        cl.show(MathSnake.getInstance().getCardsJPanel(), "menu");
        state = STATE.IN_GAME;
        countdownMovement.stop();
        movementSecond = 25.0;
        stopMoving();
    }
    
    
   private void checkMovement() { 
        if (movementSecond == 23) {
            startMovingLeft();
        }
        if (movementSecond == 22.5) {
            stopMoving();
        }
        if (movementSecond == 20.0) {
            startMovingRight();
        }
        if (movementSecond == 19.0) {
            startMovingLeft();
        }
        if (movementSecond == 18.0) {
            stopMoving();
        }
        if (movementSecond == 15.5) {
            startMovingRight();
        }
        if (movementSecond == 15.0) {
            stopMoving();
        }
        if (movementSecond == 14.5) {
            startMovingRight();
        }
        if (movementSecond == 14.0) {
            stopMoving();
        }
        if (movementSecond == 13.5) {
            startMovingLeft();
        }
        if (movementSecond == 12.5) {
            startMovingRight();
        }
        if (movementSecond == 11.5) {
            stopMoving();
        }
        if (movementSecond == 9.5) {
            startMovingLeft();
        }
        if (movementSecond == 8.5) {
            startMovingRight();
        }
        if (movementSecond == 7.5) {
            stopMoving();
        }
        if (movementSecond == 6.5) {
            startMovingLeft();
        }
        if (movementSecond == 5.0) {
            startMovingRight();
            countdownMovement.stop();
            movementSecond = 25.0;
        }

    }
    
    private void startMovingRight() {
        leftPressed = false;
        rightPressed = true;
    }
    
    private void startMovingLeft() {
        leftPressed = true;
        rightPressed = false;
    }
    
    private void stopMoving() {
        leftPressed = false;
        rightPressed = false;
    }
    
    private double determineDownSpeed(){
        return this.downSpeed;
    }
    
    @Override
    protected void addListeners() {
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                state = STATE.GAMEOVER;
                gameOver();
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 *
 * @author edoardocossentino
 */
public class DemoBoard extends Board {
    private double movementSecond = 50.0;
    private final double SECOND = movementSecond - 2.0;

    private Timer countdownMovement = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(movementSecond > 0)
                movementSecond = movementSecond - 0.5;
            }
        });
     
    public DemoBoard() {
        super.state = STATE.IN_GAME;
        super.initBoard();
    }
    
    @Override
    public void run(){
        long beforeTime, delta, sleep; 
        beforeTime = System.currentTimeMillis();
        stop = false;
        while(!stop){
            if(!pause){
            if (state == STATE.IN_GAME) {
                if(!CThread.isAlive()){
                    initialState();
                    constructorThread = new ConstructorThreadDemoBoard(this);
                    CThread = new Thread(constructorThread);
                    CThread.start();
                }
            }
            if(state == STATE.IN_GAME){
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
        movementSecond = SECOND + 2.0;
        stopMoving();
        stop();
    }
    
    
   private void checkMovement() { 
        if (movementSecond == SECOND) {
            startMovingLeft();
        }
        if (movementSecond == SECOND - 0.5) {
            stopMoving();
        }
        if (movementSecond == SECOND - 3.0) {
            startMovingRight();
        }
        if (movementSecond == SECOND - 4.0) {
            startMovingLeft();
        }
        if (movementSecond == SECOND - 5.0) {
            stopMoving();
        }
        if (movementSecond == SECOND - 7.5) {
            startMovingRight();
        }
        if (movementSecond == SECOND - 8.0) {
            stopMoving();
        }
        if (movementSecond == SECOND - 8.5) {
            startMovingRight();
        }
        if (movementSecond == SECOND - 9.0) {
            stopMoving();
        }
        if (movementSecond == SECOND - 9.5) {
            startMovingLeft();
        }
        if (movementSecond == SECOND - 10.5) {
            startMovingRight();
        }
        if (movementSecond == SECOND - 11.5) {
            stopMoving();
        }
        if (movementSecond == SECOND - 13.5) {
            startMovingLeft();
        }
        if (movementSecond == SECOND - 14.5) {
            startMovingRight();
        }
        if (movementSecond == SECOND - 15.5) {
            stopMoving();
        }
        if (movementSecond == SECOND - 16.5) {
            startMovingLeft();
        }
        if (movementSecond == SECOND - 18.0) {
            startMovingRight();
        }
        if (movementSecond == SECOND - 19.0) {
            stopMoving();
        }
        if (movementSecond == SECOND - 22.5) {
            startMovingLeft();
        }
        if (movementSecond == SECOND - 23.0) {
            stopMoving();
        }
        if (movementSecond == SECOND - 25.0) {
            startMovingRight();
        }
        if (movementSecond == SECOND - 26.0) {
            stopMoving();
        }
        if (movementSecond == SECOND - 28.0) {
            startMovingLeft();
        }
        if (movementSecond == SECOND - 28.5) {
            stopMoving();
        }
        if (movementSecond == SECOND - 29.0) {
            startMovingRight();
        }
        if (movementSecond == SECOND - 29.5) {
            stopMoving();
        }
        if (movementSecond == SECOND - 30.5) {
            startMovingLeft();
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
        super.addListeners();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                state = STATE.GAMEOVER;
                gameOver();
            }
        });
    }
}

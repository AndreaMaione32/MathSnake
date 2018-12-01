package mathsnake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;



public class SnakeBoard extends JPanel implements ActionListener {
    
    private STATE state = STATE.COUNTDOWN;
    
    private Timer timer;
    private Timer countdownTimer;
    private int secondsLeft = 3;
    private JLabel countdown = new JLabel(Integer.toString(secondsLeft));
    private Image ball;
    private Image head;
    private final Snake snake = new Snake();
    
    private double snakeSpeed = 300;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    
    private int gameBest = Environment.STARTLIFEPOINTS;
    private Runnable constructorBlockThread = new ConstructorBlockThread(snake);
    private Runnable updaterBlockThread = new UpdaterBlockThread(snake);

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
        head = snake.loadImage(Environment.PATHIMAGES + "smiling.png");
    }
    
    private void initGame() {
        // Viene inizializzato il timer necessario per i repaint
        timer = new Timer(Environment.DELAY, this);
        
        timer.start();
        //Start threads
        new Thread(constructorBlockThread).start();
        new Thread(updaterBlockThread).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void doDrawing(Graphics g) {
        int numDots = snake.getDots();
        double x = snake.getX();
        double[] yVector = snake.getY();
     
        switch (state) {
            case COUNTDOWN:
                countdown.setText(Integer.toString(secondsLeft));
                checkCountdown();
                break;
            case IN_GAME:
                
                //DRAWING BLOCK
                BlocksManager blocksManager = BlocksManager.getInstance();
                for(int i = 0; i < blocksManager.numBlocks(); i++){
                    Block b = blocksManager.getBlock(i);
                    b.printBlock(g);
                }

                for (int z = 0; z < numDots - 1; z++){
                    g.drawImage(ball, (int)x, (int)yVector[z], this);
                }
                g.drawImage(head, (int)x, (int)yVector[numDots - 1], this);

                g.setColor(Color.black);
                g.drawString(Integer.toString(snake.getLife()), (int)x + 15, (int)yVector[numDots - 1] + 10);


                Font font = new Font("Arial", Font.BOLD, 14);
                FontMetrics metrics = g.getFontMetrics(font);
                g.setFont(font);
                g.setColor(Color.black);
                String text = "CURRENT BEST : NULL"; //Dovrà mostrare il miglior punteggio della Scoreboard
                g.drawString("CURRENT BEST : NULL", Environment.JP_WIDTH - (10 + metrics.stringWidth(text)), 20); 

                text = "GAME BEST : " + Integer.toString(gameBest);
                g.setFont(font);
                g.setColor(Color.black);
                g.drawString("GAME BEST : " + Integer.toString(gameBest), Environment.JP_WIDTH - (10 + metrics.stringWidth(text)), 20 + metrics.getHeight());
                break;
            case GAMEOVER:
                //da implementare
                gameOver(g);
                break;
            default:
                break;
        }
    }

    private void gameOver(Graphics g) {
        // da implementare
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (Environment.JP_WIDTH - metr.stringWidth(msg)) / 2, Environment.JP_HEIGHT / 2);
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
                    if(state == STATE.IN_GAME) {
                        state = STATE.PAUSE;
                        timer.stop();
                    }
                    else if(state == STATE.PAUSE) {
                        state = STATE.IN_GAME;
                        timer.start();
                    }
            }
            
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
        Block b;
           
        if (state == STATE.IN_GAME){
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
        }        
    }
    
    private void changeLife(String op, int value, Snake snake){
        int actualLife = snake.getLife();
        

        if (op.equals("+")){
            snake.setLife(actualLife + value);
        }
        if (op.equals("x")){
            snake.setLife(actualLife * value);
        }
        if (op.equals("-")){
            snake.setLife(actualLife - value);
        }
        if (op.equals("/")){
            snake.setLife(actualLife / value);
        }
        
        actualLife = snake.getLife();
        if (actualLife < 0){
            snake.setLife(0);
        } else{
            if (actualLife > gameBest){
                gameBest = actualLife;
            }
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (hasFocus()) {
            if(!countdownTimer.isRunning()) {
                countdownTimer.start();
            }
        }
        if (state == STATE.IN_GAME) {  
            checkCollision();
            snake.move();
            
            snake.setHorizontalMovement(0);	
            if ((leftPressed) && (!rightPressed)) {
                snake.setHorizontalMovement(-snakeSpeed);
            } else if ((rightPressed) && (!leftPressed)) {
                snake.setHorizontalMovement(snakeSpeed);
            }
        }
        repaint();
    }
     
    private enum STATE {
        COUNTDOWN,
        IN_GAME,
        PAUSE,
        GAMEOVER
    }
}
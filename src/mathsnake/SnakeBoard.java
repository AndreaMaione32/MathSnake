package mathsnake;

import java.awt.CardLayout;
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
    
    private Timer repaintTimer = new Timer(Environment.DELAY, this);
    private Timer countdownTimer;
    private int secondsLeft = 3;
    private JLabel countdown = new JLabel(Integer.toString(secondsLeft));
    private JLabel gameOverLabel = new JLabel("GAME OVER");
    private Image ball;
    private Image head;
    private final Snake snake = new Snake();
    
    private int gameBest = Environment.STARTLIFEPOINTS;
    private ConstructorBlockThread constructorBlockThread = new ConstructorBlockThread(snake);
    private UpdaterBlockThread updaterBlockThread = new UpdaterBlockThread(snake);
    private Thread CBThread = new Thread(constructorBlockThread); 
    private Thread UBThread = new Thread(updaterBlockThread); 

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
        repaintTimer = new Timer(Environment.DELAY, this);
        repaintTimer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void doDrawing(Graphics g) {
        int numDots = snake.getDots();
        int x = snake.getX();
        int[] yVector = snake.getY();
     
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
                    g.drawImage(ball, x, yVector[z], this);
                }
                g.drawImage(head, x, yVector[numDots - 1], this);

                g.setColor(Color.black);
                g.drawString(Integer.toString(snake.getLife()), x + 15, yVector[numDots - 1] + 10);


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
                gameOver();
                break;
            default:
                break;
        }
    }

    private void gameOver() {
        constructorBlockThread.stopThread();
        updaterBlockThread.stopThread();
        long endTime = System.currentTimeMillis() + 1000;
        while(System.currentTimeMillis() != endTime) {
            // Wait 1 second
        }
        state = STATE.COUNTDOWN;
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
        snake.setLife(10);
        secondsLeft = 3;
    }
    
    private void addListeners() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight()) && state == STATE.IN_GAME) {
                    snake.setLeftDirection(true);
                    snake.setRightDirection(false);
                }

                if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft()) && state == STATE.IN_GAME) {
                    snake.setLeftDirection(false);
                    snake.setRightDirection(true);
                }
                if (key == KeyEvent.VK_P)
                    if(state == STATE.IN_GAME) {
                        state = STATE.PAUSE;
                        repaintTimer.stop();
                        constructorBlockThread.stopThread();
                        updaterBlockThread.stopThread();
                    }
                    else if(state == STATE.PAUSE) {
                        state = STATE.IN_GAME;
                        repaintTimer.start();
                        constructorBlockThread = new ConstructorBlockThread(snake);
                        updaterBlockThread = new UpdaterBlockThread(snake);
                        CBThread = new Thread(constructorBlockThread);
                        UBThread = new Thread(updaterBlockThread);
                        CBThread.start();
                        UBThread.start();
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (hasFocus() && state == STATE.COUNTDOWN) {
            if(!countdownTimer.isRunning()) {
                countdown();
                countdownTimer.start();
            }
        }
        if (hasFocus() && state == STATE.IN_GAME) {
            if(!CBThread.isAlive() && !UBThread.isAlive()) {
                initialState();
                constructorBlockThread = new ConstructorBlockThread(snake);
                updaterBlockThread = new UpdaterBlockThread(snake);
                CBThread = new Thread(constructorBlockThread);
                UBThread = new Thread(updaterBlockThread);
                CBThread.start();
                UBThread.start();
            }
        }
        if (state == STATE.IN_GAME) {
            try {
                snake.move();
                checkCollision();
            } catch (InterruptedException ex) {
                Logger.getLogger(SnakeBoard.class.getName()).log(Level.SEVERE, null, ex);
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
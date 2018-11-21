/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

/**
 *
 * @author antonino
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;



public class SnakeBoard extends JPanel implements ActionListener {
    
    private boolean inGame = true;
    
    private Timer timer;
    private Image ball;
    private Image head;
    private final Snake snake = new Snake();
    
    private int gameBest = Environment.STARTLIFEPOINTS;
    private Runnable constructorBlockThread = new ConstructorBlockThread(snake);
    private Runnable updaterBlockThread = new UpdaterBlockThread(snake);
    private Runnable destroyerBlockThread = new DestroyerBlockThread();

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
        
        addKeyListener(new TAdapter());
        setBackground(Color.WHITE);
        setFocusable(true);
        setPreferredSize(new Dimension(Environment.JP_WIDTH, Environment.JP_HEIGHT));
        loadImages();
        initGame();
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
        new Thread(destroyerBlockThread).start();
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
        
        if (inGame) {            
            //DRAWING BLOCK
            BlocksManager blocksManager = BlocksManager.getInstance();
            int numBlocks = blocksManager.numBlocks();
            for(int i = 0; i < numBlocks; i++){
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
            
        } else {
            //da implementare
            gameOver(g);
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
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            try {
                snake.move();
                
                checkCollision();
            } catch (InterruptedException ex) {
                Logger.getLogger(SnakeBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        repaint();
    }

    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight())) {
            snake.setLeftDirection(true);
            snake.setRightDirection(false);
            }

        if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft())) {
            snake.setLeftDirection(false);
            snake.setRightDirection(true);
            }
        }
    }
    
    private void checkCollision(){
        int numDots = snake.getDots();
        int x = snake.getX();
        int[] yVector = snake.getY();
        int xBlock;
        int yBlock;
        BlocksManager blocksManager = BlocksManager.getInstance();
        int numBlocks = blocksManager.numBlocks();
        Block b;
           
        if (inGame){
            for(int y=0; y<numBlocks; y++){
                b = blocksManager.getBlock(y);
                if(snake.collide(b.getAssociatedRectangle())){     //check if snake's head collide with block's rectangle
                    changeLife(b.getStrOp(), b.getValue(), snake);
                    blocksManager.removeBlock(b);
                    y--; //decrease y by one because when a block is eliminated the other blocks in list are shifted by one to left, so the next element to check is y again
                    numBlocks = blocksManager.numBlocks();
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
     
}
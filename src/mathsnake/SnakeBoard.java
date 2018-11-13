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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

    public SnakeBoard() {
        initSnakeBoard();
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
        ball = snake.loadImage(Environment.PATHIMAGES + "smiling.png");
        head = snake.loadImage(Environment.PATHIMAGES + "incazzato.png");
    }
    
    private void initGame() {
        // INIZIALIZZO IL GIOCO 
        timer = new Timer(Environment.DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        int numDots = snake.getDots();
        int x = snake.getX();
        int[] yVector = snake.getY();
        
        if (inGame) {
            for (int z = 0; z < numDots - 1; z++)
                g.drawImage(ball, x, yVector[z], this);
            g.drawImage(head, x, yVector[numDots - 1], this);
            Toolkit.getDefaultToolkit().sync();

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
}
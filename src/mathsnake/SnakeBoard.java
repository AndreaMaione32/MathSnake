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
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;



public class SnakeBoard extends JPanel implements ActionListener {
    // costanti utilizzate nel gioco
    
    private final int x[] = new int[Environment.MAX_DOTS];// memorizzano le coordinate x e y del serpente
    private final int y[] = new int[Environment.MAX_DOTS];

    private int dots;
    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private final boolean inGame = true;
    
    private Timer timer;
    private Image ball;
    private Image head;

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
        //Carico le immagini..\\..\\..\\
        ImageIcon iid = new ImageIcon("../MathSnake/src/images/dot.png");
        ball = iid.getImage();

        ImageIcon iih = new ImageIcon("../MathSnake/src/images/head.png");
        head = iih.getImage();
    }

    private void initGame() {
        // INIZIALIZZO IL GIOCO 

        dots = 11;

        for (int z = 0; z < dots; z++) {
            x[z] = 400 - z * 10;
            y[z] = 400;
            System.out.print("Points of snake " + x [z] + "\n");
        }
        timer = new Timer(Environment.DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {
            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[0], y[z], this);
                } else {
                    g.drawImage(ball, x[0], y[z]+z*10, this);
                }
            }

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

    private void move() {

        for (int z = 0; z < dots; z++) { 
            System.out.print("Points of snake " + x [z] + "\n"); 
        } 
        int shift = Environment.DOT_SIZE + 10;
        if (leftDirection) {
            if (x[0]- shift < 0){
                for (int z = 0; z < dots; z++) { 
                x[z] = 0;
                }
            }else{
                for (int z = 0; z < dots; z++) { 
                    x[z] -= shift;
                }
            }
            leftDirection = false;
        }
        if (rightDirection) {
            if (x[0]+shift >= Environment.JP_WIDTH){
                for (int z = 0; z < dots; z++) { 
                x[z] = Environment.JP_WIDTH-10;
                }
            }else{
                for (int z = 0; z < dots; z++) { 
                    x[z] += shift;
                }
            }
            rightDirection = false;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            move();
        }
        repaint();
    }

    
    private class TAdapter extends KeyAdapter {

        
        @Override
        public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
            leftDirection = true;
            rightDirection = false;
            }

        if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
            rightDirection = true;
            leftDirection = false;    
            }
        }
    }
}

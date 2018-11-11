package mathsnake;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author antoniocoppola
 */
public class BlockTest extends JPanel {
    
    public void paintBlock(Block b, Graphics g, int x, int y){
        b.printBlock(x, y, g);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        this.paintBlock(new Block(2, Operation.ADD),g, 7, 7);
        this.paintBlock(new Block(4, Operation.DIV),g, 200, 200);
        g.setColor(Color.BLACK);
        g.fillRect(7, 7, 5, 5);
        g.setColor(Color.BLACK);
        g.fillRect(200, 200, 5, 5);
    }
    
    @Override
   public Dimension getPreferredSize() {
      // so that our GUI is big enough
      return new Dimension(500,500);
   }
    
    private static void createUI(){
        JFrame jframe = new JFrame("BlockTest");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.add(new BlockTest());
        jframe.pack();
        jframe.setVisible(true);
    }
    
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                createUI();
            }
        
        });
    }
}

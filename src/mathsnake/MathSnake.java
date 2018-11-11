/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;
import java.awt.EventQueue;
import javax.swing.JFrame;
/**
 *
 * @author antonino
 */


public class MathSnake extends JFrame {

    public MathSnake() {

        initUI();
    }
    
    private void initUI() {
        
        add(new SnakeBoard());
        pack();
        setTitle("Snake");    
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new MathSnake();
            ex.setVisible(true);
        });
    }
}

package mathsnake;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class MathSnake extends JFrame {

    public MathSnake() {

        initUI();
    }
    
    private void initUI() {
        
        add(new SnakeBoard());
        pack();
        setTitle("Math Snake");
        setResizable(false);
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

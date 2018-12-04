package mathsnake;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class FirstPage extends JPanel {
    
    private JLabel pressSpacebar = new JLabel("Press Spacebar");
    private Timer flashingTimer;
    
    public FirstPage() {
        initFirstPage();
    }
    
    private void initFirstPage() {
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(Environment.JP_WIDTH, Environment.JP_HEIGHT));
        add(pressSpacebar);
        addListeners();
        flashingTimer.start();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        Font font1 = new Font("Arial", Font.BOLD, 50);
        FontMetrics metrics1 = g.getFontMetrics(font1);
        int x1 = (Environment.JP_WIDTH - metrics1.stringWidth("MATH SNAKE")) / 2; //Stringa centrata nel panel
        g.setFont(font1);
        g.setColor(Color.WHITE);
        g.drawString("MATH SNAKE", x1, 200);
        
        Font font2 = new Font("Arial", Font.BOLD, 20);
        pressSpacebar.setFont(font2);
        pressSpacebar.setForeground(Color.WHITE);
        pressSpacebar.setLocation(Environment.JP_WIDTH / 2 - pressSpacebar.getSize().width / 2, 350);
    }

    private void addListeners() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_SPACE) {
                    MathSnake.state = GameState.MENU;
                    CardLayout cl = MathSnake.getInstance().getCardLayout();
                    cl.show(MathSnake.getInstance().getCardsJPanel(), "menu");
                }
            }           
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                requestFocusInWindow();
            }
        });
         flashingTimer = new Timer(700, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressSpacebar.setVisible(!pressSpacebar.isVisible());
            }
        });
    }
    
}

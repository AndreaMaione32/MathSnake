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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
        setPreferredSize(new Dimension(Environment.getInstance().JP_WIDTH, Environment.getInstance().JP_HEIGHT));
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
        new Background(Environment.getInstance().PATHBACKGROUND).drawBackground(g);
        Font font1 = new Font("Arial", Font.BOLD, 50);
        FontMetrics metrics1 = g.getFontMetrics(font1);
        int x1 = (Environment.getInstance().JP_WIDTH - metrics1.stringWidth("MATH SNAKE")) / 2; //Stringa centrata nel panel
        g.setFont(font1);
        g.setColor(Environment.getInstance().WRITECOLOR);
        g.drawString("MATH SNAKE", x1, 200);
        
        Font font2 = new Font("Arial", Font.BOLD, 20);
        pressSpacebar.setFont(font2);
        pressSpacebar.setForeground(Environment.getInstance().WRITECOLOR);
        pressSpacebar.setLocation(Environment.getInstance().JP_WIDTH / 2 - pressSpacebar.getSize().width / 2, 350);
    }

    private void addListeners() {
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                for(Component component : getComponents()) {
                    if(component instanceof JLabel)
                        component.setForeground(Environment.getInstance().WRITECOLOR);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                //DO NOTHING
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_SPACE) {
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

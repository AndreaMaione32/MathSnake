package panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import mathsnake.MathSnake;
import background.Background;
import environment.Environment;
import javax.swing.ImageIcon;

public class FirstPage extends JPanel {
    
    private Timer flashingTimer;
    private JLabel pressSpacebar;
    private JLabel iconLabel;
    private ImageIcon snakeIcon;
    
    public FirstPage() {
        initFirstPage();
    }
    
    private void initFirstPage() {
        snakeIcon = new ImageIcon(new ImageIcon(Environment.getInstance().PATHIMAGES + "snake_icon.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        iconLabel = new JLabel(snakeIcon, JLabel.CENTER);
        pressSpacebar = new JLabel("Press Spacebar");
        setFocusable(true);
        setPreferredSize(new Dimension(Environment.getInstance().JP_WIDTH, Environment.getInstance().JP_HEIGHT));
        add(iconLabel);
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
        iconLabel.setLocation(Environment.getInstance().JP_WIDTH / 2 - iconLabel.getSize().width / 2, 70);
        pressSpacebar.setFont(new Font("Arial", Font.BOLD, 20));
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

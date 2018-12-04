package mathsnake;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameOver extends JPanel implements ActionListener {
    
    private JLabel gameOverLabel = new JLabel("GAME OVER :(");
    private Timer endTimer = new Timer(Environment.DELAY, this);
    
    public GameOver() {
        initGameOver();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        new Background(Environment.PATHBACKGROUND).drawBackground(g);
    }

    private void initGameOver() {
        setBackground(Color.WHITE);
        setFocusable(true);
        setPreferredSize(new Dimension(Environment.JP_WIDTH, Environment.JP_HEIGHT));
        setLayout(new GridBagLayout());
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 50));
        gameOverLabel.setForeground(Environment.WRITECOLOR);
        add(gameOverLabel);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                requestFocusInWindow();
            }
        });
        endTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(hasFocus()) {
            long endTime = System.currentTimeMillis() + 2000;
            while(System.currentTimeMillis() != endTime) {
                // Wait 3 seconds
            }
            CardLayout cl = MathSnake.getInstance().getCardLayout();
            cl.show(MathSnake.getInstance().getCardsJPanel(), "menu");
        }
    }
    
}
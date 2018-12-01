package mathsnake;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Market extends JPanel {
    
    private int currentCoins = Coins.getInstance().getCurrentCoins();
    private JLabel marketLabel = new JLabel("MARKET");
    private JLabel coinsLabel = new JLabel("Coins: " + Integer.toString(currentCoins));
    private JButton backButton = new JButton("Back");
    private JButton b1 = new JButton("10");
    private JButton b2 = new JButton("20");
    private JButton b3 = new JButton("15");
    private JButton b4 = new JButton("25");
    private JLabel l1 = new JLabel("SKIN 1");
    private JLabel l2 = new JLabel("SKIN 2");
    private JLabel l3 = new JLabel("BACKGROUND 1");
    private JLabel l4 = new JLabel("BACKGROUND 2");
    
    public Market() {
        initMarket();
    }

    private void initMarket() {
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(Environment.JP_WIDTH, Environment.JP_HEIGHT));
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(layout);
        backButton.setFont(new Font("Arial", 1, 20));
        marketLabel.setFont(new Font("Arial", Font.BOLD, 50));
        marketLabel.setForeground(Color.WHITE);
        coinsLabel.setFont(new Font("Arial", 1, 20));
        coinsLabel.setForeground(Color.WHITE);
        b1.setFont(new Font("Arial", 1, 20));
        b2.setFont(new Font("Arial", 1, 20));
        b3.setFont(new Font("Arial", 1, 20));
        b4.setFont(new Font("Arial", 1, 20));
        l1.setFont(new Font("Arial", 1, 20));
        l1.setForeground(Color.WHITE);
        l2.setFont(new Font("Arial", 1, 20));
        l2.setForeground(Color.WHITE);
        l3.setFont(new Font("Arial", 1, 20));
        l3.setForeground(Color.WHITE);
        l4.setFont(new Font("Arial", 1, 20));
        l4.setForeground(Color.WHITE);
        addListeners();
        
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weighty = 1.0;
        add(marketLabel, c);
        
        c.weighty = 0;
        c.gridwidth = 2;
        c.ipadx = 20;
        c.ipady = 20;
        
        c.insets = new Insets(0, 0, 20, 40);
        c.gridx = 0;
        c.gridy = 1;
        add(b1, c);
        
        c.insets = new Insets(0, 0, 20, 0);
        c.gridx = 2;
        c.gridy = 1;
        add(l1, c);
        
        c.insets = new Insets(0, 0, 20, 40);
        c.gridx = 0;
        c.gridy = 2;
        add(b2, c);
        
        c.insets = new Insets(0, 0, 20, 0);
        c.gridx = 2;
        c.gridy = 2;
        add(l2, c);
        
        c.insets = new Insets(0, 0, 20, 40);
        c.gridx = 0;
        c.gridy = 3;
        add(b3, c);
        
        c.insets = new Insets(0, 0, 20, 0);
        c.gridx = 2;
        c.gridy = 3;
        add(l3, c);
        
        c.insets = new Insets(0, 0, 0, 40);
        c.gridx = 0;
        c.gridy = 4;
        add(b4, c);
        
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 2;
        c.gridy = 4;
        add(l4, c);
        
        c.weighty = 1;
        c.weightx = 1;
        c.ipadx = 0;
        c.ipady = 0;
        
        c.insets = new Insets(0, 10, 10, 0);
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        add(coinsLabel, c);
        
        c.insets = new Insets(0, 0, 10, 10);
        c.gridx = 2;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        add(backButton, c);
    }

    private void addListeners() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MathSnake.state = GameState.MENU;
                CardLayout cl = MathSnake.getInstance().getCardLayout();
                cl.show(MathSnake.getInstance().getCardsJPanel(), "menu");
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                requestFocusInWindow();
            }
        });
    }
    
}

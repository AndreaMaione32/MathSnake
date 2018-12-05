package mathsnake;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Market extends JPanel {
    
    private CoinsSaver coinsSaver = new CoinsSaver();
    private JLabel marketLabel = new JLabel("MARKET");
    private JLabel coinsLabel = new JLabel("Coins: " + Integer.toString(coinsSaver.getCurrentCoins()));
    private JButton backButton = new JButton("Back");
    private JButton b1 = new JButton("10");
    private JButton b2 = new JButton("20");
    private JButton buyCloudBackground = new JButton("100");
    private JButton setCloudBackground = new JButton("SET");
    private JButton buyDirtBackground = new JButton("200");
    private JButton setDirtBackground = new JButton("SET");
    private JLabel l1 = new JLabel("SKIN 1");
    private JLabel l2 = new JLabel("SKIN 2");
    private JLabel l3 = new JLabel("BACKGROUND 1");
    private JLabel l4 = new JLabel("BACKGROUND 2");
    
    public Market() {
        initMarket();
    }
@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        new Background(Environment.getInstance().PATHBACKGROUND).drawBackground(g);
    }
    
    private void initMarket() {
        setFocusable(true);
        setPreferredSize(new Dimension(Environment.getInstance().JP_WIDTH, Environment.getInstance().JP_HEIGHT));
        GridBagLayout layout = new GridBagLayout();
        
        setLayout(layout);
        setComponents();
        
        
        addListeners();
    }
    
    private void setComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weighty = 1.0;
        marketLabel.setFont(new Font("Arial", Font.BOLD, 50));
        marketLabel.setForeground(Environment.getInstance().WRITECOLOR);
        add(marketLabel, c);
        
        c.weighty = 0;
        c.gridwidth = 2;
        c.ipadx = 20;
        c.ipady = 20;
        
        c.insets = new Insets(0, 0, 20, 40);
        c.gridx = 0;
        c.gridy = 1;
        b1.setFont(new Font("Arial", 1, 20));
        add(b1, c);
        
        c.insets = new Insets(0, 0, 20, 0);
        c.gridx = 2;
        c.gridy = 1;
        l1.setFont(new Font("Arial", 1, 20));
        l1.setForeground(Environment.getInstance().WRITECOLOR);
        add(l1, c);
        
        c.insets = new Insets(0, 0, 20, 40);
        c.gridx = 0;
        c.gridy = 2;
        b2.setFont(new Font("Arial", 1, 20));
        add(b2, c);
        
        c.insets = new Insets(0, 0, 20, 0);
        c.gridx = 2;
        c.gridy = 2;
        l2.setFont(new Font("Arial", 1, 20));
        l2.setForeground(Environment.getInstance().WRITECOLOR);
        add(l2, c);
        
        c.insets = new Insets(0, 0, 20, 40);
        c.gridx = 0;
        c.gridy = 3;
        if(Environment.getInstance().BOUGHT_FEATURES.get("cloud_background.png") == false) {
            if(this == setCloudBackground.getParent())
                remove(setCloudBackground);
            buyCloudBackground.setFont(new Font("Arial", 1, 20));
            add(buyCloudBackground, c);
        }
        else {
            if(this == buyCloudBackground.getParent())
                remove(buyCloudBackground);
            setCloudBackground.setFont(new Font("Arial", 1, 20));
            add(setCloudBackground, c);
        }
        
        c.insets = new Insets(0, 0, 20, 0);
        c.gridx = 2;
        c.gridy = 3;
        l3.setFont(new Font("Arial", 1, 20));
        l3.setForeground(Environment.getInstance().WRITECOLOR);
        add(l3, c);
        
        c.insets = new Insets(0, 0, 0, 40);
        c.gridx = 0;
        c.gridy = 4;
        if(Environment.getInstance().BOUGHT_FEATURES.get("dirt_background.png") == false) {
            if(this == setDirtBackground.getParent())
                remove(setDirtBackground);
            buyDirtBackground.setFont(new Font("Arial", 1, 20));
            add(buyDirtBackground, c);
        }
        else {
            if(this == buyDirtBackground.getParent())
                remove(buyDirtBackground);
            setDirtBackground.setFont(new Font("Arial", 1, 20));
            add(setDirtBackground, c);
        }
        
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 2;
        c.gridy = 4;
        l4.setFont(new Font("Arial", 1, 20));
        l4.setForeground(Environment.getInstance().WRITECOLOR);
        add(l4, c);
        
        c.weighty = 1;
        c.weightx = 1;
        c.ipadx = 0;
        c.ipady = 0;
        
        c.insets = new Insets(0, 10, 10, 0);
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        coinsLabel.setFont(new Font("Arial", 1, 20));
        coinsLabel.setForeground(Environment.getInstance().WRITECOLOR);
        add(coinsLabel, c);
        
        c.insets = new Insets(0, 0, 10, 10);
        c.gridx = 2;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        backButton.setFont(new Font("Arial", 1, 20));
        add(backButton, c);
    }

    private void addListeners() {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MathSnake.state = GameState.MENU;
                coinsSaver.saveCoins();
                CardLayout cl = MathSnake.getInstance().getCardLayout();
                cl.show(MathSnake.getInstance().getCardsJPanel(), "menu");
            }
        });
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                coinsSaver = new CoinsSaver();
                coinsLabel.setText("Coins: " + Integer.toString(coinsSaver.getCurrentCoins()));
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
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                requestFocusInWindow();
            }
        });
        buyCloudBackground.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int price = Integer.parseInt(buyCloudBackground.getText());
                if(coinsSaver.getCurrentCoins() >= price) {
                    coinsSaver.setCurrentCoins(coinsSaver.getCurrentCoins() - price);
                    coinsLabel.setText("Coins: " + Integer.toString(coinsSaver.getCurrentCoins()));
                    Environment.getInstance().BOUGHT_FEATURES.put("cloud_background.png", true);
                    /**
                    remove(buyCloudBackground);
                    GridBagConstraints c = new GridBagConstraints();
                    c.insets = new Insets(0, 0, 20, 40);
                    c.gridx = 0;
                    c.gridy = 3;
                    add(setCloudBackground, c);
                    **/
                    setComponents();
                    try {
                        Environment.getInstance().writeBoughtFeatures();
                    } catch (IOException ex) {
                        Logger.getLogger(Market.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        setCloudBackground.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Environment.getInstance().PATHBACKGROUND = Environment.getInstance().PATHIMAGES + "cloud_background.png";
                Environment.getInstance().WRITECOLOR = Color.black;
                try {
                    Environment.getInstance().writeGraphicConfiguration();
                } catch (IOException ex) {
                    Logger.getLogger(Market.class.getName()).log(Level.SEVERE, null, ex);
                }
                for(Component component : getComponents()) {
                    if(component instanceof JLabel)
                        component.setForeground(Environment.getInstance().WRITECOLOR);
                }
                repaint();
            }
        });
        buyDirtBackground.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int price = Integer.parseInt(buyDirtBackground.getText());
                if(coinsSaver.getCurrentCoins() >= price) {
                    coinsSaver.setCurrentCoins(coinsSaver.getCurrentCoins() - price);
                    coinsLabel.setText("Coins: " + Integer.toString(coinsSaver.getCurrentCoins()));
                    Environment.getInstance().BOUGHT_FEATURES.put("dirt_background.png", true);
                    /**
                    remove(buyDirtBackground);
                    GridBagConstraints c = new GridBagConstraints();
                    c.insets = new Insets(0, 0, 0, 40);
                    c.gridx = 0;
                    c.gridy = 4;
                    add(setDirtBackground, c);
                    **/
                    setComponents();
                    try {
                        Environment.getInstance().writeBoughtFeatures();
                    } catch (IOException ex) {
                        Logger.getLogger(Market.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        setDirtBackground.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Environment.getInstance().PATHBACKGROUND = Environment.getInstance().PATHIMAGES + "dirt_background.png";
                Environment.getInstance().WRITECOLOR = Color.white;
                try {
                    Environment.getInstance().writeGraphicConfiguration();
                } catch (IOException ex) {
                    Logger.getLogger(Market.class.getName()).log(Level.SEVERE, null, ex);
                }
                for(Component component : getComponents()) {
                    if(component instanceof JLabel)
                        component.setForeground(Environment.getInstance().WRITECOLOR);
                }
                repaint();
            }
        });
    }
    
}

package mathsnake;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Market extends JPanel {
    
    private CoinsSaver coinsSaver = new CoinsSaver();
    private JLabel marketLabel = new JLabel("MARKET");
    private JLabel coinsLabel = new JLabel();
    private JButton backButton = new JButton("Back");
    private JButton buyBlackSkin = new JButton("100");
    private JButton setBlackSkin = new JButton("SET");
    private JButton buyRedSkin = new JButton("200");
    private JButton setRedSkin = new JButton("SET");
    private JButton setDefaultSkin_black = new JButton("UNSET");
    private JButton setDefaultSkin_red = new JButton("UNSET");
    private JButton buyCloudBackground = new JButton("100");
    private JButton setCloudBackground = new JButton("SET");
    private JButton buyDirtBackground = new JButton("200");
    private JButton setDirtBackground = new JButton("SET");
    private JButton setDefaultBackground_cloud = new JButton("UNSET");
    private JButton setDefaultBackground_dirt = new JButton("UNSET");
    private ImageIcon blackSkin;
    private ImageIcon redSkin;
    private ImageIcon cloud;
    private ImageIcon dirt;
    private JLabel l1 = new JLabel("BLACK SKIN");
    private JLabel l2 = new JLabel("RED SKIN");
    private JLabel l3 = new JLabel("IN THE CLOUDS BACKGROUND");
    private JLabel l4 = new JLabel("DIRTY DIRT BACKGROUND");
    
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
        loadImages();
        setComponents();
        addListeners();
    }
    
    private void loadImages() {
        blackSkin = new ImageIcon(new ImageIcon(Environment.getInstance().PATHIMAGES + "black_skin.png").getImage().getScaledInstance(10, 60, Image.SCALE_DEFAULT));
        redSkin = new ImageIcon(new ImageIcon(Environment.getInstance().PATHIMAGES + "red_skin.png").getImage().getScaledInstance(10, 60, Image.SCALE_DEFAULT));
        cloud = new ImageIcon(new ImageIcon(Environment.getInstance().PATHIMAGES + "cloud.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        dirt = new ImageIcon(new ImageIcon(Environment.getInstance().PATHIMAGES + "dirt.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
    }
    
    private void setComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weighty = 1.0;
        marketLabel.setFont(new Font("Arial", Font.BOLD, 40));
        marketLabel.setForeground(Environment.getInstance().WRITECOLOR);
        add(marketLabel, c);
        
        c.weighty = 0;
        c.gridwidth = 2;
        c.ipadx = 20;
        c.ipady = 20;
        c.anchor = GridBagConstraints.CENTER;
        
        c.weightx = 2;
        c.gridx = 0;
        
        c.insets = new Insets(0, 0, 5, 40);
        c.gridx = 0;
        c.gridy = 1;
        if(Environment.getInstance().BOUGHT_FEATURES.get("black_dot.png") == false) {
            if(this == setBlackSkin.getParent())
                remove(setBlackSkin);
            buyBlackSkin.setFont(new Font("Arial", 1, 20));
            add(buyBlackSkin, c);
        }
        else {
            if(this == buyBlackSkin.getParent())
                remove(buyBlackSkin);
            if(this == setBlackSkin.getParent())
                remove(setBlackSkin);
            if(this == setDefaultSkin_black.getParent())
                remove(setDefaultSkin_black);
            if(Environment.getInstance().PATHSKIN.equals(Environment.getInstance().PATHIMAGES + "black_dot.png")) {
                setDefaultSkin_black.setFont(new Font("Arial", 1, 20));
                add(setDefaultSkin_black, c);
            }
            else {
                setBlackSkin.setFont(new Font("Arial", 1, 20));
                add(setBlackSkin, c);
            }
        }
        
        c.insets = new Insets(0, 0, 5, 0);
        c.gridx = 2;
        c.gridy = 1;
        l1.setFont(new Font("Arial", 1, 12));
        l1.setForeground(Environment.getInstance().WRITECOLOR);
        l1.setHorizontalTextPosition(JLabel.CENTER);
        l1.setVerticalTextPosition(JLabel.BOTTOM);
        l1.setIcon(blackSkin);
        add(l1, c);
        
        c.insets = new Insets(0, 0, 0, 40);
        c.gridx = 0;
        c.gridy = 2;
        if(Environment.getInstance().BOUGHT_FEATURES.get("red_dot.png") == false) {
            if(this == setRedSkin.getParent())
                remove(setRedSkin);
            buyRedSkin.setFont(new Font("Arial", 1, 20));
            add(buyRedSkin, c);
        }
        else {
            if(this == buyRedSkin.getParent())
                remove(buyRedSkin);
            if(this == setRedSkin.getParent())
                remove(setRedSkin);
            if(this == setDefaultSkin_red.getParent())
                remove(setDefaultSkin_red);
            if(Environment.getInstance().PATHSKIN.equals(Environment.getInstance().PATHIMAGES + "red_dot.png")) {
                setDefaultSkin_red.setFont(new Font("Arial", 1, 20));
                add(setDefaultSkin_red, c);
            }
            else {
                setRedSkin.setFont(new Font("Arial", 1, 20));
                add(setRedSkin, c);
            }
        }
        
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 2;
        c.gridy = 2;
        l2.setFont(new Font("Arial", 1, 12));
        l2.setForeground(Environment.getInstance().WRITECOLOR);
        l2.setHorizontalTextPosition(JLabel.CENTER);
        l2.setVerticalTextPosition(JLabel.BOTTOM);
        l2.setIcon(redSkin);
        add(l2, c);
        
        c.insets = new Insets(0, 0, 0, 40);
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
            if(this == setDefaultBackground_cloud.getParent())
                remove(setDefaultBackground_cloud);
            if(this == setCloudBackground.getParent())
                remove(setCloudBackground);
            if(Environment.getInstance().PATHBACKGROUND.equals(Environment.getInstance().PATHIMAGES + "cloud_background.png")){
                setDefaultBackground_cloud.setFont(new Font("Arial", 1, 20));
                add(setDefaultBackground_cloud, c);
            }
            else {
                setCloudBackground.setFont(new Font("Arial", 1, 20));
                add(setCloudBackground, c);
            }
        }
        
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 2;
        c.gridy = 3;
        l3.setFont(new Font("Arial", 1, 12));
        l3.setForeground(Environment.getInstance().WRITECOLOR);
        l3.setHorizontalTextPosition(JLabel.CENTER);
        l3.setVerticalTextPosition(JLabel.BOTTOM);
        l3.setIcon(cloud);
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
            if(this == setDefaultBackground_dirt.getParent())
                remove(setDefaultBackground_dirt);
            if(this == setDirtBackground.getParent())
                remove(setDirtBackground);
            if(Environment.getInstance().PATHBACKGROUND.equals(Environment.getInstance().PATHIMAGES + "dirt_background.png")){
                setDefaultBackground_dirt.setFont(new Font("Arial", 1, 20));
                add(setDefaultBackground_dirt, c);
            } else{
                setDirtBackground.setFont(new Font("Arial", 1, 20));
                add(setDirtBackground, c);
            }
        }
        
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 2;
        c.gridy = 4;
        l4.setFont(new Font("Arial", 1, 12));
        l4.setForeground(Environment.getInstance().WRITECOLOR);
        l4.setHorizontalTextPosition(JLabel.CENTER);
        l4.setVerticalTextPosition(JLabel.BOTTOM);
        l4.setIcon(dirt);
        add(l4, c);
        
        c.weighty = 2;
        c.weightx = 1;
        c.ipadx = 0;
        c.ipady = 0;
        
        c.insets = new Insets(0, 10, 5, 0);
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        coinsLabel.setText(Integer.toString(coinsSaver.getCurrentCoins()));
        coinsLabel.setFont(new Font("Arial", 1, 20));
        coinsLabel.setForeground(Environment.getInstance().WRITECOLOR);
        coinsLabel.setIcon(new ImageIcon(Environment.getInstance().PATHIMAGES+"medium_retro_coins.png"));
        add(coinsLabel, c);
        
        c.insets = new Insets(0, 0, 5, 10);
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
                coinsSaver.saveCoins();
                CardLayout cl = MathSnake.getInstance().getCardLayout();
                cl.show(MathSnake.getInstance().getCardsJPanel(), "menu");
            }
        });
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                coinsSaver = new CoinsSaver();
                coinsLabel.setText(Integer.toString(coinsSaver.getCurrentCoins()));
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
        buyBlackSkin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int price = Integer.parseInt(buyBlackSkin.getText());
                if(coinsSaver.getCurrentCoins() >= price) {
                    coinsSaver.setCurrentCoins(coinsSaver.getCurrentCoins() - price);
                    coinsLabel.setText(Integer.toString(coinsSaver.getCurrentCoins()));
                    Environment.getInstance().BOUGHT_FEATURES.put("black_dot.png", true);
                    setComponents();
                    try {
                        Environment.getInstance().writeBoughtFeatures();
                    } catch (IOException ex) {
                        Logger.getLogger(Market.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        setBlackSkin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Environment.getInstance().PATHSKIN = Environment.getInstance().PATHIMAGES + "black_dot.png";
                try {
                    Environment.getInstance().writeGraphicConfiguration();
                } catch (IOException ex) {
                    Logger.getLogger(Market.class.getName()).log(Level.SEVERE, null, ex);
                }
                setComponents();
                repaint();
            }
        });
        buyRedSkin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int price = Integer.parseInt(buyRedSkin.getText());
                if(coinsSaver.getCurrentCoins() >= price) {
                    coinsSaver.setCurrentCoins(coinsSaver.getCurrentCoins() - price);
                    coinsLabel.setText(Integer.toString(coinsSaver.getCurrentCoins()));
                    Environment.getInstance().BOUGHT_FEATURES.put("red_dot.png", true);
                    setComponents();
                    try {
                        Environment.getInstance().writeBoughtFeatures();
                    } catch (IOException ex) {
                        Logger.getLogger(Market.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        setRedSkin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Environment.getInstance().PATHSKIN = Environment.getInstance().PATHIMAGES + "red_dot.png";
                try {
                    Environment.getInstance().writeGraphicConfiguration();
                } catch (IOException ex) {
                    Logger.getLogger(Market.class.getName()).log(Level.SEVERE, null, ex);
                }
                setComponents();
                repaint();
            }
        });
        buyCloudBackground.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int price = Integer.parseInt(buyCloudBackground.getText());
                if(coinsSaver.getCurrentCoins() >= price) {
                    coinsSaver.setCurrentCoins(coinsSaver.getCurrentCoins() - price);
                    coinsLabel.setText(Integer.toString(coinsSaver.getCurrentCoins()));
                    Environment.getInstance().BOUGHT_FEATURES.put("cloud_background.png", true);
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
                setComponents();
                repaint();
            }
        });
        buyDirtBackground.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int price = Integer.parseInt(buyDirtBackground.getText());
                if(coinsSaver.getCurrentCoins() >= price) {
                    coinsSaver.setCurrentCoins(coinsSaver.getCurrentCoins() - price);
                    coinsLabel.setText(Integer.toString(coinsSaver.getCurrentCoins()));
                    Environment.getInstance().BOUGHT_FEATURES.put("dirt_background.png", true);
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
                setComponents();
                repaint();
            }
        });
        setDefaultSkin_black.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultSkin();
            }
        });
        setDefaultSkin_red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultSkin();
            }
        });
        setDefaultBackground_cloud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultBackground();
            }
        });
        setDefaultBackground_dirt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultBackground();
            }
        });
    }
    
    private void setDefaultSkin() {
        Environment.getInstance().PATHSKIN = Environment.getInstance().PATHIMAGES + "dot.png";
        try {
            Environment.getInstance().writeGraphicConfiguration();
        } catch (IOException ex) {
            Logger.getLogger(Market.class.getName()).log(Level.SEVERE, null, ex);
        }
        setComponents();
        repaint();
    }
    
    private void setDefaultBackground() {
        Environment.getInstance().PATHBACKGROUND = Environment.getInstance().PATHIMAGES + "base_background.png";
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
        setComponents();
        repaint();
    }
}

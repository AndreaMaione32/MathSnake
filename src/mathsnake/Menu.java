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

public class Menu extends JPanel{
    
    private JLabel mathSnakeLabel = new JLabel("MATH SNAKE");
    private JLabel gameBestLabel = new JLabel();
    private JButton play = new JButton("Play");
    private JButton scoreBoard = new JButton("Scores");
    private JButton market = new JButton("Market");
    private JButton demo = new JButton("Demo");
    private int count = 1;

    public Menu() {
        initMenu();
    }
    
    private void initMenu() {
        setFocusable(true);
        setPreferredSize(new Dimension(Environment.getInstance().JP_WIDTH, Environment.getInstance().JP_HEIGHT));
        addListeners();
        setLayout(new GridBagLayout());
        paintComponent();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        new Background(Environment.getInstance().PATHBACKGROUND).drawBackground(g);
    }
    
    
    
    private void paintComponent() {
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weighty = 1.0;
        mathSnakeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        mathSnakeLabel.setForeground(Environment.getInstance().WRITECOLOR);
        add(mathSnakeLabel, c);
        
        c.gridx = 1;
        c.gridy = 1;
        gameBestLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gameBestLabel.setForeground(Environment.getInstance().WRITECOLOR);
        add(gameBestLabel,c);
        
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.CENTER;
        
        c.gridx = 1;
        c.gridy = 2;
        play.setFont(new Font("Arial", Font.BOLD, 20));
        add(play, c);
        
        c.gridx = 1;
        c.gridy = 3;
        scoreBoard.setFont(new Font("Arial", Font.BOLD, 20));
        add(scoreBoard, c);
        
        c.gridx = 1;
        c.gridy = 4;
        market.setFont(new Font("Arial", Font.BOLD, 20));
        add(market, c);
        
        c.gridx = 1;
        c.gridy = 5;
        demo.setFont(new Font("Arial", Font.BOLD, 20));
        add(demo, c); 
    }
    
    private void addListeners() {
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                for(Component component : getComponents()) {
                    if(component instanceof JLabel)
                        component.setForeground(Environment.getInstance().WRITECOLOR);
                    try {
                        gameBestLabel.setText(Float.toString(new ScoreBoard().getBestScore()));
                    } catch (IOException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                //DO NOTHING
            }
        });
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = MathSnake.getInstance().getCardLayout();
                cl.show(MathSnake.getInstance().getCardsJPanel(), "game");
            }
        });
        scoreBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = MathSnake.getInstance().getCardLayout();
                cl.show(MathSnake.getInstance().getCardsJPanel(), "score");
            }
        });
        market.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = MathSnake.getInstance().getCardLayout();
                cl.show(MathSnake.getInstance().getCardsJPanel(), "market");
            }
        });
        demo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = MathSnake.getInstance().getCardLayout();
                cl.show(MathSnake.getInstance().getCardsJPanel(), "demo");
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

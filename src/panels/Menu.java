package panels;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mathsnake.MathSnake;
import background.Background;
import environment.Environment;
import iofiles.ScoreBoard;

public class Menu extends JPanel{
    
    private JLabel mathSnakeLabel;
    private JLabel gameBestLabel;
    private JButton play;
    private JButton scoreBoard;
    private JButton market;
    private JButton demo;
    private int count;

    public Menu() {
        initMenu();
    }
    
    private void initMenu() {
        mathSnakeLabel = new JLabel("MATH SNAKE");
        gameBestLabel = new JLabel();
        play = new JButton("Play");
        scoreBoard = new JButton("Scores");
        market = new JButton("Market");
        demo = new JButton("Demo");
        count = 1;
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
        gameBestLabel.setFont(new Font("Arial", Font.BOLD, 25));
        gameBestLabel.setForeground(Environment.getInstance().WRITECOLOR);
        gameBestLabel.setIcon(new ImageIcon(Environment.getInstance().PATHIMAGES+"trophy.png"));
        add(gameBestLabel,c);
        
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
                        gameBestLabel.setText(Integer.toString(new ScoreBoard().getBestScore()));
                    } catch (IOException | ClassNotFoundException ex) {
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

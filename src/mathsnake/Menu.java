package mathsnake;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel {
    
    private JButton play = new JButton("Play");
    private JButton scoreBoard = new JButton("Scores");
    private JButton market = new JButton("Market");

    public Menu() {
        initMenu();
    }
    
    private void initMenu() {
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(Environment.JP_WIDTH, Environment.JP_HEIGHT));
        add(play);
        add(scoreBoard);
        add(market);
        addListeners();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        Font font1 = new Font("Arial", Font.BOLD, 40);
        FontMetrics metrics1 = g.getFontMetrics(font1);
        int x1 = (Environment.JP_WIDTH - metrics1.stringWidth("MATH SNAKE")) / 2; //Stringa centrata nel panel
        g.setFont(font1);
        g.setColor(Color.WHITE);
        g.drawString("MATH SNAKE", x1, 100);
        
        Font font2 = new Font("Arial", Font.BOLD, 20);
        play.setFont(font2);
        scoreBoard.setFont(font2);
        market.setFont(font2);
        play.setLocation(Environment.JP_WIDTH / 2 - play.getSize().width / 2, 200);
        scoreBoard.setLocation(Environment.JP_WIDTH / 2 - scoreBoard.getSize().width / 2, 300);
        market.setLocation(Environment.JP_WIDTH / 2 - market.getSize().width / 2, 400);
    }

    private void addListeners() {
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MathSnake.state = GameState.GAME;
                CardLayout cl = MathSnake.getInstance().getCardLayout();
                cl.show(MathSnake.getInstance().getCardsJPanel(), "game");
            }
        });
        scoreBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MathSnake.state = GameState.SCOREBOARD;
                CardLayout cl = MathSnake.getInstance().getCardLayout();
                cl.show(MathSnake.getInstance().getCardsJPanel(), "score");
            }
        });
        market.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MathSnake.state = GameState.MARKET;
                CardLayout cl = MathSnake.getInstance().getCardLayout();
                cl.show(MathSnake.getInstance().getCardsJPanel(), "market");
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

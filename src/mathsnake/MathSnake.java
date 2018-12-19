package mathsnake;

import environment.Environment;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import panels.*;
import panels.snakeboards.*;

public class MathSnake extends JFrame {
    
    private CardLayout cl;
    private JPanel cardsJPanel;
    private FirstPage firstPage;
    private GameOver gameOver;
    private Menu menu;
    private SnakeBoard game;
    private DemoBoard demo;
    private ScoreBoardInterface score;
    private Market market;
    private ImageIcon icon;
    
    private static MathSnake instance = null; 
    
    public static MathSnake getInstance() {
        if(instance == null)
            instance = new MathSnake();
        return instance;
    }
    
    private MathSnake() {
        initUI();
    }

    public synchronized CardLayout getCardLayout() {
        return cl;
    }
    
    public synchronized JPanel getCardsJPanel() {
        return cardsJPanel;
    }
    
    private void initUI() {
        cl = new CardLayout();
        cardsJPanel = new JPanel(cl);
        firstPage = new FirstPage();
        menu = new Menu();
        game = new SnakeBoard();
        demo = new DemoBoard();
        market = new Market();
        try {
            this.score = new ScoreBoardInterface();
            this.gameOver = new GameOver(this.game);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MathSnake.class.getName()).log(Level.SEVERE, null, ex);
        }
        icon = new ImageIcon(new ImageIcon(Environment.getInstance().PATHIMAGES + "snake_icon.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        setTitle("Math Snake");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                int choice = JOptionPane.showConfirmDialog(MathSnake.getInstance(), "Are you sure you want to exit ?", "Choose an option", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
                if(choice == 0)
                    System.exit(0);
            }
        });
        cardsJPanel.add(firstPage, "firstPage");
        cardsJPanel.add(menu, "menu");
        cardsJPanel.add(game, "game");
        cardsJPanel.add(score,"score");
        cardsJPanel.add(gameOver,"gameOver");
        cardsJPanel.add(market,"market");
        cardsJPanel.add(demo,"demo");
        add(cardsJPanel);
        cl.show(this.cardsJPanel, "firstPage");
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = MathSnake.getInstance();
            ex.setVisible(true);
        });
        
    }
}
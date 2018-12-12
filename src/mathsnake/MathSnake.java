package mathsnake;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MathSnake extends JFrame {
    
    private CardLayout cl = new CardLayout();
    private JPanel cardsJPanel = new JPanel(cl);
    private FirstPage firstPage = new FirstPage();
    private GameOver gameOver;
    private Menu menu = new Menu();
    private SnakeBoard game = new SnakeBoard();
    private ScoreBoard score;
    private Market market = new Market();
    private static MathSnake instance = null; 
    
    public synchronized static MathSnake getInstance() {
        if(instance == null)
            instance = new MathSnake();
        return instance;
    }
    
    private MathSnake() {
        try {
            this.score = new ScoreBoard();
            this.gameOver = new GameOver(this.game);
        } catch (IOException ex) {
            Logger.getLogger(MathSnake.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MathSnake.class.getName()).log(Level.SEVERE, null, ex);
        }
        initUI();
    }

    public synchronized CardLayout getCardLayout() {
        return cl;
    }
    
    public synchronized JPanel getCardsJPanel() {
        return cardsJPanel;
    }
    
    private void initUI() {
        setTitle("Math Snake");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                int choice = JOptionPane.showConfirmDialog(MathSnake.getInstance(), "Are you sure you want to exit ?", "Choose an option", JOptionPane.YES_NO_OPTION);
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
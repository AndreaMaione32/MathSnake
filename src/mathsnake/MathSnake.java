package mathsnake;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MathSnake extends JFrame {
    
    public static GameState state = GameState.FIRST_PAGE;
    
    private CardLayout cl = new CardLayout();
    private JPanel cardsJPanel = new JPanel(cl);
    private FirstPage firstPage = new FirstPage();
    private Menu menu = new Menu();
    private SnakeBoard game = new SnakeBoard();
    private ScoreBoard score = new ScoreBoard();
    private Market market = new Market();
    private GameOver gameOver = new GameOver();
    private static MathSnake instance = null;
    
    public synchronized static MathSnake getInstance() {
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
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_ESCAPE) {
                    if(game.hasFocus()) {
                        int choice = JOptionPane.showConfirmDialog(MathSnake.getInstance(), "Are you sure you want to go to the menu ?", "Choose an option", JOptionPane.YES_NO_OPTION);
                        if(choice == 0) {
                            cl.show(cardsJPanel, "menu");
                        }
                    }
                    else {
                        int choice = JOptionPane.showConfirmDialog(MathSnake.getInstance(), "Are you sure you want to exit ?", "Choose an option", JOptionPane.YES_NO_OPTION);
                        if(choice == 0)
                            System.exit(0);
                    }
                }
            }
        });
        cardsJPanel.add(firstPage, "firstPage");
        cardsJPanel.add(menu, "menu");
        cardsJPanel.add(game, "game");
        cardsJPanel.add(score,"score");
        cardsJPanel.add(market,"market");
        cardsJPanel.add(gameOver,"gameOver");
        add(cardsJPanel);
        cl.show(cardsJPanel, "market");
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = MathSnake.getInstance();
            ex.setVisible(true);
        });
        
    }
}
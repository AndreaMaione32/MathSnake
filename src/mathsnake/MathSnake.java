package mathsnake;

import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MathSnake extends JFrame {
    
    public static GameState state = GameState.FIRST_PAGE;
    
    private CardLayout cl = new CardLayout();
    private JPanel cardsJPanel = new JPanel(cl);
    private FirstPage firstPage = new FirstPage();
    private GameOver gameOver = new GameOver();
    private Menu menu = new Menu();
    private SnakeBoard game = new SnakeBoard();
    private ScoreBoard score = new ScoreBoard();
    private JPanel market;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardsJPanel.add(firstPage, "firstPage");
        cardsJPanel.add(menu, "menu");
        cardsJPanel.add(game, "game");
        cardsJPanel.add(score,"score");
        cardsJPanel.add(gameOver,"gameOver");
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
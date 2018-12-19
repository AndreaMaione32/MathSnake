package panels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import mathsnake.MathSnake;
import background.Background;
import environment.Environment;
import iofiles.ScoreBoard;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import panels.snakeboards.SnakeBoard;

public class GameOver extends JPanel {
    
    private SnakeBoard sb;
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField jTextField1;

    public GameOver(SnakeBoard sb) {
        initComponents();
        this.sb = sb;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        new Background(Environment.getInstance().PATHBACKGROUND).drawBackground(g);
    }
                                   
    private void initComponents() {

        jTextField1 = new JTextField();
        jLabel1 = new JLabel();
        jButton1 = new JButton();
        jLabel2 = new JLabel();
        

        setBackground(new Color(0, 0, 0));
        setPreferredSize(new Dimension(500, 500));
       
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                for(Component component : getComponents()) {
                    if(component instanceof JLabel)
                        component.setForeground(Environment.getInstance().WRITECOLOR);
                }
                jTextField1.requestFocusInWindow();
            }

            @Override
            public void focusLost(FocusEvent e) {
                jTextField1.setText("");
            }
        });
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                requestFocusInWindow();
            }
        });
        
        jTextField1.setFont(new Font("Arial", 1, 18));

        jLabel1.setFont(new Font("Arial", 1, 50));
        jLabel1.setForeground(Environment.getInstance().WRITECOLOR);
        jLabel1.setText("GAME OVER :(");

        jButton1.setText("OK");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed();
            }
        });
        
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel2.setBackground(new Color(0, 0, 0));
        jLabel2.setFont(new Font("Arial", 0, 24));
        jLabel2.setForeground(Environment.getInstance().WRITECOLOR);
        jLabel2.setText("INSERT YOUR NAME");
        

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE)
                            .addGap(65, 65, 65)))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(129, 129, 129))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jLabel2)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jButton1)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_ENTER)
                    jButton1ActionPerformed();
            }
        });
    }           
    
    private void jTextField1KeyTyped(KeyEvent evt) {
    boolean max = jTextField1.getText().length() > 9;
    if ( max ){
        evt.consume();
    }        
}

    private void jButton1ActionPerformed()  {
        String name;
        String s = jTextField1.getText();
        
        try {
            ScoreBoard SB = new ScoreBoard();
            if(s.isEmpty()){
                s = "UNKNOWN";
                ScoreBoard.Score a = SB.new Score(sb.getGameBest(),new Date(),s);
                SB.insert_score(a);
                jTextField1.setText("");
            }
            else {
                name=s;
                ScoreBoard.Score a = SB.new Score(sb.getGameBest(),new Date(),s);
                SB.insert_score(a);
                jTextField1.setText("");
            }    
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(GameOver.class.getName()).log(Level.SEVERE, null, ex);
        }
        CardLayout cl = MathSnake.getInstance().getCardLayout();
        cl.show(MathSnake.getInstance().getCardsJPanel(), "menu");
    }           
}
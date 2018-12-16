package mathsnake;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import mathsnake.ScoreBoard.Score;

/**
 *
 * @author VALE
 */
public class ScoreBoardInterface extends javax.swing.JPanel {
    
    private JButton jButton1 = new JButton();    
    
    public ScoreBoardInterface() throws IOException, FileNotFoundException, ClassNotFoundException {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(Environment.getInstance().JP_WIDTH, Environment.getInstance().JP_HEIGHT));
        initComponents();
    }
    
    private void initComponents(){
        jButton1.setText("Back");
        jButton1.setFont(new java.awt.Font("Arial",1,20));
        jButton1.addActionListener(this::jButton1ActionPerformed);
        
        addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                removeAll();
                repaint();
                try {
                    createScoreBoard();
                } catch (IOException ex) {
                    Logger.getLogger(ScoreBoardInterface.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ScoreBoardInterface.class.getName()).log(Level.SEVERE, null, ex);
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
    }


   @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        new Background(Environment.getInstance().PATHBACKGROUND).drawBackground(g);
    }
                         
    private void createScoreBoard() throws IOException, FileNotFoundException, ClassNotFoundException {
        JLabel scoreBoardLabel = new JLabel("SCOREBOARD");
        JLabel jLabelName = new JLabel("NAME");
        JLabel jLabelScore = new JLabel("SCORE");
        JLabel jLabelDate = new JLabel("DATE");
    
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 6;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weighty = 1.0;
        scoreBoardLabel.setFont(new Font("Arial", Font.BOLD, 40));
        scoreBoardLabel.setForeground(Environment.getInstance().WRITECOLOR);
        add(scoreBoardLabel, c);
        c.weightx = 1.0;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        jLabelName.setFont(new Font("Arial", Font.BOLD, 25));
        jLabelName.setForeground(Environment.getInstance().WRITECOLOR);
        add(jLabelName, c);
        c.gridx = 2;
        jLabelScore.setFont(new Font("Arial", Font.BOLD, 25));
        jLabelScore.setForeground(Environment.getInstance().WRITECOLOR);
        add(jLabelScore, c);
        c.gridx = 4;
        jLabelDate.setFont(new Font("Arial", Font.BOLD, 25));
        jLabelDate.setForeground(Environment.getInstance().WRITECOLOR);
        add(jLabelDate, c);
        //FILL THE SCOREBOARD
        fillScoreBoard(c);
        
        c.weighty = 2;
        c.insets = new Insets(0, 0, 5, 10);
        c.anchor = GridBagConstraints.LAST_LINE_END;
        jButton1.setFont(new Font("Arial", 1, 20));
        add(jButton1, c);
    }                      

    private void fillScoreBoard(GridBagConstraints c) throws IOException, FileNotFoundException, ClassNotFoundException{
        ScoreBoard SB = new ScoreBoard();
        List<Score> list = SB.getList();
        for(int i = 0; i<Environment.getInstance().SCOREBOARD_SIZE; i++){
            if(i >= list.size()){
                JLabel empty = new JLabel("");
                c.gridy ++;
                c.gridx = 0;
                this.add(empty, c);
                c.gridx = 2;
                this.add(empty, c);
                c.gridx = 4;
                this.add(empty, c);
            }
            else{
           Score s = list.get((list.size()-1)-i);
           JLabel name = new JLabel(s.getNamePlayer());
           JLabel score = new JLabel(Integer.toString(s.getScore()));
           String str_date = new SimpleDateFormat().format(s.getDateScore());
           JLabel date = new JLabel(str_date);
           c.gridy ++;
           c.gridx = 0;
           name.setFont(new Font("Arial", Font.BOLD, 22));
           name.setForeground(Environment.getInstance().WRITECOLOR);
           this.add(name,c);
           c.gridx = 2;
           this.add(score,c);
           score.setFont(new Font("Arial", Font.BOLD, 24));
           score.setForeground(Environment.getInstance().WRITECOLOR);
           c.gridx = 4;
           date.setFont(new Font("Arial", Font.BOLD, 18));
           date.setForeground(Environment.getInstance().WRITECOLOR);
           this.add(date, c);
            }
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
     
                CardLayout cl = MathSnake.getInstance().getCardLayout();
                cl.show(MathSnake.getInstance().getCardsJPanel(), "menu");
    }                                        
                   
}
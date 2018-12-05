package mathsnake;

import java.awt.CardLayout;
import java.awt.Color;
import static java.awt.Color.white;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author VALE
 */
public class ScoreBoard extends javax.swing.JPanel {


    public ScoreBoard() throws IOException, FileNotFoundException, ClassNotFoundException {
        initComponents();
    }
    
    private void initComponents(){
        addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                removeAll();
               createScoreBoard();
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
                         
    private void createScoreBoard() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        
        try {
            SB = new ScoreBoard2();
          
        } catch (IOException ex) {
            Logger.getLogger(ScoreBoard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ScoreBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        Font font1 =new Font("Arial", Font.BOLD, 16);
        
        setPreferredSize(new java.awt.Dimension(500, 500));
        setSize(new java.awt.Dimension(500, 500));

        jTextArea1.setOpaque(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        try {
            jTextArea1.setText(SB.toStringFromDat());
        } catch (IOException ex) {
            Logger.getLogger(ScoreBoard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ScoreBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTextArea1.setEditable(false);
        jTextArea1.setFont(font1);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setOpaque(false);
        jScrollPane1.setBorder(null);
        jButton1.setText("Back");
        jButton1.setFont(new java.awt.Font("Arial",1,20));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 40)); // NOI18N
        jLabel1.setForeground(Environment.getInstance().WRITECOLOR);
        jLabel1.setText("SCOREBOARD");
        
        jLabel1.setHorizontalAlignment( JLabel.CENTER );
        
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
    }                      

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
     
                CardLayout cl = MathSnake.getInstance().getCardLayout();
                cl.show(MathSnake.getInstance().getCardsJPanel(), "menu");
    }                                        


                   
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private ScoreBoard2 SB;                 
}
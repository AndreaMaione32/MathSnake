package mathsnake;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * 
 * @author Antonino Durazzo
 */
public class ScoreBoard2 implements Serializable{
    
    private final LinkedList<Score> list;
    private final String database= "database.dat";
    
    /**
     * classe score annidata 
     */
    public class Score implements Serializable{
        
        private final float score;
        private final Date score_date;
        private final String name_player;
        
        /**
         * Costruttore che inizializza gli attributi della classe 
         * @param score
         * @param score_date
         * @param name_player 
         */
        public Score(float score, Date score_date, String name_player){
            this.score = score;
            this.score_date = score_date;
            this.name_player = name_player;
        }
        
        /**
         * 
         * @return restituisce l'attributo score della classe
         */
        public float getScore(){
            return this.score;
        }
        /**
         * 
         * @return restituisce l'attributo score_data della classe
         */
        public Date getDateScore(){
            return this.score_date;
        }
        /**
         * 
         * @return restituisce l'attributo name_player della classe
         */
        public String getNamePlayer(){
            return this.name_player;
        }
        
        /**
         * 
         * @return 
         */
        @Override
        public String toString(){
            DateFormat dateFormat = new SimpleDateFormat();
            String strDate = dateFormat.format(this.score_date);
            return "\n   " + this.name_player + "\t                " + Float.toString(this.score) +"\t\t  "+ strDate + "\n";
        }
    }
    
    /**
     * costruttore della classe che inizializza una linkedlist di score vuota come suo attributo
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    public ScoreBoard2() throws IOException, FileNotFoundException, ClassNotFoundException{
        this.list = new LinkedList<>();
       // this.updateDB(this);
    }
    
    
    
    /**
     * 
     * @return restituisce la dimensione della lista
     */
    public int len(){
        return this.list.size();
        
    }
    
    /**
     * 
     * @return True se la scoreboard è vuota altrimenti false
     */
    public boolean isEmpty(){
        return this.list.isEmpty();
    }
    

    /**
     * Questa funzione permette di poter memorizzare una scoreboard all'interno del file binario,
     * in più viene invocata la funzione copiDbIntoTxt().
     * @param board: rappresenta la board che si vuole inserire nel file binario
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    void updateDB(ScoreBoard2 board) throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectOutputStream fbinarioOut;
        fbinarioOut = new ObjectOutputStream(new FileOutputStream(this.database));
        fbinarioOut.writeObject(board);
        //System.out.print("HEIIIItttt" + board.toString());
        fbinarioOut.flush();
        fbinarioOut.close();
        this.copyDbIntoTxt();
        
    }
    
    /**
     * Questa funzione legge l'oggetto contenuto all'interno del database e lo scrive in un file di testo database.txt
     * questa funzione è invocata ogni qual volta si apportano modifiche al database siccome è utile tener traccia in 
     * formato testuale e non binario del contenuto del database.
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    public void copyDbIntoTxt() throws IOException, FileNotFoundException, ClassNotFoundException{
        ScoreBoard2 board;
        board = this.readDB();
        //System.out.print("ciao" + board.toString());
        //Scrive l'oggetto in file di testo, sfruttando  (implicitamente) il suo metodo toString()
        try (PrintWriter ftestoOut = new PrintWriter(new FileWriter("database.txt"))) {
            ftestoOut.println(board);
            ftestoOut.flush();
        }
        
    }
    
    /**
     * Questa funzione apre un flusso in lettura verso un il file binario database.dat,
     * legge l'oggetto memorizzato, chiude il flusso e restituisce l'oggetto letto.
     * @return tmpScoreboard: rappresenta lo ScoreBoard letto dal file binario database.dat
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    ScoreBoard2 readDB() throws FileNotFoundException, IOException, ClassNotFoundException{
        ScoreBoard2 tmpScoreBoard;
        try (ObjectInputStream fin = new ObjectInputStream(new FileInputStream(this.database))) {
            tmpScoreBoard = (ScoreBoard2) fin.readObject();
            
        }
           
        //System.out.print("====================" + tmpScoreBoard.toString());
        return tmpScoreBoard;
    }
    
    
    
    /**
     * 
     * @param n: rappresenta il numero dei migliori risultati che si vuole ottenere
     * @return list_score: ScoreBoard che contiene gli elementi desiderati
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    public ScoreBoard2 top(int n) throws IOException, FileNotFoundException, ClassNotFoundException{
        ScoreBoard2 list_score = new ScoreBoard2();
        ScoreBoard2 board = this.readDB();
        if (board.isEmpty()){
            return null;
        } else{
            for (int i=0;i<n;i++){
                list_score.list.addLast(board.list.get(i));
            }
        return list_score;
        }
    }
    
    
    /**
     * 
     * @param n: rappresenta il numero dei peggiori risultati che si vuole ottenere
     * @return list_score: Scoreboard che contiene gli elementi desiderati
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    public ScoreBoard2 last(int n) throws IOException, FileNotFoundException, ClassNotFoundException{
        
        ScoreBoard2 list_score = new ScoreBoard2();
        ScoreBoard2 board = this.readDB();
        if (board.isEmpty()){
            return null;
        } else{
            System.out.println(board.len());
            for (int i=board.len()-1;i>=board.len()-n;i--){
                System.out.println(i);
                list_score.list.addLast(board.list.get(i));
            }
            return list_score;
        }
    }
    
    /**
     * Questa funziona effettua un inserimento in ordine dello score che si vuole inserire nella ScoreBoard, 
     * il criterio di ordine è dato dallo score migliore che nel nostro caso è l'intervallo di tempo migliore 
     * quindi per migliore score intendiamo un minor tempo totalizzato.Inoltre questa funzione si interfaccia 
     * con il database infatti non si memorizzano istanze temporanee degli score di questa scoreboard ma viene 
     * letta la scoreboard presente nel file binario e a quell'istanza si apportano modifche.Cio è stato sviluppato 
     * per avere una stato permanente della scoreboard del gioco.
     * @param score: score da memorizzare all'interno del database.dat
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    public void insert_score(Score score) throws IOException, FileNotFoundException, ClassNotFoundException{
        ScoreBoard2 board = this.readDB();
        if (board.isEmpty()){
            board.list.addLast(score);
        } else {
            int dim = board.len();
            boolean added = false;
            int i = 0;
            while(i<dim){
                if (score.getScore() >= board.list.get(i).getScore()){
                    board.list.add(i, score);
                    added = true;
                    break;
                }
                i++;
            }
            if ((added==false)){
                board.list.addLast(score);
            }
        }
        this.updateDB(board); 
    }
    
    /**
     * 
     * @return str: rappresenta l'oggetto ScoreBoard, memorizzato all'interno del file binario database.dat, sotto forma di caratteri  
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    public String toStringFromDat() throws IOException, FileNotFoundException, ClassNotFoundException{
        String str = "\n  PLAYER NAME       SCORE\t\tDATE\n";
        ScoreBoard2 list_score = this.readDB();
        if (!list_score.isEmpty()){
            Iterator<Score> itr = this.readDB().list.iterator();
            while(itr.hasNext()){
                Score score = itr.next();
                str += score.toString();
            }
            return str;
        }else{
            return null;
        }
        
    }
    
    /**
     * 
     * @return String str: rappresenta l'oggetto ScoreBoard sotto forma di caratteri
     */
    @Override
    public String toString(){
        String str = "\n||||\tPlayer Name\t\t\tScore\t\t\tDate\t\t\t\t|||\n";
        Iterator<Score> itr = this.list.iterator();
        while(itr.hasNext()){
            Score score = itr.next();
            str += score.toString();
        }
        return str;
    }

    
  
    


    
 
}

package mathsnake;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
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
       list = this.readDB().list; //inizializzo la mia lista
    }
    
    public ScoreBoard2(LinkedList<Score> list){
        this.list = list;
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
    ScoreBoard2 readDB() throws IOException, ClassNotFoundException{
        ScoreBoard2 tmpScoreBoard;
        ObjectInputStream fin = null;
        OutputStream out = null;
        try{
            fin = new ObjectInputStream(new FileInputStream(this.database));
            tmpScoreBoard = (ScoreBoard2) fin.readObject();
        }
        catch(FileNotFoundException ex){
            out = new FileOutputStream(this.database);
            this.updateDB(new ScoreBoard2(new LinkedList<Score>()));
            fin = new ObjectInputStream(new FileInputStream(this.database));
            tmpScoreBoard = (ScoreBoard2) fin.readObject();
        }
        finally{
            if(fin!=null)
                fin.close();
            if(out!=null)
                out.close();
        }
        //System.out.print("====================" + tmpScoreBoard.toString());
        return tmpScoreBoard;
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
        if (this.list.isEmpty()){
            this.list.addLast(score);
            this.updateDB(this);
        } else {
            if(this.list.size() == Environment.getInstance().SCOREBOARD_SIZE){
                if(score.score > this.list.get(0).score){
                    this.list.remove(this.list.get(0));
                    this.list.add(score);
                    this.list.sort(new Comparator(){
                        @Override
                        public int compare(Object o1, Object o2) {
                            Score s1 = (Score)o1;
                            Score s2 = (Score)o2;
                            if(s1.score == s2.score)
                                return 0;
                            else if (s1.score < s2.score)
                                return -1;
                            else
                                return 1;
                        }
                    });
                    this.updateDB(this);
                }
            }
            else{
                this.list.add(score);
                    this.list.sort(new Comparator(){
                        @Override
                        public int compare(Object o1, Object o2) {
                            Score s1 = (Score)o1;
                            Score s2 = (Score)o2;
                            if(s1.score == s2.score)
                                return 0;
                            else if (s1.score < s2.score)
                                return -1;
                            else
                                return 1;
                        }
                    });
                    this.updateDB(this);
            }
        }
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
        String str = "\n  PLAYER NAME       SCORE\t\tDATE\n";
        for(int i = this.list.size()-1; i >= 0; i--){
            Score score = this.list.get(i);
            str += score.toString();
        }
        return str;
    }
 
}
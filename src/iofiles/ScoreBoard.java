package iofiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import environment.Environment;

public class ScoreBoard implements Serializable {
    
    private final LinkedList<Score> list;
    private String databaseDAT = Environment.getInstance().UTILITY_FILES_PATH + "scoreboard.dat";
    
    /**
     * classe Score annidata 
     */
    public class Score implements Serializable {
        
        private final int score;
        private final Date score_date;
        private final String name_player;
        
        public Score(int score, Date score_date, String name_player) {
            this.score = score;
            this.score_date = score_date;
            this.name_player = name_player;
        }
        
        public int getScore(){
            return this.score;
        }
        
        public Date getDateScore(){
            return this.score_date;
        }
        
        public String getNamePlayer(){
            return this.name_player;
        }
        
        @Override
        public String toString(){
            DateFormat dateFormat = new SimpleDateFormat();
            String strDate = dateFormat.format(this.score_date);
            return "\n   " + this.name_player + "\t                " + Float.toString(this.score) +"\t\t  "+ strDate + "\n";
        }
    }
    
    public ScoreBoard() throws IOException, FileNotFoundException, ClassNotFoundException {
        list = this.readDB().list; //inizializzo la mia lista
    }
    
    public ScoreBoard(LinkedList<Score> list) {
        this.list = list;
    }
    
    public int len(){
        return this.list.size();
    }
    
    
    public boolean isEmpty(){
        return this.list.isEmpty();
    }

    public LinkedList<Score> getList() {
        return list;
    }
    
    public void updateDB(ScoreBoard board) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectOutputStream fbinarioOut;
        fbinarioOut = new ObjectOutputStream(new FileOutputStream(this.databaseDAT));
        fbinarioOut.writeObject(board);
        fbinarioOut.flush();
        fbinarioOut.close();
    }
    
    public ScoreBoard readDB() throws IOException, ClassNotFoundException {
        ScoreBoard tmpScoreBoard;
        ObjectInputStream fin = null;
        OutputStream out = null;
        try{
            fin = new ObjectInputStream(new FileInputStream(this.databaseDAT));
            tmpScoreBoard = (ScoreBoard) fin.readObject();
        }
        catch(FileNotFoundException ex){
            out = new FileOutputStream(this.databaseDAT);
            this.updateDB(new ScoreBoard(new LinkedList<Score>()));
            fin = new ObjectInputStream(new FileInputStream(this.databaseDAT));
            tmpScoreBoard = (ScoreBoard) fin.readObject();
        }
        finally{
            if(fin!=null)
                fin.close();
            if(out!=null)
                out.close();
        }
        return tmpScoreBoard;
    }
    
    public void insert_score(Score score) throws IOException, FileNotFoundException, ClassNotFoundException {
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
    
    public int getBestScore(){
        if(this.list.isEmpty())
            return 0;
        else
            return this.list.get(this.list.size()-1).score;
    }
    
    /**
     * 
     * @return str: rappresenta l'oggetto ScoreBoard, memorizzato all'interno del file binario database.dat, sotto forma di caratteri  
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    public String toStringFromDat() throws IOException, FileNotFoundException, ClassNotFoundException {
        String str = "\n  PLAYER NAME       SCORE\t\tDATE\n";
        ScoreBoard list_score = this.readDB();
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
    public String toString() {
        String str = "\n  PLAYER NAME       SCORE\t\tDATE\n";
        for(int i = this.list.size()-1; i >= 0; i--){
            Score score = this.list.get(i);
            str += score.toString();
        }
        return str;
    }
 
}
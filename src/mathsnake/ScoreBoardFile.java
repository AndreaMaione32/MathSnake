/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author edoardocossentino
 */
public class ScoreBoardFile implements Serializable{
    
    private final LinkedList<Score> list;
    private final String database= "database.dat";
    
    public class Score implements Serializable{
        
        private final float score;
        private final String name_player;
        
        /**
         * Costruttore che inizializza gli attributi della classe 
         * @param score
         * @param name_player 
         */
        public Score(float score, String name_player){
            this.score = score;
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
            return "\n||||\t" + this.name_player + "\t\t\t\t" + Float.toString(this.score) + "\t|||\n";
        }
    }
    
     /**
     * costruttore della classe che inizializza una linkedlist di score vuota come suo attributo
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    public ScoreBoardFile() throws IOException, FileNotFoundException, ClassNotFoundException{
        this.list = new LinkedList<>();
        //this.updateDB(this);
    }
    
    
    
    
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}

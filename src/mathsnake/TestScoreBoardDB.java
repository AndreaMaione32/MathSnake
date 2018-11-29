/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author antonino
 */
public class TestScoreBoardDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            ScoreBoard list = new ScoreBoard();
            ScoreBoard.Score a = list.new Score((float) 7.3, new Date(2018,11,4),"antonio");
            ScoreBoard.Score a1 = list.new Score((float) 7.4, new Date(2018,11,5),"valentina");
            ScoreBoard.Score a2 = list.new Score((float) 7.5, new Date(2018,11,6),"De pretis");
            ScoreBoard.Score a3 = list.new Score((float) 7.6, new Date(2018,11,7),"cioppola");
            ScoreBoard.Score a4 = list.new Score((float) 7.7, new Date(2018,11,8),"ciussentino");
            ScoreBoard.Score a5 = list.new Score((float) 7.8, new Date(2018,11,9),"maione");
            
            
            System.out.println("ADDING cioppola with score 7.6");
            list.insert_score(a3);
            System.out.println("ADDED cioppola");
            
            System.out.println("ADDING maione with score 7.8");
            list.insert_score(a5);
            System.out.println("ADDED maione");
            
            System.out.println("ADDING antonio with score 7.3");
            list.insert_score(a);
            System.out.println("ADDED antonio ");
            
            System.out.println("ADDING ciussentino with score 7.7");
            list.insert_score(a4);
            System.out.println("ADDED ciussentino");
            
            System.out.println("ADDING valentina with score 7.4");
            list.insert_score(a1);
            System.out.println("ADDED valentina");
            
            System.out.println("ADDING de pretis with score 7.5");
            list.insert_score(a2);
            System.out.println("ADDED de pretis");
            
            System.out.println("-----------------PRINT-FROM-DB-----------------");
            System.out.println(list.toStringFromDat());
            
            System.out.println("--------------PRINT-FROM-DB-TOP-3--------------");
            System.out.println(list.top(3));
            
            System.out.println("--------------PRINT-FROM-DB-LAST-3--------------");
            System.out.println(list.last(3));
            
            //list.clearDB();
            //System.out.println("-----------------PRINT-FROM-DB-----------------");
            //System.out.println(list.toStringFromDat());
            
        }catch(IOException | ClassNotFoundException e){
            Logger.getLogger(ScoreBoard.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("IO or class exeption");
        }
        
        
        
      
    }
    
}

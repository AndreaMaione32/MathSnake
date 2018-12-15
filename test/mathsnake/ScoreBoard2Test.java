/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mathsnake.ScoreBoard.Score;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andreamaione
 */
public class ScoreBoard2Test {
    
    @After
    public void deleteTempFile() {
        File fDat = new File("prova.dat");
        File fTxt = new File("prova.txt");
        if(fDat.exists())
            fDat.delete();
        if(fTxt.exists())
            fTxt.delete();
    }

    /**
     * Test of updateDB method, of class ScoreBoard.
     */
    @Test
    public void testUpdateDB() {
        try {
            String tmpFile = "prova";
            String tmpFileDAT = tmpFile + ".dat";
            ScoreBoard board = new ScoreBoard();
            ScoreBoard instance = new ScoreBoard(tmpFile);
            instance.updateDB(board);
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(tmpFileDAT));
            ScoreBoard readInstance = (ScoreBoard) reader.readObject();
            reader.close();
            assertEquals(readInstance.toString(), board.toString());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ScoreBoard2Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of copyDbIntoTxt method, of class ScoreBoard2.
     */
    /**
    @Test
    public void testCopyDbIntoTxt() {
        try {
            String tmpFile = "prova";
            String tmpFileTXT = tmpFile + ".txt";
            ScoreBoard2 instance = new ScoreBoard2(tmpFile);
            ScoreBoard2 board = new ScoreBoard2();
            instance.updateDB(board);
            instance.copyDbIntoTxt();
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(new File(tmpFileTXT)));
            ScoreBoard2 readInstance = (ScoreBoard2) reader.readObject();
            reader.close();
            assertEquals(readInstance.toString(), instance.toString());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ScoreBoard2Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }**/

    /**
     * Test of readDB method, of class ScoreBoard.
     */
    @Test
    public void testReadDB() {
        try {
            ScoreBoard instance = new ScoreBoard();
            ScoreBoard result = instance.readDB();
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream("database.dat"));
            ScoreBoard readInstance = (ScoreBoard) reader.readObject();
            reader.close();
            assertEquals(readInstance.toString(), result.toString());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ScoreBoard2Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of insert_score method, of class ScoreBoard.
     */
    @Test
    public void testInsertScore() {
        try {
            String tmpFile = "prova";
            boolean flag = true;
            ScoreBoard instance = new ScoreBoard(tmpFile);
            Score s1 = instance.new Score(10000, new Date(), "Player1");
            Score s2 = instance.new Score(9000, new Date(), "Player2");
            Score s3 = instance.new Score(12000, new Date(), "Player3");
            instance.insert_score(s1);
            instance.insert_score(s2);
            instance.insert_score(s3);
            List<Score> l = instance.getList();
            for (int i = 1; i < l.size(); i++)
                if(l.get(i).getScore() < l.get(i -1).getScore()) {
                    flag = false;
                    break;
                }
            assertTrue(flag);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ScoreBoard2Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

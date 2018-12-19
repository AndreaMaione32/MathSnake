import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import environment.Environment;
import iofiles.ScoreBoard;
import iofiles.ScoreBoard.Score;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ScoreBoardTest {
    
    @Before
    public void copyFile() throws IOException{
        //if scoreboard file exists copy content of scoreboard in scoreboard_copy in order to restore the state after the test
        if(new File(Environment.getInstance().UTILITY_FILES_PATH + "scoreboard.dat").exists())
            Files.copy(new File(Environment.getInstance().UTILITY_FILES_PATH + "scoreboard.dat").toPath(),new File(Environment.getInstance().UTILITY_FILES_PATH+"scoreboard_copy.dat").toPath() , StandardCopyOption.REPLACE_EXISTING);
    }
    
    @After
    public void undoModificationTest(){
        //restore the state existing before the test
        new File(Environment.getInstance().UTILITY_FILES_PATH + "scoreboard.dat").delete();
        //if scoreboard_copy exists rename it in scoreboard
        if(new File(Environment.getInstance().UTILITY_FILES_PATH + "scoreboard_copy.dat").exists()){
            new File(Environment.getInstance().UTILITY_FILES_PATH + "scoreboard_copy.dat").renameTo(new File(Environment.getInstance().UTILITY_FILES_PATH+"scoreboard.dat"));}
    }
    
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
     * Test if the scoreboard is updated correctly after a new game best
     */
    @Test
    public void testInsertBest(){
        try {
            ScoreBoard sb = new ScoreBoard();
            int s = sb.getBestScore();
            int nsBest = s + 1;
            int ns1 = s-1;
            int ns2 = s-2;
            int ns3 = s;
            ScoreBoard.Score scoreBest = sb.new Score(nsBest,new Date(),"");
            ScoreBoard.Score score1 = sb.new Score(ns1,new Date(),"");
            ScoreBoard.Score score2 = sb.new Score(ns2,new Date(),"");
            ScoreBoard.Score score3 = sb.new Score(ns3,new Date(),"");
            sb.insert_score(scoreBest);
            sb.insert_score(score1);
            sb.insert_score(score2);
            sb.insert_score(score3);
            assertEquals(nsBest, sb.getBestScore());
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ScoreBoardTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Test it the number of scores saved in the file is maximum Environment.ScoreBoardSize
     */
    @Test
    public void testInsert(){
        try {
            ScoreBoard sb = new ScoreBoard();
            ScoreBoard.Score sd = sb.new Score(0, new Date(), "");
            int size = sb.getList().size();
            if(size == Environment.getInstance().SCOREBOARD_SIZE){
                //DO NOTHING
            }
            else{
                while(sb.getList().size() < Environment.getInstance().SCOREBOARD_SIZE){ //fill the scoreboard
                    sb.insert_score(sd);
                }
            }
            sb.insert_score(sd); //insert an element more
            assertEquals(sb.getList().size(), Environment.getInstance().SCOREBOARD_SIZE);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ScoreBoardTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

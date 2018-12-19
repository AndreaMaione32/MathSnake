import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import environment.Environment;
import iofiles.CoinsSaver;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CoinsSaverTest {
    
    /**
     * Test of saveCoins method, of class CoinsSaver.
     */
    @Before
    public void copyFile() throws IOException{
        //if coins file exists copy content of coins in coins_copy in order to restore the state after the test
        if(new File(Environment.getInstance().UTILITY_FILES_PATH + "coins.txt").exists())
            Files.copy(new File(Environment.getInstance().UTILITY_FILES_PATH + "coins.txt").toPath(),new File(Environment.getInstance().UTILITY_FILES_PATH+"coins_copy.txt").toPath() , StandardCopyOption.REPLACE_EXISTING);
    }
    
    @After
    public void undoModificationTest(){
        //restore the state existing before the test
        new File(Environment.getInstance().UTILITY_FILES_PATH + "coins.txt").delete();
        //if coins_copy exists rename it in coins
        if(new File(Environment.getInstance().UTILITY_FILES_PATH + "coins_copy.txt").exists())
            new File(Environment.getInstance().UTILITY_FILES_PATH + "coins_copy.txt").renameTo(new File(Environment.getInstance().UTILITY_FILES_PATH+"coins.txt"));
    }
    
    @Test
    public void testSaveCoins() {
        int coinsSaved = 0;
        CoinsSaver coinsSaver = new CoinsSaver();
        coinsSaver.setCurrentCoins(20);
        coinsSaver.saveCoins();
        try {
            coinsSaved = coinsSaver.readCoins();
        } catch (IOException ex) {
            Logger.getLogger(CoinsSaverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(coinsSaved, 20);
    }
    
    
}

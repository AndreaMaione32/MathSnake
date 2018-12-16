/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
/**
 *
 * @author andreamaione
 */
public class CoinsSaverTest {
    
    @Rule
    private boolean fileModified = false;
    /**
     * Test of saveCoins method, of class CoinsSaver.
     */
    @Before
    public void copyFile() throws IOException{
        //copy content of coins in coins_copy in order to restore the state after the test
        Files.copy(new File(Environment.getInstance().UTILITY_FILES_PATH+"coins.txt").toPath(),new File(Environment.getInstance().UTILITY_FILES_PATH+"coins_copy.txt").toPath() , StandardCopyOption.REPLACE_EXISTING);
    }
    
    @After
    public void undoModificationTest(){
        //restore the state before the test
        new File(Environment.getInstance().UTILITY_FILES_PATH+"coins.txt").delete();
        new File(Environment.getInstance().UTILITY_FILES_PATH+"coins_copy.txt").renameTo(new File(Environment.getInstance().UTILITY_FILES_PATH+"coins.txt"));
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

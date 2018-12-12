/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import static org.mockito.Mockito.*;

/**
 *
 * @author andreamaione
 */
public class CoinsSaverTest {
    
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /**
     * Test of saveCoins method, of class CoinsSaver.
     */
    @Test
    public void testSaveCoins() {
        try {
            CoinsSaver instance = new CoinsSaver();
            File f = folder.newFile("temp.txt");
            int expectedCoins = 10;
            int coinsRead;
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
                bw.write(Integer.toString(expectedCoins));
            }
            instance.setFile(f);
            instance.setCurrentCoins(10);
            instance.saveCoins();
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                coinsRead = Integer.parseInt(br.readLine());
            }
            assertEquals(expectedCoins, coinsRead);
        } catch (IOException ex) {
            Logger.getLogger(CoinsSaverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of readCoins method, of class CoinsSaver.
     */
    @Test
    public void testReadCoins() {
        try {
            CoinsSaver instance = new CoinsSaver();
            instance.readCoins();
            BufferedReader br = new BufferedReader(new FileReader(instance.getFile()));
            int expectedCoins = Integer.parseInt(br.readLine());
            assertEquals(expectedCoins, instance.getCurrentCoins());
        } catch (IOException ex) {
            Logger.getLogger(CoinsSaverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

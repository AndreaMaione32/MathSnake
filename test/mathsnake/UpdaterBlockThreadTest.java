/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author antoniocoppola
 */
public class UpdaterBlockThreadTest {
        
    @Test
    public void testRunThread(){
        UpdaterThread updaterBlockRunnable = new UpdaterThread(new Snake());
        Thread updaterBlockThread = new Thread(updaterBlockRunnable);
        updaterBlockThread.start();
        assertEquals(updaterBlockRunnable.isStop(), false); //verify is thread is running
        updaterBlockRunnable.stopThread();
    }
    
    @Test
    public void stopTest(){
        UpdaterThread updaterBlockRunnable = new UpdaterThread(new Snake());
        Thread updaterBlockThread = new Thread(updaterBlockRunnable);
        updaterBlockThread.start();
        updaterBlockRunnable.stopThread();
        assertEquals(updaterBlockRunnable.isStop(), true); //verify is thread is stoped
    }
    
    @Test
    public void updateTest(){
        UpdaterThread updaterBlockRunnable = new UpdaterThread(new Snake());
        Thread updaterBlockThread = new Thread(updaterBlockRunnable);
        ElementManager blocksmanager = ElementManager.getInstance();
        Block block = new Block(1, Operation.ADD, 0, 0);
        int old_y = block.getY();
        blocksmanager.addBlock(block);
        updaterBlockThread.start();
        try {
            Thread.sleep(Environment.BLOCKDELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(ConstructorBlockThreadTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int new_y = block.getY();
        assertTrue(new_y != old_y);  //check if the block's y has changed   
        updaterBlockRunnable.stopThread();
    }
}

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
public class DestroyerBlockThreadTest {
    @Test
    public void testRunThread(){
        DestroyerBlockThread destroyerBlockRunnable = new DestroyerBlockThread();
        Thread destroyerBlockThread = new Thread(destroyerBlockRunnable);
        destroyerBlockThread.start();
        assertEquals(destroyerBlockRunnable.isStop(), false); //verify is thread is running
        destroyerBlockRunnable.stopThread();
    }
    
    @Test
    public void stopTest(){
        DestroyerBlockThread destroyerBlockRunnable = new DestroyerBlockThread();
        Thread destroyerBlockThread = new Thread(destroyerBlockRunnable);
        destroyerBlockThread.start();
        destroyerBlockRunnable.stopThread();
        assertEquals(destroyerBlockRunnable.isStop(), true); //verify is thread is stoped
    }
    
    @Test
    public void destroyTest(){
        DestroyerBlockThread destroyerBlockRunnable = new DestroyerBlockThread();
        Thread destroyerBlockThread = new Thread(destroyerBlockRunnable);
        BlocksManager blocksManager = BlocksManager.getInstance();
        Block block1 = new Block(1, Operation.ADD, 0, Environment.JP_HEIGHT + 2);  //creation of blocks that are not visibile on the screen
        Block block2 = new Block(1, Operation.ADD, 50, Environment.JP_HEIGHT + 2);
        blocksManager.addBlock(block1);
        blocksManager.addBlock(block2);
        destroyerBlockThread.start();
        try {
            Thread.sleep(1000);               //Attends one second in order to be sure that thread do the frist blocks elimination
        } catch (InterruptedException ex) {
            Logger.getLogger(ConstructorBlockThreadTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(blocksManager.numBlocks() == 0);  //check if the number of block is equal to zero  
        destroyerBlockRunnable.stopThread();
    }
}

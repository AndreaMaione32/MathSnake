/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author antoniocoppola
 */
public class ConstructorBlockThreadTest {
    
    @Test
    public void testRunThread(){
        ConstructorBlockThread constructorBlockRunnable = new ConstructorBlockThread(new Snake());
        Thread constructorBlockThread = new Thread(constructorBlockRunnable);
        constructorBlockThread.start();
        assertEquals(constructorBlockRunnable.isStop(), false); //verify is thread is running
        constructorBlockRunnable.stopThread();
    }
    
    @Test
    public void stopTest(){
        ConstructorBlockThread constructorBlockRunnable = new ConstructorBlockThread(new Snake());
        Thread constructorBlockThread = new Thread(constructorBlockRunnable);
        constructorBlockThread.start();
        constructorBlockRunnable.stopThread();
        assertEquals(constructorBlockRunnable.isStop(), true); //verify is thread is stoped
    }
    
    @Test
    public void creationTest(){
        ConstructorBlockThread constructorBlockRunnable = new ConstructorBlockThread(new Snake());
        Thread constructorBlockThread = new Thread(constructorBlockRunnable);
        constructorBlockThread.start();
        BlocksManager blocksmanager = BlocksManager.getInstance();
        try {
            Thread.sleep(2*Environment.CREATEBLOCKDELAY);
        } catch (InterruptedException ex) {
            Logger.getLogger(ConstructorBlockThreadTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(blocksmanager.numBlocks());
        assertTrue(blocksmanager.numBlocks() > 1);  //thread must to be created at least two block
        constructorBlockRunnable.stopThread();
    }
}
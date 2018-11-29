/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antoniocoppola
 */
public class BlocksManagerTest {
    
    @Test
    public void testUniqueInstance(){
        BlocksManager bm1 = BlocksManager.getInstance();
        BlocksManager bm2 = BlocksManager.getInstance();
        assertSame(bm1,bm2);    //the instance must to be the same
    }
    
    @Test
    public void addTest(){
    BlocksManager bm = BlocksManager.getInstance();
    Block b = new Block(1, Operation.ADD,0,0);
    bm.addBlock(b);
    assertTrue(bm.numBlocks() >= 1);  //the block must to be equalst to one at least 
    }
    
    @Test
    public void removeTest(){
        BlocksManager bm = BlocksManager.getInstance();
        Block b = new Block(1, Operation.ADD,0,0);
        bm.addBlock(b);
        int old_num_blocks = bm.numBlocks();
        bm.removeBlock(b);
        assertTrue(old_num_blocks > bm.numBlocks()); //the number of blocks must to be deacreases 
    }
    
    @Test
    public void getTestWithIndex(){
        BlocksManager bm = BlocksManager.getInstance();
        Block b = new Block(1, Operation.ADD,0,0);
        bm.addBlock(b);
        Block bGet = bm.getBlock(bm.numBlocks() - 1); //get last block
        assertSame(b, bGet);
    }
    
    @Test
    public void getTestWith(){
        BlocksManager bm = BlocksManager.getInstance();
        Block b = new Block(1, Operation.ADD,0,0);
        bm.addBlock(b);
        Block bGet = bm.getBlock(b);
        assertSame(b, bGet);
    }
}
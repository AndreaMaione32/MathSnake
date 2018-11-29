/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author antoniocoppola
 BlocksManager manages the block list of Math Snake. BlocksManager adds or remove block from
 block list. 
 * The BlocksManager must to be unique, we have one set of list in our game thus we want to have unique BlockManager
 * For this reason we use Singleton Design Pattern
 */
public class BlocksManager {
    private List<Block> blockList;
    private static BlocksManager instance = null;
    
    private BlocksManager(){
        blockList = new ArrayList<Block>();
    }
    
    public static BlocksManager getInstance(){
        synchronized(BlocksManager.class){          //THREAD SAFE
            if(instance==null)
                    instance = new BlocksManager();
                return instance;
            }
    }
    
    public synchronized void addBlock(Block block){  //THREAD SAFE
        blockList.add(block);
    }
    
    public synchronized boolean removeBlock(Block block){  //THREAD SAFE
        return blockList.remove(block);
    }
    
    public synchronized int numBlocks(){           //THREAD SAFE
        return blockList.size();
    }
    
    public synchronized Block getBlock(int i){     //THREAD SAFE
        return blockList.get(i);
    }
    
    public synchronized Block getBlock(Block block){     //THREAD SAFE
        return blockList.get(blockList.indexOf(block));
    }
    
    public synchronized void flush() {
        blockList.clear();
    }
}

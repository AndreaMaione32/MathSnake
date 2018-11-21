/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antoniocoppola
 *  The Runnable, that is called Thread in order to explain better its function, remove blocks that is no more visibile
 * on screen
 */
public class DestroyerBlockThread implements Runnable {
    private  BlocksManager blocksmanager; 
    private boolean stop = false;
    
    public DestroyerBlockThread(){
        super();
        blocksmanager = BlocksManager.getInstance();
    }
    
    @Override
    public void run() {
        while(!stop){
            BlocksManager blocksManager = BlocksManager.getInstance();
            for(int i = 0; i < blocksManager.numBlocks(); i++){                  //for any blocks verify y, in order to verify if the block is visible
                Block b = blocksManager.getBlock(i);
                if(b.getY() > Environment.JP_HEIGHT){
                    blocksManager.removeBlock(b);
                    i--;
                }
            }
            try {
                Thread.sleep(Environment.BLOCKDESTROYDELAY);
            } catch (InterruptedException ex) {
                Logger.getLogger(ConstructorBlockThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void stopThread(){
        stop = true;
    }

    public boolean isStop() {
        return stop;
    }
    
    
}

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
 * The Runnable, that is called Thread in order to explain better its function, updates Block's coordinates in order to move 
 * down the block on the screen
 */
public class UpdaterBlockThread implements Runnable {
    private BlocksManager blocksmanager; 
    private boolean stop = false;
    private boolean pause = false;
    
    private Snake snake;
    
    public UpdaterBlockThread(Snake snake){
        super();
        blocksmanager = BlocksManager.getInstance();
        this.snake = snake;
    }
    
     @Override
    public void run() {
        while(!stop){
            if(!pause) {
                BlocksManager blocksManager = BlocksManager.getInstance();
                for(int i = 0; i < blocksManager.numBlocks(); i++){                  //for any blocks modify y in order to move down block 
                    Block b = blocksManager.getBlock(i);
                    if((snake.getLife()/Environment.LIFEINCREASING) > (Environment.MAXBLOCKSHIFT)){
                        b.setY(b.getY() + (Environment.MAXBLOCKSHIFT + Environment.BLOCKSHIFT));
                    } else {
                        b.setY(b.getY() + Environment.BLOCKSHIFT + (snake.getLife()/Environment.LIFEINCREASING));
                    }
                }
                try {
                    Thread.sleep(Environment.BLOCKDELAY);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConstructorBlockThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public boolean isStop() {
        return stop;
    }
    
    public void start() {
        stop = false;
    }
    
    public void stopThread(){
        stop = true;
    }
    
    public void pause(boolean pause) {
        this.pause = pause;
    }
    
}

package mathsnake;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Runnable, that is called Thread in order to explain better its function, updates Block's coordinates and power ups cordinates in order to move 
 * down them on the screen
 */

public class UpdaterThread implements Runnable {
    private  BlocksManager blocksmanager; 
    private PowerUpsManager powerUpsManager; 
    private boolean stop = false;
    private boolean pause = false;
    
    private Snake snake;
    
    public UpdaterThread(Snake snake){
        super();
        blocksmanager = BlocksManager.getInstance();
        this.powerUpsManager = PowerUpsManager.getInstance();
        this.snake = snake;
    }

    public boolean isStop() {
        return stop;
    }
    
     @Override
    public void run() {
        while(!stop){
            BlocksManager blocksManager = BlocksManager.getInstance();
            for(int i = 0; i < blocksManager.numBlocks(); i++){                  //for any blocks modify y in order to move down block 
                Block b = blocksManager.getBlock(i);
                if((snake.getLife()/Environment.LIFEINCREASING) > (Environment.MAXBLOCKSHIFT)){
                    b.setY(b.getY() + (Environment.MAXBLOCKSHIFT + Environment.BLOCKSHIFT));
                } else {
                    b.setY(b.getY() + Environment.BLOCKSHIFT + (snake.getLife()/Environment.LIFEINCREASING));
                }
            }
            for(int i=0; i<powerUpsManager.powerUpsnums(); i++){
                PowerUps p = powerUpsManager.getPowerUps(i);
                //POWER UPS MUST TO HAVE THE SAME VELOCITY OF BLOCKS
                if((snake.getLife()/Environment.LIFEINCREASING) > (Environment.MAXBLOCKSHIFT)){
                    p.setY(p.getY() + (Environment.MAXBLOCKSHIFT + Environment.BLOCKSHIFT));
                } else {
                    p.setY(p.getY() + Environment.BLOCKSHIFT + (snake.getLife()/Environment.LIFEINCREASING));
                }
            }
            try {
                Thread.sleep(Environment.BLOCKDELAY);
            } catch (InterruptedException ex) {
                Logger.getLogger(ConstructorThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void stopThread(){
        stop = true;
    }

}

package mathsnake;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Runnable, that is called Thread in order to explain better its function, creates the Block
 */

public abstract class ConstructorThread implements Runnable {
    protected  ElementManager elementManager; 
    private boolean stop = false;
    private boolean pause = false;
    protected Board board;
    
    public ConstructorThread(Board board) {
        super();
        this.elementManager = ElementManager.getInstance();
        this.board = board;
    }
    
    @Override
    public void run() {
        while(!stop){
            createBlocks();
            createPowerUps();
            createCoins();
            try {
                if (board.getGameBest() < Environment.getInstance().LIFEINCREASING){
                    Thread.sleep(Environment.getInstance().MINTHREADDELAY);
                }
                else {
                    int actualShift = (board.getGameBest())/Environment.getInstance().LIFEINCREASING;
                    if (actualShift > (Environment.getInstance().MAXINCREMENT)){
                        Thread.sleep(Environment.getInstance().MAXTHREADDELAY);
                    }
                    else {
                        int actualSleep = ((Environment.getInstance().MINTHREADDELAY - Environment.getInstance().MAXTHREADDELAY)/Environment.getInstance().MAXINCREMENT)*actualShift;
                        Thread.sleep(Environment.getInstance().MINTHREADDELAY - actualSleep);
                    }
                }   
            } catch (InterruptedException ex) {
                Logger.getLogger(ConstructorThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    protected abstract void createBlocks();
    
    protected abstract void createPowerUps();
    
    protected abstract void createCoins();
    
    public boolean isStop() {
        return stop;
    }
    
    public void stopThread(){
        stop = true;
    }
   
}

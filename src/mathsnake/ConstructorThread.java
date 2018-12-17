package mathsnake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Runnable, that is called Thread in order to explain better its function, creates the Block
 * The run method of constructorThread creates the elements on the screen and sleep for a certain time that depends on
 * the game best in game.
 * The method that creates the elements are different for the construction during the demo and the contruction during the game.
 * Thus, the run algorithms for the different constructorThreads have the same structure and differ for the constrcutor methods implementation.
 * We use the template method.
 * Create methods are hook methods.
 */

public abstract class ConstructorThread implements Runnable {
    protected  ElementManager elementManager; 
    private boolean stop = false;
    private boolean pause = false;
    protected Board board;
    private Object pauseLock;
    
    public ConstructorThread(Board board, Object pauseLock) {
        super();
        this.elementManager = ElementManager.getInstance();
        this.board = board;
        this.pauseLock = pauseLock;
    }
    
    @Override
    public void run() {
        while(!stop){
            if(!pause){
                List<DownElement> oldList = new ArrayList<> (ElementManager.getInstance().getElementList());
                createBlocks(); //hook method
                createPowerUps(); //hook method
                createCoins(); //hook method
                //element list modified during the pause, restore the state
                if(pause){
                    ElementManager.getInstance().setElementList(oldList);
                }
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
            else{
                synchronized(pauseLock){
                    try {
                        pauseLock.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ConstructorThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
    }
    
    protected abstract void createBlocks();  //hook method
    
    protected abstract void createPowerUps(); //hook method
    
    protected abstract void createCoins(); //hook method
    
    public boolean isStop() {
        return stop;
    }
    
    public void stopThread(){
        stop = true;
    }
    
    public void setPause(boolean pause){
        this.pause = pause;
    }
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antoniocoppola
 * The Runnable, that is called Thread in order to explain better its function, creates the Block
 */
public class ConstructorBlockThread implements Runnable {
    private BlocksManager blocksmanager; 
    private boolean stop = false;
    private boolean pause = false;
    private Snake snake;
    
    public ConstructorBlockThread(Snake snake) {
        super();
        blocksmanager = BlocksManager.getInstance();
        this.snake = snake;
    }
    
    @Override
    public void run() {
        while(!stop){
            if(!pause) {
                int y=5;
                boolean mul = false;
                boolean dea = false;
                for(int i=0; i<7; i++){
                    Random random = new Random();
                    int casuale = random.nextInt(99);
                    if (casuale >= 0 && casuale <= 24){ //25%
                        blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                    }
                    if (casuale >= 25 && casuale <= 49){ //25%
                        blocksmanager.addBlock(new Block(10,Operation.SUB,y+(70*i),-100));
                    }
                    if (casuale >= 50 && casuale <= 74){ //25%
                        blocksmanager.addBlock(new Block(4,Operation.DIV,y+(70*i),-100));
                    }
                    if (casuale >= 75 && casuale <= 79){ //5%
                        if (mul == false){
                            blocksmanager.addBlock(new Block(2,Operation.MUL,y+(70*i),-100));
                            mul = true;
                        } else{
                            int choise = random.nextInt(1);
                            if(choise == 1){
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{
                                blocksmanager.addBlock(new Block(10,Operation.SUB,y+(70*i),-100));
                            }
                        }         
                    }
                    if (casuale >= 80 && casuale <= 99){ //20%
                        if (dea == false){
                            blocksmanager.addBlock(new Block(0,Operation.DEA,y+(70*i),-100));
                            dea = true;
                        } else{
                            int choise = random.nextInt(1);
                            if(choise == 1){
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{
                                blocksmanager.addBlock(new Block(10,Operation.SUB,y+(70*i),-100));
                            }
                        }
                    }
                }
                try {
                    if (snake.getLife() < Environment.LIFEINCREASING){
                        Thread.sleep(Environment.MINTHREADDELAY);
                    } else{
                        int actualShift = (snake.getLife())/Environment.LIFEINCREASING;
                        if (actualShift > (Environment.MAXBLOCKSHIFT)){
                            Thread.sleep(Environment.MAXTHREADDELAY);
                        } else{
                            int actualSleep = ((Environment.MINTHREADDELAY - Environment.MAXTHREADDELAY)/Environment.MAXBLOCKSHIFT)*actualShift;
                            Thread.sleep(Environment.MINTHREADDELAY - actualSleep);
                        }
                    }
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
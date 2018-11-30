/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antoniocoppola
 * The Runnable, that is called Thread in order to explain better its function, creates the Block
 */
public class ConstructorThread implements Runnable {
    private  BlocksManager blocksmanager; 
    private PowerUpsManager powerUpsManager;
    private boolean stop = false;
    private boolean pause = false;
    private int counterPowerUps = 0;  //it's used to create power ups every x blocks creation
    
    private Snake snake;
    
    public ConstructorThread(Snake snake) {
        super();
        this.blocksmanager = BlocksManager.getInstance();
        this.powerUpsManager = PowerUpsManager.getInstance();
        this.snake = snake;
    }
    
    @Override
    public void run() {
        while(!stop){
            int y=5;
            boolean mul = false;
            boolean dea = false;
            createPowerUps();
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
                Logger.getLogger(ConstructorThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        
    }
    
    public void createPowerUps(){
        if(counterPowerUps == 5){   //create power ups every seven block creations
        int rand = (int)(Math.random()*10);
        if(rand > 4){ //powerups is created with probability of 50%
        }
        else{
            if(rand < 2){
                this.powerUpsManager.addPowerUps(new PowerUpsSpeedUp(this.randomX(), -240)); //probability of 20% power ups speed up
            }
            if(rand < 4 && rand >= 2){
                this.powerUpsManager.addPowerUps(new PowerUpsIncreaseLife(this.randomX(), -240)); //probability of 20% power ups increase life
            }
            if(rand == 4){ //probability of 10%
                this.powerUpsManager.addPowerUps(new PowerUpsShield(this.randomX(), -240)); //probability of 20% power ups increase life
            }
        }
        counterPowerUps = 0;
        }
        else
            counterPowerUps ++;
    }

    private int randomX(){
        return (int)(Math.random()*(Environment.JP_WIDTH -Environment.POWERUPS_WIDHT - 10));
    }
    
    public boolean isStop() {
        return stop;
    }
    
    
    public void stopThread(){
        stop = true;
    }
   
}

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
            boolean dea = false;
            boolean positive = false;
            createPowerUps();
            //creazione blocchi
            for(int i=0; i<7; i++){
                Random random = new Random();
                int casuale = random.nextInt(99);
                int life = snake.getLife();
                
                
                if(life <= 299){ //primo livello di difficoltà
                    System.out.println("Primo livello");
                    if (casuale >= 0 && casuale <= 29){ //30% BLOCCO VUOTO 
                        positive = true;
                    }
                    
                    if (casuale >= 30 && casuale <= 59){ //30% BLOCCO ADD
                        int choise = random.nextInt(99);
                        if (choise >= 0 && choise <= 79){ //80%
                            blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                        } 
                        if (choise >= 80){ //20%
                            blocksmanager.addBlock(new Block(20,Operation.ADD,y+(70*i),-100));
                        }
                        positive = true;
                    }
                    
                    if (casuale >= 60 && casuale <= 79){ //20% BLOCCO SUB
                        if (i==6 && positive == false){
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{ //50%
                                blocksmanager.addBlock(new Block(10,Operation.NULL,y+(70*i),-100));
                            }
                        } else{
                        int choise = random.nextInt(99);
                            if (choise >= 0 && choise <= 79){ //80%
                                blocksmanager.addBlock(new Block(10,Operation.SUB,y+(70*i),-100));
                            } 
                            if (choise >= 80){ //20%
                                blocksmanager.addBlock(new Block(20,Operation.SUB,y+(70*i),-100));
                            }
                        }
                    }
                    
                    if (casuale >= 80 && casuale <= 99){ //20% BLOCCO DIV
                        if (i==6 && positive == false){
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{ //50%
                                blocksmanager.addBlock(new Block(10,Operation.NULL,y+(70*i),-100));
                            }
                        } else{
                            blocksmanager.addBlock(new Block(2,Operation.DIV,y+(70*i),-100));
                        }
                    }
                }
                
                
                
                if(life >= 299 && life <= 599){ //secondo livello di difficoltà
                    System.out.println("Secondo livello");
                    if (casuale >= 0 && casuale <= 24){ //25% BLOCCO VUOTO 
                        positive = true;
                    }
                    
                    if (casuale >= 25 && casuale <= 44){ //20% BLOCCO ADD
                        int choise = random.nextInt(99);
                        if (choise >= 0 && choise <= 79){ //80%
                            blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                        } 
                        if (choise >= 80){ //20%
                            blocksmanager.addBlock(new Block(20,Operation.ADD,y+(70*i),-100));
                        }
                        positive = true;
                    }
                    
                    if (casuale >= 45 && casuale <= 64){ //20% BLOCCO SUB
                        if (i==6 && positive == false){
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{ //50%
                                blocksmanager.addBlock(new Block(10,Operation.NULL,y+(70*i),-100));
                            }
                        } else{
                            int choise = random.nextInt(99);
                            if (choise >= 0 && choise <= 79){ //80%
                                blocksmanager.addBlock(new Block(10,Operation.SUB,y+(70*i),-100));
                            } 
                            if (choise >= 80){ //20%
                                blocksmanager.addBlock(new Block(20,Operation.SUB,y+(70*i),-100));
                            }
                        }
                    }
                    
                    if (casuale >= 65 && casuale <= 89){ //25% BLOCCO DIV
                        if (i==6 && positive == false){
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{ //50%
                                blocksmanager.addBlock(new Block(10,Operation.NULL,y+(70*i),-100));
                            }
                        } else{
                            blocksmanager.addBlock(new Block(2,Operation.DIV,y+(70*i),-100));
                        }
                    }
                    
                    if (casuale >= 90 && casuale <= 99){ //10% BLOCCO DEA
                        if (i==6 && positive == false){
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{ //50%
                                blocksmanager.addBlock(new Block(10,Operation.NULL,y+(70*i),-100));
                            }
                        } else{
                            if (dea == false){
                                blocksmanager.addBlock(new Block(0,Operation.DEA,y+(70*i),-100));
                                dea = true;
                            } else{
                                int choise = random.nextInt(1);
                                if(choise == 1){ //50%
                                    blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                                    positive = true;
                                } else{ //50%
                                    blocksmanager.addBlock(new Block(10,Operation.SUB,y+(70*i),-100));
                                }
                            }
                        }
                    }
                }
                
                
                
                if(life >= 600 && life <= 899){ //terzo livello di difficoltà
                    System.out.println("Terzo livello");
                    if (casuale >= 0 && casuale <= 19){ //20% BLOCCO VUOTO 
                        positive = true;
                    }
                    
                    if (casuale >= 20 && casuale <= 39){ //20% BLOCCO ADD
                        int choise = random.nextInt(99);
                        if (choise >= 0 && choise <= 69){ //70%
                            blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                        } 
                        if (choise >= 70){ //30%
                            blocksmanager.addBlock(new Block(20,Operation.ADD,y+(70*i),-100));
                        }
                        positive = true;
                    }
                    
                    if (casuale >= 40 && casuale <= 59){ //20% BLOCCO SUB
                        if (i==6 && positive == false){
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{ //50%
                                blocksmanager.addBlock(new Block(10,Operation.NULL,y+(70*i),-100));
                            }
                        } else{
                            int choise = random.nextInt(99);
                            if (choise >= 0 && choise <= 69){ //70%
                                blocksmanager.addBlock(new Block(10,Operation.SUB,y+(70*i),-100));
                            } 
                            if (choise >= 70){ //30%
                                blocksmanager.addBlock(new Block(20,Operation.SUB,y+(70*i),-100));
                            }
                        }
                    }
                    
                    if (casuale >= 60 && casuale <= 84){ //25% BLOCCO DIV
                        if (i==6 && positive == false){
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{ //50%
                                blocksmanager.addBlock(new Block(10,Operation.NULL,y+(70*i),-100));
                            }
                        } else{
                            int choise = random.nextInt(99);
                            if (choise >= 0 && choise <= 69){ //70%
                                blocksmanager.addBlock(new Block(2,Operation.DIV,y+(70*i),-100));
                            } 
                            if (choise >= 70){ //30%
                                blocksmanager.addBlock(new Block(4,Operation.DIV,y+(70*i),-100));
                            }
                        }
                    }
                    
                    if (casuale >= 85 && casuale <= 99){ //15% BLOCCO DEA
                        if (i==6 && positive == false){
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{ //50%
                                blocksmanager.addBlock(new Block(10,Operation.NULL,y+(70*i),-100));
                            }
                        } else{
                            if (dea == false){
                                blocksmanager.addBlock(new Block(0,Operation.DEA,y+(70*i),-100));
                                dea = true;
                            } else{
                                int choise = random.nextInt(1);
                                if(choise == 1){ //50%
                                    blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                                    positive = true;
                                } else{ //50%
                                    blocksmanager.addBlock(new Block(10,Operation.SUB,y+(70*i),-100));
                                }
                            }
                        }
                    }
                }
                
                
                
                if(life >= 900 && life <= 1199){ //quarto livello di difficoltà
                    System.out.println("Quarto livello");
                    if (casuale >= 0 && casuale <= 14){ // 15% BLOCCO VUOTO 
                        positive = true;
                    }
                    
                    if (casuale >= 15 && casuale <= 29){ //15% BLOCCO ADD
                        int choise = random.nextInt(99);
                        if (choise >= 0 && choise <= 39){ //40%
                            blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                        } 
                        if (choise >= 40 && choise <= 89){ //50%
                            blocksmanager.addBlock(new Block(20,Operation.ADD,y+(70*i),-100));
                        } 
                        if (choise >= 90){ //10%
                            blocksmanager.addBlock(new Block(30,Operation.ADD,y+(70*i),-100));
                        }
                        positive = true;
                    }
                    
                    if (casuale >= 30 && casuale <= 44){ //15% BLOCCO SUB
                        if (i==6 && positive == false){
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{ //50%
                                blocksmanager.addBlock(new Block(10,Operation.NULL,y+(70*i),-100));
                            }
                        } else{
                            int choise = random.nextInt(99);
                            if (choise >= 0 && choise <= 39){ //40%
                                blocksmanager.addBlock(new Block(10,Operation.SUB,y+(70*i),-100));
                            } 
                            if (choise >= 40 && choise <= 89){ //50%
                                blocksmanager.addBlock(new Block(20,Operation.SUB,y+(70*i),-100));
                            } 
                            if (choise >= 90){ //10%
                                blocksmanager.addBlock(new Block(30,Operation.SUB,y+(70*i),-100));
                            }
                        }
                    }
                    
                    if (casuale >= 45 && casuale <= 74){ //30% BLOCCO DIV
                        if (i==6 && positive == false){
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{ //50%
                                blocksmanager.addBlock(new Block(10,Operation.NULL,y+(70*i),-100));
                            }
                        } else{
                            int choise = random.nextInt(99);
                            if (choise >= 0 && choise <= 29){ //30%
                                blocksmanager.addBlock(new Block(4,Operation.DIV,y+(70*i),-100));
                            } 
                            if (choise >= 30 && choise <= 94){ //65%
                                blocksmanager.addBlock(new Block(2,Operation.DIV,y+(70*i),-100));
                            } 
                            if (choise >= 95){ //5%
                                blocksmanager.addBlock(new Block(6,Operation.DIV,y+(70*i),-100));
                            }
                        }
                    }
                    
                    if (casuale >= 75 && casuale <= 99){ //25% BLOCCO DEA
                        if (i==6 && positive == false){
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{ //50%
                                blocksmanager.addBlock(new Block(10,Operation.NULL,y+(70*i),-100));
                            }
                        } else{
                            if (dea == false){
                                blocksmanager.addBlock(new Block(0,Operation.DEA,y+(70*i),-100));
                                dea = true;
                            } else{
                                int choise = random.nextInt(1);
                                if(choise == 1){ //50%
                                    blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                                    positive = true;
                                } else{ //50%
                                    blocksmanager.addBlock(new Block(10,Operation.SUB,y+(70*i),-100));
                                }
                            }
                        }
                    }
                }
                
                
                
                if(life >= 1200){ //quinto livello di difficoltà
                    System.out.println("Quinto livello");
                    if (casuale >= 0 && casuale <= 9){ // 10% BLOCCO VUOTO 
                        positive = true;
                    }
                    
                    if (casuale >= 10 && casuale <= 24){ //15% BLOCCO ADD
                        int choise = random.nextInt(99);
                        if (choise >= 0 && choise <= 29){ //30%
                            blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                        } 
                        if (choise >= 30 && choise <= 74){ //45%
                            blocksmanager.addBlock(new Block(20,Operation.ADD,y+(70*i),-100));
                        } 
                        if (choise >= 75){ //25%
                            blocksmanager.addBlock(new Block(30,Operation.ADD,y+(70*i),-100));
                        }
                        positive = true;
                    }
                    
                    if (casuale >= 25 && casuale <= 39){ //15% BLOCCO SUB
                        if (i==6 && positive == false){
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{ //50%
                                blocksmanager.addBlock(new Block(10,Operation.NULL,y+(70*i),-100));
                            }
                        } else{
                            int choise = random.nextInt(99);
                            if (choise >= 0 && choise <= 29){ //30%
                                blocksmanager.addBlock(new Block(10,Operation.SUB,y+(70*i),-100));
                            } 
                            if (choise >= 30 && choise <= 74){ //45%
                                blocksmanager.addBlock(new Block(20,Operation.SUB,y+(70*i),-100));
                            } 
                            if (choise >= 75){ //25%
                                blocksmanager.addBlock(new Block(30,Operation.SUB,y+(70*i),-100));
                            }
                        }
                    }
                    
                    if (casuale >= 40 && casuale <= 69){ //30% BLOCCO DIV
                        if (i==6 && positive == false){
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{ //50%
                                blocksmanager.addBlock(new Block(10,Operation.NULL,y+(70*i),-100));
                            }
                        } else{
                            int choise = random.nextInt(99);
                            if (choise >= 0 && choise <= 39){ //40%
                                blocksmanager.addBlock(new Block(4,Operation.DIV,y+(70*i),-100));
                            } 
                            if (choise >= 40 && choise <= 79){ //40%
                                blocksmanager.addBlock(new Block(2,Operation.DIV,y+(70*i),-100));
                            }
                            if (choise >= 80 && choise <= 94){ //15%
                                blocksmanager.addBlock(new Block(6,Operation.DIV,y+(70*i),-100));
                            } 
                            if (choise >= 95){ //5%
                                blocksmanager.addBlock(new Block(8,Operation.DIV,y+(70*i),-100));
                            }
                        }
                    }
                    
                    if (casuale >= 70 && casuale <= 99){ //30% BLOCCO DEA
                        if (i==6 && positive == false){
                            int choise = random.nextInt(1);
                            if(choise == 1){ //50%
                                blocksmanager.addBlock(new Block(10,Operation.ADD,y+(70*i),-100));
                            } else{ //50%
                                blocksmanager.addBlock(new Block(10,Operation.NULL,y+(70*i),-100));
                            }
                        } else{
                            blocksmanager.addBlock(new Block(0,Operation.DEA,y+(70*i),-100));
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

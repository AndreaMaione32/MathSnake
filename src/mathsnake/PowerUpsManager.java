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
 PowerUpsManager manages the power ups list of Math Snake. PowerUpsManager adds or remove power ups from
 power ups list. 
 * The PowerUpsManager must to be unique, we have one powerups list in our game thus we want to have unique PowerUpsManager
 * For this reason we use Singleton Design Pattern
 */
public class PowerUpsManager {
    private List<PowerUps> powerUpsList;
    private static PowerUpsManager instance = null;
    
    private PowerUpsManager(){
        this.powerUpsList = new ArrayList<>();
    }
    
    public static PowerUpsManager getInstance(){
        synchronized(BlocksManager.class){          //THREAD SAFE
            if(instance==null)
                    instance = new PowerUpsManager();
                return instance;
            }
    }
    
    public synchronized void addPowerUps(PowerUps powerUps){  //THREAD SAFE
        powerUpsList.add(powerUps);
    }
    
    public synchronized boolean removePowerUps(PowerUps powerUps){  //THREAD SAFE
        return powerUpsList.remove(powerUps);
    }
    
    public synchronized int powerUpsnums(){           //THREAD SAFE
        return powerUpsList.size();
    }
    
    public synchronized PowerUps getPowerUps(int i){     //THREAD SAFE
        return powerUpsList.get(i);
    }
    
    public synchronized PowerUps getPowerUps(PowerUps powerUps){     //THREAD SAFE
        return powerUpsList.get(powerUpsList.indexOf(powerUps));
    }
    
    public synchronized void flush(){
        powerUpsList.clear();
    }
}

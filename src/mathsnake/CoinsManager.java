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
 */
public class CoinsManager {
    private List<Coin> coinsList;
    private static CoinsManager instance = null;
    
    private CoinsManager(){
        coinsList = new ArrayList<Coin>();
    }
    
    public static CoinsManager getInstance(){
        synchronized(CoinsManager.class){          //THREAD SAFE
            if(instance==null)
                    instance = new CoinsManager();
                return instance;
            }
    }
    
    public synchronized void addCoins(Coin coin){  //THREAD SAFE
        coinsList.add(coin);
    }
    
    public synchronized boolean removeCoin(Coin coin){  //THREAD SAFE
        return coinsList.remove(coin);
    }
    
    public synchronized int numCoins(){           //THREAD SAFE
        return coinsList.size();
    }
    
    public synchronized Coin getCoin(int i){     //THREAD SAFE
        return coinsList.get(i);
    }
    
    public synchronized Coin getCoin(Coin coin){     //THREAD SAFE
        return coinsList.get(coinsList.indexOf(coin));
    }
    
    public synchronized void flush(){
        coinsList.clear();
    }
}

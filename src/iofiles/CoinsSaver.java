package iofiles;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import environment.Environment;

public class CoinsSaver {
    
    private int currentCoins;
    
    public CoinsSaver() {
        try {
            currentCoins = readCoins();
        } catch (IOException ex) {
            Logger.getLogger(CoinsSaver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getCurrentCoins() {
        return currentCoins;
    }
    
    public void setCurrentCoins(int coins) {
        currentCoins = coins;
    }
    
    public void saveCoins() {
        try {
            writeCoins(currentCoins);
        } catch (IOException ex) {
            Logger.getLogger(CoinsSaver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int readCoins() throws FileNotFoundException, IOException {
        File file = new File(Environment.getInstance().UTILITY_FILES_PATH + "coins.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(CoinsSaver.class.getName()).log(Level.SEVERE, null, ex);
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write("0");
            }
            return 0;
        }
        int coins;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            coins = Integer.parseInt(br.readLine());
        }
        return coins;
    }
    
    private void writeCoins(int coins) throws IOException {
        File file = new File(Environment.getInstance().UTILITY_FILES_PATH + "coins.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(CoinsSaver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(Integer.toString(coins));
        }
    }
    
}
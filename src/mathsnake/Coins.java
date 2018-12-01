package mathsnake;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Coins {
    
    private static Coins instance = null;
    private int currentCoins;
    
    public synchronized static Coins getInstance() {
        if(instance == null)
            instance = new Coins();
        return instance;
    }
    
    private Coins() {
        try {
            currentCoins = readCoins();
        } catch (IOException ex) {
            Logger.getLogger(Coins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized int getCurrentCoins() {
        return currentCoins;
    }
    
    public synchronized void setCurrentCoins(int coins) {
        currentCoins = coins;
        try {
            writeCoins(coins);
        } catch (IOException ex) {
            Logger.getLogger(Coins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int readCoins() throws FileNotFoundException, IOException {
        File file = new File("coins.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Coins.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("0");
            bw.close();
            return 0;
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        int coins = Integer.parseInt(br.readLine());
        br.close();
        return coins;
    }
    
    private void writeCoins(int coins) throws IOException {
        File file = new File("coins.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Coins.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(Integer.toString(coins));
        bw.close();
    }
    
}

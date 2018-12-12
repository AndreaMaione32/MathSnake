package mathsnake;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoinsSaver {
    
    private int currentCoins;
    private File file;
    
    public CoinsSaver() {
        file = new File("coins.txt");
    }
    
    public int getCurrentCoins() {
        return currentCoins;
    }
    
    public void setCurrentCoins(int coins) {
        currentCoins = coins;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    public void readCoins() {
        try {
            currentCoins = read();
        } catch (IOException ex) {
            Logger.getLogger(CoinsSaver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveCoins() {
        try {
            writeCoins(currentCoins);
        } catch (IOException ex) {
            Logger.getLogger(CoinsSaver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int read() throws FileNotFoundException, IOException {
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

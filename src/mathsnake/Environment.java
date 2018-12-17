package mathsnake;

import java.awt.Color;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Environment {
    private static Environment instance = null;
    
    public final int JP_WIDTH = 500; //larghezza del pannello 
    public final int JP_HEIGHT = 500; //altezza del pannello 
    public final int DOT_SIZE = 18; //altezza e larghezza di ogni dot di cui è composto lo snake
    public final int DELAY = 15; // determina la velocità di animazione
    public final int BLOCKDELAY = 70; //determina la velcoità di aggiornamento dei blocchi
    public final int MAXINCREMENT = 15; //numero massimo di incrementi alla velocità iniziale dei blocchi
    public final int MAXVELOCITYSHIFT = 450; //massimo aumento dala down velocity iniziale
    public final double STARTDOWNSPEED = 250;
    public final int MINTHREADDELAY = 1410; //ritardo del thread nella situazione iniziale
    public final int MAXTHREADDELAY = 660; //ritardo del thread nella situazione di massima velocità
    public final int LIFEINCREASING = 100; //la velocità aumenta quando la vita aumenta di questo valore (ad esempio ogni 100 di vita in più un aumento di velocità)
    public int STARTLIFEPOINTS = 10;
    public final int BLOCKSHIFT = 10; //determina lo shift dei blocchi
    public final String PATHIMAGES = "../MathSnake/images/";
    public final int BLOCK_HEIGHT = 70;
    public final int BLOCK_WIDTH = 70;
    public final int POWERUPS_WIDTH = 30;
    public final int POWERUPS_HEIGHT = 39;
    public final int SPEED_UP_DURATION = 7000;
    public final int SHIELD_DURATION = 7000;
    public final int COIN_WIDTH = 35;
    public final int COIN_HEIGHT = 35;
    public String PATHBACKGROUND = "../MathSnake/images/base_background.png"; //Background di default
    public String PATHSKIN = "../MathSnake/images/dot.png"; //Skin di default
    public Map<String, Boolean> BOUGHT_FEATURES = new HashMap();
    public final int DOT_NUM = 7; //numero di dot di cui è composto lo snake
    public final int SCOREBOARD_SIZE = 5;
    public final String UTILITY_FILES_PATH = "utility_files/";
    public Color WRITECOLOR;
    
    private Environment(){
        BOUGHT_FEATURES.put("base_background.png", true);
        BOUGHT_FEATURES.put("cloud_background.png", false);
        BOUGHT_FEATURES.put("dirt_background.png", false);
        BOUGHT_FEATURES.put("dot.png", true);
        BOUGHT_FEATURES.put("black_dot.png", false);
        BOUGHT_FEATURES.put("red_dot.png", false);
        try {
            readGraphicConfiguration();
        } catch (IOException ex) {
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Environment getInstance(){
        if(instance == null){
            instance = new Environment();
        }
        return instance;
    }
    
    private void readGraphicConfiguration() throws IOException {
        File f = new File(this.UTILITY_FILES_PATH+"graphic_configuration.txt");
        if(!f.exists()) {
            f.createNewFile();
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
                bw.write("base_background.png", 0, "base_background.png".length());
                bw.newLine();
                bw.write(Integer.toString(Color.black.getRGB()));
                WRITECOLOR = Color.black;
                bw.newLine();
                bw.write("dot.png", 0, "dot.png".length());
            }
        }
        else {
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                PATHBACKGROUND = PATHIMAGES + br.readLine();
                WRITECOLOR = new Color(Integer.parseInt(br.readLine()));
                PATHSKIN = PATHIMAGES + br.readLine();
            }
        }
        
        f = new File(this.UTILITY_FILES_PATH+"bought_features.txt");
        if(!f.exists()) {
            f.createNewFile();
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(f));
            writer.writeObject(BOUGHT_FEATURES);
        }
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream(f));
        try {
            BOUGHT_FEATURES = (HashMap) reader.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writeGraphicConfiguration() throws IOException {
        String background = PATHBACKGROUND.split("/")[3];
        String skin = PATHSKIN.split("/")[3];
        File f = new File(this.UTILITY_FILES_PATH+"graphic_configuration.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, false))) {
            bw.write(background, 0, background.length());
            bw.newLine();
            bw.write(Integer.toString(WRITECOLOR.getRGB()));
            bw.newLine();
            bw.write(skin, 0, skin.length());
        }
    }
    
    public void writeBoughtFeatures() throws FileNotFoundException, IOException {
        File f = new File(this.UTILITY_FILES_PATH+"bought_features.txt");
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(f));
        writer.writeObject(BOUGHT_FEATURES);
    }
}

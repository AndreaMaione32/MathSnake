package environment;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import iofiles.GraphicConfiguration;

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
    public String PATHBACKGROUND = PATHIMAGES + "base_background.png"; //Background di default
    public String PATHSKIN = PATHIMAGES + "dot.png"; //Skin di default
    public Map<String, Boolean> BOUGHT_FEATURES;
    public final int DOT_NUM = 7; //numero di dot di cui è composto lo snake
    public final int SCOREBOARD_SIZE = 5;
    public final String UTILITY_FILES_PATH = "../MathSnake/utility_files/";
    public Color WRITECOLOR;
    public GraphicConfiguration gc;
    
    private Environment() {
        BOUGHT_FEATURES = new HashMap();
        BOUGHT_FEATURES.put("base_background.png", true);
        BOUGHT_FEATURES.put("cloud_background.png", false);
        BOUGHT_FEATURES.put("dirt_background.png", false);
        BOUGHT_FEATURES.put("dot.png", true);
        BOUGHT_FEATURES.put("black_dot.png", false);
        BOUGHT_FEATURES.put("red_dot.png", false);
    }
    
    public static Environment getInstance(){
        if(instance == null){
            instance = new Environment();
            instance.gc = new GraphicConfiguration();
        }
        return instance;
    }
    
}

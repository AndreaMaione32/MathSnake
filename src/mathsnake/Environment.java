/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

/**
 *
 * @author antonino
 */
public interface Environment {
    public final int JP_WIDTH = 500; //larghezza del pannello 
    public final int JP_HEIGHT = 500; //altezza del pannello 
    public final int DOT_SIZE = 10; //altezza e larghezza di ogni dot di cui è composto lo snake
    public final int DELAY = 50; // determina la velocità di animazione
    public final int BLOCKDELAY = 70; //determina la velcoità di aggiornamento dei blocchi
    public final int MAXBLOCKSHIFT = 15; //massimo aumento allo shift iniziale
    public final int MINTHREADDELAY = 1850; //ritardo del thread nella situazione iniziale
    public final int MAXTHREADDELAY = 800; //ritardo del thread nella situazione di massima velocità
    public final int LIFEINCREASING = 200; //la velocità aumenta quando la vita aumenta di questo valore (ad esempio ogni 100 di vita in più un aumento di velocità)
    public final int STARTLIFEPOINTS = 10;
    public final int CREATEBLOCKDELAY = 4000; //determina la velocità di creazione dei blocchi
    public final int BLOCKSHIFT = 10; //determina lo shift dei blocchi
    public final String PATHIMAGES = "../MathSnake/images/";
    public final int BLOCK_HEIGHT = 70;
    public final int BLOCK_WIDTH = 70;
    public final int BLOCKDESTROYDELAY = 10000; //determina la velocità di distruzione dei blocchi
    public final int POWERUPS_WIDHT = 30;
    public final int POWERUPS_HEIGHT = 39;
}

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
    public final int JP_WIDTH = 500;//dimensione del pannello 
    public final int JP_HEIGHT = 500;//dimensione del pannello 
    public final int DOT_SIZE = 10;//dimensione massima delle palline del serpente dipende dai pixel dell'immagine
    public final int MAX_DOTS = (JP_WIDTH*JP_HEIGHT)/(DOT_SIZE*DOT_SIZE);// massimo numero di punti vita del serpente = (500*500)/(10*10)
    public final int DELAY = 100;// determina la velocità di animazione
}

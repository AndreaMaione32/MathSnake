/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Snake {
    private int x; // La coordinata x dello snake è univoca per tutti i dot che lo costituiscono ed può assumere valori compresti fra 0 e JP_WIDTH
    private int[] y; // Ogni dot che costituisce lo snake ha coordinata y differente e fissata in quanto lo snake ha altezza fissa
    
    private final int dots; // Numero di dots che costituiscono lo snake
    private boolean leftDirection = false;
    private boolean rightDirection = false;
    
    // Costruttore con paramtetro di deafult di dots
    public Snake() {
        dots = 10;
        
        int snakeStartPoint = Environment.JP_WIDTH / 2; // Lo snake viene creato al centro della finestra
        x = snakeStartPoint;
        
        y = new int[dots]; // Il vettore delle coordinate y viene settatto a dimensione uguale al valore dots di default (a ogni elemento corrisponde una coordinata di un dot)
        for (int z = 0; z < dots; z++) {
            y[z] = Environment.JP_HEIGHT - (z * Environment.DOT_SIZE); // Poichè la dimensione di ogni dot è DOT_SIZE px la differenza fra due coordinate adiacenti nel vettore è pari a 10, il primo elemento del vettore è il primo dot della coda, mentre l'ultimo è la testa dello snake; il primo dot ha y = JP_WIDTH, il secondo JP_WIDTH - 10 e così via 
        }
    }
    
    // Costruttore con parametro opzionale di dots
    public Snake(int dots) {
        this.dots = dots;
        
        int snakeStartPoint = Environment.JP_WIDTH / 2; // Lo snake viene creato al centro della finestra
        x = snakeStartPoint;
        
        y = new int[dots]; // Il vettore delle coordinate y viene settatto a dimensione uguale al valore dots opzionale (a ogni elemento corrisponde una coordinata di un dot)
        for (int z = 0; z < dots; z++) {
            y[z] = 500 - (z * 10); // Poichè la dimensione di ogni dot è di 10 px la differenza fra due coordinate adiacenti nel vettore è pari a 10, il primo elemento del vettore è il primo dot della coda, mentre l'ultimo è la testa dello snake; il primo dot ha y = JP_WIDTH, il secondo JP_WIDTH - 10 e così via 
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public int[] getY() {
        return y;
    }

    public int getDots() {
        return dots;
    }

    public boolean isMovingLeft() {
        return leftDirection;
    }

    public void setLeftDirection(boolean leftDirection) {
        this.leftDirection = leftDirection;
    }

    public boolean isMovingRight() {
        return rightDirection;
    }

    public void setRightDirection(boolean rightDirection) {
        this.rightDirection = rightDirection;
    }
    
    public Image loadImage(String PATH) {
        ImageIcon iid = new ImageIcon(PATH);
        Image icon = iid.getImage();
        return icon;
    }
    
    public void move() throws InterruptedException {
        int shift = Environment.DOT_SIZE + 10;
        if (leftDirection) {
            if (x - shift < 0)
                x = 0;
            else
                x -= shift;
            leftDirection = false;
        }
        if (rightDirection) {
            if (x + shift >= Environment.JP_WIDTH)
                x = Environment.JP_WIDTH - 10; 
            else    
                x += shift;
            rightDirection = false;
        }
    }
}



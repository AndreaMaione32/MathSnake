/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Snake {
    private int x; // La coordinata x dello snake è univoca per tutti i dot che lo costituiscono ed può assumere valori compresti fra 0 e JP_WIDTH
    private int[] y; // Ogni dot che costituisce lo snake ha coordinata y differente e fissata in quanto lo snake ha altezza fissa
    
    private final int dots; // Numero di dots che costituiscono lo snake
    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private Rectangle rectangle;   //Rect associated to Snake's Head, it's used to mange collision
    private int lifepoints; 
    
    // Costruttore con paramtetro di deafult di dots
    public Snake() {
        dots = 10;
        
        int snakeStartPoint = Environment.JP_WIDTH / 2; // Lo snake viene creato al centro della finestra
        x = snakeStartPoint;
        
        lifepoints = Environment.STARTLIFEPOINTS;
        
        y = new int[dots]; // Il vettore delle coordinate y viene settatto a dimensione uguale al valore dots di default (a ogni elemento corrisponde una coordinata di un dot)
        for (int z = 0; z < dots; z++) {
            y[z] = Environment.JP_HEIGHT - (z * Environment.DOT_SIZE); // Poichè la dimensione di ogni dot è DOT_SIZE px la differenza fra due coordinate adiacenti nel vettore è pari a 10, il primo elemento del vettore è il primo dot della coda, mentre l'ultimo è la testa dello snake; il primo dot ha y = JP_WIDTH, il secondo JP_WIDTH - 10 e così via
            this.rectangle = new Rectangle(x, y[dots-1], Environment.DOT_SIZE, Environment.DOT_SIZE);  //coordinates are the coordinates of the head
        }
    }

    public Rectangle getAssociatedRectangle() {
        return rectangle;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        rectangle.setLocation(x, this.y[dots-1]); //Move also Rectangle associated
    }
    
    public void setLife(int lifepoints){
        this.lifepoints = lifepoints;
    }
    
    public int[] getY() {
        return y;
    }

    public int getDots() {
        return dots;
    }
    
    public int getLife() {
        return lifepoints;
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
    
     public boolean collide(Rectangle rect){        //return true if rect intersect snake's head associated rectangle
        return this.rectangle.intersects(rect);
    }
    
    public void move() throws InterruptedException {
        int shift = Environment.DOT_SIZE + 15;
        if (leftDirection) {
            if (x - shift < 0)
                this.setX(0);
            else
                this.setX(this.getX() - shift);
            leftDirection = false;
        }
        if (rightDirection) {
            if (x + shift >= Environment.JP_WIDTH)
                this.setX(Environment.JP_WIDTH - 10);
            else  
                this.setX(this.getX() + shift);
            rightDirection = false;
        }
    }
}



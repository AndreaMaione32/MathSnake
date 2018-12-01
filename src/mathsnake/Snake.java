/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Snake{
    private final int x[] = new int[Environment.DOT_NUM]; // La coordinata x dello snake è univoca per tutti i dot che lo costituiscono ed può assumere valori compresti fra 0 e JP_WIDTH
    private final int y[] = new int[Environment.DOT_NUM]; // Ogni dot che costituisce lo snake ha coordinata y differente e fissata in quanto lo snake ha altezza fissa
    private boolean leftDirection = false;
    private boolean rightDirection = false;
    private final Rectangle rectangle;   //Rect associated to Snake's Head, it's used to mange collision
    private int lifepoints; 
    
    // Costruttore con paramtetro di deafult di dots
    public Snake() {
        this.lifepoints = Environment.STARTLIFEPOINTS;
        for (byte i=0; i<Environment.DOT_NUM; i++) {
            x[i] = Environment.JP_WIDTH / 2; // Lo snake viene creato al centro della finestra
        }
        for (byte i=0; i<Environment.DOT_NUM; i++) {
            y[i] = Environment.JP_HEIGHT - (i * Environment.DOT_SIZE); // Poichè la dimensione di ogni dot è DOT_SIZE px la differenza fra due coordinate adiacenti nel vettore è pari a 10, il primo elemento del vettore è il primo dot della coda, mentre l'ultimo è la testa dello snake; il primo dot ha y = JP_WIDTH, il secondo JP_WIDTH - 10 e così via
        }
        this.rectangle = new Rectangle(x[Environment.DOT_NUM - 1], y[Environment.DOT_NUM - 1], Environment.DOT_SIZE, Environment.DOT_SIZE);  //coordinates are the coordinates of the head
    }

    public Rectangle getAssociatedRectangle() {
        return rectangle;
    }

    public int[] getX() {
        return x;
    }
    
    public int[] getY() {
        return y;
    }
    
    public int getLife() {
        return lifepoints;
    }
    
    public boolean isMovingLeft() {
        return leftDirection;
    }
    
    public boolean isMovingRight() {
        return rightDirection;
    }
    
    public void setLife(int lifepoints){
        this.lifepoints = lifepoints;
    }
    
    public void setLeftDirection(boolean leftDirection) {
        this.leftDirection = leftDirection;
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
    
    public void move(){
        int shift = 1; //Environment.DOT_SIZE + 15;
        for (byte i=0; i<Environment.DOT_NUM - 1; i++){
            x[i] = x[i+1];
        }
        if (leftDirection) {
            if (x[Environment.DOT_NUM - 1] - shift < 0)
                x[Environment.DOT_NUM - 1] = 0;
            else
                x[Environment.DOT_NUM - 1] -= shift;
            leftDirection = false;
            rectangle.setLocation(x[Environment.DOT_NUM - 1], y[Environment.DOT_NUM - 1]);
        }
        if (rightDirection) {
            if (x[Environment.DOT_NUM - 1] + shift >= Environment.JP_WIDTH)
                x[Environment.DOT_NUM - 1] = Environment.JP_WIDTH - 10;
            else
                x[Environment.DOT_NUM - 1] += shift;
            rightDirection = false;
            rectangle.setLocation(x[Environment.DOT_NUM - 1], y[Environment.DOT_NUM - 1]);
        }
    }
}
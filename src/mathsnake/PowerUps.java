/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author antoniocoppola
 */
public abstract class PowerUps {
    private int x;
    private int y;
    private Rectangle rectangle;
    
     public PowerUps(int x, int y) {
        this.x = x;
        this.y = y;
        this.rectangle = new Rectangle(x,y,Environment.POWERUPS_WIDHT, Environment.POWERUPS_HEIGHT);
    }

    public void setX(int x) {
        this.x = x;
        rectangle.setLocation(x, this.y);   //Move also Rectangle assoicated
    }

    public void setY(int y) {
        this.y = y;
        rectangle.setLocation(this.x, y);  //Move also Rectangle assoicated
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getAssociatedRectangle() {
        return rectangle;
    } 
    
    public abstract void action(Snake snake);    //perform an action on the snake when snake collide power ups
    
    public abstract void drawPowerUps(Graphics g);
}

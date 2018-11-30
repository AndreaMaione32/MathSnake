/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author antoniocoppola
 */
public abstract class PowerUps {
    private int x;
    private int y;
    private Rectangle rectangle;
    protected String nameimg;
    
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
    
    protected Image loadImage(String PATH) {
        ImageIcon iid = new ImageIcon(PATH);
        Image icon = iid.getImage();
        return icon;
    }
    
    public void drawPowerUps(Graphics g) {
        Image img = this.loadImage(Environment.PATHIMAGES+this.nameimg);
        g.drawImage(img, this.getX(), this.getY(), null);
    }
    
    public abstract void action(Snake snake);
}

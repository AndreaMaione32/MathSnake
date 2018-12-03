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
public class Coin {
    private Image img;
    private double x;
    private double y;
    private Rectangle rectangle;
    
    public Coin(int x, int y){
        this.x = x;
        this.y = y;
        this.img = loadImage(Environment.PATHIMAGES+"retro_coins.png");
        this.rectangle = new Rectangle((int)x,(int)y,Environment.COIN_WIDTH, Environment.COIN_HEIGHT);
    }
    
    protected Image loadImage(String PATH) {
        ImageIcon iid = new ImageIcon(PATH);
        Image icon = iid.getImage();
        return icon;
    }
    
    private void setX(double x) {
        this.x = x;
        rectangle.setLocation((int)x, (int)this.y);   //Move also Rectangle assoicated
    }

    private void setY(double y) {
        this.y = y;
        rectangle.setLocation((int)this.x, (int)y);  //Move also Rectangle assoicated
    }
    
    public void move(double velocity){
        double shift = (Environment.DELAY * velocity) / 1000; 
        this.setY(y + shift);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Rectangle getAssociatedRectangle() {
        return rectangle;
    } 
    
    public void drawCoin(Graphics g){
      g.drawImage(img, (int)this.getX(), (int)this.getY(), null);  
    }
}

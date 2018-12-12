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
 * This class represents any element that moves down on the screen and any element that can be smashed by the snake.
 * We have different class of elements on the screen and for each class the action associated to an collision with an element change from class
 * to class. 
 * We use Strategy Design Pattern.
 * For any concrete implemention of the method that defines the action associated to an collision it's defined a sub class of DownElement.
 * 
 */
public abstract class DownElement {
    private final Image img;
    protected double x;
    protected double y;
    private final Rectangle rectangle;
    
    public DownElement(int x, int y, String pathImage){
        this.x = x;
        this.y = y;
        this.img = this.loadImage(pathImage);
        this.rectangle = new Rectangle((int) x, (int) y, this.img.getWidth(null), this.img.getHeight(null));
    }
    
    protected Image loadImage(String PATH) {
        ImageIcon iid = new ImageIcon(PATH);
        Image icon = iid.getImage();
        return icon;
    }
    
    private void setX(double x) {
        this.x = x;
        rectangle.setLocation((int) x, (int) this.y);   //Move also Rectangle assoicated
    }

    private void setY(double y) {
        this.y = y;
        rectangle.setLocation((int) this.x, (int) y);  //Move also Rectangle assoicated
    }
    
    public void move(double velocity){
        double shift = (Environment.getInstance().DELAY * velocity) / 1000; 
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
    
    public void draw(Graphics g){
      g.drawImage(img, (int) this.getX(), (int) this.getY(), null);  
    }
    
    public abstract void collisionAction(SnakeBoard snakeBoard); //this method define the action linked to a collision with the element. 
}

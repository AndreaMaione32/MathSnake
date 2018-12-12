/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author antoniocoppola
 */
public class Background {
    private Image img;
    private double x;
    private double y;
    
    public Background(String pathImg) {
         this.img = this.loadImage(pathImg);
         this.x = 0;
         this.y = -this.img.getHeight(null)+Environment.getInstance().JP_HEIGHT;
    }
    
    protected Image loadImage(String PATH) {
        ImageIcon iid = new ImageIcon(PATH);
        Image icon = iid.getImage();
        return icon;
    }
    
    public void move(double velocity) {
        double shift = (Environment.getInstance().DELAY * velocity) / 1000;
        if(y + shift >= 0){
            this.y = Environment.getInstance().JP_HEIGHT - this.img.getHeight(null);
        }
        else
            this.y += shift;
    }
    
    public void drawBackground(Graphics g) {
        g.drawImage(img, (int) x, (int)y, null);  
    }
}

package mathsnake;


import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author antoniocoppola
 */
public class PowerUpsIncreaseLife extends PowerUps{
   
    private final String nameimg = "heart.png";
    
    public PowerUpsIncreaseLife(int x, int y){
        super(x, y);
    }

    @Override
    public void action(Snake snake) {
        snake.setLife(snake.getLife() + 30);  //increases snake's life
    }

    @Override
    public void drawPowerUps(Graphics g) {
        Image img = this.loadImage(Environment.PATHIMAGES+this.nameimg);
        g.drawImage(img, this.getX(), this.getY(), null);
    }
    
    private Image loadImage(String PATH) {
        ImageIcon iid = new ImageIcon(PATH);
        Image icon = iid.getImage();
        return icon;
    }
}

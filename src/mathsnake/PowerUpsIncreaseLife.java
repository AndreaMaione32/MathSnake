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
   
    
    public PowerUpsIncreaseLife(int x, int y){
        super(x, y);
        super.nameimg = "heart.png";
    }

    @Override
    public void action(Snake snake) {
        snake.setLife(snake.getLife() + 100);  //increases snake's life
    }
}

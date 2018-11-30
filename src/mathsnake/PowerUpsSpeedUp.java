/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author antoniocoppola
 */
public class PowerUpsSpeedUp extends PowerUps{

    public PowerUpsSpeedUp(int x, int y) {
        super(x, y);
        super.nameimg = "speed_up.png";
    }

    @Override
    public void action(Snake snake) {
        snake.speed_up();
    }
}

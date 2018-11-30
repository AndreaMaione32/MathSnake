/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

/**
 *
 * @author antoniocoppola
 */
public class PowerUpsShield extends PowerUps{

    public PowerUpsShield(int x, int y) {
        super(x, y);
        super.nameimg = "shield.png";
    }

    @Override
    public void action(Snake snake) {
        snake.shield();
    }
    
}

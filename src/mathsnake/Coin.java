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
public class Coin extends DownElement{
    private Animation animation;
    
    public Coin(int x, int y){
        super(x,y,Environment.getInstance().PATHIMAGES+"coin_animation/coin_animation_1.png" );
        this.animation = new Animation(Environment.getInstance().PATHIMAGES+"coin_animation/","coin_animation", 7, 1, 6);
    }

    @Override
    public void collisionAction(Board board){
        CoinsSaver coinsSaver = board.getCoinsSaver();
        coinsSaver.setCurrentCoins(coinsSaver.getCurrentCoins()+1);
    }
    
    @Override
    public void draw(Graphics g){
        animation.update();
        super.img = animation.getImage();
        super.draw(g);
    }
    
}

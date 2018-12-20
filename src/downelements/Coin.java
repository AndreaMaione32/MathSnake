/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downelements;

import java.awt.Graphics;
import animation.Animation;
import environment.Environment;
import iofiles.CoinsSaver;
import panels.snakeboards.Board;

public class Coin extends DownElement{
    private Animation animation;
    
    public Coin(int x, int y){
        super(x,y,"coin_animation_1" );
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

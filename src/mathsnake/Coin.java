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
    
    public Coin(int x, int y){
        super(x,y,Environment.getInstance().PATHIMAGES+"retro_coins.png" );
    }

    @Override
    public void collisionAction(Board board){
        CoinsSaver coinsSaver = board.getCoinsSaver();
        coinsSaver.setCurrentCoins(coinsSaver.getCurrentCoins()+1);
    }
}

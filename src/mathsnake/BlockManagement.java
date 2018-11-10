/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author antoniocoppola
 BlockManagement class manage blocks game
 */
public class BlockManagement{
   private static int WIDTH = 100;
   private static int HEIGHT = 200;
   public static void printBlock(Block block, int x, int y,Graphics g){ //print block given x, y coordinate and graphics object, associeted to an JPanel
       g.setColor(block.getColor());
       g.fillRect(x, y, WIDTH, HEIGHT);
       g.setColor(Color.WHITE);
       g.drawString(block.getStrOp()+Integer.toString(block.getValue()), (x+WIDTH)/2, (y+HEIGHT)/2);
   }
   
   //TEST
public static void main(){
    
}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.Color;
import java.awt.Rectangle;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author antoniocoppola
 */
public class BlockTest {
    
    @Test
    public void setXTest(){
        Block b = new Block(1, Operation.ADD, 0, 0);
        b.setX(20);
        assertEquals(b.getX(), 20);   //check if x value is expected one
    }
    
    @Test
    public void setYTest(){
        Block b = new Block(1, Operation.ADD, 0, 0);
        b.setY(20);
        assertEquals(b.getY(), 20);   //check if Y value is expected one
    }
    
    @Test
    public void differentColorTest(){
        Block b1 = new Block(1, Operation.ADD, 0, 0);
        Block b2 = new Block(2, Operation.MUL, 0, 0);
        assertTrue(b1.getColor() != b2.getColor());   //blocks with different operation must to have different colors
    }
    
    @Test
    public void ColorTest(){
        Block b = new Block(1, Operation.ADD, 0, 0);
        assertEquals(b.getColor(), Color.ORANGE);  //ADD Block must to be orange
    }
    
    //TEST IF BLOCK, in (x,y) point, COLLIDES WITH RECTANGLE THAT IS IN ONE OF THE EXTREME POINT x, y OR x+BLOCKWIDTH and y+BLOCKHEIGHT
    
    @Test
    public void fristExtremeCollide(){
    Block b = new Block(1, Operation.ADD, 0, 0);
    Rectangle rect = new Rectangle(0,0, 10, 10);
    assertTrue(b.collide(rect));
    }
    
    @Test
    public void secondExtremeCollide(){
    Block b = new Block(1, Operation.ADD, 0, 0);
    Rectangle rect = new Rectangle(0 + Environment.BLOCK_WIDTH -10,0 + Environment.BLOCK_HEIGHT-10, 10, 10);
    assertTrue(b.collide(rect));
    }
}

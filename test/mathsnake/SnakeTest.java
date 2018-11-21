/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import mathsnake.Snake;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andreamaione
 */
public class SnakeTest {
    /**
     * Test of move method, of class Snake.
     */
    @Test
    public void testMove() throws Exception {
        Snake instance = new Snake();
        instance.setLeftDirection(true);
        instance.move();
        assertEquals(instance.getX(), 230);
        instance.setRightDirection(true);
        instance.move();
        assertEquals(instance.getX(), 250);
    }
    
    /**
     * Test of snake's life point modification.
     */
    @Test
    public void testSnakeLifePoints(){
        Snake snake = new Snake();
        snake.setLife(10);
        assertEquals(snake.getLife(), 10);
    }
    
    /**
     * Test of snake's collision.
     */
    @Test
    public void snakeCollide(){
        Snake snake = new Snake();
        Rectangle rect = new Rectangle(snake.getX() -5 , snake.getY()[snake.getDots() - 1] -5 , 10, 10);  //rectangle in (x-5, y-5) with (x,y) snake's head coordinate
        assertTrue(snake.collide(rect));
    }
}

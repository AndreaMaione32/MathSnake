/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.Rectangle;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andreamaione
 */
public class SnakeTest {
    /**
     * Test of move method, of class Snake.
     * @throws java.lang.Exception
     */
    @Test
    public void testMove() throws Exception {
        Snake instance = new Snake();
        int oldX[] = instance.getX();
        instance.setLeftDirection(true);
        instance.move();
        assertFalse(instance.getX() == oldX);
        oldX = instance.getX();
        instance.setRightDirection(true);
        instance.move();
        assertFalse(instance.getX() == oldX);
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
        Rectangle rect = new Rectangle(snake.getX()[Environment.DOT_NUM - 1]-5 , snake.getY()[Environment.DOT_NUM - 1]-5 , Environment.DOT_SIZE, Environment.DOT_SIZE);  //rectangle in (x-5, y-5) with (x,y) snake's head coordinate
        assertTrue(snake.collide(rect));
    }
}

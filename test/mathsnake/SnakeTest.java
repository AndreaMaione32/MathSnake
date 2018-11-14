/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.Image;
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
     * Test of loadImage method, of class Snake.
     */
    @Test
    public void testLoadImage() {
        String PATH = Environment.PATHIMAGES + "dot.png";
        Snake instance = new Snake();
        ImageIcon img = new ImageIcon(PATH);
        Image expResult = img.getImage();
        Image result = instance.loadImage(PATH);
        assertEquals(expResult, result);
    }

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
    
}

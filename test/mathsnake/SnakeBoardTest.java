/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.Image;
import javax.swing.ImageIcon;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andreamaione
 */
public class SnakeBoardTest {
    
    /**
     * Test of loadImages method, of class SnakeBoard.
     */
    @Test
    public void testLoadImages() {
        SnakeBoard instance = new SnakeBoard();
        String PATH1 = Environment.PATHIMAGES + "smiling.png";
        String PATH2 = Environment.PATHIMAGES + "incazzato.png";
        ImageIcon imgicon1 = new ImageIcon(PATH1);
        Image img1 = imgicon1.getImage();
        ImageIcon imgicon2 = new ImageIcon(PATH2);
        Image img2 = imgicon2.getImage();
        assertEquals(instance.getBall(), img1);
        assertEquals(instance.getHead(), img2);
    }
    
}

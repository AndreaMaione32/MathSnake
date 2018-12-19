import java.awt.Rectangle;
import environment.Environment;
import snake.Snake;
import static org.junit.Assert.*;
import org.junit.Test;

public class SnakeTest {
    
    /**
     * Test of snake's life point modification.
     */
    @Test
    public void testSnakeLifePoints(){
        Snake instance = new Snake();
        instance.setLife(10);
        assertEquals(instance.getLife(), 10);
    }
    
    /**
     * Test of collide method, of class Snake.
     */
    @Test
    public void testCollide(){
        Snake instance = new Snake();
        Rectangle rect = new Rectangle((int) instance.getX()[Environment.getInstance().DOT_NUM - 1] - 5 , (int) instance.getY()[Environment.getInstance().DOT_NUM - 1] - 5 , 10, 10);  //rectangle in (x-5, y-5) with (x,y) snake's head coordinate
        assertTrue(instance.collide(rect));
    }
    
    /**
     * Test of move method, of class Snake.
     */
    @Test
    public void testMove() {
        Snake instance = new Snake();
        double dx = 300.0;
        double shift = (Environment.getInstance().DELAY * dx) / 1000;
        double oldPosition = instance.getX()[Environment.getInstance().DOT_NUM - 1];
        double newPosition = instance.getX()[Environment.getInstance().DOT_NUM - 1] + shift;
        instance.setHorizontalMovement(dx);
        instance.move();
        assertEquals(newPosition, instance.getX()[Environment.getInstance().DOT_NUM - 1], 0);
    }
}

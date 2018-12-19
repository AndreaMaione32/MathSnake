import downelements.PowerUpsIncreaseLife;
import panels.snakeboards.SnakeBoard;
import static org.junit.Assert.*;
import org.junit.Test;

public class PowerUpsIncreaseLifeTest {

    /**
     * Test of collisionAction method, of class PowerUpsIncreaseLife.
     */
    @Test
    public void testCollisionAction() {
        SnakeBoard snakeboard = new SnakeBoard();
        PowerUpsIncreaseLife instance = new PowerUpsIncreaseLife(0, 0);
        int actualLifePoints = snakeboard.getSnake().getLife();
        instance.collisionAction(snakeboard);
        int expectedLifePoints = actualLifePoints + 100;
        int newLifePoints = snakeboard.getSnake().getLife();
        assertEquals(expectedLifePoints, newLifePoints);
    }
    
}

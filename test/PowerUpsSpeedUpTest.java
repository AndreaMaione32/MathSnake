import downelements.PowerUpsSpeedUp;
import panels.snakeboards.SnakeBoard;
import static org.junit.Assert.*;
import org.junit.Test;

public class PowerUpsSpeedUpTest {

    /**
     * Test of collisionAction method, of class PowerUpsSpeedUp.
     */
    @Test
    public void testCollisionAction() {
        SnakeBoard snakeboard = new SnakeBoard();
        PowerUpsSpeedUp instance = new PowerUpsSpeedUp(0, 0);
        instance.collisionAction(snakeboard);
        assertTrue(snakeboard.getSnake().isSpeedUped());
    }
    
}

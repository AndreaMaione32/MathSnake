import downelements.PowerUpsShield;
import panels.snakeboards.SnakeBoard;
import static org.junit.Assert.*;
import org.junit.Test;

public class PowerUpsShieldTest {

    /**
     * Test of collisionAction method, of class PowerUpsShield.
     */
    @Test
    public void testCollisionAction() {
        SnakeBoard snakeboard = new SnakeBoard();
        PowerUpsShield instance = new PowerUpsShield(0, 0);
        instance.collisionAction(snakeboard);
        assertTrue(snakeboard.getSnake().isShielded());
    }
    
}

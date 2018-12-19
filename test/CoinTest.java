import downelements.Coin;
import panels.snakeboards.SnakeBoard;
import static org.junit.Assert.*;
import org.junit.Test;

public class CoinTest {

    /**
     * Test of collsionAction method, of class Coin.
     */
    @Test
    public void testCollisionAction() {
        SnakeBoard snakeboard = new SnakeBoard();
        Coin coin = new Coin(0, 0);
        int actualCoins = snakeboard.getCoinsSaver().getCurrentCoins();
        coin.collisionAction(snakeboard);
        int expectedCoins = actualCoins + 1;
        assertEquals(expectedCoins, snakeboard.getCoinsSaver().getCurrentCoins());
    }
    
}

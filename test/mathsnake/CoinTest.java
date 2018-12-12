package mathsnake;

import org.junit.Test;
import static org.junit.Assert.*;

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andreamaione
 */
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

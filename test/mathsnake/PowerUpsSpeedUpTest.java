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

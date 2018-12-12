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

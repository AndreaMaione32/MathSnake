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
public class BlockTest {

    /**
     * Test of getStrOp method, of class Block.
     */
    @Test
    public void testGetStrOp() {
        Block instance1 = new Block(1, Operation.ADD);
        String expResult = "+";
        String result = instance1.getStrOp();
        assertEquals(expResult, result);
        Block instance2 = new Block(2, Operation.MUL);
        expResult = "x";
        result = instance2.getStrOp();
        assertEquals(expResult, result);
        Block instance3 = new Block(1, Operation.SUB);
        expResult = "-";
        result = instance3.getStrOp();
        assertEquals(expResult, result);
        Block instance4 = new Block(2, Operation.DIV);
        expResult = "/";
        result = instance4.getStrOp();
        assertEquals(expResult, result);
    }
    
}

import downelements.Block;
import operation.Operation;
import panels.snakeboards.SnakeBoard;
import static org.junit.Assert.*;
import org.junit.Test;

public class BlockTest {

    /**
     * Test of getStrOp method, of class Block.
     */
    @Test
    public void testGetStrOp() {
        Block b = new Block(1, Operation.ADD, 0, 0);
        String expResult = "+";
        String result = b.getStrOp();
        assertEquals(expResult, result);
    }

    /**
     * Test of collsionAction method, of class Block.
     */
    @Test
    public void testCollisionAction() {
        SnakeBoard snakeBoard = new SnakeBoard();
        Block b = new Block(1, Operation.ADD, 0, 0);
        int actualLife = snakeBoard.getSnake().getLife();
        b.collisionAction(snakeBoard);
        int expectedLife = actualLife + b.getValue();
        assertEquals(expectedLife, snakeBoard.getSnake().getLife());
    }
    
}

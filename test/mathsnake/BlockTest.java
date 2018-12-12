package mathsnake;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

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
     * Test of draw method, of class Block.
    
    @Test
    public void testDraw() {
        try {
            Graphics gMock = mock(Graphics.class);
            Block b = new Block(1, Operation.ADD, 0, 0);
            b.draw(gMock);
            Field xField = getField("x");
            xField.setAccessible(true);
            Field yField = getField("y");
            yField.setAccessible(true);
            int x = (int) (double) xField.get(b);
            int y = (int) (double) yField.get(b);
            String text = b.getStrOp() + Integer.toString(b.getValue());
            Font expectedFont = new Font("Arial", Font.BOLD, 30);
            String expectedText = b.getStrOp() + Integer.toString(b.getValue());
            FontMetrics metrics = MathSnake.getInstance().getCardsJPanel().getComponent(2).getFontMetrics(expectedFont);
            int textX = x + (Environment.getInstance().BLOCK_WIDTH - metrics.stringWidth(text)) / 2;
            int textY = (int)y + ((Environment.getInstance().BLOCK_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
            verify(gMock).setFont(expectedFont);
            verify(gMock).setColor(Color.WHITE);
            verify(gMock).drawString(text, textX, textY);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(BlockTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }**/

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
    
    private Field getField(String nameField) {
        Field field = null;
        try {
            field = Block.class.getDeclaredField(nameField);
        } catch (NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(BackgroundTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return field;
    }
    
}

import java.awt.Graphics;
import java.awt.Image;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import background.Background;
import environment.Environment;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class BackgroundTest {

    /**
     * Test of move method, of class Background.
     */
    @Test
    public void testMove() {
        try {
            double velocity = Environment.getInstance().STARTDOWNSPEED;
            double shift = (Environment.getInstance().DELAY * velocity) / 1000;
            Background instance = new Background(Environment.getInstance().PATHBACKGROUND);
            Field y = getField("y");
            y.setAccessible(true);
            double yOld = (double) y.get(instance);
            instance.move(velocity);
            double yNew = (double) y.get(instance);
            assertEquals(yOld + shift, yNew, 0);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(BackgroundTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of drawBackground method, of class Background.
     */
    @Test
    public void testDrawBackground() {
        String PATH = Environment.getInstance().PATHBACKGROUND;
        Background instance = new Background(PATH);
        Graphics gMock = mock(Graphics.class);
        instance.drawBackground(gMock);
        Field img = getField("img");
        img.setAccessible(true);
        Field x = getField("x");
        x.setAccessible(true);
        Field y = getField("y");
        y.setAccessible(true);
        try {
            verify(gMock).drawImage((Image) img.get(instance), (int) (double) x.get(instance), (int) (double) y.get(instance), null);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(BackgroundTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Field getField(String nameField) {
        Field field = null;
        try {
            field = Background.class.getDeclaredField(nameField);
        } catch (NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(BackgroundTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return field;
    }

}

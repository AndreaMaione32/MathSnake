/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author antoniocoppola
 */
public class ConstructorThreadTest {
    
    private ConstructorThread constructorBlockRunnable;
    
    @After
    public void stopThread() {
        constructorBlockRunnable.stopThread();
    }
    
    @Test
    public void creationTest(){
        try {
            constructorBlockRunnable = new ConstructorThread(new SnakeBoard());
            Thread constructorBlockThread = new Thread(constructorBlockRunnable);
            constructorBlockThread.start();
            ElementManager em = ElementManager.getInstance();
            Thread.sleep(2 * Environment.getInstance().MINTHREADDELAY);
            Field listField = getField("elementList");
            listField.setAccessible(true);
            List<DownElement> list = (ArrayList<DownElement>) listField.get(em);
            System.out.println("Current elements: " + Integer.toString(list.size()));
            assertTrue(list.size() > 0);  //thread must to be created at least two block
        } catch (IllegalArgumentException | IllegalAccessException | InterruptedException ex) {
            Logger.getLogger(ConstructorThreadTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Field getField(String nameField) {
        Field field = null;
        try {
            field = ElementManager.class.getDeclaredField(nameField);
        } catch (NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(BackgroundTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return field;
    }
}

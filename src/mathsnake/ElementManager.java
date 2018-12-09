package mathsnake;

import java.util.ArrayList;
import java.util.List;

/**
 ElementManager manages the element list of Math Snake. ElementManager adds or remove DownElement from
 element list. 
 * The ElementManager must to be unique, we have one set of downelement in our game thus we want to have unique ElementManager
 * For this reason we use Singleton Design Pattern
 */

public class ElementManager {
    private final List<DownElement> elementList;
    private static ElementManager instance = null;
    
    private ElementManager(){
        elementList = new ArrayList<DownElement>();
    }
    
    public static ElementManager getInstance(){
        synchronized(ElementManager.class){          //THREAD SAFE
            if(instance==null)
                    instance = new ElementManager();
                return instance;
            }
    }
    
    public synchronized void addElement(DownElement element){  //THREAD SAFE
        elementList.add(element);
    }
    
    public synchronized boolean removeElement(DownElement element){  //THREAD SAFE
        return elementList.remove(element);
    }
    
    public synchronized int numElements(){           //THREAD SAFE
        return elementList.size();
    }
    
    public synchronized DownElement getElement(int i){     //THREAD SAFE
        return elementList.get(i);
    }
    
    public synchronized DownElement getBlock(DownElement downElement){     //THREAD SAFE
        return elementList.get(elementList.indexOf(downElement));
    }
    
    public synchronized void flush(){
        elementList.clear();
    }
}

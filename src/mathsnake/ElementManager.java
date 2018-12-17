package mathsnake;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 ElementManager manages the element list of Math Snake. ElementManager adds or remove DownElement from
 element list. 
 * The ElementManager must to be unique, we have one set of downelement in our game thus we want to have unique ElementManager
 * For this reason we use Singleton Design Pattern
 */

public class ElementManager implements Iterable<DownElement>{
    private  List<DownElement> elementList;
    private static ElementManager instance = null;
    
    private ElementManager() {
        elementList = new ArrayList<DownElement>();
    }
    
    public static ElementManager getInstance() {
        synchronized(ElementManager.class){          //THREAD SAFE
            if(instance==null)
                    instance = new ElementManager();
                return instance;
            }
    }
    
    public synchronized void addElement(DownElement element) {  //THREAD SAFE
        elementList.add(element);
    }
    
    public synchronized boolean removeElement(DownElement element) {  //THREAD SAFE
        return elementList.remove(element);
    }
    
    private synchronized int numElements() {           //THREAD SAFE
        return elementList.size();
    }
    
    private synchronized DownElement getElement(int i) {     //THREAD SAFE
        return elementList.get(i);
    }
    
    public synchronized void flush() {
        elementList.clear();
    }

    public List<DownElement> getElementList() {
        return elementList;
    }

    public void setElementList(List<DownElement> elementList) {
        this.elementList = elementList;
    }
    
    
    //Iterator Pattern used to iterate the elements of element list in a synchronized way 
    
    private class ElementManagerIterator implements Iterator<DownElement> {
        private ElementManager em;
        private int current;
        
        public ElementManagerIterator(ElementManager em){
            this.current = 0;
            this.em = em;
        }
        
        @Override
        public boolean hasNext() {
            if(current < em.numElements())
                return true;
            else
                return false;
        }

        @Override
        public  DownElement next() {
            DownElement e = em.getElement(current);
            current ++;
            return e;
        }
        
    }

    @Override
    public Iterator<DownElement> iterator() {
        return new ElementManagerIterator(this);
    }
}

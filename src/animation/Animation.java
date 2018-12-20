package animation;

import iofiles.Loader;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;

public class Animation {
    private String directory;
    private String nameImg;
    private int delay;
    private int count;
    private int numFrames;
    private int currentFrame;
    
    public Animation(String directory, String nameImg, int delay, int currentFrame, int numFrames){
        this.directory = directory;
        this.delay = delay;
        this.numFrames = numFrames;
        this.count = 0;
        this.currentFrame = currentFrame;
        this.nameImg = nameImg;
    }
    
    public void update() {	
        if(delay < 0) return;
        count++;
        if(count == delay) {
            if(currentFrame + 1 > numFrames)
                currentFrame = 1;
            else
                currentFrame ++;
            count = 0;
        }
		
    }
    
    private int getNumFrames(){
        return new File(this.directory).listFiles().length;
    }
    
    public Image getImage() { 
        return Loader.getInstance().getImage(nameImg+"_"+currentFrame);
    }
    
}

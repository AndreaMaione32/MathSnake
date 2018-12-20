/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iofiles;

import environment.Environment;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 *
 * @author antoniocoppola
 * Loader load the most frequently used images in order to load them once.
 */
public class Loader {
    private Map<String, Image> map;
    private static Loader instance = null;
    
    public static Loader getInstance(){
        if(instance == null){
            instance = new Loader();
        }
        return instance;
    }
    
    private Loader(){
        map = new HashMap<>();
        fillMap();
    }
    
    private void fillMap(){
        //DOWN ELEMENT IMAGES
        map.put("retro_skull", loadImage(Environment.getInstance().PATHIMAGES + "retro_skull.png"));
        map.put("retro_block_p", loadImage(Environment.getInstance().PATHIMAGES + "retro_block_p.png"));
        map.put("retro_block_d", loadImage(Environment.getInstance().PATHIMAGES + "retro_block_d.png"));
        map.put("retro_block_n", loadImage(Environment.getInstance().PATHIMAGES + "retro_block_n.png"));
        map.put("heart", loadImage(Environment.getInstance().PATHIMAGES+"heart.png"));
        map.put("shield", loadImage(Environment.getInstance().PATHIMAGES+"shield.png"));
        map.put("speed_up", loadImage(Environment.getInstance().PATHIMAGES+"speed_up.png"));
        map.put("coin_animation_1", loadImage(Environment.getInstance().PATHIMAGES+"coin_animation/coin_animation_1.png"));
        map.put("coin_animation_2", loadImage(Environment.getInstance().PATHIMAGES+"coin_animation/coin_animation_2.png"));
        map.put("coin_animation_3", loadImage(Environment.getInstance().PATHIMAGES+"coin_animation/coin_animation_3.png"));
        map.put("coin_animation_4", loadImage(Environment.getInstance().PATHIMAGES+"coin_animation/coin_animation_4.png"));
        map.put("coin_animation_5", loadImage(Environment.getInstance().PATHIMAGES+"coin_animation/coin_animation_5.png"));
        map.put("coin_animation_6", loadImage(Environment.getInstance().PATHIMAGES+"coin_animation/coin_animation_6.png"));
    }
    
    private Image loadImage(String PATH) {
        ImageIcon iid = new ImageIcon(PATH);
        Image icon = iid.getImage();
        return icon;
    }
    
    public Image getImage(String nameImg){
        return map.get(nameImg);
    }
}

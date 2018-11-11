/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author antonino
 */
public class Snake {
    private final int x[] = new int[Environment.MAX_DOTS];// memorizzano le coordinate x e y del serpente
    private final int y[] = new int[Environment.MAX_DOTS];

    private int dots = 11;
    private boolean leftDirection = false;
    private boolean rightDirection = false;
    
    public Snake(int n){
        dots = n;

        for (int z = 0; z < dots; z++) {
            x[z] = 400;
            y[z] = 400-z;
            //System.out.print("Points of snake " + x [z] + "\n");
        }
        
    }
    
    public int getSnakeX(int index) {
        return x[index];
    }

    public int getSnakeY(int index) {
        return y[index];
    }
    
    public void setSnakeX(int i) {
        x[0] = i;
    }

    public void setSnakeY(int i) {
        y[0] = i;
    }

    public boolean isMovingLeft() {
        return leftDirection;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.leftDirection = movingLeft;
    }

    public boolean isMovingRight() {
        return rightDirection;
    }

    public void setMovingRight(boolean movingRight) {
        this.rightDirection = movingRight;
    }

    public int getDots() {
        return this.dots;
    }

    public void setDots(int j) {
        this.dots = j;
    }
    
    public Image loadImage(String PATH) {
        //Carico le immagini..\\..\\..\\
        ImageIcon iid = new ImageIcon(PATH);
        Image icon = iid.getImage();
        return icon;
    }
    
    public void move() throws InterruptedException{
        for (int z = 0; z < dots; z++) { 
            System.out.print("Points of snake [" + x [z] +"|"+ y[z] + "]" + "\n"); 
        } 
        int shift = Environment.DOT_SIZE + 10;
        if (leftDirection) {
            if (x[0]- shift < 0){
                for (int z = 0; z < dots; z++) { 
                x[z] = 0;
                }
            }else{
                for (int z = 0; z < dots; z++) { 
                    
                    x[z] -= shift;
                    //TimeUnit.MILLISECONDS.sleep(150);
                }
            }
            leftDirection = false;
        }
        if (rightDirection) {
            if (x[0]+shift >= Environment.JP_WIDTH){
                for (int z = 0; z < dots; z++) { 
                x[z] = Environment.JP_WIDTH-10;
                }
            }else{
                for (int z = 0; z < dots; z++) { 
                    
                    x[z] += shift;
                    //TimeUnit.MILLISECONDS.sleep(150);
                }
            }
            rightDirection = false;
        }
    }
}



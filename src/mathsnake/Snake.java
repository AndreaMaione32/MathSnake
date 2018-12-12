package mathsnake;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Snake implements ActionListener {
    
    private double[] y = new double[Environment.getInstance().DOT_NUM]; // Ogni dot che costituisce lo snake ha coordinata y differente e fissata in quanto lo snake ha altezza fissa
    private double dx; 
    private double[] x = new double[Environment.getInstance().DOT_NUM];
    private Timer speedUpTimer;
    private Timer shieldTimer;
    private Rectangle rectangle;   //Rect associated to Snake's Head, it's used to manage collision
    private int lifepoints; 
    private boolean speed_uped = false; //if true increase snake's velocity of 70%
    private boolean shield = false; //if true the snake's life don't decreases
    
    // Costruttore con paramtetro di deafult di dots
    public Snake() {
        this.shieldTimer = new Timer(Environment.getInstance().SHIELD_DURATION, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                shield = false;
                shieldTimer.stop();
            }
        });
        lifepoints = Environment.getInstance().STARTLIFEPOINTS;
        this.speedUpTimer = new Timer(Environment.getInstance().SPEED_UP_DURATION, this);
        for (int z = 0; z < Environment.getInstance().DOT_NUM; z++) {
            x[z] = Environment.getInstance().JP_WIDTH / 2;
            y[z] = (Environment.getInstance().JP_HEIGHT-2*Environment.getInstance().DOT_SIZE) - (z * Environment.getInstance().DOT_SIZE); // Poichè la dimensione di ogni dot è DOT_SIZE px la differenza fra due coordinate adiacenti nel vettore è pari a 10, il primo elemento del vettore è il primo dot della coda, mentre l'ultimo è la testa dello snake; il primo dot ha y = JP_WIDTH, il secondo JP_WIDTH - 10 e così via
            this.rectangle = new Rectangle((int)x[Environment.getInstance().DOT_NUM-1],(int)y[Environment.getInstance().DOT_NUM-1], Environment.getInstance().DOT_SIZE,Environment.getInstance().DOT_SIZE);  //coordinates are the coordinates of the head
        }
    }
    
    private void setX(double x) {
        double[] xCopy = this.x.clone();
        this.x[Environment.getInstance().DOT_NUM - 1] = x;
        for(int z = Environment.getInstance().DOT_NUM - 2; z >= 0; z--) {
            this.x[z] = xCopy[z+1];
        }
        rectangle.setLocation((int)this.x[Environment.getInstance().DOT_NUM-1], (int)this.y[Environment.getInstance().DOT_NUM-1]); //Move also Rectangle assoicated
    }

    public Rectangle getAssociatedRectangle() {
        return rectangle;
    }

    public double[] getX() {
        return x;
    }
    
    public void setLife(int lifepoints){
        if(shield){  
            if(this.lifepoints > lifepoints){} //if the snake has a shield its life must to don't decrease
            else
                this.lifepoints = lifepoints;
        }
        else
            this.lifepoints = lifepoints;
    }
    
    public double[] getY() {
        return y;
    }
    
    public int getLife() {
        return lifepoints;
    }
    
    public Image loadImage(String PATH) {
        ImageIcon iid = new ImageIcon(PATH);
        Image icon = iid.getImage();
        return icon;
    }
    
     public boolean collide(Rectangle rect){        //return true if rect intersect snake's head associated rectangle
        return this.rectangle.intersects(rect);
    }
    
    public void move() {
        double shift = (Environment.getInstance().DELAY * dx) / 1000;
        if ((dx < 0) && (x[Environment.getInstance().DOT_NUM-1] < 0))
            return;
        if ((dx > 0) && (x[Environment.getInstance().DOT_NUM-1] > Environment.getInstance().JP_WIDTH - Environment.getInstance().DOT_SIZE))
            return;
        this.setX(x[Environment.getInstance().DOT_NUM-1] + shift);
    }
    
    public void setHorizontalMovement(double dx) {
        if(speed_uped)
            this.dx = (1.7) * dx;
        else
            this.dx = dx;
    }
    
    public void speed_up(){
        if(!this.speed_uped)
            this.speed_uped = true; 
        else
            this.speedUpTimer.stop(); //if snake is already accelerated stop speed up timer and start it again
        this.speedUpTimer.start();
    }
    //it's used to speed up the snake temporarily 

    public void shield(){
        if(this.shield)
            shieldTimer.stop(); //if snake has already a shield stop shield timer and start it again
        else
            this.shield = true;
        shieldTimer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.speed_uped = false;
        this.speedUpTimer.stop();
    }

    public boolean isSpeedUped() {
        return speed_uped;
    }

    public boolean isShielded() {
        return shield;
    }
}
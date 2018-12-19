package snake;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import environment.Environment;

public class Snake implements ActionListener {
    
    private double[] x;
    private double[] y; // Ogni dot che costituisce lo snake ha coordinata y differente e fissata in quanto lo snake ha altezza fissa 
    private double dx;
    private Timer speedUpTimer;
    private Timer shieldTimer;
    private Rectangle rectangle;   //Rect associated to Snake's Head, it's used to manage collision
    private int lifepoints; 
    private boolean speed_uped; //if true increase snake's velocity of 70%
    private boolean shield; //if true the snake's life don't decreases
    private double radius;

    // Costruttore con parametro di deafult di dots
    public Snake() {
        y = new double[Environment.getInstance().DOT_NUM];
        x = new double[Environment.getInstance().DOT_NUM];
        speed_uped = false;
        shield = false;
        radius = Environment.getInstance().DOT_SIZE / 2;
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
            y[z] = (Environment.getInstance().JP_HEIGHT-2*Environment.getInstance().DOT_SIZE) - (z * Environment.getInstance().DOT_SIZE); 
            this.rectangle = new Rectangle((int)x[Environment.getInstance().DOT_NUM-1],(int)y[Environment.getInstance().DOT_NUM-1], Environment.getInstance().DOT_SIZE,Environment.getInstance().DOT_SIZE);  //coordinates are the coordinates of the head
        }
    }
    
    private void setX(double x) {
        this.x[Environment.getInstance().DOT_NUM - 1] = x;
        for(int z = Environment.getInstance().DOT_NUM - 2; z >= 0; z--) {
            double curr = this.x[z];
            double last = this.x[z+1]; 
            double diffX = curr - last;
            double diffY = this.y[z] - this.y[z+1];
            double angle = Math.atan2(diffY, diffX);
            double nx = 2*(this.radius-2)*Math.cos(angle);
            this.x[z] = nx + last;
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
            if(this.lifepoints > lifepoints){} //if the snake has a shield its life must not decrease
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
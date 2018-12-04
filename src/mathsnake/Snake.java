package mathsnake;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Snake implements ActionListener{
    private double x; // La coordinata x dello snake è univoca per tutti i dot che lo costituiscono ed può assumere valori compresti fra 0 e JP_WIDTH
    private double[] y; // Ogni dot che costituisce lo snake ha coordinata y differente e fissata in quanto lo snake ha altezza fissa
    private double dx; 
    private Timer speedUpTimer;
    private Timer shieldTimer;
    private final int dots; // Numero di dots che costituiscono lo snake
    //private boolean leftDirection = false;
    //private boolean rightDirection = false;
    private Rectangle rectangle;   //Rect associated to Snake's Head, it's used to mange collision
    private int lifepoints; 
    private boolean speed_uped = false; //if true increase snake's velocity of 70%
    private boolean shield = false; //if true the snake's life don't decreases
    // Costruttore con paramtetro di deafult di dots
    public Snake() {
        dots = 10;
        
        int snakeStartPoint = Environment.JP_WIDTH / 2; // Lo snake viene creato al centro della finestra
        x = snakeStartPoint;
        this.shieldTimer = new Timer(Environment.SHIELD_DURATION, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                shield = false;
                shieldTimer.stop();
            }
        });
        lifepoints = Environment.STARTLIFEPOINTS;
        this.speedUpTimer = new Timer(Environment.SPEED_UP_DURATION, this);
        y = new double[dots]; // Il vettore delle coordinate y viene settatto a dimensione uguale al valore dots di default (a ogni elemento corrisponde una coordinata di un dot)
        for (int z = 0; z < dots; z++) {
            y[z] = Environment.JP_HEIGHT - (z * Environment.DOT_SIZE); // Poichè la dimensione di ogni dot è DOT_SIZE px la differenza fra due coordinate adiacenti nel vettore è pari a 10, il primo elemento del vettore è il primo dot della coda, mentre l'ultimo è la testa dello snake; il primo dot ha y = JP_WIDTH, il secondo JP_WIDTH - 10 e così via
        this.rectangle = new Rectangle((int)x,(int)y[dots-1], Environment.DOT_SIZE,Environment.DOT_SIZE);  //coordinates are the coordinates of the head
        }
    }

    public Rectangle getAssociatedRectangle() {
        return rectangle;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        rectangle.setLocation((int)x, (int)this.y[dots-1]); //Move also Rectangle assoicated
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

    public int getDots() {
        return dots;
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
        
        double shift = (Environment.DELAY * dx) / 1000;
        
        if ((dx < 0) && (x < 0)) {
            return;
	}
        
        if ((dx > 0) && (x > Environment.JP_WIDTH - Environment.DOT_SIZE)) {
            return;
	}
        
        this.setX(x + shift);
    }
    
    public void setHorizontalMovement(double dx) {
        if(speed_uped)
            this.dx = (1.7)*dx;
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
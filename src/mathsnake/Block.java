/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import static javafx.scene.input.KeyCode.F;

/**
 *
 * @author antoniocoppola
 */
public class Block {
    private final int value; //indicates the value of operation
    private final Operation op; //indicates the operation
    private Color color;
    private int x;
    private int y;
    private Rectangle rectangle;   //Rect associated to Block, it's used to mange collision
    
    public Block(int value, Operation op, int x, int y){
        this.value = value;
        this.op = op;
        color = defineColor(op);   //define color accordint to operation
        this.x = x;
        this.y = y;
        this.rectangle = new Rectangle(x,y,Environment.BLOCK_WIDTH, Environment.BLOCK_HEIGHT);
    }
    
    //block must to be thread safe on X and Y, only two fields that can be modify
    public synchronized void setX(int x) {     
        this.x = x;
        rectangle.setLocation(x, this.y); //Move also Rectangle assoicated
    }

    public  synchronized void setY(int y) {   
        this.y = y;
        rectangle.setLocation(this.x, y);  //Move also Rectangle assoicated
    }

    public synchronized int getX() {
        return x;
    }

    public synchronized int getY() {
        return y;
    }
    
    private Color defineColor(Operation op){
        switch(op){
            case ADD:
                return Color.ORANGE;
            case MUL:
                 return Color.GREEN;
            case SUB:
                return Color.MAGENTA;
            case DIV:
                return Color.RED;
            case DEA:
                return Color.BLACK;
            case NULL:
                return Color.WHITE;
            default:
                return Color.WHITE;
        }
    }

    public int getValue() {
        return value;
    }

    public String getStrOp() {
        switch(op){
            case ADD:
                return "+";
            case MUL:
                 return "x";
            case SUB:
                return "-";
            case DIV:
                return "/";
            case DEA:
                return "x";
            case NULL:
                return "";
            default:
                return "";
        }
    }

    public Color getColor() {
        return color;
    }

    public Operation getOp() {
        return op;
    }

    public Rectangle getAssociatedRectangle() {
        return rectangle;
    }
    
    public boolean collide(Rectangle rect){        //return true if rect intersect block associated rectangle
        return this.rectangle.intersects(rect);
    }
    
    
    public void printBlock(Graphics g){ //print block given x, y coordinate and graphics object, associeted to an JPanel
       g.setColor(this.getColor());
       g.fillRect(x, y, Environment.BLOCK_WIDTH, Environment.BLOCK_HEIGHT);
       Font font = new Font("Arial", Font.BOLD, 17);
       /*
       String text;
       if (this.getStrOp().equals("#")){
           text = "x0";
       } else{
           text = this.getStrOp()+Integer.toString(this.getValue());
       }
       */
       String text = this.getStrOp()+Integer.toString(this.getValue());
       // Get the FontMetrics
       FontMetrics metrics = g.getFontMetrics(font);
       // Determine the X coordinate for the text
       int textX = x + (Environment.BLOCK_WIDTH - metrics.stringWidth(text)) / 2;
      // Determine the Y coordinate for the text
       int textY = y + ((Environment.BLOCK_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
       g.setFont(font);
       g.setColor(Color.WHITE);
       g.drawString(text, textX, textY);
   }
}

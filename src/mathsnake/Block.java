package mathsnake;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Block {
    private final int value; //indicates the value of operation
    private final Operation op; //indicates the operation
    private Color color;
    private double x;
    private double y;
    private Image img;
    private Rectangle rectangle;   //Rect associated to Block, it's used to mange collision
    
    public Block(int value, Operation op, int x, int y){
        this.value = value;
        this.op = op;
        //color = defineColor(op);   //define color accordint to operation
        if(op == Operation.ADD || op == Operation.MUL){
            img = this.loadImage(Environment.getInstance().PATHIMAGES+"retro_block_p.png");
        }
        if(op == Operation.DEA)
            img = this.loadImage(Environment.getInstance().PATHIMAGES+"retro_block_d.png");
        if(op == Operation.DIV || op == Operation.SUB)
            img = this.loadImage(Environment.getInstance().PATHIMAGES+"retro_block_n.png");
        this.x = x;
        this.y = y;
        this.rectangle = new Rectangle(x,y,Environment.getInstance().BLOCK_WIDTH, Environment.getInstance().BLOCK_HEIGHT);
    }
    
    //block must to be thread safe on X and Y, only two fields that can be modify
    private synchronized void setX(double x) {     
        this.x = x;
        rectangle.setLocation((int)x, (int)this.y); //Move also Rectangle assoicated
    }

    private  synchronized void setY(double y) {   
        this.y = y;
        rectangle.setLocation((int)this.x, (int)y);  //Move also Rectangle assoicated
    }

    public synchronized int getX() {
        return (int)x;
    }

    public synchronized int getY() {
        return (int)y;
    }
    
    public void move(double velocity){
        double shift = (Environment.getInstance().DELAY * velocity) / 1000; 
        this.setY(y + shift);
    }
    
    private Color defineColor(Operation op){
        switch(op){
            case ADD:
                return new Color(38,166,191);
            case MUL:
                 return new Color(38,166,191);
            case SUB:
                return new Color(3, 58, 112);
            case DIV:
                return new Color(3, 58, 112);
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
    
    protected Image loadImage(String PATH) {
        ImageIcon iid = new ImageIcon(PATH);
        Image icon = iid.getImage();
        return icon;
    }
    
    
    public void printBlock(Graphics g){ 
       g.drawImage(this.img, (int) x, (int) y, null);
       if(this.op == Operation.DEA){
          Image img_d = this.loadImage(Environment.getInstance().PATHIMAGES+"retro_skull.png");
          int img_d_X = (int)x + (Environment.getInstance().BLOCK_WIDTH - img_d.getWidth(null))/2;
          int img_d_Y = (int)y + (Environment.getInstance().BLOCK_HEIGHT - img_d.getHeight(null))/2;
          g.drawImage(img_d, img_d_X, img_d_Y, null);
       }
       else{
            Font font = new Font("Arial", Font.BOLD, 30);
            String text = this.getStrOp()+Integer.toString(this.getValue());
            // Get the FontMetrics
            FontMetrics metrics = g.getFontMetrics(font);
            // Determine the X coordinate for the text
            int textX = (int)x + (Environment.getInstance().BLOCK_WIDTH - metrics.stringWidth(text)) / 2;
           // Determine the Y coordinate for the text
            int textY = (int)y + ((Environment.getInstance().BLOCK_HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
            g.setFont(font);
            g.setColor(Color.WHITE);
            g.drawString(text, textX, textY);
       }
   }
}

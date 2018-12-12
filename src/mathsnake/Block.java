package mathsnake;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Block extends DownElement {
    private final int value; //indicates the value of operation
    private final Operation op; //indicates the operation
    private Color color;
    
    public Block(int value, Operation op, int x, int y){
        super(x,y,defineBlockPathImage(op));
        this.value = value;
        this.op = op;
    }
    
    private static String defineBlockPathImage(Operation op){
        String pathImage = null;
        if(op == Operation.ADD || op == Operation.MUL)
            pathImage = Environment.getInstance().PATHIMAGES+"retro_block_p.png";
        if(op == Operation.DEA)
            pathImage = Environment.getInstance().PATHIMAGES+"retro_block_d.png";
        if(op == Operation.DIV || op == Operation.SUB)
            pathImage = Environment.getInstance().PATHIMAGES+"retro_block_n.png";
        return pathImage;
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
    
    @Override
    public void draw(Graphics g){ 
        super.draw(g);
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

    @Override
    public void collsionAction(Board board){
        Snake snake = board.getSnake();
        int actualLife = snake.getLife();
        String op = this.getStrOp();
        if (op.equals("+"))
            snake.setLife(actualLife + value);
        if (op.equals("x"))
            snake.setLife(actualLife * value);
        if (op.equals("-"))
            snake.setLife(actualLife - value);
        if (op.equals("/"))
            snake.setLife(actualLife / value);
        
        actualLife = snake.getLife();
        if (actualLife < 0)
            snake.setLife(0);
        else {
            if (actualLife > board.getGameBest())
                board.setGameBest(actualLife);
        }
        if (snake.getLife() == 0)
            board.setState(Board.STATE.GAMEOVER);
    }
    
}

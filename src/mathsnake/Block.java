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
import static javafx.scene.input.KeyCode.F;

/**
 *
 * @author antoniocoppola
 */
public class Block {
    private static int WIDTH = 100;
    private static int HEIGHT = 100;
    private int value; //indicates the value of operation
    private Operation op; //indicates the operation
    private Color color;
    
    public Block(int value, Operation op){
        this.value = value;
        this.op = op;
        color = defineColor(op);
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
            default:
                return "";
        }
    }

    public Color getColor() {
        return color;
    }
    
    public void printBlock(int x, int y,Graphics g){ //print block given x, y coordinate and graphics object, associeted to an JPanel
       g.setColor(this.getColor());
       g.fillRect(x, y, WIDTH, HEIGHT);
       Font font = new Font("Arial", Font.BOLD, 17);
       String text = this.getStrOp()+Integer.toString(this.getValue());
       // Get the FontMetrics
       FontMetrics metrics = g.getFontMetrics(font);
       // Determine the X coordinate for the text
       int textX = x + (WIDTH- metrics.stringWidth(text)) / 2;
      // Determine the Y coordinate for the text
       int textY = y + ((HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
       g.setFont(font);
       g.setColor(Color.WHITE);
       g.drawString(text, textX, textY);
   }
}

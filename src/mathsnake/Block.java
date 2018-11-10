/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathsnake;
import java.awt.Color;

/**
 *
 * @author antoniocoppola
 */
public class Block {
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
    
    
}

package downelements;

import environment.Environment;
import panels.snakeboards.Board;

public class PowerUpsShield extends DownElement {

    public PowerUpsShield(int x, int y) {
        super(x, y, "shield");
    }
    
    @Override
    public void collisionAction(Board board) {
        board.getSnake().shield();
    }
    
}

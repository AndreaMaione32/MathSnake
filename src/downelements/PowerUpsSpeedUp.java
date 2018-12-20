package downelements;

import environment.Environment;
import panels.snakeboards.Board;

public class PowerUpsSpeedUp extends DownElement {

    public PowerUpsSpeedUp(int x, int y) {
        super(x, y, "speed_up");
    }
    
    @Override
    public void collisionAction(Board board) {
        board.getSnake().speed_up();
    }
}

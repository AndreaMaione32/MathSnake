package downelements;

import environment.Environment;
import panels.snakeboards.Board;

public class PowerUpsSpeedUp extends DownElement {

    public PowerUpsSpeedUp(int x, int y) {
        super(x, y, Environment.getInstance().PATHIMAGES+"speed_up.png");
    }
    
    @Override
    public void collisionAction(Board board) {
        board.getSnake().speed_up();
    }
}

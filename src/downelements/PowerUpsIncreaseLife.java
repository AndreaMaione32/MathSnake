package downelements;

import environment.Environment;
import panels.snakeboards.Board;

public class PowerUpsIncreaseLife extends DownElement {
   
    public PowerUpsIncreaseLife(int x, int y){
        super(x, y, Environment.getInstance().PATHIMAGES+"heart.png");
    }
    
    @Override
    public void collisionAction(Board board) {
        board.getSnake().setLife(board.getSnake().getLife() + 100);  //increases snake's life
    }
}
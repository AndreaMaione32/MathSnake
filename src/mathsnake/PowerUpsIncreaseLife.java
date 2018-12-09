package mathsnake;

import java.awt.Image;

public class PowerUpsIncreaseLife extends DownElement {
   
    public PowerUpsIncreaseLife(int x, int y){
        super(x, y, Environment.getInstance().PATHIMAGES+"heart.png");
    }
    
    @Override
    public void collsionAction(SnakeBoard snakeBoard) {
        snakeBoard.getSnake().setLife(snakeBoard.getSnake().getLife() + 100);  //increases snake's life
    }
}

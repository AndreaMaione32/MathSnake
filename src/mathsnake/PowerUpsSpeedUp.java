package mathsnake;

public class PowerUpsSpeedUp extends DownElement {

    public PowerUpsSpeedUp(int x, int y) {
        super(x, y, Environment.getInstance().PATHIMAGES+"speed_up.png");
    }
    
    @Override
    public void collsionAction(SnakeBoard snakeBoard) {
        snakeBoard.getSnake().speed_up();
    }
}

package mathsnake;

public class PowerUpsShield extends DownElement {

    public PowerUpsShield(int x, int y) {
        super(x, y, Environment.getInstance().PATHIMAGES+"shield.png");
    }
    
    @Override
    public void collisionAction(SnakeBoard snakeBoard) {
        snakeBoard.getSnake().shield();
    }
    
}

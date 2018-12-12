package mathsnake;

public class PowerUpsIncreaseLife extends DownElement {
   
    public PowerUpsIncreaseLife(int x, int y){
        super(x, y, Environment.getInstance().PATHIMAGES+"heart.png");
    }
    
    @Override
    public void collisionAction(SnakeBoard snakeBoard) {
        snakeBoard.getSnake().setLife(snakeBoard.getSnake().getLife() + 100);  //increases snake's life
    }
}

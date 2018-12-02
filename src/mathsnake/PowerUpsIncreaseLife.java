package mathsnake;

public class PowerUpsIncreaseLife extends PowerUps {
   
    public PowerUpsIncreaseLife(int x, int y){
        super(x, y);
        super.nameimg = "heart.png";
    }

    @Override
    public void action(Snake snake) {
        snake.setLife(snake.getLife() + 100);  //increases snake's life
    }
}

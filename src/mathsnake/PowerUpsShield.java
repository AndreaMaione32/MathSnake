package mathsnake;

public class PowerUpsShield extends PowerUps {

    public PowerUpsShield(int x, int y) {
        super(x, y);
        super.nameimg = "shield.png";
    }

    @Override
    public void action(Snake snake) {
        snake.shield();
    }
    
}

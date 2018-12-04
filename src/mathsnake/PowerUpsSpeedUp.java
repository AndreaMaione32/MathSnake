package mathsnake;

public class PowerUpsSpeedUp extends PowerUps {

    public PowerUpsSpeedUp(int x, int y) {
        super(x, y);
        this.img = this.loadImage(Environment.getInstance().PATHIMAGES+"speed_up.png");
    }

    @Override
    public void action(Snake snake) {
        snake.speed_up();
    }
}

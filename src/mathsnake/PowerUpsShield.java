package mathsnake;

public class PowerUpsShield extends PowerUps {

    public PowerUpsShield(int x, int y) {
        super(x, y);
        this.img = this.loadImage(Environment.getInstance().PATHIMAGES+"shield.png");
    }

    @Override
    public void action(Snake snake) {
        snake.shield();
    }
    
}

package blocks;

import assets.ResourceLoader;
import assets.Sprite;

public class Key extends BlockType {

    private final int POINTS;

    public Key(int points) {
        this.image = ResourceLoader.getSprite(Sprite.KEY);
        this.POINTS = points;
        this.isSolid = false;
    }

    public void openBarricade(Barricade b) {
        if (POINTS == b.getPoints()) {
            b.unlock();
        } else {
            this.showWarning();
        }
    }

    public void disappear() {
        this.isSolid = false;
        this.setSprite(Sprite.WALL);
    }

    private void showWarning() {

    }
}

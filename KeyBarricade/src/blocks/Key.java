package blocks;

import assets.ResourceLoader;
import assets.Sprite;

public class Key extends BlockType {

    public Key(int points) {
        this.image = ResourceLoader.getSprite(Sprite.KEY);
        this.points = points;
        this.isSolid = false;
    }

    public void openBarricade(Barricade b) {
        if (points == b.getPoints()) {
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

import processing.core.PApplet;

import java.awt.*;

public class Rect extends Shape {
    public Rect(int x, int y, Color color, int type) {
        super(x, y, color, type);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.fill(color.getRGB());
        pApplet.rect(x, y, 40, 40);
    }

    @Override
    public Rect clone() {
        return (Rect) super.clone();
    }

    @Override
    public boolean isCollision(int x, int y) {
        if (x > this.x - 20 && x < this.x + 20 && y > this.y - 20 && y < this.y + 20)
            return true;
        return false;
    }


}

import processing.core.PApplet;

import java.awt.*;

public class Triangle extends Shape {
    public Triangle(int x, int y, Color color, int type) {
        super(x, y, color, type);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.fill(color.getRGB());
        pApplet.triangle(x, y, x - 20, y + 40, 20 + x, 40 + y);
    }

    @Override
    public Triangle clone() {
        return (Triangle) super.clone();
    }

    @Override
    public boolean isCollision(int x, int y) {
        if (x > this.x - 20 && x < this.x + 20 && y > this.y && y < this.y + 40)
            return true;
        return false;
    }

}

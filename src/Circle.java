import processing.core.PApplet;

import java.awt.*;

public class Circle extends Shape {
    public Circle(int x, int y, Color color, int type) {
        super(x, y, color, type);
    }

    @Override
    public void draw(PApplet pApplet) {
        pApplet.fill(color.getRGB());
        pApplet.ellipse(x, y, 40, 40);

    }

    @Override
    public Circle clone() {
        return (Circle) super.clone();
    }

    @Override
    public boolean isCollision(int x, int y) {
        if (x > this.x - 20 && x < this.x + 20 && y > this.y - 20 && y < this.y + 20)
            return true;
        return false;
    }

}

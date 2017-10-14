import processing.core.PApplet;

import java.awt.*;

public abstract class Shape implements Cloneable {
    protected int x;
    protected int y;
    protected Color color;
    protected int type;

    public Shape(int x, int y, Color color, int type) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.type = type;
    }

    // 부모가 굳이 구현을 제공할 필요가 없다면, 추상 메소드가 좋다.
    public abstract void draw(PApplet pApplet);

    public boolean isCollision(int x, int y) {
        return false;
    }

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public int getType() {
        return type;
    }
}

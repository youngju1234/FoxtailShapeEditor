import processing.core.PApplet;
import processing.event.MouseEvent;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Program extends PApplet {

    private List<Shape> shapes = new ArrayList<>();
    private int index = -1;
    private boolean checkShapeCollision = false;
    private boolean isControlPressed = false;


    private static String FILE_PATH = "/Users/dudwn/IdeaProjects/FoxtailShapeEditor/shape_list.txt";


    @Override
    public void settings() {
        this.size(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
    }

    @Override
    public void setup() {
        this.background(0);
        rectMode(CENTER);
        ellipseMode(CENTER);
    }

    @Override
    public void draw() {
        background(0);
        for (Shape shape : shapes) {
            shape.draw(this);
        }
    }

    public static void main(String[] args) {
        PApplet.main("Program");
    }


    private int getCollisionShapeIndex(int x, int y) {

        index = 0;
        for (Shape shape : shapes) {
            checkShapeCollision = shape.isCollision(x, y);
            if (checkShapeCollision) {
                return index;
            }
            index++;
        }

        checkShapeCollision = false;
        return -1;

    }


    @Override
    public void mousePressed(MouseEvent event) {

        if (getCollisionShapeIndex(mouseX, mouseY) != -1) {
            Color color = shapes.get(index).getColor();
            shapes.get(index).setColor(new Color(color.getRGB() + 100));
        }

    }

    @Override
    public void mouseDragged(MouseEvent event) {

        if (checkShapeCollision) {

            shapes.get(index).setX(mouseX);
            shapes.get(index).setY(mouseY);

        }
    }

    @Override
    public void mouseReleased() {

        if (checkShapeCollision) {
            Color color = shapes.get(index).getColor();
            shapes.get(index).setColor(new Color(color.getRGB() - 100));
            checkShapeCollision = false;
            System.out.println("@@" + shapes.size());
        }

    }

    @Override
    public void mouseClicked(MouseEvent event) {

        if (!keyPressed)
            return;

        if (key == '1') {
            shapes.add(new Rect(mouseX, mouseY, setColor(), 1));

        } else if (key == '2') {
            shapes.add(new Circle(mouseX, mouseY, setColor(), 2));

        } else if (key == '3') {
            shapes.add(new Triangle(mouseX, mouseY, setColor(), 3));
        }

        if ((key == 'D' || key == 'd')) {

            if (getCollisionShapeIndex(mouseX, mouseY) != -1) {
                Shape copyShape = shapes.get(index).clone();
                copyShape.setX(mouseX + 10);
                copyShape.setY(mouseY + 10);

                shapes.add(copyShape);

                checkShapeCollision = false;
            }

        }

    }

    @Override
    public void keyPressed() {
        if (keyCode == CONTROL) {
            isControlPressed = true;
        }
        if (!isControlPressed) {
            return;
        }

        if (keyCode == 'S' || keyCode == 's') {
            saveFile();
        }
        if (keyCode == 'Z' || keyCode == 'z') {
            readFile();
        }
    }


    @Override
    public void keyReleased() {
        if (keyCode == CONTROL) {
            isControlPressed = false;
        }
    }

    private Color setColor() {
        int r = (int) (Math.random() * 255);
        int g = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);
        return new Color(r, g, b);
    }


    private void saveFile() {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            for (Shape s : shapes) {
                bos.write((s.getType() + " " + s.getX() + " " + s.getY() + " "
                        + s.getColor().getRed() + " " + s.getColor().getGreen() + " " + s.getColor().getBlue() + "\n").getBytes());
            }

            bos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readFile() {
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            BufferedInputStream bis = new BufferedInputStream(fis);

            int ch;
            String str = "";

            while ((ch = bis.read()) != -1) {
                str += (char) ch;
            }

            getShapesRecord(str);


            bis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void getShapesRecord(String str) {
        String shapeRecord[] = str.split("\n");
        String data[];

        shapes = new ArrayList<>();

        for (String s : shapeRecord) {
            data = s.split(" ");

            int type = parseInt(data[0]);
            int x = parseInt(data[1]);
            int y = parseInt(data[2]);
            Color color = new Color(parseInt(data[3]), parseInt(data[4]), parseInt(data[5]));

            println(s);

            if (type == 1) {
                shapes.add(new Rect(x, y, color, type));
            } else if (type == 2) {
                shapes.add(new Circle(x, y, color, type));
            } else if (type == 3) {
                shapes.add(new Triangle(x, y, color, type));
            }

        }


    }

}




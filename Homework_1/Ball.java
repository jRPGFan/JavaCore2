package Homework_1;

import java.awt.*;
import java.util.Random;

public class Ball extends Sprite {
    Random rnd = new Random();
//    private final Color color = new Color (
//            (int)(Math.random() * 255),
//            (int)(Math.random() * 255),
//            (int)(Math.random() * 255)
//    );

    private final Color color;
    private float vX;
    private float vY;

    Ball() {
        halfHeight = 20 + (float) (Math.random() * 50f);
        halfWidth = halfHeight;
        color = new Color(rnd.nextInt());
        vX = (float) (100f + (Math.random() * 200f));
        vY = (float) (100f + (Math.random() * 200f));
    }

    @Override
    void update(GameCanvas canvas, float deltaTime) {
        x += vX * deltaTime;
        y += vY * deltaTime;

        if (getLeft() < canvas.getLeft()) {
            setLeft(canvas.getLeft());
            vX = -vX;
        }
        if (getRight() > canvas.getRight()) {
            setRight(canvas.getRight());
            vX = -vX;
        }
        if (getTop() < canvas.getTop()) {
            setTop(canvas.getTop());
            vY = -vY;
        }
        if (getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            vY = -vY;
        }

        for (Sprite s1 : MainCircles.sprites) {
            Rectangle r1 = s1.getBounds();
            for (Sprite s2 : MainCircles.sprites) {
                Rectangle r2 = s2.getBounds();

                if (s1 != s2 && r1.intersects(r2)){
                    if (s1.getLeft() > s2.getRight()){
                        s1.setLeft(s2.getRight());
                        vX = -vX;
                    }

                    if (s1.getRight() < s2.getLeft()){
                        s1.setRight(s2.getLeft());
                        vX = -vX;
                    }

                    if (s1.getTop() > s2.getBottom()){
                        s1.setTop(s2.getBottom());
                        vY = -vY;
                    }

                    if (s1.getBottom() < s2.getTop()){
                        s1.setBottom(s2.getTop());
                        vY = -vY;
                    }
                }
            }
        }
    }

    @Override
    void render(GameCanvas canvas, Graphics g) {
        g.setColor(color);
        g.fillOval((int) getLeft(), (int) getTop(),
                (int) getWidth(), (int) getHeight());
    }
}

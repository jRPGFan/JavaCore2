package Homework_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainCircles extends JFrame {

    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    static Sprite[] sprites = new Sprite[1];
	static Sprite background;

    public static void main(String[] args) {
        new MainCircles();
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");
        GameCanvas canvas = new GameCanvas(this);
        add(canvas);
        initApplication();
        setVisible(true);
    }

    private void initApplication() {
        background = new Background();
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Ball();
        }
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);

    }

    private void update(GameCanvas canvas, float deltaTime) {
		background.update(canvas, deltaTime);
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].update(canvas, deltaTime);
            }
        }

    private void render(GameCanvas canvas, Graphics g) {
		background.render(canvas, g);
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].render(canvas, g);
        }
    }
}

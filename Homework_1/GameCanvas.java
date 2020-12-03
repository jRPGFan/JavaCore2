package Homework_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameCanvas extends JPanel {

    long lastFrameTime;
    double delta;
    long timer = System.currentTimeMillis();
    int frames = 0;
    JLabel fps;
    MainCircles controller;

    GameCanvas(MainCircles controller) {
        lastFrameTime = System.nanoTime();
        this.controller = controller;
        GridBagConstraints gBC = new GridBagConstraints();
        fps = new JLabel(Integer.toString(frames));
        gBC.gridx = 0;
        gBC.gridy = 0;
        gBC.weightx = 1;
        gBC.anchor = GridBagConstraints.NORTHWEST;
        gBC.insets = new Insets(2,0,0,2);
        this.add(fps, gBC);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Sprite[] tempArray;
                if(SwingUtilities.isLeftMouseButton(e)){
                    tempArray = new Sprite[controller.sprites.length + 1];
                    System.arraycopy(controller.sprites, 0, tempArray, 0, controller.sprites.length);
                    tempArray[tempArray.length - 1] = new Ball();
                    controller.sprites = tempArray;
                }

                else if(SwingUtilities.isRightMouseButton(e)){
                    if(controller.sprites.length > 0){
                        tempArray = new Sprite[controller.sprites.length - 1];
                        System.arraycopy(controller.sprites, 0, tempArray, 0, controller.sprites.length - 1);
                        controller.sprites = tempArray;
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        lastFrameTime = currentTime;
        controller.onDrawFrame(this, g, deltaTime);
        delta += deltaTime;
        if (delta >= 1) delta--;
        frames++;
        if (System.currentTimeMillis() - timer > 1000){
            timer+=1000;
            fps.setText("FPS: " + Integer.toString(frames));
            frames = 0;
        }

        try {
            Thread.sleep(17); // ~60 fps
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        repaint();
    }

    public int getLeft() { return 0; }
    public int getRight() { return getWidth() - 1; }
    public int getTop() { return 0; }
    public int getBottom() { return getHeight() - 1; }
}

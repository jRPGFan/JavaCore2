package Homework_1;

import javax.swing.*;
import java.awt.*;

public class Background extends Sprite{
    private Color color;
    int r = 0;
    int g = 255;
    int b = 128;

    @Override
    public void update(GameCanvas gameCanvas, float deltaTime){
        if (r == 256) r = 0;
        if (g == -1) g = 255;
        if (b == -1) b = 255;
        color = new Color(r++, g--, b--);
    }

    @Override
    public void render(GameCanvas gameCanvas, Graphics g){
        gameCanvas.setBackground(color);
    }
}

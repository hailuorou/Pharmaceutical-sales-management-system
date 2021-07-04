package com.java.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 用于绘制背景图片
 * */
public class BackPanel extends JDesktopPane{

    Image background;

    public BackPanel(String url) {


        try {
            background = ImageIO.read(new File(url));
        } catch (IOException e) {
            System.out.println("背景图片为读取到");
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0,getWidth(),getHeight(), null);
    }

}

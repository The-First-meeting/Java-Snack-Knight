package ui;

import javax.swing.*;
import java.net.URL;
public class Data {
    public static URL headURL = Data.class.getResource("image/knight.png");
    public static ImageIcon head = new ImageIcon(headURL);
    public static URL bodyURL = Data.class.getResource("image/knight.png");
    public static ImageIcon body = new ImageIcon(bodyURL);
    public static URL bulletURL = Data.class.getResource("image/bullet.png");
    public static ImageIcon bullet = new ImageIcon(bulletURL);
    public static URL brickURL = Data.class.getResource("image/brick.png");
    public static ImageIcon brick = new ImageIcon(brickURL);
    public static URL floorURL = Data.class.getResource("image/floor.png");
    public static ImageIcon floor = new ImageIcon(floorURL);
}

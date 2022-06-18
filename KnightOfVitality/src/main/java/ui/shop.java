package ui;

import run.Run;
import util.Map;
import util.Winner;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Shop extends JPanel implements KeyListener{

    public Shop() {
        this.setLayout(null);
        this.setVisible(true);
        this.setFocusable(true); // 获得焦点坐标
        this.addKeyListener(this);
        init();
    }
    public void init() {
        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        g.setFont(new Font("微软雅黑",Font.BOLD,40));
        g.setColor(Color.white); //设置字体
        g.drawString("经典贪吃蛇",300,200);
        g.drawString("商店",300,300);
        g.setColor(Color.red); //设置字体
        g.drawString("骑士",300,400);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if(keycode == e.VK_ESCAPE)
        {
            this.removeAll();
            repaint();
            Run.frame.remove(Run.frame.sp);
            menu.seen=true;
            Run.frame.repaint();
            Run.frame.requestFocus();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import run.*;
public class menu extends JFrame implements KeyListener, ActionListener {
    static boolean seen;
    GamePanel gp;
    shop sp;
    int index = 0;
    public menu() throws Exception {
        seen = true;
        this.setTitle("yqqs与sbdyy！");
        this.setBounds(300,100,900,720);
        this.setResizable(false);
        this.addKeyListener(this); //添加键盘监听器

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭组件同时停止运行代码
        this.setVisible(true);                                       //显示组件
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if(seen)
        {
            Run.isFinish = false;
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            if(index == 0)
            {
                g.setColor(Color.red); //设置字体
                g.drawString("经典贪吃蛇",300,200);
                g.setColor(Color.white); //设置字体
                g.drawString("商店",300,300);
                g.drawString("骑士",300,400);
            }
            else if(index == 1)
            {
                g.setColor(Color.white); //设置字体
                g.drawString("经典贪吃蛇",300,200);
                g.setColor(Color.red); //设置字体
                g.drawString("商店",300,300);
                g.setColor(Color.white); //设置字体
                g.drawString("骑士",300,400);
            }
            else if(index == 2)
            {
                g.setColor(Color.white); //设置字体
                g.drawString("经典贪吃蛇",300,200);
                g.drawString("商店",300,300);
                g.setColor(Color.red); //设置字体
                g.drawString("骑士",300,400);

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == e.VK_DOWN)
        {
            if(index < 2){
                index++;
                repaint();
            }
        }
        else if(keyCode == e.VK_UP)
        {
            if(index > 0)
            {
                index--;
                repaint();
            }
        }
        else if(keyCode == e.VK_ENTER)
        {
            if(index == 0)
            {
                try {
                    gp = new GamePanel();
                    this.add(gp);
                    gp.requestFocus();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                seen = false;
                repaint();
            }
            else if(index == 1)
            {
                this.add(sp);
                sp.requestFocus();
                this.removeKeyListener(this);
                seen = false;
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

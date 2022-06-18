package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class shop extends JPanel implements KeyListener, ActionListener {
    public shop()
    {
        init();
    }
    public void init()
    {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if(keycode == e.VK_E)
        {
            removeAll();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package ui;

import javax.swing.*;
import java.awt.*;

public class test implements Runnable{
    JLabel spikeOn = new JLabel();
    JLabel spikeOn2 = new JLabel();
    Shop sp;
    public test(Shop sp)
    {
        this.sp = sp;
        ImageIcon spike1 = new ImageIcon("image/spikeOff.png");
        spike1.setImage(spike1.getImage().getScaledInstance(25,25, Image.SCALE_DEFAULT));
        spikeOn.setIcon(spike1);
        spikeOn2.setIcon(spike1);
        this.sp.add(spikeOn);
        this.sp.add(spikeOn2);
        spikeOn2.setBounds(300,300,25,25);
        spikeOn.setBounds(300, 300, 25, 25);
        spikeOn.setVisible(true);
        spikeOn2.setVisible(false);
    }
    @Override
    public void run() {
        while(true){
            boolean t2 = true;
            if(t2) {
                spikeOn.setVisible(true);
                spikeOn2.setVisible(false);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t2 = false;
            }
            else {
                spikeOn.setVisible(false);
                spikeOn2.setVisible(true);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t2 = true;
            }

        }
    }
}

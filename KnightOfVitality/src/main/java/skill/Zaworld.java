package skill;

import ui.Data;
import ui.GamePanel;
import util.Hit;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Zaworld implements Runnable{
    public List<String> list = new ArrayList<>();
    public GamePanel gp;
    public int zaworld_num=0;
    public Zaworld(GamePanel gp) throws Exception {
        this.gp = gp;
        readZaworld();
    }

    public void readZaworld() throws Exception{
        // 构造文件输入流
        FileInputStream fis = new FileInputStream("zaworld/zaworld.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        //直接读取一行数据
        String value = br.readLine();
        list = new ArrayList<>();

        while (value != null) {
            //将读取到的一行数据加入到容器中
            list.add(value);
            value = br.readLine();
        }

        br.close();

        //将读到的字符创转换成整数，并赋值
        String str = list.get(0);
        String[] values = str.split(",");
        this.zaworld_num=Integer.parseInt(values[0]);
    }

    @Override
    public void run() {
    }
}


package util;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Winner {
    public List<String> list = new ArrayList<>();
    public int x;
    public int y;

    public Winner(int index) throws Exception {
        // 构造文件输入流
        FileInputStream fis;
        if(index == 0)
        {
            fis = new FileInputStream("winner/winner1.txt");
        }
        else if(index == 1)
        {
            fis = new FileInputStream("winner/winner2.txt");
        }
        else if(index == 2)
        {
            fis = new FileInputStream("winner/winner3.txt");
        }
        else
        {
            fis  = new FileInputStream("winner/winner1.txt");
        }


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
        this.x = Integer.parseInt(values[0]);
        this.y = Integer.parseInt(values[1]);
    }
    public void winner(int score)
    {
        JOptionPane.showMessageDialog(null, "You win the game! Score:"+score, "元气骑士！",JOptionPane.INFORMATION_MESSAGE);
    }
}

package role;

import ui.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Coin implements Runnable{
    public static List<String> list = new ArrayList<>();
    public GamePanel gp;
    public int bx;
    public int by;
    public boolean flag = true;
    public JLabel coin;

    public Coin(GamePanel gp, String[] values) throws Exception {
        this.gp = gp;
        this.bx = Integer.parseInt(values[0]);
        this.by = Integer.parseInt(values[1]);
        // 设置金币的初始位置
        ImageIcon iconCoin = new ImageIcon("image/coin1.png");
        iconCoin.setImage(iconCoin.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        coin = new JLabel(iconCoin);
        this.gp.add(coin);
        coin.setBounds(this.bx, this.by, 25, 25);
        System.out.println("金币坐标设置成功");
    }

    public void stop() {
        this.flag = false;
    }

    public static List<String> readCoin(int index) throws Exception {
        // 构造文件输入流
        FileInputStream fis = new FileInputStream("coin/coin1.txt");
        if (index == 0) {
            fis = new FileInputStream("coin/coin1.txt");
        } else if (index == 1) {
            fis = new FileInputStream("coin/coin2.txt");
        }else if (index == 2) {
            fis = new FileInputStream("coin/coin3.txt");
        }

        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        // 直接读取一行数据
        String value = br.readLine();
        list = new ArrayList<>();

        while (value != null) {
            // 将读取到的一行数据加入到容器中
            list.add(value);
            value = br.readLine();
        }
        br.close();
        return list;
    }
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
    public static void writeCoin(String str, String path) throws IOException {
        //文件目录
        File writefile;
        BufferedWriter bw;
        writefile = new File(path);
        boolean append = false;  //  是否追加
        if (writefile.exists() == false)   // 判断文件是否存在，不存在则生成
        {
            try {
                writefile.createNewFile();
                writefile = new File(path);
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        } else {        // 存在先删除，再创建
            writefile.delete();
            try {
                writefile.createNewFile();
                writefile = new File(path);
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(writefile, append);
            bw = new BufferedWriter(fw);
            fw.write(str);
            fw.flush();
            fw.close();

        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
    @Override
    public void run() {

    }
}

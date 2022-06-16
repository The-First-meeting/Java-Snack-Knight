package role;

import ui.GamePanel;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Helmet implements Runnable{
    public List<String> list = new ArrayList<>();

    public GamePanel gp;
    public int hx;
    public int hy;
    public int hel_num;
    public int toward;//上下左右 对应 1234

    public JLabel jhelmet = new JLabel();
    public Helmet(GamePanel gp) throws Exception {
        this.gp = gp;
        readHelmet();
    }

    public void readHelmet() throws Exception{
        // 构造文件输入流
        FileInputStream fis = new FileInputStream("helmet/helmet.txt");
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
        this.hel_num=Integer.parseInt(values[0]);
    }
    public static void writeHel(String str, String path) throws IOException {
        //文件目录
        File writefile;
        BufferedWriter bw;
        writefile = new File(path);
        boolean append = false;  //  是否追加
        if (!writefile.exists())   // 判断文件是否存在，不存在则生成
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

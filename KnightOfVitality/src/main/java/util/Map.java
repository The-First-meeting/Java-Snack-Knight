package util;

import org.junit.Test;
import role.Knight;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Map {

    //数据容器
    public List<String> list = new ArrayList<>();
    // 二维数组元素又是一个一维数组：行列矩阵
    public int[][] map = null;



    public int[][] readMap(int index) throws Exception {
        // 构造文件输入流
        FileInputStream fis;
        if(index == 0)
        {
            fis = new FileInputStream("map/map1.txt");
        }
        else if(index == 1)
        {
            fis = new FileInputStream("map/map2.txt");
        }
        else if(index == 2)
        {
            fis = new FileInputStream("map/map3.txt");
        }
        else
        {
            fis  = new FileInputStream("map/map1.txt");
        }


        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        //直接读取一行数据
        String value = br.readLine();
        list = new ArrayList<>();

        while (value != null) {
            //将读取到的一行数据加入到容器中
            //System.out.println("访问到txt");
            list.add(value);
            value = br.readLine();
        }

        br.close();

        //得到多少行多少列
        int row = list.size();
        int cloum = 0;

        for (int i = 0; i < 1; i++) {
            String str = list.get(i);
            String[] values = str.split(",");
            cloum = values.length;
        }


        map = new int[row][cloum];

        //将读到的字符创转换成整数，并赋值给二位数组map
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            String[] values = str.split(",");
            for (int j = 0; j < values.length; j++) {
                map[i][j] = Integer.parseInt(values[j]);
            }
        }
        return map;
    }
}

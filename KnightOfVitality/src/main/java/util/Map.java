package util;

import org.junit.Test;

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


    @Test
    public void testResult() throws Exception {
        int[][] result = readMap();
        // 二维数组的内容输出，看一下是否是地图的配置信息
        for(int i = 0 ; i < result.length ; i++ ){
            for(int j = 0 ; j < result[i].length ; j++) {
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }
    }

    public int[][] readMap() throws Exception {
        // 构造文件输入流
        FileInputStream fis = new FileInputStream("./map.txt");


        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        //直接读取一行数据
        String value = br.readLine();

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

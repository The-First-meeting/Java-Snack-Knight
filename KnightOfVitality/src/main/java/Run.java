import ui.GamePanel;

import javax.swing.*;

public class Run {
    //主函数，程序入口
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("元气骑士与sbdyy");              //创建一个普通的窗体对象
        frame.setBounds(300,100,900,720);       //设置组件的位置与长宽
//        frame.setLocation(300,100);                                 //设置组件的显示位置
        frame.setResizable(false);                                    //设置组件大小不可被用户改变
        frame.add(new GamePanel());                                   //向容器中增加组件（新建一个GamePanel类在窗体中显示）
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭组件同时停止运行代码
        frame.setVisible(true);
    }
}

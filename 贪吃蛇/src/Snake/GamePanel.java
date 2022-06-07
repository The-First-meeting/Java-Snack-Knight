package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    //定义蛇的数据结构
    int length; //蛇的长度
    int[] snakeX = new int [600]; //蛇的X坐标
    int[] snakeY = new int [500]; //蛇的Y坐标
    String fx; //蛇头的方向

    int score; //成绩
    int power; //倍率

    //食物的坐标
    int foodX;
    int foodY;
    Random random = new Random();
    //游戏当前的状态： 开始/停止
    boolean isStart; //默认不开始
    boolean isFail;
    //定时器 以毫秒为单位 1000ms = 1s, 第一个参数为刷新周期
    Timer timer = new Timer(50,this);
    //构造函数
    public GamePanel(){
        this.setVisible(true);
        isStart = false;
        isFail = false;
        init();
        //获得焦点和键盘事件
        this.setFocusable(true); //获得焦点坐标
        this.addKeyListener(this); //添加键盘监听器
        timer.start(); //游戏一开始定时器就启动
    }

    //初始化方法
    public void init(){
        //每个像素格都是25*25
        length = 3;
        snakeX[0] = 100;snakeY[0] = 100; //脑袋的坐标
        snakeX[1] = 75;snakeY[1] = 100; //第一个身体的坐标
        snakeX[2] = 50;snakeY[2] = 100; //第二个身体的坐标

        fx = "R";
        foodX = 25 + 25*random.nextInt(34);
        foodY = 75 + 25*random.nextInt(24);
        power = 1;
        score = 0;
    }

    //绘制面板
    //我们游戏中的所有东西，都是用这个类画
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 清屏

        //绘制静态面板
        this.setBackground(Color.BLACK);
        Data.header.paintIcon(this,g,25,10);//广告栏
        g.fillRect(25,75,850,600);//默认游戏界面
        //画成绩
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑",Font.BOLD,15));
        g.drawString("长度:"+length,750,35);
        g.drawString("分数:"+score,750,50);
        //画食物
        //ImageIcon image=new ImageIcon(Data.food.getImage().getScaledInstance(Data.food.getIconWidth()*power,Data.food.getIconHeight()*power,Image.SCALE_DEFAULT));
        Data.food.paintIcon(this,g,foodX,foodY);

        //把蛇头画上去
        if(fx.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]); //蛇头初始化向右
        }
        else if(fx.equals("L")){
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]); //蛇头初始化向左
        }
        else if(fx.equals("U")){
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]); //蛇头初始化向上
        }
        else if(fx.equals("D")){
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]); //蛇头初始化向下
        }

        //画身体
        for(int i = length-1;i>0;i--){
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }


        if (isStart == false) {
            g.setColor(Color.white); //设置字体
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏",300,300);
        }

        if(isFail){
            g.setColor(Color.red); //设置字体
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("失败，按下空格重新开始",300,300);
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        //监听键盘
        int keyCode = keyEvent.getKeyCode();

        if(keyCode == KeyEvent.VK_SPACE){
            if(isFail){
                //重新开始
                isFail = false;
                init();
                timer.setDelay(50);
            }
            else if(isStart == false)//开始游戏
            {
                isStart = !isStart;
                timer.setDelay(50);
            }
            repaint();
        }
        //小蛇转向
        if(keyCode == keyEvent.VK_LEFT){
            if(fx.equals("R"));
            else
            fx = "L";
        }else if(keyCode == keyEvent.VK_DOWN){
            if(fx.equals("U"));
            else
            fx = "D";
        }else if(keyCode == keyEvent.VK_UP){
            if(fx.equals("D"));
            else
            fx = "U";
        }else if(keyCode == keyEvent.VK_RIGHT){
            if(fx.equals("L"));
            else
            fx = "R";
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
    //事件监听--需要通过固定事件来刷新，1s--10次
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(isStart&&!isFail){
            //如果游戏是开始状态，就让小蛇动起来

            //吃食物
            if(snakeX[0] == foodX&&snakeY[0] == foodY){
                length += 1 * power; //长度+1
                score += 10 * power;
                if(score>=100&&power==1)
                {
                    power++;
                    timer.setDelay(40);//加速

                }
                else if(score>=300&&power==2)
                {
                    power++;
                    timer.setDelay(30);
                }
                foodX = 25 + 25*random.nextInt(34);
                foodY = 75 + 25*random.nextInt(24);
            }
            //身体部分后一节移到前一节的位置
            for(int i = length-1;i>0;i--){
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            if(fx.equals("R")) {
                snakeX[0] = snakeX[0] + 25;
                //边界判断
                if(snakeX[0]>850){
                    snakeX[0] = 25;
                    //isFail = true;
                }
            }else if(fx.equals("L")) {
                snakeX[0] = snakeX[0] - 25;
                //边界判断
                if(snakeX[0]<25){
                   snakeX[0] = 850;
                   // isFail = true;
                }
            }else if(fx.equals("U")) {
                snakeY[0] = snakeY[0] - 25;
                //边界判断
                if(snakeY[0]<75){
                    snakeY[0] = 650;
                    //isFail = true;
                }
            }else if(fx.equals("D")) {
                snakeY[0] = snakeY[0] + 25;
                //边界判断
                if(snakeY[0]>650){
                    snakeY[0] = 75;
                    //isFail = true;
                }
            }
            //失败判定
            for(int i = 1;i<length;i++)
            {
                if(snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i])
                    isFail = true;
            }

            repaint(); //重画页面
        }
    }
}

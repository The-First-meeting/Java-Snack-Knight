package ui;

import role.Bullet;
import role.Knight;
import util.GameOver;
import util.Map;
import util.Winner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    public int index;
    public Knight knight;
    public Winner winner;
    public int length = 2;
    Thread nightThread;
    Thread bulletThread;
    boolean isStart;
    boolean isFail;
    boolean isWin;
    public Bullet bullet;
    Timer timer = new Timer(300,this);//时钟信息
    public int[][] map = null;//地图信息
    // 实例代码块中初始化地图资源的数据
    Map mp = new Map();

    //构造函数
    public GamePanel(){
        this.setLayout(null);
        this.setVisible(true);
        this.setFocusable(true); //获得焦点坐标
        this.addKeyListener(this);
        index = 0;
        isStart = false;
        isFail = false;
        isWin = false;
        init();
        timer.start();//时钟启动
    }

    public void init(){
        isFail = false;
        isStart = false;
        isWin = false;
        // 读取地图，并配置地图
        try {
            knight=new Knight(this,length,index);
            bullet=new Bullet(this);
            winner=new Winner(index);
            map = mp.readMap(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置终点
        JLabel win=new JLabel(new ImageIcon("image/winner.png"));
        win.setBounds(winner.x,winner.y,25,25);
        this.add(win);
        for (int i = 0; i < map.length; i++) {
            //System.out.println("map.length="+map.length);
            for (int j = 0; j < map[0].length; j++) {
                //System.out.println("现在是:"+i+"行，"+j+"列");
                if (map[i][j]==1)//1是道路
                {
                    JLabel j1=new JLabel(new ImageIcon("image/floor.png"));
                    j1.setBounds(25*j,25*(i-1)+25,25,25);
                    this.add(j1);
                }
                else if(map[i][j]==0)//2是墙壁
                {
                    JLabel j2=new JLabel(new ImageIcon("image/brick.png"));
                    j2.setBounds(25*j,25*(i-1)+25,25,25);
                    this.add(j2);
                }
                else
                {
                    System.out.println("存在非0和1的元素!");
                }
            }
        }

        //设置骑士的线程
        nightThread = new Thread(knight);
        nightThread.start();
        //子弹的线程
        bulletThread = new Thread(bullet);
        bulletThread.start();

    }

//    @Override
//    protected void paintComponent(Graphics g){
//        if (!isStart) {
//            g.setColor(Color.black); //设置字体
//            g.setFont(new Font("微软雅黑",Font.BOLD,40));
//            g.drawString("按下空格开始游戏",300,300);
//        }
//        if(isFail){
//            g.setColor(Color.red); //设置字体
//            g.setFont(new Font("微软雅黑",Font.BOLD,40));
//            g.drawString("失败，按下空格重新开始",300,300);
//        }
//        if(isWin){
//            g.setColor(Color.red); //设置字体
//            g.setFont(new Font("微软雅黑",Font.BOLD,40));
//            g.drawString("胜利！按下空格重新开始",300,300);
//        }
//        if(isStart&&!isFail&&!isWin){
//            super.paintComponent(g); // 清屏
//        }
//    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //游戏开始
        if(isStart&&!isFail) {
            if (knight.toward == 1)//方向向上时
            {
                knight.bool = knight.hit.hitCheck(knight.kx, knight.ky, knight.toward, this.map);//未碰壁
                if (knight.bool) {
                    for (int i = knight.klenth - 1; i > 0; i--)//身体前进
                    {
                        knight.Tx[i] = knight.Tx[i - 1];
                        knight.Ty[i] = knight.Ty[i - 1];
                    }
                    knight.Tx[0] = knight.kx;
                    knight.Ty[0] = knight.ky;
                    knight.ky -= 25;//头前进
                }
            } else if (knight.toward == 2)//下
            {
                knight.bool = knight.hit.hitCheck(knight.kx, knight.ky, knight.toward, this.map);
                if (knight.bool) {
                    for (int i = knight.klenth - 1; i > 0; i--) {
                        knight.Tx[i] = knight.Tx[i - 1];
                        knight.Ty[i] = knight.Ty[i - 1];
                    }
                    knight.Tx[0] = knight.kx;
                    knight.Ty[0] = knight.ky;
                    knight.ky += 25;
                }
            } else if (knight.toward == 3)//左
            {
                knight.bool = knight.hit.hitCheck(knight.kx, knight.ky, knight.toward, this.map);
                if (knight.bool) {
                    for (int i = knight.klenth - 1; i > 0; i--) {
                        knight.Tx[i] = knight.Tx[i - 1];
                        knight.Ty[i] = knight.Ty[i - 1];
                    }
                    knight.Tx[0] = knight.kx;
                    knight.Ty[0] = knight.ky;
                    knight.kx -= 25;
                }
            } else if (knight.toward == 4)//右
            {
                knight.bool = knight.hit.hitCheck(knight.kx, knight.ky, knight.toward, this.map);
                if (knight.bool) {
                    for (int i = knight.klenth - 1; i > 0; i--) {
                        knight.Tx[i] = knight.Tx[i - 1];
                        knight.Ty[i] = knight.Ty[i - 1];
                    }
                    knight.Tx[0] = knight.kx;
                    knight.Ty[0] = knight.ky;
                    knight.kx += 25;
                }
            }
            //刷新图画
            knight.jknight.setBounds(knight.kx, knight.ky, 25, 25);
            for (int i = 0; i < knight.klenth; i++) {
                knight.jTail[i].setBounds(knight.Tx[i], knight.Ty[i], 25, 25);
            }
            knight.isChangeToward = true;
            //头与子弹碰撞————失败
            for(int i = 0;i < this.bullet.num; i++)
            {
                if (this.bullet.tx[i] == this.knight.kx && Math.abs(this.bullet.ty[i] - this.knight.ky) <= 25) {
                    bullet.stop();
                    isFail = true;
                    GameOver go = new GameOver();
                    go.gameOver();
                    repaint();
                }
                for (int j = 0; j < knight.klenth; j++) {
                    //尾巴与子弹碰撞————切断
                    if (this.bullet.tx[i] == this.knight.Tx[j] && Math.abs(this.bullet.ty[i] - this.knight.Ty[j]) <= 25) {
                        for (int k = j; k < knight.klenth; k++) {
                            this.knight.gp.remove(this.knight.jTail[k]);
                        }
                        this.knight.klenth = j;
                    }
                    //咬到尾巴————失败
                    if (this.knight.Tx[j] == this.knight.kx && this.knight.Ty[j] == this.knight.ky && knight.bool) {
                        bullet.stop();
                        isFail = true;
                        GameOver go = new GameOver();
                        go.gameOver();
                        repaint();
                    }
                }
            }




            //胜利判断
            if (knight.kx == winner.x && knight.ky == winner.y && !isWin) {
                //获得胜利
                isWin = true;
                winner.winner(knight.klenth);
                bullet.stop();
                isFail = false;
                index++;
                repaint();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(isStart&&!isFail&&!isWin) {
            if (knight.isChangeToward) {
                //转向
                if (keyCode == e.VK_LEFT) {
                    if (knight.toward != 4 && knight.hit.hitCheck(knight.kx, knight.ky, 3, this.map)) {
                        knight.toward = 3;
                        knight.isChangeToward = false;
                    }
                } else if (keyCode == e.VK_RIGHT) {
                    if (knight.toward != 3 && knight.hit.hitCheck(knight.kx, knight.ky, 4, this.map)) {
                        knight.toward = 4;
                        knight.isChangeToward = false;
                    }
                } else if (keyCode == e.VK_UP) {
                    if (knight.toward != 2 && knight.hit.hitCheck(knight.kx, knight.ky, 1, this.map)) {
                        knight.toward = 1;
                        knight.isChangeToward = false;
                    }
                } else if (keyCode == e.VK_DOWN) {
                    if (knight.toward != 1 && knight.hit.hitCheck(knight.kx, knight.ky, 2, this.map)) {
                        knight.toward = 2;
                        knight.isChangeToward = false;
                    }
                }
            }
        }
        else
        {
            if(keyCode == KeyEvent.VK_SPACE){
                if(isFail){
                    //重新开始
                    this.removeAll();
                    init();
                    isStart = true;
                    timer.setDelay(300);
                }
                else if(!isWin)//开始游戏
                {
                    isStart = true;
                    timer.setDelay(300);
                }
                else {//游戏胜利
                    this.removeAll();
                    init();
                    timer.setDelay(300);
                }
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

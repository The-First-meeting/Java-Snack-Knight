package ui;

import role.*;
import util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    public java.util.List<String> listBullet = new ArrayList<>();
    public java.util.List<String> listArrow = new ArrayList<>();
    public int index;
    public Knight knight;
    public Winner winner;
    public int length = 2;
    Thread nightThread;
    Thread[] bulletThread = new Thread[100];
    Thread[] arrowThread = new Thread[100];
    boolean isStart;
    boolean isFail;
    boolean isWin;
    public Bullet[] bullet = new Bullet[100];
    public Arrow[] arrow = new Arrow[100];
    public int bulletNum;
    public int arrowNum;
    Timer timer = new Timer(300,this);//时钟信息
    public int[][] map = null;//地图信息
    // 实例代码块中初始化地图资源的数据
    Map mp = new Map();

    //构造函数
    public GamePanel() throws Exception {
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

    public void init() throws Exception {
        isFail = false;
        isStart = false;
        isWin = false;
        // 读取地图，并配置地图
        try {
            knight=new Knight(this,length,index);
            winner=new Winner(index);
            map = mp.readMap(index);
            listBullet = Bullet.readBullet(index);
            listArrow = Arrow.readArrow(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        bulletNum = listBullet.size();
        for(int i = 0; i < bulletNum; i++)
        {
            String str = listBullet.get(i);
            String[] values = str.split(",");
            bullet[i] = new Bullet(this,values);
        }
        arrowNum = listArrow.size();
        for(int i = 0; i < arrowNum; i++)
        {
            String str = listArrow.get(i);
            String[] values = str.split(",");
            arrow[i] = new Arrow(this,values);
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
        for(int i = 0; i < bulletNum; i++)
        {
            bulletThread[i] = new Thread(bullet[i]);
            bulletThread[i].start();
        }
        //弩箭的线程
        for(int i = 0; i < arrowNum; i++)
        {
            arrowThread[i] = new Thread(arrow[i]);
        }
    }

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
            for(int i = 0; i < this.bulletNum; i++)
            {
                if(bullet[i].toward == 1)//竖着
                {
                    if (this.bullet[i].bx == this.knight.kx && Math.abs(this.bullet[i].by - this.knight.ky) <= 24) {
                        bullet[i].stop();
                        isFail = true;
                        GameOver go = new GameOver();
                        go.gameOver();
                        repaint();
                    }
                    for (int j = 0; j < knight.klenth; j++) {
                        //尾巴与子弹碰撞————切断
                        if (this.bullet[i].bx == this.knight.Tx[j] && Math.abs(this.bullet[i].by - this.knight.Ty[j]) <= 24) {
                            for (int k = j; k < knight.klenth; k++) {
                                this.knight.gp.remove(this.knight.jTail[k]);
                            }
                            this.knight.klenth = j;
                            repaint();
                        }
                    }
                }
                else if(bullet[i].toward == 0)//横着
                {
                    if (this.bullet[i].by == this.knight.ky && Math.abs(this.bullet[i].bx - this.knight.kx) <= 24) {
                        bullet[i].stop();
                        isFail = true;
                        GameOver go = new GameOver();
                        go.gameOver();
                        repaint();
                    }
                    for (int j = 0; j < knight.klenth; j++) {
                        //尾巴与子弹碰撞————切断
                        if (this.bullet[i].by == this.knight.Ty[j] && Math.abs(this.bullet[i].bx - this.knight.Tx[j]) <= 24) {
                            for (int k = j; k < knight.klenth; k++) {
                                this.knight.gp.remove(this.knight.jTail[k]);
                            }
                            this.knight.klenth = j;
                            repaint();
                        }
                    }
                }
            }
            //与弩箭碰撞
            for(int i = 0; i < this.arrowNum; i++)
            {
                //头触发弩箭
                if(arrow[i].setx == this.knight.kx && arrow[i].sety == this.knight.ky)
                {
                    arrow[i].shot();
                    arrowThread[i].start();
                }
                if(arrow[i].toward == 0 || arrow[i].toward == 1)//竖着
                {
                    if (this.arrow[i].ax == this.knight.kx && Math.abs(this.arrow[i].ay - this.knight.ky) <= 24) {
                        isFail = true;
                        GameOver go = new GameOver();
                        go.gameOver();
                        repaint();
                    }
                    for (int j = 0; j < knight.klenth; j++) {
                        //尾巴与弩箭碰撞————切断
                        if (this.arrow[i].ax == this.knight.Tx[j] && Math.abs(this.arrow[i].ay - this.knight.Ty[j]) <= 24) {
                            for (int k = j; k < knight.klenth; k++) {
                                this.knight.gp.remove(this.knight.jTail[k]);
                            }
                            this.knight.klenth = j;
                            repaint();
                        }
                    }
                }
                else//横着
                {
                    if (this.arrow[i].ay == this.knight.ky && Math.abs(this.arrow[i].ax - this.knight.kx) <= 24) {
                        isFail = true;
                        GameOver go = new GameOver();
                        go.gameOver();
                        repaint();
                    }
                    for (int j = 0; j < knight.klenth; j++) {
                        //尾巴与子弹碰撞————切断
                        if (this.arrow[i].ay == this.knight.Ty[j] && Math.abs(this.arrow[i].ax - this.knight.Tx[j]) <= 24) {
                            for (int k = j; k < knight.klenth; k++) {
                                this.knight.gp.remove(this.knight.jTail[k]);
                            }
                            this.knight.klenth = j;
                            repaint();
                        }
                    }
                }
            }

            for (int i = 0; i < knight.klenth; i++) {
                //咬到尾巴————失败
                if (this.knight.Tx[i] == this.knight.kx && this.knight.Ty[i] == this.knight.ky && knight.bool) {
                    System.out.println(this.knight.Ty[i] + "====" + this.knight.ky);
                    isFail = true;
                    GameOver go = new GameOver();
                    go.gameOver();
                    repaint();
                }
            }

            //胜利判断
            if (knight.kx == winner.x && knight.ky == winner.y && !isWin) {
                //获得胜利
                this.length = knight.klenth;
                isWin = true;
                winner.winner(knight.klenth);
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
                    try {
                        init();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
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
                    try {
                        init();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
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

package util;

public class Hit {

    public boolean hitCheck(int x,int y,int direction,int map[][])
    {
        //1是上，2是下，3是左，4是右
        int temp=0;
        switch (direction)
        {
            case 1:
                if(y>=25&&y<=650)
                {
                    //System.out.println("x="+x+"y="+y);//100,20
                    x=x/25;//5
                    y=y/25;//1
                    //System.out.println("x="+x+"y="+y);
                    if (y==0){
                        return false;
                    }
                    else if(map[y-1][x]==0){
                        return false;
                    }
                    else return true;
                }
                break;
            case 2:
                if(y>=0&&y<=625)
                {
                    //System.out.println("x="+x+"y="+y);//20,280
                    x=x/25;//5
                    y=y/25;//1
                    //System.out.println("x="+x+"y="+y);
                    if (map[y+1][x]==0) return false;
                    else return true;
                }
                break;
            case 3://左
                if(x>=25&&x<=850)
                {
                    //System.out.println("x="+x+"y="+y);//100,20
                    x=x/25;//5
                    y=y/25;//1
                    //System.out.println("x="+x+"y="+y);
                    if (map[y][x-1]==0) return false;
                    else return true;
                }
                break;
            case 4:
                if(x>=0&&x<=825)
                {
                    //System.out.println("x="+x+"y="+y);//40,20
                    x=x/25;//2
                    y=y/25;//1
                   // System.out.println("x="+x+"y="+y);
                    if (map[y][x+1]==0) return false;
                    else return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }
}

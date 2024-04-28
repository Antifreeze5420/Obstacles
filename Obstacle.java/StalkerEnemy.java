import java.awt.*;

public class StalkerEnemy extends Enemy
{
    
   
    private int speed;
    private Rectangle player;
    public StalkerEnemy(int x, int y, int w, int h, Rectangle Rect,int sp) {
        super(x,y,w,h);
        player = Rect;
        speed = sp;

    }
    public Color getColor(){
        return Color.red;
    }
    public void move(){
        if(Math.abs(super.getRectangle().getX()-player.getX())>speed){
            super.getRectangle().setLocation((int)super.getRectangle().getX()-(2*((super.getRectangle().getX()>player.getX()) ? 1 : 0)-1)*speed,(int)super.getRectangle().getY());
        }else{
            super.getRectangle().setLocation((int)player.getX(),(int)super.getRectangle().getY());
        }
        if(Math.abs(super.getRectangle().getY()-player.getY())>speed){
            super.getRectangle().setLocation((int)super.getRectangle().getX(),(int)super.getRectangle().getY()-(2*((super.getRectangle().getY()>player.getY()) ? 1 : 0)-1)*speed);
        }else{
            super.getRectangle().setLocation((int)super.getRectangle().getX(),(int)player.getY());
        }
    }
}
import java.awt.*;

public class BomberEnemy extends StalkerEnemy{
    private int bomb_timer;
    private int time; 
    private double w;
    private double h;
    private Color color;
    public BomberEnemy(int x, int y, int w, int h, Rectangle Rect,int bomb_time){
        super(x,y,w,h,Rect,1);
        bomb_timer = 0;
        time = bomb_time;
        color = new Color(128,128,0);
    }

    public Color getColor(){
        return color;
    }
    public void move(){
        super.move();
        bomb_timer++;
        if(bomb_timer==time){
            w = super.getRectangle().getWidth();
            h = super.getRectangle().getHeight();
            super.getRectangle().setRect(super.getRectangle().getX()-4*super.getRectangle().getWidth(),super.getRectangle().getY()-4*super.getRectangle().getHeight(),super.getRectangle().getWidth()*5,super.getRectangle().getHeight()*5);
            color = new Color(128,128,128);
            
        }
        if(bomb_timer==time+15){
            super.getRectangle().setRect(super.getRectangle().getX()+2*w,super.getRectangle().getY()+2*h,w,h);
            bomb_timer = 0;
            color = new Color(128,128,0);
        }
    }

}
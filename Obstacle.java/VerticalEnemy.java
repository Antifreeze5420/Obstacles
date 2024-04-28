import java.awt.*;

public class VerticalEnemy extends Enemy
{
    private int vert_speed;
    private int screen_height;
    private int bottom_level;
    private int top_level;
    private boolean up;
    public VerticalEnemy(int x, int y, int w, int h, int sH, int yS) {
        super(x,y,w,h);
        vert_speed = yS;
        screen_height = sH;
        top_level = 0;
        bottom_level = sH-h;
        up = true;
    }
    public VerticalEnemy(int x, int y, int w, int h,int tL, int bL,int yS) {
        super(x,y,w,h);
        vert_speed = yS;
        
        top_level = tL;
        bottom_level = bL-h;
        up = true;
    }
    
    public Color getColor(){
        return Color.cyan;
    }
    public void move(){
        
        if(super.getRectangle().getY()>=bottom_level){
            up = true;
        }else if (super.getRectangle().getY()<= top_level){
            up = false;
        }
        super.getRectangle().setLocation((int)super.getRectangle().getX(),(int)super.getRectangle().getY()-(2*((up) ? 1 : 0)-1)*vert_speed);
    }
}
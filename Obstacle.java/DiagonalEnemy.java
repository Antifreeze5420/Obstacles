import java.awt.*;

public class DiagonalEnemy extends Enemy
{

    private int vert_speed;
    private int hori_speed;
    private int screen_height;
    private int screen_width;
    private int bottom_level;
    private int top_level;
    private int right_level;
    private int left_level;
    private boolean up;
    public DiagonalEnemy(int x, int y, int w, int h, int sH, int yS,int sW, int xS) {
        super(x,y,w,h);
        vert_speed = yS;
        hori_speed = xS;
        screen_height = sH;
        screen_width = sW;
        top_level = 0;
        bottom_level = sH-h;
        right_level = sW-w;
        left_level = 0;
        up = true;
    }
    public DiagonalEnemy(int x, int y, int w, int h, int sH, int yS,int tL, int bL,int rL,int lL, int sW, int xS) {
        super(x,y,w,h);
        vert_speed = yS;
        hori_speed = xS;
        screen_height = sH;
        screen_width = sW;
        top_level = tL;
        bottom_level = bL-h;
        right_level = rL-w;
        left_level = lL;
        up = true;
    }
    
    public Color getColor(){
        return Color.black;
    }
    public void move(){
        
        if(super.getRectangle().getY()>=bottom_level || super.getRectangle().getX()>=right_level){
            up = true;
        }else if (super.getRectangle().getY()<= top_level ||super.getRectangle().getX()<= left_level){
            up = false;
        }
        
        super.getRectangle().setLocation((int)super.getRectangle().getX()-(2*((up) ? 1 : 0)-1)*hori_speed,(int)super.getRectangle().getY()-(2*((up) ? 1 : 0)-1)*vert_speed);
    }

}
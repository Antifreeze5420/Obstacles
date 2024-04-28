import java.awt.*;

public class Teleporter extends Enemy
{
    private Enemy[] enemies;
    private double teleport_speed;
    public Teleporter(int x, int y, int w, int h, Enemy[] b,double tS){
        super(x,y,w,h);
        enemies = b;
        teleport_speed = tS;
    }
    public Color getColor(){
        return Color.orange;
    }
    public void updateEnemies(Enemy[] a){enemies = a;}
    public void move(){
        if(Math.random()>teleport_speed){
            
            int enemy_1 = (int) (Math.random()*enemies.length);
            int enemy_2 = (int) (Math.random()*(enemies.length-1));
            enemy_2 += ((enemy_2>=enemy_1)? 1: 0);
            if(enemies[enemy_1] != null && enemies[enemy_2] !=null ){
                int temp_x = (int)enemies[enemy_1].getRectangle().getX();
                int temp_y = (int)enemies[enemy_1].getRectangle().getY();
                enemies[enemy_1].getRectangle().setLocation((int)enemies[enemy_2].getRectangle().getX(),(int)enemies[enemy_2].getRectangle().getY());
                enemies[enemy_2].getRectangle().setLocation(temp_x,temp_y);
            }
        }    
    }

}
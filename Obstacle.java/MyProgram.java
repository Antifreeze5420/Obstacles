import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class MyProgram extends JPanel implements ActionListener, KeyListener {

    private Rectangle player = new Rectangle(); //a rectangle that represents the player
    private Rectangle goal = new Rectangle(); //a rectangle that represents the goal
    private Enemy[] enemies = new Enemy[9]; //the array of Enemy objects
    private Enemy[][] enemy_levels = new Enemy[5][1];
   private Rectangle[] walls = new Rectangle[9];
   private Rectangle[][] wall_levels = new Rectangle[5][1];
    private boolean up, down, left, right; //booleans that track which keys are currently pressed
    private Timer timer; //the update timer
   
    private int gameWidth = 475; //the width of the game area
    private int gameHeight = 330; //the height of the game area
    private int level = 0;

    private static JLabel dialogLabel;
    private static JFrame frame;
    private static JDialog dialog;
   
    //Sets up the basic GUI for the game
    public static void main(String[] args) {
        frame = new JFrame();
       
        dialog = new JDialog(frame, "Status");
        dialogLabel = new JLabel("");
        dialogLabel.setHorizontalAlignment(JLabel.CENTER);
        dialog.add(dialogLabel);
        dialog.setBounds(125, 125, 100, 70);
        dialog.setVisible(false);
       
        frame.setTitle("Obstacle Game");
        frame.setLayout(new BorderLayout());
       
        MyProgram game = new MyProgram();
        frame.add(game, BorderLayout.CENTER);
      
        game.addKeyListener(game);
        frame.addKeyListener(game);
       
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
       
        game.setUpGame(0);
        // game.enterFullScreen();
    }
   
    //Constructor for the game panel
    public MyProgram() {
         setPreferredSize(new Dimension(gameWidth, gameHeight));
    }
   
    //Method that is called by the timer 30 times per second (roughly)
    //Most games go through states - updating objects, then drawing them
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }
   
    //Called every time a key is pressed
    //Stores the down state for use in the update method
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP|| e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN|| e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT|| e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT|| e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
    }
   
    //Called every time a key is released
    //Stores the down state for use in the update method
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP|| e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN|| e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT|| e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT|| e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
    }
   
    //Called every time a key is typed
    public void keyTyped(KeyEvent e) {
    }
   
    //Sets the initial state of the game
    //Could be modified to allow for multiple levels
    public void setUpGame(int l) {
   
        if(timer != null) {
            timer.stop();
        }
   
        timer = new Timer(1000 / 30, this); //roughly 30 frames per second
        timer.start();
       
        up = down = left = right = false;
   
        player = new Rectangle(50, 50, 20, 20);
        goal = new Rectangle(400, 300, 20, 20);
        Teleporter s = new Teleporter(200,100,20,20,enemies,0.2);
        Teleporter s1 = new Teleporter(200,100,20,20,enemies,0.2);
        Teleporter s2 = new Teleporter(200,100,20,20,enemies,0.3);
        enemy_levels[3] = new Enemy[]{new SpinningEnemy(250, 200, 20, 20, 100),new VerticalEnemy(200, 300, 20, 20, gameHeight, 10),new DiagonalEnemy(300, 300, 20, 20, gameHeight, 5, gameWidth, 3),new DiagonalEnemy(250, 270, 20, 20, gameHeight, 3, gameWidth, 4),s1, new StalkerEnemy(200, 200, 20, 20, player,3), new StalkerEnemy(150,150,20, 20, player,2),new DiagonalEnemy(250, 250, 20, 20, gameHeight, 5, gameWidth, 7),new VerticalEnemy(220, 300, 20, 20, gameHeight, 3),new BomberEnemy(200,200,20,20,player,40)};
        enemy_levels[4] = new Enemy[]{new SpinningEnemy(250, 200, 20, 20, 100),new VerticalEnemy(200, 300, 20, 20, gameHeight, 10),new DiagonalEnemy(300, 300, 20, 20, gameHeight, 5, gameWidth, 3),new DiagonalEnemy(250, 270, 20, 20, gameHeight, 3, gameWidth, 4), s2, new StalkerEnemy(200, 200, 20, 20, player,3), new StalkerEnemy(150,150,20, 20, player,2),new DiagonalEnemy(250, 250, 20, 20, gameHeight, 5, gameWidth, 7),new VerticalEnemy(220, 300, 20, 20, gameHeight, 3),new BomberEnemy(200,200,20,20,player,40)};
        Teleporter a = new Teleporter(420,33,19,20,enemies,0.7);
        enemy_levels[0] = new Enemy[]{new VerticalEnemy(220,120,19,20,20,120,2),new VerticalEnemy(260,40,19,20,20,120,2),new VerticalEnemy(180,35,19,20,20,120,2),new VerticalEnemy(140,95,19,20,20,120,2),new VerticalEnemy(300,74,19,20,20,120,2),new VerticalEnemy(340,62,19,20,20,120,2),new VerticalEnemy(380,34,19,20,20,120,2), a,new HorizontalEnemy(29,120,20,20,0,100,1),new HorizontalEnemy(98,160,20,20,0,100,2),new HorizontalEnemy(48,200,20,20,0,100,2),new HorizontalEnemy(86,240,20,20,0,100,2),new HorizontalEnemy(33,280,20,20,0,100,2)};
        enemy_levels[1] = new Enemy[]{new BomberEnemy(0,gameHeight,20,20,player,40),new BomberEnemy(gameWidth,0,20,20,player,30),new BomberEnemy(gameWidth,gameHeight,20,20,player,20),new StalkerEnemy(700,700,20,20, player, 2), new SpinningEnemy(300, 240, 20, 20, 100),new VerticalEnemy(0,0,20,20,0,200,6),new HorizontalEnemy(0,0,20,20,0,200,6)};
        enemy_levels[2] = new Enemy[]{new BomberEnemy(0,gameHeight,20,20,player,40),new BomberEnemy(gameWidth,0,20,20,player,60),new BomberEnemy(gameWidth,gameHeight,20,20,player,70),new StalkerEnemy(700,700,20,20, player, 2), new SpinningEnemy(300, 240, 20, 20, 100),new VerticalEnemy(0,0,20,20,0,200,6),new HorizontalEnemy(0,0,20,20,0,200,6)};
        
        wall_levels[2] = new Rectangle[]{new Rectangle(20,100,100,20),new Rectangle(100,20,20,100), new Rectangle(180,90,20,150), new Rectangle(90,180,150,20)};
        wall_levels[4] = new Rectangle[]{new Rectangle(gameWidth-100,gameHeight-140,100,20),new Rectangle(gameWidth-140,gameHeight-100,20,100)};
        wall_levels[0] = new Rectangle[]{new Rectangle(100,0,20,300),new Rectangle(120,0,320,20),new Rectangle(140,120,300,20),new Rectangle(140,120,20,gameHeight-120)};
        
        enemies = enemy_levels[level];
        walls = wall_levels[level];
        a.updateEnemies(Arrays.copyOfRange(enemy_levels[0], 0, 8));
        s.updateEnemies(enemy_levels[2]);
        s1.updateEnemies(enemy_levels[3]);
        s2.updateEnemies(enemy_levels[4]);
        
    }
   
    private void enterFullScreen() {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = graphicsEnvironment.getDefaultScreenDevice();
        if (device.isFullScreenSupported()) {
            device.setFullScreenWindow(frame);
            frame.validate();
        }
    }
   
    //The update method does 5 things
    //1 - it has the player move based on what key is currently being pressed
    //2 - it prevents the player from leaving the screen
    //3 - it checks if the player has reached the goal, and if so congratualtes them and restarts the game
    //4 - it checks if any of the Enemy objects are touching the player, and if so notifies the player of their defeat and restarts the game
    //5 - it tells each of the Enemy objects to update()
    public void update() {
        if(up) {
            player.y-=3;
            for (Rectangle a :walls){
                if(a == null)
                    continue;
                if(player.y < a.y + a.height && player.y + player.height > a.y && ((player.x + player.width > a.x && player.x + player.width < a.x + a.width) || (player.x > a.x && player.x < a.x + a.width))){
                    player.setLocation(player.x,a.y+a.height);
                }
                
            }
        }
        if(down) {
            player.y+=3;
            
            for (Rectangle a :walls){
                if(a == null){
                    continue;
                }
                if(down&&player.y+player.height>a.y&&player.y<a.y+a.height &&(player.x+player.width>a.x && player.x<a.x+a.width)){
                    player.setLocation(player.x,a.y-player.height);
                }
            }
            
            
        }
        if(left) {
            player.x-=3;
           }
        if(right) {
            player.x+=3;
}
       
        if(player.x < 0) {
            player.x = 0;
        }
        else if(player.x + player.width > gameWidth) {
            player.x = gameWidth - player.width;
        }
       
        if(player.y < 0) {
            player.y = 0;
        }
        else if(player.y + player.height > gameHeight) {
            player.y = gameHeight - player.height;
        }
       
        if(player.intersects(goal)) {
            onWin();
        }
        
        for (Rectangle a :walls){
            //case for player colliding from the left
            if (a== null)
                continue;
            if(player.intersects(a)){
                
                
                if(left&&player.x<a.x+a.width&& player.x+player.width>a.x){
                    player.setLocation(a.x+a.width,player.y);
                }

                else if(right && player.x+player.width>a.x && player.x<a.x+a.width){
                    player.setLocation(a.x-player.width,player.y);
                }
                
            }
        }
        
        
        for(Enemy e: enemies) {
            if(e == null)
                continue;
       
            if(e.intersects(player)) {
                onLose();
            }
           
            e.move();
        }
       
    }
   
    //The paint method does 3 things
    //1 - it draws a white background
    //2 - it draws the player in blue
    //3 - it draws the goal in green
    //4 - it draws all the Enemy objects
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, gameWidth, gameHeight);
   
        g.setColor(Color.BLUE);
        g.fillRect(player.x, player.y, player.width, player.height);
       
        g.setColor(Color.GREEN);
        g.fillRect(goal.x, goal.y, goal.width, goal.height);
       
        for(Enemy e: enemies) {
            if(e == null)
                continue;
            e.draw(g);
        }
        for(Rectangle rect:walls ){
            if (rect == null)
                continue;
            g.setColor(Color.GRAY);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
    }
   
    private void onWin() {
        createDialog("You Won!", 2000);
        level++;
        if(level == 2){createDialog("Too easy.", 3000);}
        
        if(level ==4 ){
            createDialog("Now what?" , 3000);
        }
        if(level == 5){
            createDialog("Ÿou win.",2000);
            
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        
        }
        
        setUpGame(level);
    }
   
    private void onLose() {
        createDialog("You Lost", 500);
        setUpGame(level);
    }
   
    // Sets visible a Pseudo-dialog that removes itself after a fixed time interval
    // Uses a thread to not block the rest of the program
    //
    // @param: message: String -> The message that will appear on the dialog
    // @param: delay: int -> How long (in milliseconds) that Dialog is visible
    private void createDialog(String message, int delay) {
        dialogLabel.setText(message);
        dialog.setVisible(true);
        frame.requestFocus();
       
        Thread thread = new Thread(() -> {
            try {
                // Show pop up for [delay] milliseconds
                Thread.sleep(delay);
            } catch(Exception e) {
                System.out.println("Thread failed :(");
                dialog.setVisible(false);
                frame.requestFocus();
            }
            // End of 3 seconds
            // Close the pop up
            dialog.setVisible(false);
            frame.requestFocus();
        });
        thread.start();
    }
}
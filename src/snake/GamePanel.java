package snake;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Math.random;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Hassan Diab
 */
public class GamePanel extends JPanel implements ActionListener,KeyListener{
    int[] x_pos=new int[700];
    int[] y_pos=new int[700];
    char direction='R';
    int bodyparts=5;
    int apple_x=0;
    int apple_y=0;
    
   JFrame frame =new JFrame();
   Timer time =new Timer(75,this);
   Random ran = new Random();
  
   
   
    GamePanel() // Constructor to make a Frame and add componenet into it at starting a progrm
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(250, 50, 710, 710);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setResizable(false);
        frame.add(this);
        frame.addKeyListener(this);

        apple();
        start();
   
    }
    
   @Override
     public void paintComponent(Graphics g){ //Functionto fraw components (Snalke , Apple)
         super.paintComponent(g);
          g.setColor(Color.red);
          g.fillOval(apple_x, apple_y, 25, 25);  // Apple
         
    for(int i=0 ;i<bodyparts ;i++) // Snake
    {
        if (i == 0)  // to Make a head of the snake(represented at index 0) with little different color
        { 
            g.setColor(Color.pink);
            g.fillRect(x_pos[i],y_pos[i] , 25, 25);
        }
        else

        {         

            g.setColor(Color.red);
            g.fillRect(x_pos[i],y_pos[i] , 25, 25);
        }
           
    }
     
     }
    
  void start()
   {
    time.start();
   }
  
  void apple(){  // to change the loction of the apple Randomly in the screen if snake ate it 
      
     apple_x=ran.nextInt(695/25)*25;
     apple_y=ran.nextInt(640/25)*25;
  }
  
  
   void check() {  // check method to see if the snake move with the right way and position or not
       
   if(x_pos[0]==apple_x && y_pos[0]==apple_y) // if the snale ate the apple 
   {
       apple();
       bodyparts++;
   }
   
   
   
   for(int i = 1 ;i<bodyparts ;i++) // check if the snake crash with its body or not 
   {
    if(x_pos[0] == x_pos[i] && y_pos[0] == y_pos[i])
    {
     bodyparts=5;
     direction='R';
     x_pos[0]=0;
     y_pos[0]=0;
     repaint();
    }
   }
   
  // this to make the redirect the snake if he pass the bounds of x,y
   if(x_pos[0]>700)
      x_pos[0]=0;
   
   if( x_pos[0]<0)
        x_pos[0]=675;
       
   
   if(y_pos[0]>673 )
       y_pos[0]=0;
    
    if( y_pos[0]<0 )
       y_pos[0]=675;
    
    
  
  
   }
  
  
  void move(){
  
  
  
   for(int z=bodyparts ;z>0;z--) // transfering value of x_pos[0](head) to the rest of the body which create  the movment of the snake
   {
   x_pos[z]=x_pos[z-1];
   y_pos[z]=y_pos[z-1];
   
   }
   
   
   switch(direction) // set direction of th snake while moving 
  {
      case 'R':
          x_pos[0]=x_pos[0]+25;
          break;
      case 'L':
          x_pos[0]=x_pos[0]-25;
          break;
      case 'U':
          y_pos[0]=y_pos[0]-25;
          break;
      case 'D':
           y_pos[0]=y_pos[0]+25;
          
  }
  
  
  }

    @Override
    public void actionPerformed(ActionEvent e) {
        
       move();
       check();
       repaint();
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar()=='s')
            direction='D';
        else if(e.getKeyChar()=='d')
            direction='R';
        else if(e.getKeyChar()=='a')
            direction='L';
        else if(e.getKeyChar()=='w')
            direction='U';
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
 
         
}


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ssnakegame extends JFrame  {
    Ssnakegame()
    {
        Dimension d=new Dimension();
        d.height=535;
        d.width=510;
      
        setVisible(true);
        setSize(d);
        setResizable(false);
        back bb=new back();
        addKeyListener((KeyListener) bb);
        Thread t=new Thread( bb);
        add(bb);
        t.start();
       
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        
        Ssnakegame ss=new Ssnakegame();
    }
    
}
class back extends JPanel implements Runnable, KeyListener
{
   
    int startpoint=20;
    boolean up=false;
    boolean down= false;
    boolean right = true;
    boolean left =false;
    int XX=20,YY=40;
    back()
    {
      
        repaint();
      setBackground(Color.black) ;
       
        System.out.println(" j gd ");
       
    }
    int lock=0;
    boolean gameoverr=false;
    public void paintComponent(Graphics G)
    {
       super.paintComponent(G);
         System.out.println("45641");
        G.setColor(Color.red); 
         G.drawRect(0, 0, 500, 500);
       fruit(G);
        if(lock==0)
        {
            drawsnakestart1(G);
            lock=1;
        }
        
        drawsnakestart2(G);
        System.out.println("  paint ^");
      gameoverr=gameover();
 
    }
    void drawrow(Graphics G)
    {
        int k=10;
        for(int i=1;i<50;i++)
                G.drawLine(0, k*i, 500, k*i);  
    }
    void drawcolum(Graphics G)
    {
     int k=10;
        for(int i=1;i<50;i++)
                G.drawLine(k*i, 0, k*i, 500);  
       
    }
    int XA[]=new int[100];
    int YA[]=new int[100];
    int dot=3;
    void drawsnakestart1(Graphics G)
    {
         XA[0]=XX;
         YA[0]=YY;
        XA[1]=XX - 10;
         YA[1]=YY;
         XA[2]=XX - 20;
         YA[2]=YY;
         
        for(int i=0;i<dot;i++)
        {
            G.fillRect(XA[i], YA[i], 10, 10);
            G.setColor(Color.green);
        }
        
        
    }
    void drawsnakestart2(Graphics G)
    {
        int tempx,tempy,tempx1,tempy1;
       
        for(int i=dot-1;i>0;i--)
        {
          
            XA[i]=XA[i-1];
            YA[i]=YA[i-1];
            
        }
        XA[0]=XX;
        YA[0]=YY;
        for(int i=0;i<dot;i++)
        {
            G.fillRect(XA[i], YA[i], 10, 10);
            G.setColor(Color.green);
        }
        if(XA[0]==XF && YA[0]==YF)
        {
            dot++;
            sleep-=5;
            fruit=false;
        }
        
    }
    
    boolean gameover()
    {
        
        if(XA[0]<0 || XA[0]>490)
          return true;
        else if(YA[0]<0 || YA[0]>490)
            return true;
        else
        {
            for(int i=1;i<dot;i++)
            {
               
                if(XA[0]==XA[i] && YA[0]==YA[i])
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    boolean fruit=false;
    int XF;
    int YF;
    void fruit(Graphics G)
    {
        if(!fruit)
        {
            Random rand = new Random();

           XF = (rand.nextInt(49) + 0)*10;
           YF=(rand.nextInt(49)+0)*10;
            
            fruit=true;
        }
        else
        {
            G.fillOval(XF, YF, 10, 10);
        }
    }
    int count=0;
    int sleep=100;
    public void run()
    {
        System.out.println(" iiiii 544 ");
     
        
        while(true)
        {
            System.out.println(" in in ");
            if(left )
            {
                XX-=10;
            }
            else if (right)
            {
                XX+=10;
            }
            else if(up)
            {
                YY-=10;
            }
            else if(down)
            {
                YY+=10;
            }
            repaint();
            if(count > 10 && gameoverr )
                return ;
            try
            {
             Thread.sleep(sleep);
            }
            catch(InterruptedException e)
            {
                System.out.println("   er  "+e);
            }
            count++;
        }
        
        
    }
  
   

    @Override
    public void keyTyped(KeyEvent e) {
           }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        //System.out.println("    vvvvvv  "+key);
       if(key==40 && !up)
       {
           left=false;
           right=false;
           up=false;
           down=true;
           //System.out.println("  DOWN  ");
           
       }
       else if(key == 39 && !left)
       {
           left=false;
           right=true;
           up=false;
           down=false;
           //System.out.println(" RiGHT");
       }
       else if(key ==38 && !down)
       {
           left=false;
           right=false;
           up=true;
           down=false;
           //System.out.println("  UP ");
       }
       else if(key == 37 && !right)
       {
           left=true;
           right=false;
           up=false;
           down=false;
           //System.out.println(" LEFT ");
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}

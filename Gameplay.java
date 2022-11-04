
package oop.project;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener 
{
	private boolean play = false;
	private int score = 0;
	private int temp = 0;
	private int highscore = 70;
	private int level = 1;
	private String pname; 
	private int totalBricks = 12;
	
	private Timer timer;
	private int delay=8;
	
	
	private int playerX = 310;
	
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private MapGenerator map;
	
	
	public Gameplay()
	{		
		map = new MapGenerator(level, 12);
                
		
                pname=JOptionPane.showInputDialog("Enter players Name: ");
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g)
	{    	
            //for background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		map.draw((Graphics2D) g);
		
		//for border
		g.setColor(Color.blue);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
                //for player name
                g.setColor(Color.lightGray);
		g.setFont(new Font("serif",Font.BOLD, 25));
		g.drawString("P.name:"+pname, 160,30);
                
                //for Score
		g.setColor(Color.orange);
		g.setFont(new Font("serif",Font.BOLD, 25));
		g.drawString("Score:"+score, 560,30);
		
                //for HighScore
		g.setColor(Color.green);
		g.setFont(new Font("arial",Font.BOLD, 25));
		g.drawString("HighScore:"+highscore, 370,30);
                
                //for Level
		g.setColor(Color.gray);
		g.setFont(new Font("arial",Font.BOLD, 25));
		g.drawString("Level:"+level, 50,30);
                
                //for paddle
		g.setColor(Color.cyan);
		g.fillRect(playerX, 550, 100, 8);
		
               //for ball 
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
                
                
                
	
		 if(totalBricks <=0 && ballposY<=570){
                       play = false;
                       ballXdir = 0;
                       ballYdir = 0;
               g.setColor(Color.red);
               g.setFont(new Font("serif",Font.BOLD,35));
               g.drawString("You have Completed level "+level , 160, 250);
               g.setFont(new Font("arial",Font.BOLD,30));
               g.drawString("Your Score: "+score, 260,300);
            
               g.setFont(new Font("serif",Font.BOLD,25));
               g.drawString("Press (n) to start next level ", 230, 350);
        }
		
		if(ballposY > 570){
        
			 play = false;
                         ballXdir = 0;
     		         ballYdir = 0;
                 g.setColor(Color.RED);
                 g.setFont(new Font("serif",Font.BOLD, 50));
                 g.drawString("Game Over" ,190,300);
                 if(score>=highscore){
                     g.setColor(Color.RED);
                     g.setFont(new Font("serif",Font.BOLD, 30));
                     g.drawString(pname+" Win the game" ,220,340);   
                 }
                 else{
                      g.setColor(Color.RED);
                     g.setFont(new Font("serif",Font.BOLD, 30));
                     g.drawString(pname+" lost the game" ,220,340);  
                 }
                 g.setColor(Color.RED);
                 g.setFont(new Font("serif",Font.BOLD, 25));
                 g.drawString("Final Score: "+score, 230,380);
             
                 g.setColor(Color.RED);
                 g.setFont(new Font("serif",Font.BOLD, 20));           
                 g.drawString("Press (Enter) to Restart",200,420);        
        }
		
		g.dispose();
	}	

	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{        
			if(playerX >= 600)
			{
				playerX = 600;
			}
			else
			{
				moveRight();
			}
        }
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{          
			if(playerX < 10)
			{
				playerX = 10;
			}
			else
			{
				moveLeft();
			}
        }		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{          
			if(!play)
			{      if(temp>=highscore){
                                    highscore= temp;   
                                }
                            pname=JOptionPane.showInputDialog(pname+" scored "+score+"\n" +" your target is "+highscore+"\n" +" Enter your  Name: ");
				play = true;
                            
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
                               
                                score=0;
                            if(temp>=highscore){
                                    highscore= temp;   
                                }
                                level=1;
				totalBricks = level*12;
				map = new MapGenerator(level, 12);
				
				repaint();
                              
			}
        }
                
                if(e.getKeyCode() == KeyEvent.VK_N && ballposY<=570){
                        if(!play){
                           
                            play = true;
              
                            ballposX = 120;
                            ballposY = 350;
                            ballXdir = -1;
                            ballYdir = -2;
                            playerX = 310;
                            level++;
                            score = score;
                    
                
                totalBricks =level*12;
               
                
               map = new MapGenerator(level, 12);
                
                
                repaint();
            }
        }
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void moveRight()
	{
		play = true;
		playerX+=30;	
	}
	
	public void moveLeft()
	{
		play = true;
		playerX-=30;	 	
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		if(play)
		{			
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 30, 8)))
			{
				ballYdir = -ballYdir;
				ballXdir = -2;
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8)))
			{
				ballYdir = -ballYdir;
				ballXdir = ballXdir + 1;
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8)))
			{
				ballYdir = -ballYdir;
			}
					
			A: for(int i = 0; i<map.map.length; i++)
			{
				for(int j =0; j<map.map[0].length; j++)
				{				
					if(map.map[i][j] > 0)
					{
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect))
						{					
							map.setBrickValue(0, i, j);
							score+=5;
                                                        temp=score;
							totalBricks--;
							
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width)	
							{
								ballXdir = -ballXdir;
							}
							else
							{
								ballYdir = -ballYdir;				
							}
							
							break A;
						}
					}
				}
			}
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			
			if(ballposX < 0)
			{
				ballXdir = -ballXdir;
			}
			if(ballposY < 0)
			{
				ballYdir = -ballYdir;
			}
			if(ballposX > 670)
			{
				ballXdir = -ballXdir;
			}		
			
			repaint();		
		}
	}
}

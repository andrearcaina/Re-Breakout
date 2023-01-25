/// Mouse GUI Assignment: Breakout
/// Andre Arcaina
/// Due Date: 2021/11/19 @ 11:59 PM 
/// Version 3.03

import javax.swing.*;
import java.awt.event.*;

///Extra Features
/// 1. add a win/lose screen
/// 2. add a hidden power up (increase life when you press on the "life" font)

public class BreakoutAndreA implements MouseListener, MouseMotionListener{
	///properties
	JFrame frame = new JFrame("Atari Breakout 2600");
	BreakoutPanel BPanel = new BreakoutPanel();
	boolean blnClick = true; //so that it stops after 3 clicks
	
	///methods
	// mouse listener
	public void mouseExited(MouseEvent evt){
		
	}
	public void mouseEntered(MouseEvent evt){
		
	}
	public void mouseReleased(MouseEvent evt){
		
	}
	public void mousePressed(MouseEvent evt){
		if(SwingUtilities.isRightMouseButton(evt)){
			if(BPanel.intStart == 1){
				BPanel.intStart = 2;
			}  
		}else if(SwingUtilities.isLeftMouseButton(evt)){
			if(blnClick == true){
				if(evt.getX() >= 580 && evt.getX() <= 700 && evt.getY() >= 20 && evt.getY() <= 50){ //if you press on the "life" font, you will gain life 
					System.out.println("Clicked hidden power up!");
					BPanel.intLives = BPanel.intLives + 1; //increase life of user when clicked on a certain box
					BPanel.intPowerUp = BPanel.intPowerUp - 1; //decrease power up chances
					if(BPanel.intPowerUp == 0){
						blnClick = false;
					}
				}
			}
		}
	}
	public void mouseClicked(MouseEvent evt){
		
	}
	
	// motion listener
	public void mouseMoved(MouseEvent evt){
		BPanel.intPaddleX = evt.getX() - 48; //paddle moves on mouse x coordinate
		if(BPanel.intPaddleX < 51){ //stops the paddle from going lesser than the x coordinate of 51 
			BPanel.intPaddleX = 50; 
		}else if(BPanel.intPaddleX > 651){ //stops the paddle from going greater than the x coordinate of 651
			BPanel.intPaddleX = 650;
		} 
	}
	public void mouseDragged(MouseEvent evt){
		 
	}
	
	///constructor
	public BreakoutAndreA(){
		BPanel.addMouseListener(this);
		BPanel.addMouseMotionListener(this);
		
		frame.setContentPane(BPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	
	///main program
	public static void main(String[] args){
		new BreakoutAndreA();
	}
}

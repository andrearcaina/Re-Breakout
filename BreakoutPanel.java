/// Mouse GUI Assignment: Breakout Panel
/// Andre Arcaina
/// Due Date: 2021/11/19 @ 11:59 PM 
/// Version 3.04

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BreakoutPanel extends JPanel implements ActionListener{
	///properties
	Timer timer = new Timer(1000/60, this);
	
	//brick variables 
	int[][] BrickLayout;
	int intBrickY;
	int intBrickWidth = 70;
	int intBrickHeight = 40;
	int intRow = 3;
	int intCol = 10;
	int intCount;
	int intCount2;
	
	//lives and score
	int intScore = 0;
	int intLives = 3;
	int intPowerUp = 3;
	
	//paddle coordinates
	int intPaddleX = 350;
	
	//ball coordinates
	int intBallX = 395;
	int intBallY = 540;
	
	//ball speed
	int intSpeedX = -5;
	int intSpeedY = 5;
	
	//start game/serve ball
	int intStart = 1;
	
	//font 
	Font lives = new Font("OCR A Extended", Font.BOLD, 40);
	Font helpfont = new Font("OCR A Extended", Font.PLAIN, 20);
	
	///methods
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == timer){
			this.repaint();
		} 
	}
	
	public void paintComponent(Graphics g){
		//background 
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		
		g.setColor(new Color(100, 55, 141));
		g.fillRect(0, 585, 50, 15);
		g.fillRect(750, 585, 50, 15);
		
		//outline of game
		g.setColor(Color.GRAY); 
		g.fillRect(0, 80, 50, 505); //left rectangle 
		g.fillRect(750, 80, 50, 505); //right rectangle  
		g.fillRect(0, 80, 800, 50); // top rectangle outline
		
		//user paddle
		g.setColor(Color.GRAY);
		g.fillRect(intPaddleX, 570, 100, 15);
		
		//ball and starting of the game
		if(intStart == 1){ 
			g.setColor(Color.GRAY);
			g.setFont(helpfont);
			g.drawString("Press right click on your mouse to start.", 0, 75);
			
			g.setColor(Color.WHITE);
			g.fillRect(intBallX, intBallY, 10, 10);
		}else if(intStart == 2){ //draws breakout ball moving at certain speed at x and y
			
			g.setColor(Color.WHITE);
			g.fillRect(intBallX, intBallY, 10, 10);
			intBallX = intBallX + intSpeedX;
			intBallY = intBallY + intSpeedY;
		}
		
		//ball collision into paddle
		///new Rectangle(intBallX, intBallY, 10, 10) is the breakout ball
		///new Rectangle(intPaddleX, 570, 100, 15) is the paddle the user uses
		if(new Rectangle(intBallX, intBallY, 10, 10).intersects(new Rectangle(intPaddleX, 570, 100, 15))){
			intSpeedY = -5;
		}
		
		//collisions into wall
		if(intBallX < 50 || intBallX > 740){ //left wall and right wall
			intSpeedX = -intSpeedX;
		}else if(intBallY < 135){ //top wall
			intSpeedY = 5;
		}
		//drawing bricks
		for(intCount = 0; intCount < intRow; intCount++){ //rows
			if(intCount == 0){
				g.setColor(new Color(255, 233, 0));
			}else if(intCount == 1){
				g.setColor(new Color(176, 27, 46));
			}else if(intCount == 2){
				g.setColor(new Color(30, 121, 44));
			} 
			for(intCount2 = 0; intCount2 < intCol; intCount2++){ //column
				intBrickY = intCount*intBrickHeight+200; //coordinate value for brick y
				g.fillRect(BrickLayout[intCount][intCount2], intBrickY, intBrickWidth, intBrickHeight); //draws bricks
				
				Rectangle Brick = new Rectangle(BrickLayout[intCount][intCount2], intBrickY, intBrickWidth, intBrickHeight);
				Rectangle Ball = new Rectangle(intBallX, intBallY, 10, 10);
				
				//collisions
				if(Ball.intersects(Brick)){	
					BrickLayout[intCount][intCount2] = -1000; //"destroying" the bricks (in reality I'm just moving it someplace where it cannot be hit by the ball
					intScore = intScore + 1; //increase score everytime you hit one brick
					
					if(intBallX + 10 < Brick.x || intBallX + 10 > Brick.x + Brick.width){
						intSpeedX = -intSpeedX; // when ball hits left or right of brick
					}else{
						intSpeedY = -intSpeedY; // when ball hits top or bottom of brick
					}
				}
			}
		}
		
		//lose life (bottom 'rectangle' / area)
		if(intBallY > 630){
			intLives = intLives - 1;
			intStart = 1;
			intBallX = 395;
			intBallY = 540;
		}
		//score and life count
		g.setColor(Color.GRAY);
		g.setFont(lives);
		g.drawString("SCORE: "+intScore, 20, 50);
		g.drawString("LIFE: 0"+intLives, 580, 50);
		
		//draws win screen
		if(intScore == 30){	
			intStart = 1; 
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.WHITE);
			g.setFont(lives);
			g.drawString("CONGRATS!", 50, 100);
			g.drawString("You have beaten Atari Breakout.", 20, 300);
		}
		if(intScore == 30 && intLives >= 3){	
			intStart = 1; 
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.WHITE);
			g.setFont(lives);
			g.drawString("EASTEREGG EASTEREGG EASTEREGG EASTEREGG EASTEREGG", 20, 0);
			g.drawString("EASTEREGG EASTEREGG EASTEREGG EASTEREGG EASTEREGG", 20, 100);
			g.drawString("EASTEREGG EASTEREGG EASTEREGG EASTEREGG EASTEREGG", 20, 200);
			g.drawString("EASTEREGG EASTEREGG EASTEREGG EASTEREGG EASTEREGG", 20, 300);
			g.drawString("EASTEREGG EASTEREGG EASTEREGG EASTEREGG EASTEREGG", 20, 400);
			g.drawString("EASTEREGG EASTEREGG EASTEREGG EASTEREGG EASTEREGG", 20, 500);
			g.drawString("EASTEREGG EASTEREGG EASTEREGG EASTEREGG EASTEREGG", 20, 600);
		}
		
		//draws death screen
		if(intLives == 0){	
			intStart = 1; 
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.WHITE);
			g.setFont(lives);
			g.drawString("FINAL SCORE: "+intScore, 20, 50);
			g.drawString("R.I.P! You have lost.", 50, 200);
		}
	}
	
	///constructor
	public BreakoutPanel(){
		super();
		this.setLayout(null);
		this.setPreferredSize(new Dimension(800, 600));
		timer.start();
		
		//initializing array outside of method
		BrickLayout = new int[intRow][intCol];
		for(intCount = 0; intCount < BrickLayout.length; intCount++){
			for(intCount2 = 0; intCount2 < BrickLayout[0].length; intCount2++){
				BrickLayout[intCount][intCount2] = intCount2*intBrickWidth+50; 
				// making brick layout array and each brick equal to x coordinate of brick 
			}			
		}
	}
}

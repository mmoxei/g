
import java.util.*;
import java.lang.Math;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*; 	
import javafx.scene.input.*;
import javafx.scene.image.*;
import java.io.*;

public class Unit extends StackPane{
	private  int[] pos = new int[2];

	private final int tileSize = 32;
	
	private  int shotDamage = 4;
	
	private  int moveDistance = 5;
	
	private  int distance = 4;
	
	private  int Health = 10;
	
	public int[] getPos(){return Arrays.copyOf(pos, pos.length);}
	public int getMoveDistance(){
	return moveDistance;	
	}
	public int getShotDamage(){
	return shotDamage;	
	}
	public int getShotDistance(){
	return distance;
	}
	public int getHealth(){return Health;}
	public void moveUnit(int x, int y){relocate(x * tileSize, y * tileSize);}
	public void setPos(int x, int y){
	pos[0] = x;	
	pos[1] = y;
	}
	public void setHealth(int a){Health = a;}
	public void setDistance(int b){
	if(b < 0){
	return;	
	}
	distance = b;	
	}
	public void setMoveDistance(int b){
	if(b < 0){
	return;	
	}
	moveDistance = b;	
	}
	public void setShotDamage(int s){
		shotDamage = s;
	}
}
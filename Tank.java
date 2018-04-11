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
import javafx.scene.paint.*;
import javafx.scene.image.*;

public class Tank extends Unit{
	
	private String tankName;
	private String tankImage = "tank_red.png";
	private ImageView i = new ImageView(new Image("tank_red.png"));

	public void setTankImage(String s){tankImage = new String(s);}
	public String getTankName(){return new String(tankName);}
	public void setI(String s){
		tankImage = new String(s);
		getChildren().remove(i);
		i = new ImageView(new Image(tankImage));
		getChildren().add(i);
	}
	public ImageView getI(){return new ImageView(new Image(tankImage));}
	public void setTankName(String s){tankName = new String(s);}
	public String getTankImage(){return new String(tankImage);}
	
	public Tank(String name, int x, int y){
		tankName = name;
		moveUnit(x,y);
		setPos(x,y);
		setMouseTransparent(true);
		getChildren().addAll(i);
	}
	
	public Tank(String name, int x, int y, int h){
		tankName = name;
		moveUnit(x,y);
		getChildren().addAll(i);
		setPos(x,y);
		setHealth(h);
		setMouseTransparent(true);
	}

	public Tank(Tank t){
		setTankImage(new String(t.getTankImage()));
		setI(new String(t.getTankImage()));
		setTankName(new String(t.getTankName()));
		setPos(t.getPos()[0],t.getPos()[1]);
		setHealth(t.getHealth());
		setDistance(t.getShotDistance());
		setMoveDistance(t.getMoveDistance());
		setShotDamage(t.getShotDamage());
	}
}
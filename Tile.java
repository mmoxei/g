
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.paint.*;
import javafx.scene.input.*;
import javafx.scene.image.*;


public class Tile extends Rectangle{
//instance variables 

private tileType type;
private Tank tank;

//getters and setters
public tileType getType(){
return type;	
}

public void setType(tileType t){
type = t;	
}
//method that checks if tank is on a tile 
public boolean hasTank(){
return tank != null;
}

public Tank getTank(){return tank;
}

public void setTank(Tank t){
	if(t != null) {
		tank = new Tank(t);
		setType(tileType.TANK);
	}
else {
		setType(null);
		tank = null;
	}
}
//constructor for tile class
public Tile(int x, int y){
	Image i = new Image("snow3.png");

	setWidth(Game.gettileSize());
	setHeight(Game.gettileSize());
	relocate(x * Game.gettileSize(), y * Game.gettileSize());
//colour is green by default
	setFill(new ImagePattern(i));
//event handler for mouse pressed if tile without tank is pressed tank will move to tile, if tile with tank is pressed then the tank will shoot the tank on the tile  
	
}	

}
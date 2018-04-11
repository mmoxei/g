
import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;
import java.util.HashMap;
import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.scene.input.InputEvent;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import java.util.Random;


public class Game extends Application{
// instances variables 
	private Text t = new Text("Turn: Red Tank");
	private Text tank1Health = new Text();
	private Text tank2Health = new Text();
	private Group tileGroup = new Group();
	private Group tankGroup = new Group();
// size of titles 	
	private final static int tileSize = 32;
	private final int gridSize = 20;
// list of the tanks objects on the map 
	private Tank[] tankList = new Tank[2];
//2d array of objects that going to be displayed  
	private Tile[][] Board = new Tile[gridSize][gridSize];
	private int c = 0;

//getters and setters 
	public Group gettankGroup(){return new Group(tankGroup);}
	public void settankGroup(Group g){tankGroup = new Group(g);}

	public static int gettileSize(){return tileSize;}
// sets the tank object to a certain tiles attribute	
	public void setTanktoTile(int x, int y, Tank tank){Board[x][y].setTank(tank);}
	public Tile[][] getBoard(){return Board;}
	public void setBoardType(int x, int y, tileType t){Board[x][y].setType(t);}
// converts mouse pixel to a coordinate size 	
	public int Convert(double pixel){return (int)(pixel + tileSize/4)/tileSize;}
	public Group healthGroup(Tank[] t){
		Group g = new Group();
		for(int count = 0; count < t.length;count++){
			Text n = new Text(Integer.toString(t[count].getHealth()));
			g.getChildren().add(n);
		}
		return g;
	}
// checks if user move is legal, if move is legal returns true, and move is not legal returns false	
	public	boolean tryTurn(Tank tank, turnType Type, int X, int Y){
		if(Type == turnType.MOVE){
			int dy = Y - tank.getPos()[1];
			int dx = X - tank.getPos()[0];
			
			
			System.out.println(dx);
			System.out.println(dy);
			if(Math.abs(dx) > tank.getMoveDistance() || Math.abs(dy) > tank.getMoveDistance()){
				return false;
			}
			if(Math.abs(dx) != 0 && Math.abs(dy) != 0){
				return false;	
			}
			if(X - tank.getPos()[0] == 0){
				for(int count = 0; count < Math.abs(dy); count++){
					if(dy < 0){
						
						if(Board[X][Y + count].getType() != null){
							return false;
						}
					}
					if(dy > 0){
						
						if(Board[X][Y  - count].getType() != null){
							return false;
						}
					}
				}
			}
			if(Y - tank.getPos()[1] == 0){
				for(int count = 0; count < Math.abs(dx); count++){
					if(dx < 0){
						
						if(Board[X + count][Y].getType() != null){
							
							return false;
						}
					}
				
					if(dx > 0){
						
						if(Board[X - count][Y].getType() != null){
							
							return false;
							
						}
					}
				}
			}
		}
		if(Type == turnType.SHOOT){
			
			
			
			int dy = Y - tank.getPos()[1];
			int dx = X - tank.getPos()[0];
			if(dy != 0 && dx == 0){
				for(int c = 0; c < Math.abs(dy); c++){
					if(dy > 0){
						if(Board[X][Y - c].getType() == tileType.TERRAIN){return false;}
					}
					if(dy < 0){
						if(Board[X][Y + c].getType() == tileType.TERRAIN){return false;}
					}
				}
				
				
			}
			if(dx != 0 && dy == 0){
				for(int c = 0; c < Math.abs(dx); c++){
					if(dx > 0){
						if(Board[X - c][Y].getType() == tileType.TERRAIN){return false;}
					}
					if(dx < 0){
						if(Board[X + c][Y].getType() == tileType.TERRAIN){return false;}
					}
				}	
			}
			if(dy > 0 && dx > 0){
				for(int c = 0; c < dx; c++){
					
					if(Board[X - c][Y - c].getType() == tileType.TERRAIN){return false;}
					
				}
			}
			if(dy < 0 && dx < 0){
				for(int c = 0; c < Math.abs(dx); c++){
					if(Board[X + c][Y + c].getType() == tileType.TERRAIN){return false;}
				}
			}
			if(dy < 0 && dx >0){
				for(int c = 0; c < dx; c++){
					if(Board[X - c][Y + c].getType() == tileType.TERRAIN){return false;}
				}
			}
			if(dy > 0 && dx < 0){
				for(int c = 0; c < Math.abs(dx);c++){
					if(Board[X + c][Y - c].getType() == tileType.TERRAIN){return false;}
				}
			}
			if(Math.abs(dy) + Math.abs(dx) > tank.getShotDistance()){
				return false;
			}
		
		
		} 
	
		if(Type == null){
			return false;
		}
		else{
		return true;	
		}
	}
	public void makeTerrain(Tile t){
		if(t.getType() != null){return;}
		Image image = new Image("snowman.png");
		t.setFill(new ImagePattern(image));
		t.setType(tileType.TERRAIN);
	}
	
	// method that moves tank 	
	public void Turn(Tank tank, turnType Type, int X, int Y){
// if the turn type is move it will set tile that tank was on to null, and the tile that is pressed to tank		
		boolean bool = tryTurn(tank, Type, X, Y);
		if(bool == false){return;}
		if(Type == turnType.MOVE){
			

			setBoardType(X,Y,tileType.TANK);
			setTanktoTile(X,Y,Board[tank.getPos()[0]][tank.getPos()[1]].getTank());

			setBoardType(tank.getPos()[0],tank.getPos()[1],null);
			setTanktoTile(tank.getPos()[0],tank.getPos()[1],null);
			
			tank.setPos(X,Y);
			tank.moveUnit(X,Y);
		}
		printGrid();
//tank shoots 		
		if(Type == turnType.SHOOT){

			if(getBoard()[X][Y].getType() == tileType.TANK){

				Tank target = new Tank(Board[X][Y].getTank());
				System.out.println("Healthy of Target before is" + " " + target.getHealth());
				target.setHealth(target.getHealth() - tank.getShotDamage());
				System.out.println("Health of Target after is" + " " + target.getHealth());
				setTanktoTile(X,Y,target);

				if(target.getHealth() <= 0){
				
					System.exit(0);
				}

			}
		}
	}
	private boolean checkConsistency(){
	
	for(int y = 0; y < Board[0].length;y++){
			for(int x=0; x < gridSize;x++){
				
					
				if(Board[x][y].getType() != null && Board[x][y].getType().equals(tileType.TANK) && Board[x][y].getTank() == null){
					System.out.print("Error at " + x + " " + y);
					return false;
				}
	
			}
		
		}
		return true;
	}
	private void printGrid(){
		for(int y = 0; y < Board[0].length;y++){
			for(int x=0; x < gridSize;x++){
				
				if(Board[x][y].getType()==null)
					System.out.print("-");
				else if(Board[x][y].getType().equals(tileType.TANK)){
					System.out.print("T");
				}
				else if(Board[x][y].getType().equals(tileType.TERRAIN)){System.out.print("S");}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
// puts tanks, tiles, margins, into groups so it can be displayed and returns root   		
	private Pane createContent(){
		Pane root = new Pane();

		root.setPrefSize(gridSize * tileSize, gridSize * tileSize);
		root.getChildren().addAll(tileGroup, tankGroup,t);
		Rectangle rect = new Rectangle();
		t.setX(gridSize * tileSize + 40);
		t.setY(70);
		t.setFont(Font.font("Verdana",FontWeight.BOLD,50));
		for(int count = 0; count < gridSize; count++){

			
			for(int counter = 0; counter < gridSize; counter++){
				
				double d = Math.random();
					
				Tile tile = new Tile(counter, count);
				if(d > 0.96){
					makeTerrain(tile);
					System.out.println(tile.getType());
				}
				Board[counter][count] = tile;
				
				tileGroup.getChildren().add(tile);
			}
		}
		Tank tank1 = new Tank("1",8,7);
		Tank tank2 = new Tank("2",4,3);

		tank2.setI("tank_yellow.png");
	
		tankGroup.getChildren().add(tank1);
		tankGroup.getChildren().add(tank2);
		
		tankList[0] = tank1;
		tankList[1] = tank2;

		tank1Health = new Text("Red Tank Health:" + " " + Integer.toString(tank1.getHealth()));
		tank2Health = new Text("Yellow Tank Health:" + " " + Integer.toString(tank2.getHealth()));
		tank1Health.setY(120);
		tank1Health.setX(gridSize * tileSize + 40);
		tank1Health.setFont(Font.font("Verdana",FontWeight.BOLD,50));
		tank2Health.setY(170);
		tank2Health.setX(gridSize * tileSize + 40);
		tank2Health.setFont(Font.font("Verdana",FontWeight.BOLD,50));
		tankGroup.getChildren().addAll(tank1Health,tank2Health);
		
		setBoardType(tank1.getPos()[0],tank1.getPos()[1],tileType.TANK);
		setTanktoTile(tank1.getPos()[0],tank1.getPos()[1],tank1);

		
		setBoardType(tank2.getPos()[0],tank2.getPos()[1],tileType.TANK);
		setTanktoTile(tank2.getPos()[0], tank2.getPos()[1], tank2);
		
		
	
		
		return root;
	}
	private void useTile(Pane p){	
	p.setOnMouseClicked(e ->{
		int mxi = Convert(e.getSceneX());
		int myi = Convert(e.getSceneY());

		if(mxi >= gridSize || myi >= gridSize || mxi < 0 || myi < 0){return;}
		
		
		if(c % 2 == 0){
		t.setText("Turn: Yellow Tank");
		if(getBoard()[mxi][myi].getType() == tileType.TANK){
			Turn(tankList[0], turnType.SHOOT, mxi, myi);
		
			
		}
		if(getBoard()[mxi][myi].getType() == null){
			
			Turn(tankList[0], turnType.MOVE, mxi, myi);
			 
			
		}
		tank1Health.setText("Red Tank Health:" + " " + Integer.toString(Board[tankList[0].getPos()[0]][tankList[0].getPos()[1]].getTank().getHealth()));
		tank2Health.setText("Yellow Tank Health:" + " " + Integer.toString(Board[tankList[1].getPos()[0]][tankList[1].getPos()[1]].getTank().getHealth()));
		}
		
		if(c % 2 != 0){
			t.setText("Turn: Red Tank");
			if(getBoard()[mxi][myi].getType() == tileType.TANK){
			Turn(tankList[1], turnType.SHOOT, mxi, myi);
			
			
		}
		if(getBoard()[mxi][myi].getType() == null){
			System.out.println(getBoard()[mxi][myi].getType());
			System.out.println(tankList[1].getHealth());
			Turn(tankList[1], turnType.MOVE, mxi, myi);
			System.out.println(tankList[1].getHealth());
			
		}
		tank1Health.setText("Red Tank Health:" + " " + Integer.toString(Board[tankList[0].getPos()[0]][tankList[0].getPos()[1]].getTank().getHealth()));
		tank2Health.setText("Yellow Tank Health:" + " " + Integer.toString(Board[tankList[1].getPos()[0]][tankList[1].getPos()[1]].getTank().getHealth()));
		}
		c++;
		
		e.consume();
	});
	
	}
	public void start(Stage primaryStage){	
		Pane p = createContent();
		Scene scene = new Scene(p);
		primaryStage.setTitle("tanks");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	
		
		useTile(p);
		
	}	
	public static void main(String[] args){
		launch(args);	
		
	}	
}
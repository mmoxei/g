
public class Grid{
	public static void displayMap(String blue[][]){
		
		for(int row = 0; row < blue.length; row++){
			System.out.println("");
			for(int column = 0; column < blue[row].length; column++){
				
				System.out.print(blue[row][column] + " ");
			}
		}
		System.out.println("");
	}	
	public static String[][] newMap = new String[20][20];
	
	public static void createMap(String blue[][]){
		
		
	for(int row = 0; row < blue.length; row++){
			
			for(int column = 0; column < blue[row].length; column++){
				
				blue[row][column] = "-";
			}
		}
	}
	

}

	
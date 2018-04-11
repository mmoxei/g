
import java.util.Random;

class Terrain{
	private static Random randy = new Random();
	
	private static String typeString = null;
	
	private static int typeInt = 0;
	
	private static String[] typeList = {"S","T","B"};
	//Current legend: S = Swamp, T = Tree, B = Building
	
	public static void createTrees(int nTrees, String blue[][]){
		boolean validSpace;
		int ranRow, ranColumn;
		
		
		for(int counter = nTrees; counter > 0; counter--){
			validSpace = false;
			ranRow = randy.nextInt(blue.length);
			ranColumn = randy.nextInt(blue.length);
			while(validSpace = false){
				ranRow = randy.nextInt(blue.length);
				ranColumn = randy.nextInt(blue[ranRow].length);
				if(blue[ranRow][ranColumn] == "-"){
					
					validSpace = true;
				}
			}
			blue[ranRow][ranColumn] = "T";
		
		
		}
		
		
		
	}
	public static void createSwamps(int nSwamps, String blue[][], String ori){
		boolean validSpace;
		int ranRow, ranColumn = 0;
		
		if(ori == "h"){
			for(int counter = nSwamps; counter > 0; counter--){
				validSpace = false;
				ranRow = randy.nextInt(blue.length);
				ranColumn = randy.nextInt(blue.length);
				while(validSpace = false){
					ranRow = randy.nextInt(blue.length);
					ranColumn = randy.nextInt(blue[ranRow].length);
					if(blue[ranRow][ranColumn] == "-" && blue[ranRow][ranColumn + 1] == "-" && blue[ranRow][ranColumn - 1] == "-"){
					
						validSpace = true;
				
				
					}
		
				}
				blue[ranRow][ranColumn] = "S";
				blue[ranRow][ranColumn + 1] = "S";
				blue[ranRow][ranColumn - 1] = "S";
			
			
			
			}
		
		}
		if(ori == "v"){
			for(int counter = nSwamps; counter > 0; counter--){
				validSpace = false;
				ranRow = randy.nextInt(blue.length);
				ranColumn = randy.nextInt(blue.length);
				while(validSpace = false){
					ranRow = randy.nextInt(blue.length);
					ranColumn = randy.nextInt(blue[ranRow].length);
					if(blue[ranRow][ranColumn] == "-" && blue[ranRow + 1][ranColumn] == "-" && blue[ranRow - 1][ranColumn] == "-"){
					
						validSpace = true;
				
				
					}
		
				}
				blue[ranRow - 1][ranColumn] = "S";
				blue[ranRow][ranColumn] = "S";
				blue[ranRow + 1][ranColumn] = "S";
			}
		}
	
	
	}
	public static void createBuildings(int nBuildings, String blue[][]){
		boolean validSpace;
		int ranRow, ranColumn;
		
		for(int counter = nBuildings; counter > 0; counter--){
			validSpace = false;
			ranRow = randy.nextInt(blue.length);
			ranColumn = randy.nextInt(blue.length);
			while(validSpace = false){
				ranRow = randy.nextInt(blue.length);
				ranColumn = randy.nextInt(blue[ranRow].length);
				if(blue[ranRow][ranColumn] == "-"){
					
					validSpace = true;
				}
			}
			blue[ranRow][ranColumn] = "B";
		
		
		}
		
		
		
	}
	
	

	
	
	
	
	
	
	
}

import java.util.Scanner;


public class Main {

public static void main (String[] args){
	Scanner scan = new Scanner(System.in);
	System.out.println("Reversi by Forrest Pratistha and Tony \n");

	System.out.println("Choose your game: \n"  + "1. Small 4x4 Reversi \n"
	+ "2. Medium 6x6 Reversi \n" + "3. Standard 8x8 Reversi \n");
	int size;
	while (true){
		System.out.print ("Enter your choice: ");
		int board_size = scan.nextInt();

	  	 if (board_size == 1){
			size = 4;
			ActivePlayer(4);
	   	}
	  	 else if (board_size == 2){
			size = 6;
			ActivePlayer(4);
	   	}
	   	else if (board_size == 3){
			size = 8;
			ActivePlayer(4);
		   }
		else if  (board_size == 3){
			break;
		} 
		else if (board_size == 0){
			System.exit(0);
		}
		else{
			System.out.println("Invalid choice Enter your choice or press '0' to quit: ");
		}
	}

	}

	public static void ActivePlayer (int size){
		Scanner scan = new Scanner(System.in);
		System.out.println("\nDo you want to play Dark (x) or Light(y)");
		int active_player;
		while (true){
			System.out.print ("Enter your choice: ");
			String player = scan.nextLine();
			   if (player.toUpperCase().equals("LIGHT")){
				active_player = 1;
				Agent(size, active_player);
			   }
			   else if (player.toUpperCase().equals("DARK")){
				active_player = -1;
				Agent(size, active_player);
			   }
			   else if (player.equals("0")){
				System.exit(0);
			   }
			else{
				System.out.print ("Invalid choice Enter your choice or press '0' to quit: ");
			}
		}
	}
	public static void Agent (int size, int active_player){
		Scanner scan = new Scanner(System.in);
		Game game = new Game();
		System.out.println("\nChoose your opponent: \n"  + "1. An agent that plays randomly \n"
		+ "2. An agent that uses MINIMAX \n" + "3. An agent that uses Minimax with alpha-beta pruning \n"
		+ "4. An agent that uses H-MINIMAX with a fixed depth cutoff and a-b pruning \n" );

		while (true){
			System.out.print("Enter your agent: ");
			int agent = scan.nextInt();
			   if (agent == 1){
				System.out.println("I am Game Random");
			   }
			   else if (agent == 2){
				game.play_reversi_mini_max1(size, active_player);
			   }
			   else if (agent == 3){
				System.out.println("I am Alpha Beta Pruning");
			   }
			   else if  (agent == 4){
				System.out.println("I am H-MiniMax with depth cut off and Alpha Beta Pruning");
				} 
				else if (agent == 0){
					System.exit(0);
				}
			 	else{
				System.out.print ("Invalid choice Enter your choice or press '0' to quit: ");
			}
		}

	}
}

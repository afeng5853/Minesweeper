package Game;

import java.util.Scanner;

import ContainerObjects.Mine;
import Grid.Grid;
import Grid.GridUtilities;

public class GameRunner {
	public static void main(String[] args) {
		Grid grid = GridUtilities.generateGrid(10, 10, 20);
		Scanner input = new Scanner(System.in);
		while (true) {
			boolean flag = false;
			boolean unflag = false;
			System.out.println(grid);
			System.out.println("Which position would you like to reveal? (format: x y) or would you like to flag? (format: flag/unflag x y)");
			int x;
			int y;
			while (true) {
				String[] nums = input.nextLine().split(" ");
				if (nums.length == 2 && isNumeric(nums[0]) && isNumeric(nums[1])) {
					x = Integer.parseInt(nums[0]);
					y = Integer.parseInt(nums[1]);
					break;
				} else if (nums.length == 3 && isNumeric(nums[1]) && isNumeric(nums[2])) {
					if ("flag".equals(nums[0])) {
						flag = true;
					} else if ("unflag".equals(nums[0])) {
						unflag = true;
					}
					x = Integer.parseInt(nums[1]);
					y = Integer.parseInt(nums[2]);
					break;
				} else {
					System.out.println("Please enter a valid input");
				}
			}
			if (grid.outOfBounds(x, y)) {
				System.out.println("Please enter a valid input");
			} else {
				if (flag) {
					grid.flag(x, y);
				} else if (unflag) {
					grid.unflag(x, y);
				} else {
					grid.reveal(x, y);
					
					if (grid.getObject(x, y).getObj() instanceof Mine) {
						System.out.println(grid);
						gameOver();
						break;
					}
					if (grid.won()) {
						System.out.println(grid);
						win();
						break;
					}
				}
			}
		}
		input.close();
	}
	
	private static void win() {
		System.out.println("You win!");
	}

	private static void gameOver() {
		System.out.println("You revealed a mine! Game over.");
	}

	private static boolean isNumeric(String s) {  
		// https://stackoverflow.com/questions/14206768/how-to-check-if-a-string-is-numeric
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	}  
	
}

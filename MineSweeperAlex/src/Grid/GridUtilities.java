package Grid;

import java.util.Random;

import ContainerObjects.ContainerObject;
import ContainerObjects.Mine;
import ContainerObjects.MNumber;

public class GridUtilities {
	public static void placeBombs(Grid grid, int mineCount) {
		grid.setWinCount(grid.getWinCount() - mineCount);
		Random r = new Random();
		int y = grid.getHeight();
		int x = grid.getWidth(y);
		while (mineCount > 0) {
			int randX = r.nextInt(x-1) + 1;
			int randY = r.nextInt(y-1) + 1;
			if (grid.getObject(x, y) == null) {
				grid.placeObject(new Mine(), randX, randY);
				mineCount--;
			}
		}
	}
	
	public static void placeNumbers(Grid grid) {
		for (int i = 1; i < grid.getHeight(); i++) {
			int count = 0;
			for (int j = 1; j < grid.getWidth(i); j++) {
				if (grid.getObject(i, j) == null || !(grid.getObject(i, j).getObj() instanceof Mine)) {
					if (grid.getObject(i-1, j-1) != null && grid.getObject(i-1, j-1).getObj() instanceof Mine) {
						count++;
					}
					if (grid.getObject(i-1, j) != null && grid.getObject(i-1, j).getObj() instanceof Mine) {
						count++;
					}
					if (grid.getObject(i-1, j+1) != null &&grid.getObject(i-1, j+1).getObj() instanceof Mine) {
						count++;
					}
					if (grid.getObject(i, j-1) != null &&grid.getObject(i, j-1).getObj() instanceof Mine) {
						count++;
					}
					if (grid.getObject(i, j+1) != null &&grid.getObject(i, j+1).getObj() instanceof Mine) {
						count++;
					}
					if (grid.getObject(i+1, j-1) != null &&grid.getObject(i+1, j-1).getObj() instanceof Mine) {
						count++;
					}
					if (grid.getObject(i+1, j) != null &&grid.getObject(i+1, j).getObj() instanceof Mine) {
						count++;
					}
					if (grid.getObject(i+1, j+1) != null &&grid.getObject(i+1, j+1).getObj() instanceof Mine) {
						count++;
					}
					if (count > 0) {
						grid.placeObject(new MNumber(count), i, j);
					}
				}
				count = 0;
			}
		}
	}
	
	public static Grid generateGrid(int x, int y, int mineCount) {
		MContainer[][] grid_ = new MContainer[x+2][y+2];
		Grid grid = new Grid(grid_);
		placeBombs(grid, mineCount);
		placeNumbers(grid);
		return grid;
	}
}

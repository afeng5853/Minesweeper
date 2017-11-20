package Grid;

import ContainerObjects.ContainerObject;
import ContainerObjects.MNumber;

public class Grid {
	private MContainer[][] grid;
	private int winCount;
	
	public Grid(MContainer[][] grid) {
		this.grid = grid;
		for (int i = 1; i < this.getHeight(); i++) {
			for (int j = 1; j < this.getWidth(i); j++) {
				this.placeObject(null, i, j);
			}
		}
		this.setWinCount((this.getHeight() - 1) * (this.getWidth(0) - 1));
	}
	
	public MContainer getObject(int x, int y) {
		return grid[x+1][y+1];
	}
	
	public void placeObject(ContainerObject obj, int x, int y) {
		grid[x+1][y+1] = new MContainer(obj);
	}
	
	public int getHeight() {
		return grid.length - 2;
	}
	
	public int getWidth(int i) {
		if (grid[i] != null) {
			return grid[i].length - 2;
		}
		return 0;
	}
	
	public boolean outOfBounds(int x, int y) {
		return x <= 0 || x > this.getWidth(0) - 1 || y <= 0 || y > this.getHeight() - 1;
	}
	
	public void reveal(int x, int y) {
		MContainer node = this.getObject(x, y);
		if (node == null || node.isRevealed()) {
			return;
		}
		if (node.getObj() instanceof MNumber) {
			decrementWinCount();
			node.setRevealed(true);
		} else {
			decrementWinCount();
			node.setRevealed(true);
			reveal(x-1, y);
			reveal(x+1, y);
			reveal(x, y-1);
			reveal(x, y+1);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder build = new StringBuilder();
		build.append("  ");
		for (int i = 1; i < this.getHeight(); i++) {
			build.append(" " + i + "  ");
		}
		build.append("\n");
		for (int i = 1; i < this.getHeight(); i++) {
			build.append(i + " ");
			for (int j = 1; j < this.getWidth(i); j++) {
				if (this.getObject(i, j).isFlagged()) {
					build.append(this.getObject(i, j) + " ");
				}
				else if (this.getObject(i, j).getObj() == null && this.getObject(i, j).isRevealed()) {
					build.append("[#] ");
				} 
				else if (this.getObject(i, j).getObj() == null || !this.getObject(i, j).isRevealed()) {
					build.append("[ ] ");
				} else {
					build.append(this.getObject(i, j) + " ");
				}
			}
			build.append("\n");
		}
		return build.toString();
	}

	public int getWinCount() {
		return winCount;
	}

	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}
	
	public void decrementWinCount() {
		this.winCount--;
	}
	
	public boolean won() {
		return this.winCount == 0;
	}

	public void flag(int x, int y) {
		this.getObject(x, y).setFlagged(true);
	}
	
	public void unflag(int x, int y) {
		this.getObject(x, y).setFlagged(false);
	}
}

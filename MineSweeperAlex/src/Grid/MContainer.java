package Grid;

import ContainerObjects.ContainerObject;

public class MContainer {
	private ContainerObject obj;
	private boolean flagged;
	private boolean revealed = false;
	
	public MContainer(ContainerObject obj) {
		this.setObj(obj);
	}

	public ContainerObject getObj() {
		return obj;
	}

	public void setObj(ContainerObject obj) {
		this.obj = obj;
	}
	
	@Override
	public String toString() {
		if (flagged) {
			return "[P]";
		}
		return "[" + this.obj + "]";
	}

	public boolean isFlagged() {
		return flagged;
	}

	public void setFlagged(boolean flagged) {
		this.flagged = flagged;
	}

	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}
}

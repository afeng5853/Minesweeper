package ContainerObjects;

public class MNumber implements ContainerObject {
	public int value;
	
	public MNumber(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return Integer.toString(value);
	}
}

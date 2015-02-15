package processData;

public class EventCount {
	private float voltage;
	private int count;
	
	public EventCount(float voltage, int count) {
		this.voltage = voltage;
		this.count = count;
		
	}

	public float getVoltage() {
		return voltage;
	}

	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public String toString() {
		return this.voltage+","+this.count;
	}
}

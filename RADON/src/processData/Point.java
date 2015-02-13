package processData;

public class Point {
	
	private float voltage;
	private long time;
	
	public Point(long time, float voltage) {
		this.voltage = voltage;
		this.time = time;
	}
	
	/**
	 * @return the voltage
	 */
	public float getVoltage() {
		return voltage;
	}

	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @param voltage the voltage to set
	 */
	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}

}
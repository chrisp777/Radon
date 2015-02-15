package processData;

import java.math.BigDecimal;

public class Point {
	
	private BigDecimal voltage;
	private long time;
	
	public Point(long time, BigDecimal voltage) {
		this.voltage = voltage;
		this.time = time;
	}
	
	/**
	 * @return the voltage
	 */
	public BigDecimal getVoltage() {
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
	public void setVoltage(BigDecimal voltage) {
		this.voltage = voltage;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}
	
	public String toString() {
		return this.voltage+","+this.time;
	}

}
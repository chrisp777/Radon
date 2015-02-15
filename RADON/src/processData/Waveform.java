package processData;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Waveform {
	private static int Id;
	private Timestamp eventTime;
	private ArrayList<Point> data;
	private Point peak;

	
	public Waveform(int Id, ArrayList<Point> data,Timestamp eventTime) {
		this.Id = Id;
		this.eventTime = eventTime;
		this.data = data;
		this.setPeak(detectPeak(data));

	}
	
	private Point detectPeak(ArrayList<Point> data) {
		return new Point(0,new BigDecimal(0));
	}

	/**
	 * @return the id
	 */
	public static int getId() {
		return Id;
	}

	/**
	 * @return the data
	 */
	public ArrayList<Point> getData() {
		return data;
	}


	/**
	 * @param id the id to set
	 */
	public static void setId(int id) {
		Id = id;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(ArrayList<Point> data) {
		this.data = data;
	}

	public Point getPeak() {
		return peak;
	}

	public void setPeak(Point peak) {
		this.peak = peak;
	}

	public String toString() {
		String output = "";
		for(Point point: data){
			output += point.toString()+"\n";
		}
		return output;
	}
}

package processData;

import java.util.ArrayList;

public class Waveform {
	private static int Id;
	private ArrayList<Point> data;
	private Point peak;

	
	public Waveform(int Id, ArrayList<Point> data) {
		this.Id = Id;
		this.data = data;
		this.setPeak(detectPeak(data));

	}
	
	private Point detectPeak(ArrayList<Point> data) {
		return new Point(0,0);
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

	
}

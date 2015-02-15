package processData;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

public class LoadWaveforms {
	static int id = -1;
	ArrayList<Waveform> waveforms = new ArrayList<>();
	
	public LoadWaveforms(ArrayList<Waveform> waveforms) {
		this.waveforms = waveforms;
	}
	
	public Waveform loadWaveformFromFile(String fileName) throws IOException {
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		Scanner lines = new Scanner(br);
		
		lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();
		long timeInc = 10;//base nano
		String date = null;
		Timestamp eventTime = null;
		long time = 0;
		

		ArrayList<Point> data = new ArrayList<>();

		while(lines.hasNextLine()) {
			
			String[] parts = lines.nextLine().replaceAll("\"", "").split("\\s*,\\s*");//REGEX is any white space followed by a comma followed by any white space 
			if(parts[0].equals("Date")) {date = parts[1];}
			else if(parts[0].equals("Time")) {
				eventTime = getTimeStamp(parts[1],date);
				time = eventTime.getNanos();
				lines.nextLine(); //Skips the blank line after the time
			}
			else {
				time += timeInc;
				BigDecimal voltage = new BigDecimal(parts[1]);
				data.add(new Point(time, voltage));
			}
		}
		id++;
		return new Waveform(id,data,eventTime);
	}
	public static Timestamp getTimeStamp(String eventTime, String eventDate) {
		System.out.println(eventDate.replace("/", "-")+" "+eventTime);
		Timestamp time = Timestamp.valueOf(eventDate.replace("/", "-")+" "+eventTime);
		return time;
	}
}

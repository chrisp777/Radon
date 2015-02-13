package processData;

import java.io.*;
import java.util.*;

public class ProcessRadonData {
	static int id = -1;
	public static void main(String[] args) {
		Waveform wf = null;

		try {
			wf = loadWaveformFromFile("F:\\DATA\\TEST001.csv");
		}
		catch (IOException e) {

		}
		System.out.println(wf);
	}

	public static Waveform loadWaveformFromFile(String fileName) throws IOException {
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		Scanner lines = new Scanner(br);
		
		lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();lines.nextLine();
		long timeInc = 10;//base nano
		String date = null;
		long time = 0;
		

		ArrayList<Point> data = new ArrayList<>();

		while(lines.hasNextLine()) {
			String[] parts = lines.nextLine().replaceAll("\"", "").split(",");
			if(parts[0] == "Date") {date = parts[1];}
			else if(parts[0] == "Time") {time = getTimeStamp(parts[1],date);}
			else {
				time += timeInc;
				data.add(new Point(time, Double.valueOf(parts[1]).longValue()));
			}
			lines.nextLine();
		}
		id++;
		return new Waveform(id,data);
	}

	public static long getTimeStamp(String time, String date) {
		return new Long(0);
	}

}

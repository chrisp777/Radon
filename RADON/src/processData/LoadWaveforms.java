package processData;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

public class LoadWaveforms implements Runnable{
	static int id = -1;
	ArrayList<Waveform> waveforms = new ArrayList<>();
	File dir = null;

	public LoadWaveforms(ArrayList<Waveform> waveforms, File dir) {
		this.waveforms = waveforms;
		this.dir = dir;
	}

	@Override
	public void run() {
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(dir.listFiles()));
		for(File file : files){
			if(file.isFile()){
				Waveform waveform = null;
				try {
					waveform = loadWaveformFromFile(file.getCanonicalFile().toString());
				} catch (IOException e) {
					System.out.println("ERROR when trying to load file. "+e);
				}

				waveforms.add(waveform);
			}
		}
	}

	public static Waveform loadWaveformFromFile(String fileName) throws IOException {
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
		System.out.println("Loaded event at time:  "+eventDate.replace("/", "-")+" "+eventTime);
		Timestamp time = Timestamp.valueOf(eventDate.replace("/", "-")+" "+eventTime);
		return time;
	}

}

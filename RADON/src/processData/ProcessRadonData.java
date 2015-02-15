package processData;

import java.io.*;
import java.util.*;
public class ProcessRadonData {
	public static void main(String[] args) {
		ArrayList<Waveform> waveforms = new ArrayList<>();
		LoadWaveforms loadedWaveForms = new LoadWaveforms(waveforms);
		Waveform wf = null;

		try {
			File f = new File("F:\\Users\\Chris\\git\\Radon\\RADON\\data\\");
			ArrayList<File> files = new ArrayList<File>(Arrays.asList(f.listFiles()));
			//wf = loadedWaveForms.loadWaveformFromFile("F:\\Users\\Chris\\git\\Radon\\RADON\\data\\TEST001.csv");
			for(File file : files){
				if(file.isFile()){
					wf = loadedWaveForms.loadWaveformFromFile(file.getCanonicalFile().toString());

					waveforms.add(wf);
				}
			}
			waveforms.add(wf);
		}
		catch (Exception e) {

		}
		//System.out.println(wf);

		FindPeaks findPeaks = new FindPeaks(waveforms);
		findPeaks.findEventPeaks();

	}

}

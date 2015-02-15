package processData;

import java.io.*;
import java.util.*;
public class ProcessRadonData {
	public static void main(String[] args) {
		ArrayList<Waveform> waveforms = new ArrayList<>();
		LoadWaveforms loadedWaveForms = new LoadWaveforms(waveforms);
		Waveform wf = null;

		try {
			wf = loadedWaveForms.loadWaveformFromFile("F:\\Users\\Chris\\git\\Radon\\RADON\\data\\TEST001.csv");
			waveforms.add(wf);
		}
		catch (IOException e) {

		}
		//System.out.println(wf);
		
		FindPeaks findPeaks = new FindPeaks(waveforms);
		findPeaks.findEventPeaks();
		
	}

}

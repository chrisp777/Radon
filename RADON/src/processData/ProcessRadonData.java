package processData;

import java.io.*;
import java.util.*;
public class ProcessRadonData {
	public static void main(String[] args) {
		ArrayList<Waveform> waveforms = new ArrayList<>();
		File f = new File("F:\\Users\\Chris\\git\\Radon\\RADON\\data\\");
		
		LoadWaveforms loadedWaveforms = new LoadWaveforms(waveforms,f);
		FindPeaks findPeaks = new FindPeaks(waveforms);
		
		Thread loadTask = new Thread(loadedWaveforms);
		Thread processTask = new Thread(findPeaks);
		
		loadTask.start();
		// NOTE that currently the speed of the processing overtakes the loading and so a concurrent editing error will be thrown if the loading isn't given a head start
		try {
		    Thread.sleep(1000);                 
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		processTask.start();



	}

}

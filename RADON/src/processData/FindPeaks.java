package processData;

import java.math.BigDecimal;
import java.util.*;

public class FindPeaks {
	ArrayList<Waveform> waveforms = new ArrayList<>();
	ArrayList<EventCount> eventCounts = new ArrayList<>();

	public FindPeaks(ArrayList<Waveform> waveforms) {
		this.waveforms = waveforms;
	}

	public void findEventPeaks() {
		for(Waveform waveform : waveforms){
			Point highestV = new Point(0,new BigDecimal(0));
			Point lastPoint = new Point(0,new BigDecimal(0));

			float TtestZeroAvg = 0;
			int TtestZeroCount = 0;
			float TtestNonZeroAvg = 0;
			int TtestNonZeroCount = 0;

			for(Point point: waveform.getData()){
				float thisPoint = Float.parseFloat(point.getVoltage().toString());
				float thisPointAvg = (thisPoint+Float.parseFloat(lastPoint.getVoltage().toString()))/2;

				if(TtestZeroCount <= 9 ){
					TtestZeroAvg += thisPoint;
					TtestZeroCount++;
				}
				else if(TtestZeroCount == 10){
					TtestZeroAvg = TtestZeroAvg/10;
					TtestZeroCount++;
				}

				if (thisPointAvg > Float.parseFloat(highestV.getVoltage().toString())) {
					highestV = new Point(0,new BigDecimal(thisPointAvg));//THIS OR THE OTHER BELOW
					/*highestV = thisPointAvg;*/
				}
				else if (Float.parseFloat(highestV.getVoltage().toString())>0.1){
					if(TtestNonZeroCount <= 9 ){
						TtestNonZeroAvg += thisPoint;
						TtestNonZeroCount++;
					}
					else if(TtestNonZeroCount == 10){
						TtestNonZeroAvg = TtestNonZeroAvg/10;
						TtestNonZeroCount++;
					}
				}
				lastPoint = point;
			}

			System.out.println("Highest: "+Float.parseFloat(highestV.getVoltage().toString()));
			System.out.println("Nonzero avg: "+TtestNonZeroAvg);
			System.out.println("Zero avg: "+TtestZeroAvg);

			if (Math.abs(TtestNonZeroAvg) > Math.abs(TtestZeroAvg)) {
				//EVENT HAS A NON ZERO TAIL
				System.out.println("TAIL");
				eventCounts.add(new EventCount(Float.parseFloat(highestV.getVoltage().toString()),1));
			}
		}
		for(EventCount event : eventCounts) {
			System.out.println(event);
		}
	}
}

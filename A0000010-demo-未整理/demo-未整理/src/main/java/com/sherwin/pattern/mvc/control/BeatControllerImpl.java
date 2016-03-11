package com.sherwin.pattern.mvc.control;

import com.sherwin.pattern.mvc.model.intf.BeatModel;

public class BeatControllerImpl implements BeatController {

	public BeatControllerImpl(BeatModel beatModel) {
		this.beatModel = beatModel;
	}
	
	public void setBPM(int bpm) {
		beatModel.setBPM(bpm);
	}

	public void start() {
		beatModel.start();
	}

	public void stop() {
		beatModel.stop();
	}

	public void volumeDown() {
		int bpm = beatModel.getBPM();
		if (0 < bpm) {
			beatModel.setBPM(--bpm);
		}
	}

	public void volumeUp() {
		int bpm = beatModel.getBPM();
		if (100 > bpm) {
			beatModel.setBPM(++bpm);
		}
	}
	
	private BeatModel beatModel;

}

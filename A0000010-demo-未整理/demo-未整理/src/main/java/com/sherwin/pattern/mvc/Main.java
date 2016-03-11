package com.sherwin.pattern.mvc;

import com.sherwin.pattern.mvc.control.BeatController;
import com.sherwin.pattern.mvc.control.BeatControllerImpl;
import com.sherwin.pattern.mvc.model.impl.BeatModelImpl;
import com.sherwin.pattern.mvc.model.intf.BeatModel;
import com.sherwin.pattern.mvc.view.ControlPanel;
import com.sherwin.pattern.mvc.view.ViewPanel;

public class Main {

	public static void main(String[] args) {
		BeatModel beatModel = new BeatModelImpl();
		BeatController beatController = new BeatControllerImpl(beatModel);
		
		ControlPanel controlPanel = new ControlPanel(beatController);
		controlPanel.display();
		
		ViewPanel viewPanel = new ViewPanel(beatModel);
		viewPanel.display();
	}
}

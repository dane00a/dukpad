package com.evanram.dukpad.core.device;

public class LaunchpadS implements Launchpad {
	@Override
	public String getName() {
		return "Launchpad S";
	}

	@Override
	public Model getModel() {
		return Model.LAUNCHPAD_S;
	}
}

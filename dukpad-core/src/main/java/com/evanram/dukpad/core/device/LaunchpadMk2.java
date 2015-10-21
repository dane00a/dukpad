package com.evanram.dukpad.core.device;

public class LaunchpadMk2 implements Launchpad {
	@Override
	public String getName() {
		return "Launchpad Mk2";
	}

	@Override
	public Model getModel() {
		return Model.LAUNCHPAD_MK2;
	}
}

package com.evanram.dukpad.core.device;

public class LaunchpadPro implements Launchpad {
	public LaunchpadPro() {
		throw new UnsupportedOperationException("Launchpad Pro not currently supported.");
	}
	
	@Override
	public String getName() {
		return "Launchpad Pro";
	}

	@Override
	public Model getModel() {
		return Model.LAUNCHPAD_PRO;
	}
}

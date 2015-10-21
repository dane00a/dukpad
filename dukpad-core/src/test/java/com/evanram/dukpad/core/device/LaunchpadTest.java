package com.evanram.dukpad.core.device;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.evanram.dukpad.core.device.Launchpad.Model;

public class LaunchpadTest {
	private Launchpad pad;
	
	@Test
	public void testLaunchpadMini() {
		pad = new LaunchpadMini();
		assertTrue(pad.getModel() == Model.LAUNCHPAD_S);
	}
	
	@Test
	public void testLaunchpadS() {
		pad = new LaunchpadS();
		assertTrue(pad.getModel() == Model.LAUNCHPAD_S);
	}
	
	@Test
	public void testLaunchpadMk2() {
		pad = new LaunchpadMk2();
		assertTrue(pad.getModel() == Model.LAUNCHPAD_MK2);
	}
	
	// ensure that LaunchpadPro does not get 
	@Test(expected = UnsupportedOperationException.class)
	public void testLaunchpadPro() {
		pad = new LaunchpadPro();
		// TODO JUnit assertTrue(((LaunchpadPro) pad).getModel() == Model.LAUNCHPAD_PRO);
	}
}

package com.evanram.dukpad.desktop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evanram.dukpad.desktop.ui.Workspace;

import themidibus.MidiBus;

public final class DesktopMain {
	private DesktopMain() {
		throw new UnsupportedOperationException();
	}
	
	public static void main(String[] args) {
		final Logger log = LoggerFactory.getLogger("main");
		log.info("Dukpad");
		log.info("Available MIDI Input Devices: {}",  (Object[]) MidiBus.availableInputs());
		log.info("Available MIDI Output Devices: {}", (Object[]) MidiBus.availableOutputs());
		log.info("Unavailable MIDI Devices: {}",      (Object[]) MidiBus.unavailableDevices());

		try {
			Workspace.launch(args);
		} catch (Exception e) {
			final String error = "Failed to start workspace.";

			log.trace(error, e);
			e.printStackTrace();

//			JOptionPane.showMessageDialog(null, 
//					String.format("%s%n%s", error, e.toString()), 
//					e.toString(), 
//					JOptionPane.ERROR_MESSAGE);
		}
	}
}

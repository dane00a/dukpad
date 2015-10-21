package com.evanram.dukpad.core.event.events;

import com.evanram.dukpad.core.event.Event;

public final class AlertEvent implements Event {
	private final String message;
	private final AlertEvent.Type type;
	
	public AlertEvent(String message) {
		this(message, AlertEvent.Type.INFO);
	}
	
	public AlertEvent(String message, AlertEvent.Type type) {
		this.message = message;
		this.type = type;
	}
	
	public String getMessage() {
		return message;
	}
	
	public AlertEvent.Type getType() {
		return type;
	}
	
	public static enum Type  {
		INFO,
		WARNING,
		ERROR;
	}
}

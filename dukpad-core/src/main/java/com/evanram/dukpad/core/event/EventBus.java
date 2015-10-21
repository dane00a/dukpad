package com.evanram.dukpad.core.event;

import java.util.HashSet;
import java.util.Set;

public final class EventBus {
	private static volatile EventBus instance;
	
	private final Set<EventHandler> handlers = new HashSet<>(); 
	
	private EventBus() {
		if(instance != null) {
			synchronized(EventBus.class) {
				if(instance != null) {
					throw new IllegalStateException("Only a single instance of this class may exist!");
				}
			}
		}
	}
	
	public static EventBus getInstance() {
		if(instance == null) {
			synchronized(EventBus.class) {
				if(instance == null) {
					instance = new EventBus();
				}
			}
		}
		
		return instance;
	}
	
	public void register(EventHandler handler) {
		handlers.add(handler);
	}
	
	public void unregister(EventHandler handler) {
		handlers.remove(handler);
	}
	
	public void fire(Event event) {
		for(EventHandler handler : handlers) {
			handler.handle(event);
		}
	}
}

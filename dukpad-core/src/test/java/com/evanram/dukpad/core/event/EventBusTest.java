package com.evanram.dukpad.core.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.evanram.dukpad.core.event.events.EventTestImpl;

public class EventBusTest {
	private int handleCount = 0;
	
	@Test
	public void testEventBus() {
		EventBus bus = EventBus.getInstance();
		assertNotNull(bus);
		
		EventHandler handler = new EventHandler() {
			@Override
			public void handle(Event event) {
				handleCount++;
				assertNotNull(event);
				assertTrue(event instanceof EventTestImpl);
			}
		};
		
		bus.register(handler);
		
		// no events have been fired
		assertEquals(handleCount, 0);
		
		// single handler => increment handleCount by 1
		bus.fire(new EventTestImpl());
		assertEquals(handleCount, 1);
		
		bus.unregister(handler);
		
		// no handlers should exist
		bus.fire(new EventTestImpl());
		assertEquals(handleCount, 1);
	}
}

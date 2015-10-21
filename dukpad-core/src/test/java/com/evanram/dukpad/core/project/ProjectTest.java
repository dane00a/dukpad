package com.evanram.dukpad.core.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.evanram.dukpad.core.animation.AnimationLoaderTestImpl;
import com.evanram.dukpad.core.audio.SampleLoaderTestImpl;

public class ProjectTest {
	private ProjectData data;
	private Project project;
	
	public ProjectTest() {
		this.data = ProjectData.createDefaultData();
		
		this.project = new Project(
				data, 
				new SampleLoaderTestImpl(), 
				new AnimationLoaderTestImpl()
		);
	}
	
	@Test
	public void testData() {
		assertNotNull(data.version);
		assertNotNull(data.scriptPath);
		assertNotNull(data.animationPaths);
		assertNotNull(data.samplePaths);
		
		assertEquals(data.version, ProjectData.VERSION);
		assertEquals(data.scriptPath, ProjectData.DEFAULT_SCRIPT_PATH);
		assertEquals(data.animationPaths.size(), 0);
		assertEquals(data.samplePaths.size(), 0);
		
		String originalSerialized = data.serialize();
		
		data.animationPaths.add("test-animation-path");
		data.samplePaths.add("test-sample-path");
		
		assertNotSame(originalSerialized, data.serialize());
	}
	
	@Test
	public void testLoadSample() {
		project.loadSample("test");
		assertTrue(project.getSamples().containsKey("test"));
	}
	
	@Test
	public void testLoadAnimation() {
		project.loadAnimation("test");
		assertTrue(project.getAnimations().containsKey("test"));
	}
}

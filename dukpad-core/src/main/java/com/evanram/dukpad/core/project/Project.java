package com.evanram.dukpad.core.project;

import java.util.HashMap;
import java.util.Map;

import com.evanram.dukpad.core.animation.Animation;
import com.evanram.dukpad.core.animation.AnimationLoader;
import com.evanram.dukpad.core.audio.Sample;
import com.evanram.dukpad.core.audio.SampleLoader;
import com.evanram.dukpad.core.event.EventBus;
import com.evanram.dukpad.core.event.events.AlertEvent;

public final class Project {
	private final ProjectData data;

	private final Map<String, Sample> samples = new HashMap<>();
	private final Map<String, Animation> animations = new HashMap<>();

	private final SampleLoader sampleLoader;
	private final AnimationLoader animationLoader;

	public Project(ProjectData data, SampleLoader sampleLoader, AnimationLoader animationLoader) {
		if(!data.version.equals(ProjectData.VERSION)) {
			EventBus.getInstance()
				.fire(new AlertEvent("Project was created in a different version of this application."));
		}
		
		this.data = data;
		this.sampleLoader = sampleLoader;
		this.animationLoader = animationLoader;

		// load each required sample
		for(String samplePath : data.samplePaths) {
			loadSample(samplePath);
		}

		// load each required animation
		for(String animationPath : data.animationPaths) {
			loadAnimation(animationPath);
		}
	}
	
	public Map<String, Sample> getSamples() {
		return samples;
	}
	
	public Map<String, Animation> getAnimations() {
		return animations;
	}

	public void save() {
		// save with current version
		data.version = ProjectData.VERSION;
		
		// update both sample and animation paths
		data.samplePaths = samples.keySet();
		data.animationPaths = animations.keySet();
	}
	
	public void loadSample(String path) {
		samples.put(path, sampleLoader.load(path));
	}

	public void loadAnimation(String path) {
		animations.put(path, animationLoader.load(path));
	}
}

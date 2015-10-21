package com.evanram.dukpad.core.project;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class ProjectData {
	public static final String VERSION = "0.0.1";
	public static final String DEFAULT_SCRIPT_PATH = "./script.js";
	
	private static final Gson GSON = new GsonBuilder()
			.setPrettyPrinting()
			.create();
	
	String      version;
	String      scriptPath;
	Set<String> animationPaths;
	Set<String> samplePaths;
	
	public ProjectData() {
		
	}
	
	public String serialize() {
		return GSON.toJson(this);
	}

	public static ProjectData createDefaultData() {
		ProjectData data = new ProjectData();
		data.version = VERSION;
		data.scriptPath = DEFAULT_SCRIPT_PATH;
		data.animationPaths = new HashSet<>();
		data.samplePaths = new HashSet<>();
		
		return data;
	}
	
	public static ProjectData deserialize(String json) {
		return GSON.fromJson(json, ProjectData.class);
	}
}

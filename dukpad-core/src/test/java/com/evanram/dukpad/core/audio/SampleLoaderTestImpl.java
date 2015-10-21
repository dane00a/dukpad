package com.evanram.dukpad.core.audio;

import com.evanram.dukpad.core.audio.Sample;
import com.evanram.dukpad.core.audio.SampleLoader;

public class SampleLoaderTestImpl implements SampleLoader {
	@Override
	public Sample load(String path) {
		return new AudioSampleTestImpl();
	}
}

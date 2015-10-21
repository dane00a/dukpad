package com.evanram.dukpad.core.animation;

import com.evanram.dukpad.core.animation.Animation;
import com.evanram.dukpad.core.animation.AnimationLoader;

public class AnimationLoaderTestImpl implements AnimationLoader {
	@Override
	public Animation load(String path) {
		return new Animation();
	}
}

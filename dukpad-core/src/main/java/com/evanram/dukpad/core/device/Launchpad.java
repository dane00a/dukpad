package com.evanram.dukpad.core.device;

public interface Launchpad {
	public static enum ColorSpace {
		RGB,
		LEGACY;
	}
	
	public static enum Model {
		LAUNCHPAD_PRO(ColorSpace.RGB),
		LAUNCHPAD_MK2(ColorSpace.RGB),
		LAUNCHPAD_S(ColorSpace.LEGACY);
		
		private ColorSpace colorSpace;
		
		Model(ColorSpace colorSpace) {
			this.colorSpace = colorSpace;
		}
		
		public ColorSpace getColorSpace() {
			return colorSpace;
		}
	}
	
	public String getName();
	public Model getModel();
}

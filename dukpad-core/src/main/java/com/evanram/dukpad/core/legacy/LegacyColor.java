package com.evanram.dukpad.core.legacy;

public final class LegacyColor {
	public static final int OFF               = 0;
	public static final int BRIGHTNESS_LOW    = 1;
	public static final int BRIGHTNESS_MEDIUM = 2;
	public static final int BRIGHTNESS_FULL   = 3;

	public static final int RED_LOW    = BRIGHTNESS_LOW;
	public static final int RED_MEDIUM = BRIGHTNESS_MEDIUM;
	public static final int RED_FULL   = BRIGHTNESS_FULL;

	public static final int GREEN_OFFSET = 0x10;
	public static final int GREEN_LOW    = BRIGHTNESS_LOW    * GREEN_OFFSET;
	public static final int GREEN_MEDIUM = BRIGHTNESS_MEDIUM * GREEN_OFFSET;
	public static final int GREEN_FULL   = BRIGHTNESS_FULL   * GREEN_OFFSET;

	public static final int YELLOW_LOW    = RED_LOW    + GREEN_LOW;
	public static final int YELLOW_MEDIUM = RED_MEDIUM + GREEN_MEDIUM;
	public static final int YELLOW_HIGH   = RED_FULL   + GREEN_FULL;

	public static final String RED_NAME = "red";
	public static final String GREEN_NAME = "green";
	public static final String YELLOW_NAME = "yellow";

	private LegacyColor() {
		throw new UnsupportedOperationException();
	}

	public static int getColor(String name, int brightness) {
		// RED is always the same as brightness
		int color = brightness;

		switch(name.toLowerCase()) {
		case GREEN_NAME:
			color *= GREEN_OFFSET;
			break;

		case YELLOW_NAME:
			color += GREEN_OFFSET * brightness;
			break;
		}

		return color;
	}
}

package com.evanram.dukpad.core.legacy;

public final class LegacyColor {
	public static final int FLAG_NORMAL          = 12;
	public static final int FLAG_FLASHING        = 8;
	public static final int FLAG_DOUBLE_BUFFERED = 0;
	
	public static final int OFF               = 0;
	public static final int BRIGHTNESS_LOW    = 1;
	public static final int BRIGHTNESS_MEDIUM = 2;
	public static final int BRIGHTNESS_FULL   = 3;

	public static final int GREEN_OFFSET  = 0x10;

	public static final int RED_LOW       = getRed(BRIGHTNESS_LOW,       FLAG_NORMAL);
	public static final int RED_MEDIUM    = getRed(BRIGHTNESS_MEDIUM,    FLAG_NORMAL);
	public static final int RED_FULL      = getRed(BRIGHTNESS_FULL,      FLAG_NORMAL);

	public static final int GREEN_LOW     = getGreen(BRIGHTNESS_LOW,     FLAG_NORMAL);
	public static final int GREEN_MEDIUM  = getGreen(BRIGHTNESS_MEDIUM,  FLAG_NORMAL);
	public static final int GREEN_FULL    = getGreen(BRIGHTNESS_FULL,    FLAG_NORMAL);

	public static final int YELLOW_LOW    = getYellow(BRIGHTNESS_LOW,    FLAG_NORMAL);
	public static final int YELLOW_MEDIUM = getYellow(BRIGHTNESS_MEDIUM, FLAG_NORMAL);
	public static final int YELLOW_FULL   = getYellow(BRIGHTNESS_FULL,   FLAG_NORMAL);

	public static final String RED_NAME    = "red";
	public static final String GREEN_NAME  = "green";
	public static final String YELLOW_NAME = "yellow";

	private LegacyColor() {
		throw new UnsupportedOperationException();
	}
	
	public static int getRed(int brightness, int mode) {
		return mode + brightness;
	}
	
	public static int getGreen(int brightness, int mode) {
		return mode + brightness * GREEN_OFFSET;
	}
	
	public static int getYellow(int brightness, int mode) {
		return mode + brightness + GREEN_OFFSET * brightness;
	}

	public static int getColor(String name, int brightness, int mode) {
		switch(name.toLowerCase()) {
		case GREEN_NAME:
			return getGreen(brightness, mode);

		case YELLOW_NAME:
			return getYellow(brightness, mode);
			
		case RED_NAME:
		default:
			return getRed(brightness, mode);
		}
	}
}

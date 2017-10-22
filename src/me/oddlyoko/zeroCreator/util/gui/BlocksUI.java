package me.oddlyoko.zeroCreator.util.gui;

import java.awt.geom.GeneralPath;

public class BlocksUI {
	public static final int SIZEROUND = 4;
	public static final int HEIGHTINDICATOR = 6;
	public static final int WIDTHINDICATOR = 10;

	public static GeneralPath addGeneralPathFinalBlocks(GeneralPath path, int width, int height) {
		return addGeneralPathFinalBlocks(path, 0, 0, width, height);
	}

	public static GeneralPath addGeneralPathFinalBlocks(GeneralPath path, int x, int y, int width, int height) {

		path.moveTo(x + WIDTHINDICATOR + SIZEROUND, y + 0);
		path.lineTo(x + width - SIZEROUND, y + 0);
		path.curveTo(x + width, y + 0, x + width, y + 0, x + width, y + SIZEROUND);
		path.lineTo(x + width, y + height - SIZEROUND);
		path.curveTo(x + width, y + height, x + width, y + height, x + width - SIZEROUND, y + height);
		path.lineTo(x + WIDTHINDICATOR + SIZEROUND, y + height);
		path.curveTo(x + WIDTHINDICATOR, y + height, x + WIDTHINDICATOR, y + height, x + WIDTHINDICATOR,
				y + height - SIZEROUND);
		path.lineTo(x + WIDTHINDICATOR, y + 2.5 * HEIGHTINDICATOR);
		addLeftIndicator(path, x + WIDTHINDICATOR, y + (int) (2.5 * HEIGHTINDICATOR));
		path.lineTo(x + WIDTHINDICATOR, y + SIZEROUND);
		path.curveTo(x + WIDTHINDICATOR, y + 0, x + WIDTHINDICATOR, y + 0, x + WIDTHINDICATOR + SIZEROUND, y + 0);
		return path;
	}

	public static GeneralPath addLeftIndicator(GeneralPath path, int currX, int currY) {
		path.curveTo(currX - (WIDTHINDICATOR * 1.5), currY + (HEIGHTINDICATOR * 2), currX - (WIDTHINDICATOR * 1.5),
				currY - (HEIGHTINDICATOR * 3), currX, currY - HEIGHTINDICATOR);
		return path;
	}

	public static GeneralPath addBotIndicator(GeneralPath path, int currX, int currY, boolean fromleft) {
		if (fromleft)
			path.curveTo(currX - (WIDTHINDICATOR * 2), currY + (HEIGHTINDICATOR * 1.5), currX + (WIDTHINDICATOR * 3),
					currY + (HEIGHTINDICATOR * 1.5), currX + WIDTHINDICATOR, currY);
		else
			path.curveTo(currX + (WIDTHINDICATOR * 2), currY + (HEIGHTINDICATOR * 1.5), currX - (WIDTHINDICATOR * 3),
					currY + (HEIGHTINDICATOR * 1.5), currX - WIDTHINDICATOR, currY);
		return path;
	}
}

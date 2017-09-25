package me.oddlyoko.zeroCreator.blocks;

import me.oddlyoko.zeroCreator.composant.IComposant;

public abstract class Block implements IBlocks {
	private boolean hover = false;
	private int xHover = 0;
	private int yHover = 0;
	private boolean click = false;
	private int xClick = 0;
	private int yClick = 0;
	private boolean press = false;
	private int xPress = 0;
	private int yPress = 0;
	private boolean rightClick = false;
	private int xRightClick = 0;
	private int yRightClick = 0;

	@Override
	public void onHover() {
		for (IComposant c : getComposantList())
			if (c != null && c.isInside(xHover, yHover))
				c.onHover();
	}

	@Override
	public void onClick() {
		for (IComposant c : getComposantList())
			if (c != null && c.isInside(xClick, yClick))
				c.onClick();
	}

	@Override
	public void onRightClick() {
		for (IComposant c : getComposantList()) {
			if (c != null && c.isInside(xRightClick, yRightClick))
				c.onRightClick();
		}
	}

	@Override
	public void onPress() {
		// NOTHING TO DO HERE
	}

	public void setHover(boolean is, int x, int y) {
		hover = is;
		xHover = x;
		yHover = y;
	}

	public void setClick(boolean is, int x, int y) {
		click = is;
		xClick = x;
		yClick = y;
	}

	public void setPress(boolean is, int x, int y) {
		press = is;
		xPress = x;
		yPress = y;
	}

	public void setRightClick(boolean is, int x, int y) {
		rightClick = is;
		xRightClick = x;
		yRightClick = y;
	}

	public boolean isHover() {
		return hover;
	}

	public int getXHover() {
		return xHover;
	}

	public int getYHover() {
		return yHover;
	}

	public boolean isClicked() {
		return click;
	}

	public int getXClicked() {
		return xClick;
	}

	public int getYClicked() {
		return yClick;
	}

	public boolean isPressed() {
		return press;
	}

	public int getXPressed() {
		return xPress;
	}

	public int getYPressed() {
		return yPress;
	}

	public boolean isRightClicked() {
		return rightClick;
	}

	public int getXRightClicked() {
		return xRightClick;
	}

	public int getYRightClicked() {
		return yRightClick;
	}
}

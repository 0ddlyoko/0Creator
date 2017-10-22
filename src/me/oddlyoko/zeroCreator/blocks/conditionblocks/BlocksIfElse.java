package me.oddlyoko.zeroCreator.blocks.conditionblocks;

import java.util.ArrayList;
import java.util.List;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.Block;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.blocks.customBlocks.IBlockResultType;
import me.oddlyoko.zeroCreator.blocks.customBlocks.IBlocksChildren;
import me.oddlyoko.zeroCreator.composant.ComposantBasicText;
import me.oddlyoko.zeroCreator.composant.ComposantButton;
import me.oddlyoko.zeroCreator.composant.ComposantButton.ComposantButtonClicked;
import me.oddlyoko.zeroCreator.composant.ComposantButton.IComposantButtonClicked;
import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;
import me.oddlyoko.zeroCreator.gui.blocks.BlocksIfElseUI;

public class BlocksIfElse extends Block implements IBlocksChildren {
	private final String NAME = "IfElse";
	private Project project;
	private InternalGUIFrame internalGUIFrame;
	private boolean hasElse = false;
	private IComposant[] composants = new IComposant[3];
	private IBlocks children;

	private IBlocks ifChildren = null;
	private IBlocks elseChildren = null;
	private IBlocks ifCond = null;
	private IBlocks elseCond = null;

	public BlocksIfElse(Project project) {
		this(project, 20, 20);
	}

	public BlocksIfElse(Project project, int x, int y) {
		this.project = project;
		ComposantBasicText cbt = new ComposantBasicText("IF");
		cbt.setX(35);
		cbt.setY(2);
		// cbt.setFont(Color.ORANGE);

		ComposantButton cb = new ComposantButton('E', new IComposantButtonClicked() {

			@Override
			public void execute(ComposantButtonClicked cbc) {
				setElse(cbc.getNewActive());
			}
		});
		cb.setX(5);
		cb.setY(15);
		cb.setWidth(15);
		cb.setHeight(15);

		ComposantBasicText cbt2 = new ComposantBasicText("ELSE");
		cbt2.setX(35);
		cbt2.setY(-1000);

		composants[0] = cbt;
		composants[1] = cb;
		composants[2] = cbt2;
		internalGUIFrame = new BlocksIfElseUI(this, x, y);
	}

	public IBlocks getIfChildren() {
		return ifChildren;
	}

	public IBlocks getElseChildren() {
		return elseChildren;
	}

	public void setIfChildren(IBlocks ifChildren) {
		this.ifChildren = ifChildren;
		if (ifChildren != null)
			ifChildren.setParent(this);
	}

	public void setElseChildren(IBlocks elseChildren) {
		this.elseChildren = elseChildren;
		if (elseChildren != null)
			elseChildren.setParent(this);
	}

	public IBlocks getIfCond() {
		return ifCond;
	}

	public IBlocks getElseCond() {
		return elseCond;
	}

	public void setIfCond(IBlocks ifCond) {
		this.ifCond = ifCond;
		if (ifCond != null)
			ifCond.setParent(this);
	}

	public void setElseCond(IBlocks elseCond) {
		this.elseCond = elseCond;
		if (elseCond != null)
			elseCond.setParent(this);
	}

	@Override
	public IBlocks getChildren() {
		return children;
	}

	@Override
	public void setChildren(IBlocks b) {
		if (this.children != null)
			this.children.setParent(null);
		this.children = b;
		if (b != null)
			b.setParent(this);
	}

	@Override
	public InternalGUIFrame getInternalGUIFrame() {
		return internalGUIFrame;
	}

	@Override
	public void move(int x, int y) {
		if (x == getInternalGUIFrame().getX() && y == getInternalGUIFrame().getY())
			return;
		getInternalGUIFrame().setLocation(x, y);
	}

	@Override
	public boolean canBlockAt(IBlocks b, int x, int y) {
		if (!(b instanceof BlocksIfElse) && !(b instanceof IBlockResultType))
			return false;
		if (b == this)
			return false;
		if (b instanceof BlocksIfElse) {
			if (y >= getInternalGUIFrame().getTotalHeight() - 20 && y <= getInternalGUIFrame().getTotalHeight())
				return true;
			if (ifChildren == null && x >= getInternalGUIFrame().getLocation(1).getX()
					&& y >= getInternalGUIFrame().getLocation(1).getY()
					&& y <= getInternalGUIFrame().getLocation(1).getY() + 20)
				return true;
			if (hasElse()) {
				if (elseChildren == null && x >= getInternalGUIFrame().getLocation(3).getX()
						&& y >= getInternalGUIFrame().getLocation(3).getY()
						&& y <= getInternalGUIFrame().getLocation(3).getY() + 20)
					return true;
			}
		} else if (b instanceof IBlockResultType) {
			if (ifCond == null && x >= getInternalGUIFrame().getLocation(0).getX()
					&& x <= getInternalGUIFrame().getLocation(0).getX() + internalGUIFrame.getWidth()
					&& y >= getInternalGUIFrame().getLocation(0).getY()
					&& y <= getInternalGUIFrame().getLocation(0).getY() + internalGUIFrame.getHeight())
				return true;
			if (hasElse()) {
				if (elseCond == null && x >= getInternalGUIFrame().getLocation(2).getX()
						&& x <= getInternalGUIFrame().getLocation(2).getX() + internalGUIFrame.getWidth()
						&& y >= getInternalGUIFrame().getLocation(2).getY()
						&& y <= getInternalGUIFrame().getLocation(2).getY() + internalGUIFrame.getHeight())
					return true;
			}
		}
		return false;
	}

	@Override
	public void setBlockAt(IBlocks b, int x, int y) {
		if (!(b instanceof BlocksIfElse) && !(b instanceof IBlockResultType))
			return;
		if (b == this)
			return;
		if (b instanceof BlocksIfElse) {
			if (y >= getInternalGUIFrame().getTotalHeight() - 20 && y <= getInternalGUIFrame().getTotalHeight())
				setChildren(b);
			if (ifChildren == null && x >= getInternalGUIFrame().getLocation(1).getX()
					&& y >= getInternalGUIFrame().getLocation(1).getY()
					&& y <= getInternalGUIFrame().getLocation(1).getY() + 20)
				setIfChildren(b);
			if (hasElse()) {
				if (elseChildren == null && x >= getInternalGUIFrame().getLocation(3).getX()
						&& y >= getInternalGUIFrame().getLocation(3).getY()
						&& y <= getInternalGUIFrame().getLocation(3).getY() + 20)
					setElseChildren(b);
			}
		} else if (b instanceof IBlockResultType) {
			if (ifCond == null && x >= getInternalGUIFrame().getLocation(0).getX()
					&& x <= getInternalGUIFrame().getLocation(0).getX() + internalGUIFrame.getWidth()
					&& y >= getInternalGUIFrame().getLocation(0).getY()
					&& y <= getInternalGUIFrame().getLocation(0).getY() + internalGUIFrame.getHeight())
				setIfCond(b);
			if (hasElse()) {
				if (elseCond == null && x >= getInternalGUIFrame().getLocation(2).getX()
						&& x <= getInternalGUIFrame().getLocation(2).getX() + internalGUIFrame.getWidth()
						&& y >= getInternalGUIFrame().getLocation(2).getY()
						&& y <= getInternalGUIFrame().getLocation(2).getY() + internalGUIFrame.getHeight())
					setElseCond(b);
			}
		}
	}

	@Override
	public void updateAll() {
		if (ifChildren != null)
			ifChildren.move(getInternalGUIFrame().getX() + getInternalGUIFrame().getLocation(1).getX(),
					getInternalGUIFrame().getY() + getInternalGUIFrame().getLocation(1).getY());
		if (ifCond != null)
			ifCond.move(getInternalGUIFrame().getX() + getInternalGUIFrame().getLocation(0).getX(),
					getInternalGUIFrame().getY() + getInternalGUIFrame().getLocation(0).getY());
		if (getChildren() != null)
			getChildren().move(getInternalGUIFrame().getX(),
					getInternalGUIFrame().getY() + getInternalGUIFrame().getTotalHeight());
		if (!hasElse) {
			if (elseChildren != null) {
				elseChildren.setParent(null);
				setElseChildren(null);
			}
			if (elseCond != null) {
				elseCond.setParent(null);
				setElseCond(null);
			}
			composants[2].setY(-1000);
		} else {
			if (elseChildren != null)
				elseChildren.move(getInternalGUIFrame().getX() + getInternalGUIFrame().getLocation(3).getX(),
						getInternalGUIFrame().getY() + getInternalGUIFrame().getLocation(3).getY());
			if (elseCond != null)
				elseCond.move(getInternalGUIFrame().getX() + getInternalGUIFrame().getLocation(2).getX(),
						getInternalGUIFrame().getY() + getInternalGUIFrame().getLocation(2).getY());
			composants[2].setY(getInternalGUIFrame().getLocation(2).getY());
		}
	}

	@Override
	public List<IBlocks> getBlocks() {
		List<IBlocks> list = new ArrayList<>();
		list.add(ifChildren);
		list.add(elseChildren);
		list.add(ifCond);
		list.add(elseCond);
		list.add(getChildren());
		return list;
	}

	@Override
	public void removeBlock(IBlocks b) {
		if (b == null)
			return;
		if (ifChildren != null && ifChildren.equals(b)) {
			ifChildren.setParent(null);
			setIfChildren(null);
		} else if (elseChildren != null && elseChildren.equals(b)) {
			elseChildren.setParent(null);
			setElseChildren(null);
		} else if (ifCond != null && ifCond.equals(b)) {
			ifCond.setParent(null);
			setIfCond(null);
		} else if (elseCond != null && elseCond.equals(b)) {
			elseCond.setParent(null);
			setElseCond(null);
		} else if (getChildren() != null && getChildren().equals(b)) {
			getChildren().setParent(null);
			setChildren(null);
		} else if (getParent() != null)
			getParent().removeBlock(this);
	}

	@Override
	public void delete() {
		removeBlock(ifChildren);
		removeBlock(elseChildren);
		removeBlock(ifCond);
		removeBlock(elseCond);
		removeBlock(getChildren());
		if (getParent() != null)
			getParent().removeBlock(this);
	}

	public boolean hasElse() {
		return hasElse;
	}

	public void setElse(boolean hasElse) {
		this.hasElse = hasElse;
		getInternalGUIFrame().setNbLocations((hasElse) ? 4 : 2);
	}

	@Override
	public IComposant[] getComposantList() {
		return composants;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public IBlocks clone1() {
		return new BlocksIfElse(project);
	}

	@Override
	public String toCode() {
		// TODO END HERE
		return null;
	}

	@Override
	public Class<?> getReturnType() {
		return null;
	}
}

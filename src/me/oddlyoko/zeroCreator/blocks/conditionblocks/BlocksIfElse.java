package me.oddlyoko.zeroCreator.blocks.conditionblocks;

import java.util.ArrayList;
import java.util.List;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.blocks.customBlocks.BlocksChildren;
import me.oddlyoko.zeroCreator.composant.ComposantBasicText;
import me.oddlyoko.zeroCreator.composant.ComposantButton;
import me.oddlyoko.zeroCreator.composant.ComposantButton.ComposantButtonClicked;
import me.oddlyoko.zeroCreator.composant.ComposantButton.IComposantButtonClicked;
import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;
import me.oddlyoko.zeroCreator.gui.blocks.BlocksIfElseUI;

public class BlocksIfElse extends BlocksChildren {
	private final String NAME = "IfElse";
	private Project project;
	private InternalGUIFrame internalGUIFrame;
	private boolean hasElse = false;
	private IComposant[] composants = new IComposant[3];

	private IBlocks ifChildren = null;
	private IBlocks elseChildren = null;

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
				move(internalGUIFrame.getX(), internalGUIFrame.getY());
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
		project.getBlocksManager().updateAll();
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
		project.getBlocksManager().updateAll();
	}

	public void setElseChildren(IBlocks elseChildren) {
		this.elseChildren = elseChildren;
		if (elseChildren != null)
			elseChildren.setParent(this);
		project.getBlocksManager().updateAll();
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
		project.getBlocksManager().updateAll();
	}

	@Override
	public void updateAll() {
		if (ifChildren != null)
			ifChildren.move(getInternalGUIFrame().getX() + 40, getInternalGUIFrame().getY() + 25);
		if (getChildren() != null)
			getChildren().move(getInternalGUIFrame().getX(),
					getInternalGUIFrame().getY() + getInternalGUIFrame().getTotalHeight());
		if (!hasElse) {
			if (elseChildren != null) {
				elseChildren.setParent(null);
				setElseChildren(null);
			}
			composants[2].setY(-1000);
		} else {
			if (elseChildren != null)
				elseChildren.move(getInternalGUIFrame().getX() + 40, getInternalGUIFrame().getY()
						+ (25 + 10 + Math.max(25, elseChildren.getInternalGUIFrame().getTotalHeightWithNextElement())));
			composants[2].setY(25 + Math.max(35,
					ifChildren == null ? 0 : ifChildren.getInternalGUIFrame().getTotalHeightWithNextElement()));
		}
	}

	@Override
	public List<IBlocks> getBlocks() {
		List<IBlocks> list = new ArrayList<>();
		list.add(ifChildren);
		list.add(elseChildren);
		list.add(getChildren());
		return list;
	}

	@Override
	public void removeBlock(IBlocks b) {
		if (ifChildren != null && ifChildren.equals(b)) {
			ifChildren.setParent(null);
			setIfChildren(null);
		} else if (elseChildren != null && elseChildren.equals(b)) {
			elseChildren.setParent(null);
			setElseChildren(null);
		} else if (getChildren() != null && getChildren().equals(b)) {
			getChildren().setParent(null);
			setChildren(null);
		} else if (getParent() != null && getParent().equals(b)) {
			((BlocksChildren) getParent()).setChildren(null);
			setParent(null);
		}
		project.getBlocksManager().updateAll();
	}

	@Override
	public void delete() {
		if (ifChildren != null) {
			ifChildren.setParent(null);
			setIfChildren(null);
		}
		if (elseChildren != null) {
			elseChildren.setParent(null);
			setElseChildren(null);
		}
		if (getChildren() != null) {
			getChildren().setParent(null);
			setChildren(null);
		}
		if (getParent() != null) {
			((BlocksChildren) getParent()).setChildren(null);
			setParent(null);
		}
		project.getBlocksManager().updateAll();
	}

	@Override
	public void setChildren(IBlocks b) {
		super.setChildren(b);
		if (b != null) {
			b.setParent(this);
			b.move(getInternalGUIFrame().getX(), getInternalGUIFrame().getY() + getInternalGUIFrame().getTotalHeight());
		}
		project.getBlocksManager().updateAll();
	}

	public boolean hasElse() {
		return hasElse;
	}

	public void setElse(boolean hasElse) {
		this.hasElse = hasElse;
		project.getBlocksManager().updateAll();
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
		/*
		 * BlocksIfElse b = new BlocksIfElse(project);
		 * 
		 * ComposantButton cb1 = (ComposantButton) composants[1];
		 * ComposantButton cb2 = (ComposantButton) b.composants[1];
		 * cb2.setActive(cb1.isActive());
		 * 
		 * 
		 * return b;
		 */
		return new BlocksIfElse(project);
	}

	@Override
	public String toCode() {
		// TODO END HERE
		return null;
	}
}

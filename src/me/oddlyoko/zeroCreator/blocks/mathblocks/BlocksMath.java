package me.oddlyoko.zeroCreator.blocks.mathblocks;

import java.util.ArrayList;
import java.util.List;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.Block;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.blocks.customBlocks.IBlockResultInteger;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinal;
import me.oddlyoko.zeroCreator.composant.ComposantBasicText;
import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableList;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;
import me.oddlyoko.zeroCreator.gui.blocks.math.BlocksMathUI;
import me.oddlyoko.zeroCreator.util.gui.BlocksUI;

public class BlocksMath extends Block implements IBlockResultInteger {
	private final String NAME = "Math";
	private BlocksMathEnum bme;
	private Project project;
	private InternalGUIFrame internalGUIFrame;
	private BlocksFinal[] nbs;
	private IComposant[] composants = new IComposant[2];

	public BlocksMath(Project project, BlocksMathEnum bme) {
		this(project, bme, 13, 2);
	}

	public BlocksMath(Project project, BlocksMathEnum bme, int x, int y) {
		this.project = project;
		this.bme = bme;
		nbs = new BlocksFinal[bme.getVars().length];
		ComposantBasicText cbt = new ComposantBasicText("Math: ");
		cbt.setX(13);
		cbt.setY(2);
		composants[0] = cbt;
		ComposantEditableList cel = new ComposantEditableList(bme.name);
		for (BlocksMathEnum bmes : BlocksMathEnum.values())
			cel.addItem(bmes.getName());
		cel.setComposantEvent(() -> {
			this.bme = BlocksMathEnum.getValueOf((String) cel.getObject());
			project.getBlocksManager().updateAll();
		});
		cel.lock();
		cel.setX(45);
		cel.setY(2);
		composants[1] = cel;
		internalGUIFrame = new BlocksMathUI(this, x, y);
		project.getBlocksManager().updateAll();
	}

	public IComposant getComposant(int i) {
		return composants[i];
	}

	public BlocksFinal getNb(int nb) {
		return nbs[nb];
	}

	public boolean setNb(int nb, BlocksFinal value) {
		if (value.getReturnType() != bme.getVars()[nb])
			return false;
		nbs[nb] = value;
		value.setParent(this);
		project.getBlocksManager().updateAll();
		return true;
	}

	public void setBlocksMathEnum(BlocksMathEnum bme) {
		this.bme = bme;
	}

	public BlocksMathEnum getBlocksMathEnum() {
		return bme;
	}

	@Override
	public InternalGUIFrame getInternalGUIFrame() {
		return internalGUIFrame;
	}

	@Override
	public List<IBlocks> getBlocks() {
		List<IBlocks> list = new ArrayList<>();
		for (BlocksFinal irt : nbs)
			list.add(irt);
		return list;
	}

	@Override
	public void removeBlock(IBlocks b) {
		if (b == null)
			return;
		for (int i = 0; i < bme.getVars().length; i++)
			if (nbs[i] == b) {
				b.setParent(null);
				nbs[i] = null;
			}
		project.getBlocksManager().updateAll();
	}

	@Override
	public void delete() {
		for (BlocksFinal irt : nbs)
			removeBlock(irt);
		if (getParent() != null)
			getParent().removeBlock(this);
		project.getBlocksManager().updateAll();
	}

	@Override
	public String toCode() {
		StringBuilder result = new StringBuilder();
		if (bme.isMathClass())
			result.append("Math.").append(bme.getCmd());
		result.append("(");
		if (bme.getVars().length >= 1) {
			result.append((nbs[0] == null) ? "null" : nbs[0].toCode());
			for (int i = 1; i < bme.getVars().length; i++) {
				result.append((bme.isMathClass()) ? ", " : bme.getCmd());
				result.append((nbs[i] == null) ? "null" : nbs[i].toCode());
			}
		}
		result.append(");");
		return result.toString();
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
		if (bme.getVars().length < nbs.length)
			for (int i = bme.getVars().length; i < nbs.length; i++)
				removeBlock(nbs[i]);
		if (bme.getVars().length != nbs.length) {
			BlocksFinal[] bf = new BlocksFinal[bme.getVars().length];
			for (int i = 0; i < bf.length; i++)
				nbs[i] = bf[i];
			nbs = bf;
			project.getBlocksManager().updateAll();
		}
		int x = getInternalGUIFrame().getX() + BlocksUI.SIZEROUND + 5 + getComposantList()[0].getWidth()
				+ getComposantList()[1].getWidth() + 16;
		for (int i = 0; i < getBlocks().size(); i++) {
			if (getBlocks().get(i) != null) {
				getBlocks().get(i).move(x, getInternalGUIFrame().getY() + 2);
				x += getBlocks().get(i).getInternalGUIFrame().getTotalWidth() + 5;
			} else
				x += BlocksUI.SIZEROUND + 10 + 5;
		}
		// System.out.println("UPDATE: toCode() = " + toCode());
	}

	@Override
	public IComposant[] getComposantList() {
		return composants;
	}

	public Project getProject() {
		return project;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public IBlocks clone1() {
		return new BlocksMath(project, bme);
	}

	public enum BlocksMathEnum {
		MAX("Max", "max", true, Integer.class, Integer.class), // MAX
		MIN("Min", "min", true, Integer.class, Integer.class), // MIN
		PLUS("+", "+", false, Integer.class, Integer.class), // '+'
		MINUS("-", "-", false, Integer.class, Integer.class), // '-'
		TIMES("*", "*", false, Integer.class, Integer.class), // '*'
		DIVIDED("/", "/", false, Integer.class, Integer.class), // '*'
		MODULO("%", "%", false, Integer.class, Integer.class); // '/'

		private String name = null;
		private String cmd = null;
		private boolean mathClass = true;
		private Class<?>[] vars = null;

		private BlocksMathEnum(String name, String cmd, boolean mathClass, Class<?>... vars) {
			this.name = name;
			this.cmd = cmd;
			this.mathClass = mathClass;
			if (vars == null)
				vars = new Class<?>[0];
			this.vars = vars;
		}

		public String getName() {
			return name;
		}

		public String getCmd() {
			return cmd;
		}

		public boolean isMathClass() {
			return mathClass;
		}

		public Class<?>[] getVars() {
			return vars;
		}

		public static BlocksMathEnum getValueOf(String name) {
			for (BlocksMathEnum bme : values())
				if (bme.getName().equalsIgnoreCase(name))
					return bme;
			return MAX;
		}
	}
}

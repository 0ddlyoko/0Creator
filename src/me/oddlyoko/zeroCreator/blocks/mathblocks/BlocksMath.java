package me.oddlyoko.zeroCreator.blocks.mathblocks;

import java.util.ArrayList;
import java.util.List;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.blocks.customBlocks.BlockResultType;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinal;
import me.oddlyoko.zeroCreator.composant.ComposantBasicText;
import me.oddlyoko.zeroCreator.composant.IComposant;
import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableList;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;
import me.oddlyoko.zeroCreator.gui.blocks.math.BlocksMathUI;

public class BlocksMath extends BlockResultType<Integer> {
	private final String NAME = "Math";
	private BlocksMathEnum bme;
	private Project project;
	private InternalGUIFrame internalGUIFrame;
	private ArrayList<BlocksFinal<Integer>> nbs;
	private IComposant[] composants = new IComposant[2];

	public BlocksMath(Project project, BlocksMathEnum bme) {
		this(project, bme, 13, 2);
	}

	public BlocksMath(Project project, BlocksMathEnum bme, int x, int y) {
		this.project = project;
		this.bme = bme;
		nbs = new ArrayList<>();
		ComposantBasicText cbt = new ComposantBasicText("Math: ");
		cbt.setX(13);
		cbt.setY(2);
		composants[0] = cbt;
		ComposantEditableList cel = new ComposantEditableList(BlocksMathEnum.MAX.name);
		for (BlocksMathEnum bmes : BlocksMathEnum.values())
			cel.addItem(bmes.getName());
		cel.setComposantEvent(() -> {
			this.bme = BlocksMathEnum.getValueOf((String) cel.getObject());
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

	public BlocksFinal<Integer> getNb(int nb) {
		return nbs.get(nb);
	}

	public void setNb(int nb, BlocksFinal<Integer> value) {
		nbs.set(nb, value);
		project.getBlocksManager().updateAll();
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
		for (BlocksFinal<Integer> irt : nbs)
			list.add(irt);
		return list;
	}

	@Override
	public void removeBlock(IBlocks b) {
		if (b == null)
			return;
		if (b instanceof BlocksFinal) {
			BlocksFinal<?> irt = (BlocksFinal<?>) b;
			irt.setParent(null);
			nbs.remove(b);
		}
		project.getBlocksManager().updateAll();
	}

	@Override
	public void delete() {
		for (BlocksFinal<Integer> irt : nbs) {
			if (irt == null)
				continue;
			irt.setParent(null);
			nbs.remove(irt);
		}
		project.getBlocksManager().updateAll();
	}

	@Override
	public String toCode() {
		StringBuilder result = new StringBuilder("Math.").append(BlocksMathEnum.getValueOf(bme.getCmd()));
		if (bme.getNb() != 0) {
			result.append("(").append(nbs.get(0).toCode());
			for (int i = 1; i < bme.getNb(); i++)
				result.append(", ").append(nbs.get(1).toCode());
		}
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
		MAX("Max", "Max", 2),
		MIN("Min", "Min", 2);

		private String name;
		private String cmd;
		private int nb;

		private BlocksMathEnum(String name, String cmd, int nb) {
			this.name = name;
			this.cmd = cmd;
			this.nb = nb;
		}

		public String getName() {
			return name;
		}

		public String getCmd() {
			return cmd;
		}

		public int getNb() {
			return nb;
		}

		public static BlocksMathEnum getValueOf(String name) {
			for (BlocksMathEnum bme : values())
				if (bme.getName().equalsIgnoreCase(name))
					return bme;
			return MAX;
		}
	}
}

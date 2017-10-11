package me.oddlyoko.zeroCreator.gui.frame.blocks;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.conditionblocks.BlocksIfElse;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalBoolean;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalDouble;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalInteger;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalList;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalString;

public class BlocksGUI extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private Project project;
	private JPanel panelButtons;
	private PanelBlocks panelBlocks;

	private List<Separators> separators;

	/**
	 * Create the frame.
	 * 
	 * @param project
	 */
	public BlocksGUI(Project project) {
		this.project = project;
		separators = new ArrayList<>();
		init();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Blocks");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		panelButtons = new JPanel();
		panelButtons.setAlignmentY(Component.TOP_ALIGNMENT);
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
		setButtons(panelButtons);

		getContentPane().add(panelButtons);

		panelBlocks = new PanelBlocks(project);
	}

	public void init() {
		Separators conditional = new Separators("Conditional");
		conditional.addBlock(new BlocksIfElse(project));
		addSeparator(conditional);
		Separators finalBlocks = new Separators("Final");
		finalBlocks.addBlock(new BlocksFinalString(project, "0ddlyoko"));
		finalBlocks.addBlock(new BlocksFinalInteger(project, 1));
		finalBlocks.addBlock(new BlocksFinalDouble(project, 1.0));
		finalBlocks.addBlock(new BlocksFinalBoolean(project, true));
		BlocksFinalList bfList = new BlocksFinalList(project, "Element 1",
				new String[] { "Element 1", "Element 2", "Element 3", "Element 4" });
		finalBlocks.addBlock(bfList);
		addSeparator(finalBlocks);
	}

	private void setButtons(JPanel panelSeparators) {
		for (Separators s : separators) {
			panelSeparators.add(s.getButton());
			s.getButton().addActionListener(this);
		}
	}

	private void addSeparator(Separators separator) {
		separators.add(separator);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Separators s = null;
		for (Separators s2 : separators)
			if (e.getSource() instanceof JButton)
				if (s2.getButton().equals(e.getSource())) {
					s = s2;
					break;
				}
		if (s == null)
			return;
		panelBlocks.setSeparator(s);
		panelBlocks.repaint();
	}

	public PanelBlocks getPanelBlocks() {
		return panelBlocks;
	}
}

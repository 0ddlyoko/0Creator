package me.oddlyoko.zeroCreator.gui.frame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import me.oddlyoko.zeroCreator.blocks.Block;
import me.oddlyoko.zeroCreator.blocks.BlocksEvents;
import me.oddlyoko.zeroCreator.blocks.BlocksInstruction;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.blocks.IBlocksPrevious;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinal;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalDouble;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalInteger;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalList;
import me.oddlyoko.zeroCreator.blocks.finalblocks.BlocksFinalString;
import me.oddlyoko.zeroCreator.gui.GUIManager;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;

public class MainGUI extends JFrame implements MouseListener, MouseMotionListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private Block blockHover = null;
	private Block blockPressed = null;
	private Block blockClicked = null;

	// MENU
	private JMenuBar menuBar;
	private JMenu mnPlugin;
	private JMenu mnAbout;
	private JMenu mnBlocks;
	private JMenuItem mntmNewPlugin;
	private JMenuItem mntmLoad;
	private JMenuItem mntmSave;
	private JMenuItem mntmQuit;
	private JMenuItem mntmNewBlocks;
	private JMenuItem mntmAbout0Creator;
	private JMenuItem mntmHowToUse;

	private JPanel contentPane;
	private BlocksEvents be1;
	private List<IBlocks> blocks = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		initialize();
		initializeBlocks(); // TODO: REMOVE IT
	}

	private void initialize() {
		// Set Default Theme
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);

		// ---------- MENU ----------

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// ----- Plugin -----
		mnPlugin = new JMenu("Plugin");
		menuBar.add(mnPlugin);

		mntmNewPlugin = new JMenuItem("New");
		mnPlugin.add(mntmNewPlugin);
		mntmNewPlugin.setActionCommand("PLUGIN_NEW");
		mntmNewPlugin.addActionListener(this);

		mntmLoad = new JMenuItem("Load");
		mnPlugin.add(mntmLoad);
		mntmLoad.setActionCommand("PLUGIN_LOAD");
		mntmLoad.addActionListener(this);

		mntmSave = new JMenuItem("Save");
		mnPlugin.add(mntmSave);
		mntmSave.setActionCommand("PLUGIN_SAVE");
		mntmSave.addActionListener(this);

		mntmQuit = new JMenuItem("Quit");
		mnPlugin.add(mntmQuit);
		mntmQuit.setActionCommand("PLUGIN_QUIT");
		mntmQuit.addActionListener(this);

		// ----- Blocks -----
		mnBlocks = new JMenu("Blocks");
		menuBar.add(mnBlocks);

		mntmNewBlocks = new JMenuItem("New");
		mnBlocks.add(mntmNewBlocks);

		// ----- About -----
		mnAbout = new JMenu("About");
		menuBar.add(mnAbout);

		mntmHowToUse = new JMenuItem("How to use ?");
		mnAbout.add(mntmHowToUse);
		mntmHowToUse.setActionCommand("ABOUT_HOWTOUSE");
		mntmHowToUse.addActionListener(this);

		mntmAbout0Creator = new JMenuItem("About 0Creator");
		mnAbout.add(mntmAbout0Creator);
		mntmAbout0Creator.setActionCommand("ABOUT_ABOUT");
		mntmAbout0Creator.addActionListener(this);

		// ---------- PANNEL ----------

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.addMouseListener(this);
		contentPane.addMouseMotionListener(this);

		// ---------- REPAINT ----------

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					repaint();
				}
			}
		}).start();
	}

	private void initializeBlocks() {
		be1 = new BlocksEvents("On Plugin Load");
		be1.move(10, 10);
		addBlock(be1);
		BlocksInstruction bi1 = new BlocksInstruction();
		BlocksInstruction bi11 = new BlocksInstruction();
		BlocksInstruction bi2 = new BlocksInstruction();
		BlocksInstruction bi3 = new BlocksInstruction();
		addBlock(bi1);
		addBlock(bi11);
		addBlock(bi2);
		addBlock(bi3);
		BlocksFinal bf1 = new BlocksFinalString("0ddlyoko");
		BlocksFinal bf2 = new BlocksFinalString("C'est");
		BlocksFinal bf3 = new BlocksFinalString("Un");
		BlocksFinal bf4 = new BlocksFinalString("Developpeur");
		BlocksFinal bf5 = new BlocksFinalString("En");
		BlocksFinal bf6 = new BlocksFinalString("Mousse");
		BlocksFinal bf7 = new BlocksFinalInteger(1);
		BlocksFinal bf8 = new BlocksFinalInteger(2);
		BlocksFinal bf9 = new BlocksFinalInteger(3);
		BlocksFinal bf10 = new BlocksFinalDouble(4.1);
		BlocksFinalList bf11 = new BlocksFinalList(Boolean.TRUE);
		bf11.add(true);
		bf11.add(false);
		bf1.move(getWidth() / 2, 50);
		bf2.move(getWidth() / 2, 70);
		bf3.move(getWidth() / 2, 90);
		bf4.move(getWidth() / 2, 110);
		bf5.move(getWidth() / 2, 130);
		bf6.move(getWidth() / 2, 150);
		bf7.move(getWidth() / 2, 170);
		bf8.move(getWidth() / 2, 190);
		bf9.move(getWidth() / 2, 210);
		bf10.move(getWidth() / 2, 230);
		bf11.move(getWidth() / 2, 250);
		addBlock(bf1);
		addBlock(bf2);
		addBlock(bf3);
		addBlock(bf4);
		addBlock(bf5);
		addBlock(bf6);
		addBlock(bf7);
		addBlock(bf8);
		addBlock(bf9);
		addBlock(bf10);
		addBlock(bf11);
		bi3.setEnd(true);
		be1.setNext(bi1);
		bi1.setChildren(bi11);
		bi1.setNext(bi2);
		bi2.setNext(bi3);
	}

	@Override
	public void paintComponents(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawRoundRect(getX(), getY(), getWidth(), getHeight(), 5, 5);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd != null && !"".equalsIgnoreCase(cmd)) {
			if ("PLUGIN_NEW".equalsIgnoreCase(cmd)) {
				// TODO END HERE
			} else if ("PLUGIN_LOAD".equalsIgnoreCase(cmd)) {
				// TODO END HERE
			} else if ("PLUGIN_SAVE".equalsIgnoreCase(cmd)) {
				// TODO END HERE
			} else if ("PLUGIN_QUIT".equalsIgnoreCase(cmd)) {
				// TODO CHANGE HERE
				System.exit(0);
			} else if ("ABOUT_HOWTOUSE".equalsIgnoreCase(cmd)) {
				// TODO END HERE
			} else if ("ABOUT_ABOUT".equalsIgnoreCase(cmd)) {
				GUIManager.showAboutGUI();
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) { // TODO REWRITE THIS IN ANOTHER
												// CLASS
		if (blockHover != null) {
			if (blockHover instanceof IBlocksPrevious) {
				IBlocksPrevious ibp = (IBlocksPrevious) blockHover;
				if (ibp.getPrevious() != null)
					ibp.getPrevious().setNext(null);
				ibp.setPrevious(null);
			}
			blockHover.move(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (blockHover != null) {
			blockHover.setHover(false, 0, 0);
			blockHover = null;
		}
		IBlocks b = getBlockAt(e.getX(), e.getY());
		if (b != null && b instanceof Block) {
			blockHover = (Block) b;
			blockHover.setHover(true, e.getX() - b.getInternalGUIFrame().getX(),
					e.getY() - b.getInternalGUIFrame().getY());
			blockHover.onHover();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (blockClicked != null) {
			blockClicked.setClick(false, 0, 0);
			blockClicked = null;
		}
		IBlocks b = getBlockAt(e.getX(), e.getY());
		if (b != null && b instanceof Block) {
			blockClicked = (Block) b;
			blockClicked.setClick(true, e.getX() - b.getInternalGUIFrame().getX(),
					e.getY() - b.getInternalGUIFrame().getY());
			blockClicked.onClick();
			System.out.println("Click: " + blockClicked);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (blockPressed != null) {
			blockPressed.setPress(false, 0, 0);
			blockPressed = null;
		}
		IBlocks b = getBlockAt(e.getX(), e.getY());
		if (b != null && b instanceof Block) {
			blockPressed = (Block) b;
			blockPressed.setPress(true, e.getX() - b.getInternalGUIFrame().getX(),
					e.getY() - b.getInternalGUIFrame().getY());
			blockPressed.onPress();
			System.out.println("Pressed: " + blockPressed);
		}
		if (e.isPopupTrigger()) {
			showMenu(e, blockHover);
		}
	}

	private IBlocks getBlockAt(int x, int y) {
		IBlocks block = null;
		int minWidth = 0;
		int minHeight = 0;
		for (IBlocks b : blocks) {
			InternalGUIFrame igf = b.getInternalGUIFrame();
			if (x >= igf.getX() && x <= igf.getX() + igf.getWidth() && y >= igf.getY()
					&& y <= igf.getY() + igf.getHeight()) {
				if (block == null || igf.getWidth() < minWidth || igf.getHeight() < minHeight) {
					block = b;
					minWidth = igf.getWidth();
					minHeight = igf.getHeight();
				}
			}
		}
		return block;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (blockPressed != null) {
			blockPressed.setPress(false, 0, 0);
			blockPressed = null;
		}
		if (e.isPopupTrigger()) {
			showMenu(e, blockHover);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Nothing to do here
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Nothing to do here
	}

	public void addBlock(IBlocks block) {
		blocks.add(block);
		getContentPane().add(block.getInternalGUIFrame());
	}

	public void remove(IBlocks block) {
		blocks.remove(block);
		remove(block.getInternalGUIFrame());
	}

	// RIGHT CLICK MENU
	private JPopupMenu popupMenu;
	// New Menu
	private JMenu mnNew;
	private JMenuItem mntmBlock;
	private JMenuItem mntmBlock_1;

	// Block Menu
	private JMenuItem mntmDelete;
	private JMenuItem mntmAbout;

	private void showMenu(MouseEvent e, Block b) {
		System.out.println(b);

		popupMenu = new JPopupMenu();
		popupMenu.setBounds(0, 0, 398, 14);

		// ----- NEW -----
		mnNew = new JMenu("New");
		popupMenu.add(mnNew);

		mntmBlock = new JMenuItem("Block 1");
		mnNew.add(mntmBlock);

		mntmBlock_1 = new JMenuItem("Block 2");
		mnNew.add(mntmBlock_1);

		// ----- BLOCK -----

		if (b != null) {
			mntmDelete = new JMenuItem("Delete");
			popupMenu.add(mntmDelete);

			mntmAbout = new JMenuItem("About " + b.getName() + " block");
			popupMenu.add(mntmAbout);
		}

		popupMenu.show(e.getComponent(), e.getX(), e.getY());
	}
}

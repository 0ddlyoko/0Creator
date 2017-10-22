package me.oddlyoko.zeroCreator.gui.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.ZeroCreator;
import me.oddlyoko.zeroCreator.blocks.Block;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;

public class MainGUI extends JFrame implements MouseListener, MouseMotionListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private Project project = null;
	private boolean end = false;

	private Block blockHover = null;
	private Block blockPressed = null;
	private Block blockClicked = null;
	private Block blockRightClicked = null;

	// MENU
	private JMenuBar menuBar;
	private JMenu mnPlugin;
	private JMenu mnAbout;
	private JMenu mnBlocks;
	private JMenuItem mntmNewPlugin;
	private JMenuItem mntmLoad;
	private JMenuItem mntmSave;
	private JMenuItem mntmEdit;
	private JMenuItem mntmQuit;
	private JMenuItem mntmNewBlocks;
	private JMenuItem mntmAbout0Creator;
	private JMenuItem mntmHowToUse;

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainGUI(Project project) {
		this.project = project;
		initialize();
	}

	private void initialize() {
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

		mntmEdit = new JMenuItem("Edit");
		mnPlugin.add(mntmEdit);
		mntmEdit.setActionCommand("PLUGIN_EDIT");
		mntmEdit.addActionListener(this);

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

		add(project.getGUIManager().getBlocksGUI().getPanelBlocks());

		// ---------- REPAINT ----------

		new Thread(new Runnable() {

			@Override
			public void run() {
				startPaint();
			}
		}).start();
	}

	private void startPaint() {
		long lastTime = System.nanoTime();
		final double TICKS = 60D;
		final double NS = 1000000000 / TICKS;
		double delta = 0;
		while (!end) {
			long now = System.nanoTime();
			delta += (now - lastTime) / NS;
			lastTime = now;
			if (delta >= 1) {
				tick();
				repaint();
				delta--;
			}
		}
	}

	private void tick() {
		project.getBlocksManager().updateAll();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd != null && !"".equalsIgnoreCase(cmd)) {
			if ("PLUGIN_NEW".equalsIgnoreCase(cmd)) {
				int n = JOptionPane.showConfirmDialog(null,
						"Warning ! Creating new Project will delete this current Project\n"
								+ "Don't forget to save this project !" + "\nDo you want to continue?",
						"Warning", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					ZeroCreator.createNewProject();
				}
				// TODO END HERE
			} else if ("PLUGIN_LOAD".equalsIgnoreCase(cmd)) {
				// TODO END HERE
			} else if ("PLUGIN_SAVE".equalsIgnoreCase(cmd)) {
				// TODO END HERE
			} else if ("PLUGIN_EDIT".equalsIgnoreCase(cmd)) {
				project.askPluginInformation();
			} else if ("PLUGIN_QUIT".equalsIgnoreCase(cmd)) {
				// TODO CHANGE HERE
				System.exit(0);
			} else if ("ABOUT_HOWTOUSE".equalsIgnoreCase(cmd)) {
				// TODO END HERE
			} else if ("ABOUT_ABOUT".equalsIgnoreCase(cmd)) {
				project.getGUIManager().showAboutGUI();
			} else if ("RIGHTCLICK_DELETE".equalsIgnoreCase(cmd)) {
				if (blockHover == null)
					return;
				int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all these blocks ?",
						"Warning", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION)
					project.getBlocksManager().removeBlocks(blockHover);
			} else if ("RIGHTCLICK_ABOUT".equalsIgnoreCase(cmd)) {

			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) { // TODO REWRITE THIS
		int x = e.getX();
		int y = e.getY();
		if (blockHover != null) {
			if (x >= 0 && x <= project.getGUIManager().getBlocksGUI().getPanelBlocks().getWidth() && y >= 0
					&& y <= project.getGUIManager().getBlocksGUI().getPanelBlocks().getHeight())
				return;
			if (blockHover.getParent() != null)
				blockHover.getParent().removeBlock(blockHover);
			blockHover.move(x, y);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (blockHover != null) {
			blockHover.setHover(false, 0, 0);
			blockHover = null;
		}
		if (x >= 0 && x <= project.getGUIManager().getBlocksGUI().getPanelBlocks().getWidth() && y >= 0
				&& y <= project.getGUIManager().getBlocksGUI().getPanelBlocks().getHeight())
			return;
		IBlocks b = getBlockAt(x, y);
		if (b != null && b instanceof Block) {
			blockHover = (Block) b;
			blockHover.setHover(true, x - b.getInternalGUIFrame().getX(), y - b.getInternalGUIFrame().getY());
			blockHover.onHover();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (blockClicked != null) {
			blockClicked.setClick(false, 0, 0);
			blockClicked = null;
		}
		if (x >= 0 && x <= project.getGUIManager().getBlocksGUI().getPanelBlocks().getWidth() && y >= 0
				&& y <= project.getGUIManager().getBlocksGUI().getPanelBlocks().getHeight())
			return;
		IBlocks b = getBlockAt(x, y);
		if (b != null && b instanceof Block) {
			blockClicked = (Block) b;
			blockClicked.setClick(true, x - b.getInternalGUIFrame().getX(), y - b.getInternalGUIFrame().getY());
			blockClicked.onClick();
			System.out.println("Click: " + blockClicked);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (blockPressed != null) {
			blockPressed.setPress(false, 0, 0);
			blockPressed = null;
		}
		if (blockRightClicked != null) {
			blockRightClicked.setPress(false, 0, 0);
			blockRightClicked = null;
		}
		if (x >= 0 && x <= project.getGUIManager().getBlocksGUI().getPanelBlocks().getWidth() && y >= 0
				&& y <= project.getGUIManager().getBlocksGUI().getPanelBlocks().getHeight())
			return;
		project.getGUIManager().getBlocksGUI().getPanelBlocks().setSeparator(null);
		IBlocks b = getBlockAt(x, y);
		if (b != null && b instanceof Block) {
			blockPressed = (Block) b;
			blockPressed.setPress(true, x - b.getInternalGUIFrame().getX(), y - b.getInternalGUIFrame().getY());
			blockPressed.onPress();
			System.out.println("Pressed: " + blockPressed);
			changeIndex(b);
		}
		if (e.isPopupTrigger()) {
			if (b != null && b instanceof Block) {
				// TODO CHANGE IT ???? I DON'T KNOW
				blockRightClicked = (Block) b;
				blockRightClicked.setRightClick(true, x - b.getInternalGUIFrame().getX(),
						y - b.getInternalGUIFrame().getY());
				blockRightClicked.onRightClick();
				System.out.println("Right Clicked: " + blockRightClicked);
			}
			showMenu(e, blockHover);
		}
	}

	private IBlocks getBlockAt(int x, int y) {
		IBlocks block = null;
		int minWidth = 0;
		int minHeight = 0;
		for (IBlocks b : project.getBlocksManager().getBlocks()) {
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
		int x = e.getX();
		int y = e.getY();
		if (blockPressed != null) {
			blockPressed.setPress(false, 0, 0);
			blockPressed = null;
		}
		IBlocks b = getBlockAt(x - 1, y - 1);
		if (b != null && b != blockHover
				&& b.canBlockAt(blockHover, x - b.getInternalGUIFrame().getX(), y - b.getInternalGUIFrame().getY())) {
			b.setBlockAt(blockHover, x - b.getInternalGUIFrame().getX(), y - b.getInternalGUIFrame().getY());
			return;
		}
		if (e.isPopupTrigger()) {
			// IBlocks b2 = getBlockAt(x, y);
			if (blockHover != null && blockHover instanceof Block) {
				// TODO CHANGE IT ???? I DON'T KNOW
				blockRightClicked = blockHover;
				blockRightClicked.setRightClick(true, x - blockHover.getInternalGUIFrame().getX(),
						y - blockHover.getInternalGUIFrame().getY());
				blockRightClicked.onRightClick();
				System.out.println("Right Clicked: " + blockRightClicked);
			}
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

	public void changeIndex(IBlocks b) {
		project.getGUIManager().getMainGUI().getContentPane().setComponentZOrder(b.getInternalGUIFrame(), 0);
		for (IBlocks b2 : b.getBlocks())
			if (b2 != null)
				changeIndex(b2);
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
		System.out.println("SHOWMENU !");
		System.out.println(b);

		popupMenu = new JPopupMenu();
		popupMenu.setBounds(0, 0, 398, 14);

		// ----- NEW -----
		mnNew = new JMenu("New");
		popupMenu.add(mnNew);

		mntmBlock = new JMenuItem("Block 1");
		mnNew.add(mntmBlock);
		mntmBlock.setActionCommand("RIGHTCLICK_BLOCK1");
		mntmBlock.addActionListener(this);

		mntmBlock_1 = new JMenuItem("Block 2");
		mnNew.add(mntmBlock_1);
		mntmBlock_1.setActionCommand("RIGHTCLICK_BLOCK2");
		mntmBlock_1.addActionListener(this);

		// ----- BLOCK -----

		if (b != null) {
			mntmDelete = new JMenuItem("Delete");
			popupMenu.add(mntmDelete);
			mntmDelete.setActionCommand("RIGHTCLICK_DELETE");
			mntmDelete.addActionListener(this);

			mntmAbout = new JMenuItem("About " + b.getName() + " block");
			popupMenu.add(mntmAbout);
			mntmAbout.setActionCommand("RIGHTCLICK_ABOUT");
			mntmAbout.addActionListener(this);
		}

		popupMenu.show(e.getComponent(), e.getX(), e.getY());
	}

	public void stop() {
		end = true;
	}
}

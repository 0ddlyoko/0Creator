package me.oddlyoko.zeroCreator.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import me.oddlyoko.zeroCreator.blocks.Block;
import me.oddlyoko.zeroCreator.blocks.BlocksEvents;
import me.oddlyoko.zeroCreator.blocks.BlocksFinal;
import me.oddlyoko.zeroCreator.blocks.BlocksInstruction;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.blocks.IBlocksPrevious;

public class MainGUI extends JFrame implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private BlocksEvents be1;
	private List<IBlocks> blocks = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.addMouseListener(this);
		contentPane.addMouseMotionListener(this);
		be1 = new BlocksEvents("On Plugin Load");
		be1.move(10, 10);
		add(be1);
		BlocksInstruction bi1 = new BlocksInstruction("bi1");
		add(bi1);
		BlocksInstruction bi11 = new BlocksInstruction("bi11");
		add(bi11);
		BlocksInstruction bi2 = new BlocksInstruction("bi2");
		add(bi2);
		BlocksInstruction bi3 = new BlocksInstruction("bi3");
		add(bi3);
		BlocksFinal bf1 = new BlocksFinal("0ddlyoko");
		bf1.move(getWidth() / 2, 50);
		BlocksFinal bf2 = new BlocksFinal("C'est");
		bf2.move(getWidth() / 2, 70);
		BlocksFinal bf3 = new BlocksFinal("Un");
		bf3.move(getWidth() / 2, 90);
		BlocksFinal bf4 = new BlocksFinal("Developpeur");
		bf4.move(getWidth() / 2, 110);
		BlocksFinal bf5 = new BlocksFinal("En");
		bf5.move(getWidth() / 2, 130);
		BlocksFinal bf6 = new BlocksFinal("Mousse");
		bf6.move(getWidth() / 2, 150);
		add(bf1);
		add(bf2);
		add(bf3);
		add(bf4);
		add(bf5);
		add(bf6);
		bi3.setEnd(true);
		be1.setNext(bi1);
		bi1.setChildren(bi11);
		bi1.setNext(bi2);
		bi2.setNext(bi3);
		// bi1.setChildren(bf1);
		contentPane.add(be1.getInternalGUIFrame());
		// add(bi1.getInternalGUIFrame());
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					repaint();
				}
			}
		}).start();
	}

	@Override
	public void paintComponents(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawRoundRect(getX(), getY(), getWidth(), getHeight(), 5, 5);
	}

	private Block blockHover = null;
	private Block blockPressed = null;
	private Block blockClicked = null;

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
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Nothing to do here
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Nothing to do here
	}

	public void add(IBlocks block) {
		blocks.add(block);
		add(block.getInternalGUIFrame());
	}

	public void remove(IBlocks block) {
		blocks.remove(block);
		remove(block.getInternalGUIFrame());
	}
}

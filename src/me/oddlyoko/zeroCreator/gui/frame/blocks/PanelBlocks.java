package me.oddlyoko.zeroCreator.gui.frame.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import me.oddlyoko.zeroCreator.Project;
import me.oddlyoko.zeroCreator.blocks.Block;
import me.oddlyoko.zeroCreator.blocks.IBlocks;
import me.oddlyoko.zeroCreator.gui.InternalGUIFrame;

public class PanelBlocks extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;

	private Project project;
	private Separators separator = null;
	private Block blockHover = null;

	public PanelBlocks(Project project) {
		this.project = project;
		setSize(0, 0);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void setSeparator(Separators separator) {
		if (this.separator != null)
			for (IBlocks b : this.separator.getBlocks())
				remove(b.getInternalGUIFrame());
		this.separator = separator;
		if (this.separator != null) {
			for (IBlocks b : this.separator.getBlocks())
				add(b.getInternalGUIFrame());
			setSize(200, 200);
			initSeparator();
		} else
			setSize(0, 0);
	}

	private void initSeparator() {
		int width = 20;
		int height = 20;
		for (IBlocks b : separator.getBlocks()) {
			b.move(width, height);
			height += b.getInternalGUIFrame().getTotalHeight() + 10;
		}
	}

	private Color background = new Color(128, 128, 128, 127);

	@Override
	protected void paintComponent(Graphics g) {
		// super.paintComponent(g);
		g.setColor(background);
		g.fillRect(0, 0, getWidth(), getHeight());
		// int height = 20;
		if (separator != null) {
			for (IBlocks b : separator.getBlocks()) {
				/*
				 * System.out.println(b.getInternalGUIFrame().getLocation());
				 * b.getInternalGUIFrame().setLocation(20, height); height +=
				 * b.getInternalGUIFrame().getTotalHeight() + 10;
				 */
				b.getInternalGUIFrame().repaint();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		IBlocks b = getBlockAt(x, y);
		IBlocks b2 = b.clone1();
		b2.move(project.getGUIManager().getMainGUI().getWidth() / 2,
				project.getGUIManager().getMainGUI().getHeight() / 2);
		project.getBlocksManager().addBlock(b2);
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

	private IBlocks getBlockAt(int x, int y) {
		IBlocks block = null;
		int minWidth = 0;
		int minHeight = 0;
		for (IBlocks b : separator.getBlocks()) {
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
	public void mousePressed(MouseEvent e) {
		// NOTHING TO DO HERE
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// NOTHING TO DO HERE
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// NOTHING TO DO HERE
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// NOTHING TO DO HERE
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// NOTHING TO DO HERE
	}
}

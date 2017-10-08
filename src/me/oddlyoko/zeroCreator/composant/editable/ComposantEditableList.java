package me.oddlyoko.zeroCreator.composant.editable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import me.oddlyoko.zeroCreator.gui.frame.ListGUI;

public class ComposantEditableList extends ComposantEditable {
	private List<Object> items = new ArrayList<>();
	private boolean lock = false;

	public ComposantEditableList(Object obj) {
		super(obj);
		setFontColor(Color.GRAY);
	}

	public void addItem(Object obj) {
		if (lock)
			return;
		items.add(obj);
	}

	public void removeItem(Object obj) {
		if (lock)
			return;
		items.remove(obj);
	}

	public List<Object> getItems() {
		return items;
	}

	public void lock() {
		lock = true;
	}

	public void unlock() {
		lock = false;
	}

	public boolean isLock() {
		return lock;
	}

	@Override
	public boolean isEditable() {
		return lock;
	}

	@Override
	public void onClick() {
		Object result = JOptionPane.showInputDialog(null, "Please Choose new value: ", "", JOptionPane.QUESTION_MESSAGE,
				null, items.toArray(), items.size() == 0 ? "" : items.get(0));
		if (result != null)
			setObject(result);
	}

	@Override
	public void onRightClick() {
		if (!lock) {
			ListGUI lgui = new ListGUI(this);
			lgui.setVisible(true);
		}
	}
}

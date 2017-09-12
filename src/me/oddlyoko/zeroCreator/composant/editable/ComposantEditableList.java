package me.oddlyoko.zeroCreator.composant.editable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ComposantEditableList extends ComposantEditable {
	private List<Object> items = new ArrayList<>();

	public ComposantEditableList(Object obj) {
		super(obj);
		setFontColor(Color.GRAY);
	}

	public void addItem(Object obj) {
		items.add(obj);
	}

	public void removeItem(Object obj) {
		items.remove(obj);
	}

	public List<Object> getItems() {
		return items;
	}

	@Override
	public void onClick() {
		Object result = JOptionPane.showInputDialog(null, "Please Choose new value: ", "", JOptionPane.QUESTION_MESSAGE,
				null, items.toArray(), items.get(0));
		if (result != null)
			setObject(result);
	}
}

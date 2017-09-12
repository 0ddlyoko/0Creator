package me.oddlyoko.zeroCreator.composant.editable;

import javax.swing.JOptionPane;

public class ComposantEditableInteger extends ComposantEditable {

	public ComposantEditableInteger(Integer value) {
		super(value);
	}

	@Override
	public void onClick() {
		String result = JOptionPane.showInputDialog(null, "Please Enter new value: ");
		if (result != null) {
			try {
				setObject(Integer.parseInt(result));
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Please enter a number value", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

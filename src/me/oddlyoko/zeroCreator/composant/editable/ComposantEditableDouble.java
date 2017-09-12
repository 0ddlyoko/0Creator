package me.oddlyoko.zeroCreator.composant.editable;

import javax.swing.JOptionPane;

public class ComposantEditableDouble extends ComposantEditable {

	public ComposantEditableDouble(Double value) {
		super(value);
	}

	@Override
	public void onClick() {
		String result = JOptionPane.showInputDialog(null, "Please Enter new value: ");
		if (result != null) {
			try {
				setObject(Double.parseDouble(result));
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Please enter a float value", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

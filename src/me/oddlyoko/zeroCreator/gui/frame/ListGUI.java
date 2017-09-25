package me.oddlyoko.zeroCreator.gui.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import me.oddlyoko.zeroCreator.composant.editable.ComposantEditableList;

public class ListGUI extends JDialog implements ListSelectionListener, ActionListener {
	private static final long serialVersionUID = 1L;

	private JList<String> list;
	private DefaultListModel<String> listModel;
	private ComposantEditableList cel;

	private JPanel contentPanel;
	private JPanel buttonPane;
	private JButton addButton;
	private JButton deleteButton;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public ListGUI(ComposantEditableList cel) {
		this.cel = cel;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());

		contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		// getContentPane().add(contentPanel, BorderLayout.CENTER);

		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		addButton = new JButton("ADD");
		addButton.setActionCommand("ADD");
		addButton.addActionListener(this);
		buttonPane.add(addButton);

		deleteButton = new JButton("Delete");
		deleteButton.setActionCommand("DELETE");
		deleteButton.addActionListener(this);
		deleteButton.setEnabled(false);
		buttonPane.add(deleteButton);

		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(this);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("CANCEL");
		cancelButton.addActionListener(this);
		buttonPane.add(cancelButton);

		listModel = new DefaultListModel<>();
		for (Object obj : cel.getItems())
			listModel.addElement(obj.toString());
		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(5);
		JScrollPane listScrollPane = new JScrollPane(list);
		getContentPane().add(listScrollPane, BorderLayout.CENTER);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (list.getSelectedIndex() == -1)
			deleteButton.setEnabled(false);
		else
			deleteButton.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd == null || "".equalsIgnoreCase(cmd))
			return;
		if ("ADD".equalsIgnoreCase(cmd)) {
			String result = JOptionPane.showInputDialog(null, "Please enter a value: ", "ADD",
					JOptionPane.INFORMATION_MESSAGE);
			if (result == null || "".equalsIgnoreCase(result))
				return;
			listModel.addElement(result);
		} else if ("DELETE".equalsIgnoreCase(cmd)) {
			int index = list.getSelectedIndex();
			listModel.remove(index);

			int size = listModel.getSize();
			if (size == 0)
				deleteButton.setEnabled(false);
			else {
				if (index == listModel.getSize())
					index--;
				list.setSelectedIndex(index);
				list.ensureIndexIsVisible(index);
			}
		} else if ("OK".equalsIgnoreCase(cmd)) {
			List<Object> objs = new ArrayList<>(cel.getItems());
			for (Object obj : objs)
				cel.removeItem(obj);
			for (Object obj : listModel.toArray())
				cel.addItem(obj);
			dispose();
		} else if ("CANCEL".equalsIgnoreCase(cmd)) {
			dispose();
		}
	}
}

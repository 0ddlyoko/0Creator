package me.oddlyoko.zeroCreator.gui.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import me.oddlyoko.zeroCreator.Project;

public class AboutGUI extends JDialog {
	private static final long serialVersionUID = 1L;
	private Project project = null;

	private JPanel contentPanel;
	private JPanel buttonPanel;
	private JButton okButton;
	private JLabel lblInfo;

	/**
	 * Create the dialog.
	 */
	public AboutGUI(Project project) {
		this.project = project;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());

		// PANNEL CENTER
		contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// PANNEL BUTTON
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// TEXT INFO
		lblInfo = new JLabel("Created By 0ddlyoko");

		// BUTTON OK
		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getRootPane().setDefaultButton(okButton);

		// ADD PANNELS
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		// ADD ITEMS INTO PANNEL BUTTON
		buttonPanel.add(lblInfo);
		buttonPanel.add(okButton);
	}

}

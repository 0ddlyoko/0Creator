package me.oddlyoko.zeroCreator.gui.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import me.oddlyoko.zeroCreator.Project;

public class InformationGUI extends JDialog {
	private static final long serialVersionUID = 1L;
	private Project project = null;

	private JPanel contentPane;
	private JLabel lblPluginName;
	private JLabel lblAuthor;
	private JLabel lblPluginVersion;
	private JTextField txtFldPluginName;
	private JTextField txtFldAuthor;
	private JTextField txtFldPluginVersion;
	private JButton btnCreate;

	/**
	 * Create the frame.
	 */
	public InformationGUI(Project project) {
		this.project = project;
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblPluginName = new JLabel("Plugin Name:");
		lblPluginName.setBounds(10, 48, 85, 14);
		contentPane.add(lblPluginName);

		txtFldPluginName = new JTextField();
		txtFldPluginName.setBounds(105, 45, 319, 20);
		contentPane.add(txtFldPluginName);
		txtFldPluginName.setColumns(10);

		lblAuthor = new JLabel("Author:");
		lblAuthor.setBounds(10, 90, 85, 14);
		contentPane.add(lblAuthor);

		txtFldAuthor = new JTextField();
		txtFldAuthor.setBounds(105, 87, 319, 20);
		contentPane.add(txtFldAuthor);
		txtFldAuthor.setColumns(10);

		lblPluginVersion = new JLabel("Plugin Version:");
		lblPluginVersion.setBounds(10, 132, 85, 14);
		contentPane.add(lblPluginVersion);

		txtFldPluginVersion = new JTextField();
		txtFldPluginVersion.setBounds(105, 129, 319, 20);
		contentPane.add(txtFldPluginVersion);
		txtFldPluginVersion.setColumns(10);

		btnCreate = new JButton("Finish");
		btnCreate.setBounds(169, 237, 105, 23);
		contentPane.add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtFldPluginName != null && txtFldAuthor != null && txtFldPluginVersion != null) {
					if (!"".equalsIgnoreCase(txtFldPluginName.getText()) && !"".equalsIgnoreCase(txtFldAuthor.getText())
							&& !"".equalsIgnoreCase(txtFldPluginVersion.getText())) {
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Please Enter correct values !");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please Enter correct values !");
				}
			}
		});
	}

	public String getPluginName() {
		return txtFldPluginName.getText();
	}

	public void setPluginName(String pluginName) {
		txtFldPluginName.setText(pluginName);
	}

	public String getAuthor() {
		return txtFldAuthor.getText();
	}

	public void setAuthor(String author) {
		txtFldAuthor.setText(author);
	}

	public String getPluginVersion() {
		return txtFldPluginVersion.getText();
	}

	public void setPluginVersion(String pluginVersion) {
		txtFldPluginVersion.setText(pluginVersion);
	}
}

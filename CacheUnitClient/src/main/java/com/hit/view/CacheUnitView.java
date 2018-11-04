package com.hit.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileReader;

import javax.swing.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CacheUnitView implements ActionListener {
	private PropertyChangeSupport change;
	private JLabel textLabel;
	private JFrame frame;
	public String command = null;
	final JFileChooser fc;
	public String Request = null;
	public JsonObject jsonObject = null;

	public CacheUnitView() {
		change = new PropertyChangeSupport(this);

		fc = new JFileChooser();
		File dir = new File("src//main//resources‬");
		fc.setCurrentDirectory(dir);
		
		frame = new JFrame("CacheUnit UI");
		frame.setBounds(100, 100, 526, 399);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton statistics_Btn = new JButton("");
		statistics_Btn.setActionCommand("showStatistics");
		statistics_Btn.addActionListener(this);	
		statistics_Btn.setBounds(270, 5, 203, 51);
		statistics_Btn.setIcon(new ImageIcon("src\\main\\resources\\StatisticBtn.png"));
		frame.getContentPane().add(statistics_Btn);
		
		JButton loadReq_Btn = new JButton("");
		loadReq_Btn.setIcon(new ImageIcon("src\\main\\resources\\LoadBtn.png"));
		loadReq_Btn.setActionCommand("loadRequest");
		loadReq_Btn.addActionListener(this);
		loadReq_Btn.setBounds(22, 5, 194, 51);
		frame.getContentPane().add(loadReq_Btn);
		
		textLabel = new JLabel ("Waiting For Input");
		textLabel.setBounds(22, 69, 451, 246);
		textLabel.setFont(new Font("TimesRoman" ,Font.BOLD, 16));
		frame.getContentPane().add(textLabel);
		
	}

	public void start() {
		frame.setVisible (true);
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		change.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		change.removePropertyChangeListener(pcl);
	}

	public <T> void updateUIData(T t) {
		textLabel.setText(t.toString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getActionCommand().equals("loadRequest")) {
			int returnVal = fc.showOpenDialog(frame);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				System.out.println("Opening: " + file.getName() + ".");
				try {
					JsonParser parser = new JsonParser();
					Object obj = parser.parse(new FileReader(file.getPath()));
					jsonObject = (JsonObject) obj;
				} catch (Exception er) {
					er.printStackTrace();
				}
			}
			
			else {
				System.out.println("Open command cancellde by user.");
			}

			change.firePropertyChange("command", "afa", jsonObject.toString());
		}

		if(e.getActionCommand().equals("showStatistics")) {
			change.firePropertyChange("command", "afa", "showStatistics");
		}

	}

}

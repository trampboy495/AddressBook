package com.bridgelabz.GUI;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
public class MultiInputPanel {
	private boolean Status=false;
	JPanel panel=new JPanel();
	public JTextField t1,t2,t3,t4,t5,t6,t7;
	private String n,ln,ad,cn,st,pin,ph; 
	public MultiInputPanel(String name,String lastname,String address,String city
			,String state,String zip,String phone) {
		this.n=name;
		this.ln=lastname;
		this.ad=address;
		this.cn=city;
		this.st=state;
		this.pin=zip;
		this.ph=phone;
	}
	public boolean showInputPanel(String command,String title) {
		panel.setLayout(new GridLayout(7,2,2,2));
		panel.setPreferredSize(new Dimension(400,200 ));
		if(command.equals("Add")) {	
			JLabel name=new JLabel("First Name:");
			t1=new JTextField(n);
			name.setLabelFor(t1);
			panel.add(name);
			panel.add(t1);
		}
		JLabel lastname=new JLabel("Last Name:");
		t2=new JTextField(ln);
		lastname.setLabelFor(t2);
		panel.add(lastname);
		panel.add(t2);
		JLabel address=new JLabel("Address: ");
		t3=new JTextField(ad);
		address.setLabelFor(t3);
		panel.add(address);
		panel.add(t3);
		JLabel city=new JLabel("City: ");
		t4=new JTextField(cn);
		city.setLabelFor(t4);
		panel.add(city);
		panel.add(t4);
		JLabel state=new JLabel("State: ");
		t5=new JTextField(st);
		state.setLabelFor(t5);
		panel.add(state);
		panel.add(t5);
		JLabel zip=new JLabel("Zip: ");
		t6=new JTextField(pin);
		zip.setLabelFor(t6);
		panel.add(zip);
		panel.add(t6);
		JLabel phone=new JLabel("Phone no: ");
		t7=new JTextField(ph);
		phone.setLabelFor(t7);
		panel.add(phone);
		panel.add(t7); 
		int i=JOptionPane.showConfirmDialog(null, panel, title, 
				JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
		if(i==JOptionPane.CANCEL_OPTION) {
			return Status;
		}
		else {
			return !Status;
		}
		
		}
		
}	


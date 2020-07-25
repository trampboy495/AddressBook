package com.bridgelabz.GUI;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import com.bridgelabz.controller.AddressController;
import com.bridgelabz.service.AddressBook;
import javax.swing.JOptionPane;
public  class  AddressBookGUI implements ActionListener, WindowListener{
	public JFrame frame;
	private JScrollPane scroll;
	private JList<String> list;
	private DefaultListModel<String> model;
	private JPanel listpanel, buttonpanel;
	private JButton add=new JButton("Add");
	private JButton edit=new JButton("Edit");
	private JButton del=new JButton("Delete");
	private JButton sz=new JButton("Sort by zip");
	private JButton sn=new JButton("Sort by name");
	private JMenuBar mb=new JMenuBar();
	private JMenu file=new JMenu("File");
	private JMenuItem New=new JMenuItem("New");
	private JMenuItem open=new JMenuItem("Open");
	private JMenuItem save=new JMenuItem("Save");
	private JMenuItem saveas=new JMenuItem("Save As..");
	private AddressController controller;
	private AddressBook address;
	public AddressBookGUI() {
		this.address=new AddressBook();
		this.controller=new AddressController();
		mb.add(file);
		file.add(New);
		file.add(open);
		file.add(save);
		file.add(saveas);
		frame=new JFrame("AddressBook Application");
		frame.setLayout(new BorderLayout());
		frame.setJMenuBar(mb);
		model=new DefaultListModel<String>();
		list=new JList<String>(model);
		list.setVisibleRowCount(-1);
		scroll=new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(350, 350));
		scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
		listpanel=new JPanel();
		listpanel.setLayout(new BoxLayout(listpanel, BoxLayout.PAGE_AXIS));
		listpanel.add(scroll);
		listpanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		buttonpanel=new JPanel();
		buttonpanel.setLayout(new BoxLayout(buttonpanel,BoxLayout.LINE_AXIS));
		buttonpanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonpanel.add(Box.createHorizontalGlue());
		buttonpanel.add(add);
		buttonpanel.add(Box.createRigidArea(new Dimension(10,0)));
		buttonpanel.add(edit);
		buttonpanel.add(Box.createRigidArea(new Dimension(10,0)));
		buttonpanel.add(del);
		buttonpanel.add(Box.createRigidArea(new Dimension(10,0)));
		buttonpanel.add(sn);
		buttonpanel.add(Box.createRigidArea(new Dimension(10,0)));
		buttonpanel.add(sz);
		buttonpanel.add(Box.createHorizontalGlue());
		frame.add(listpanel, BorderLayout.CENTER);
		frame.add(buttonpanel,BorderLayout.PAGE_END);
		frame.setVisible(true);
		frame.setSize(600, 500);
		frame.setTitle("untitled-AddressBook");
		frame.addWindowListener(this);
		save.setEnabled(false);
		New.addActionListener(this);
		saveas.addActionListener(this);
		open.addActionListener(this);
		add.addActionListener(this);
		edit.addActionListener(this);
		del.addActionListener(this);
		sn.addActionListener(this);
		sz.addActionListener(this);
	}
	public void windowClosing(WindowEvent e) {
		if(address.getChangedSinceLastSave()==true) {
			try {
				controller.doOfferSaving();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
			System.exit(0);
	}
	public void updateList() {
		model.clear();
		for(Object data :address.ja) {
			model.addElement((String)data);
		}
	}
	public void setSaveStatus() {
		save.setEnabled(true);
	}
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals("New")) {
			frame.setTitle("untitled-AddressBook");
		}
		else if(cmd.equals("Add")) {
			address=controller.doAdd();
			if(address.getChangedSinceLastSave()==true) {	
				int index=address.li.getSize()-1;
				model.addElement(address.getFullNameOfPerson(index));
				setSaveStatus();
			}	
		}
		else if(cmd.contentEquals("Edit")) {
			int index=list.getSelectedIndex();
			if(index!=-1) {	
				controller.doEdit(index);
				if(address.getChangedSinceLastSave()==true) {
					String fullname=address.getFullNameOfPerson(index);
					if(fullname!=list.getSelectedValue()) {
						model.set(index, fullname);
					}
					setSaveStatus();
				}
			}
			else {
				JOptionPane.showMessageDialog(frame, "First select the entry to edit", 
						"No Entry Selected", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(cmd.equals("Delete")) {
			int i=list.getSelectedIndex();
			if(i!=-1) {
				 i=JOptionPane.showConfirmDialog(frame, "Selected entry will be permanently deleted"
						, "Confirm Delete", JOptionPane.OK_CANCEL_OPTION);
				if(i==JOptionPane.OK_OPTION) {
					controller.doDelete(i);
					model.remove(i);
					setSaveStatus();
				}
			}
			else {
				JOptionPane.showMessageDialog(frame, "First select the entry to delete", 
						"No Entry Selected", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(cmd.equals("Sort by name")) {
			controller.SortByName();
			updateList();
			setSaveStatus();
		}
		else if(cmd.equals("Sort by zip")) {
			controller.SortByZip();
			updateList();
			setSaveStatus();
		}
		else if(cmd.equals("Save As..")) {
				try {
					controller.doSaveAs();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if(address.getChangedSinceLastSave()==false) {
					frame.setTitle(address.getFile().getName());
				}
		}
		else if(cmd.equals("Open")) {
			if(address.getChangedSinceLastSave()==true) {
				try {
					controller.doOfferSaving();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			try {
				address=controller.doOpen();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(address.getOpenStatus()==true) {	
				model.clear();
				for(int i=0;i<address.li.getSize();i++) {
					model.add(i, address.getFullNameOfPerson(i));
				}
				frame.setTitle(address.getFile().getName());
			}
		}
		else if(cmd.equals("save")) {
			if(frame.getTitle().equals("untittled")) {
				try {
					controller.doSaveAs();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			else {
				try {
					controller.doSave();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
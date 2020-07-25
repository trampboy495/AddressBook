package com.bridgelabz.controller;

import java.io.File;
import java.io.IOException;

import com.bridgelabz.GUI.MultiInputPanel;
import com.bridgelabz.repo.FileSystem;
import com.bridgelabz.service.AddressBook;
import javax.swing.JOptionPane;
public class AddressController {

	private AddressBook address;
	private MultiInputPanel pn;
	private FileChooser fc=new FileChooser();
	private FileSystem fs=new FileSystem();
	private File f; 
	public AddressController(){
		this.address=new AddressBook();
	}
	public AddressBook doAdd(){
		pn=new MultiInputPanel(null,null,null,null,null,null,null);
		if(pn.showInputPanel("Add","Person")==true) {
			address.addPerson(pn.t1.getText(), pn.t2.getText(), pn.t3.getText(), 
					pn.t4.getText(), pn.t5.getText(), pn.t6.getText(), pn.t7.getText());
			return address;
		}
		else {
			return address;
		}
	}
	public void doEdit(int index) {
		pn=new MultiInputPanel(null,address.li.getValue(index).getLastName(), 
				address.li.getValue(index).getAddress(),address.li.getValue(index).getCity()
				,address.li.getValue(index).getState(),address.li.getValue(index).getZip()
				,address.li.getValue(index).getPhoneNo());
		if(pn.showInputPanel("Edit","Edit "+address.li.getValue(index).getFirstName())
				==true) {
			address.updatePerson(index, pn.t2.getText(), pn.t3.getText(), 
					pn.t4.getText(), pn.t5.getText(), pn.t6.getText(), pn.t7.getText());
		}
	}
	public void doDelete(int index) {
		address.removePerson(index);
	}
	public void SortByName() {
		address.sortByName();
	}
	public void SortByZip() {
		address.sortByZip();
	}
	public void doSaveAs() throws IOException {
		String filepath=fc.showFileChooser("Save As");
		if(filepath!=null) {
			fs.saveFile(filepath,address);
		}
	}
	public void doSave() throws IOException {
		f=address.getFile();
		fs.saveFile(f.getAbsolutePath(), address);
	}
	public void doOfferSaving() throws IOException  {
		int i=JOptionPane.showConfirmDialog(null, "Do you want to save changes made in "
		+address.getFile().getName()+" first","Confirm Save",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(i==JOptionPane.OK_OPTION) {
		
			doSave();
		}
	}
	public AddressBook doOpen() throws IOException {
		String filepath=fc.showFileChooser("Open");
		if(filepath!=null) {
			address=fs.readFile(filepath);
		}
		return address;
	}
}
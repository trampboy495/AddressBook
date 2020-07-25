package com.bridgelabz.service;
import org.json.simple.JSONArray;
import java.io.File;
public class AddressBook {
	int size;
	private Person p;
	public LinkedList li=new LinkedList();
	private boolean ChangedSinceLastSave=false;
	private boolean openStatus=false;
	private File file=new File("untitled-AddressBook");
	public JSONArray ja;
	public boolean getChangedSinceLastSave() {
		return ChangedSinceLastSave;
	}
	public void setChangedSinceLastSave(boolean ChangedSinceLastSave) {
		this.ChangedSinceLastSave=ChangedSinceLastSave;
	}
	public void setOpenStatus(boolean status) {
		this.openStatus=status;
	}
	public boolean getOpenStatus() {
		return openStatus;
	}
	public  void addPerson (String firstName, String lastName, String address, String city,
			String state, String zip, String phone) {
		p=new Person(firstName, lastName, address, city, state, zip, phone);
		li.insert(p);
		setChangedSinceLastSave(true);
	}
	public  void updatePerson(int index, String lastName, String address, String city,
			String state, String zip, String phone) {
		p=new Person(li.getValue(index).getFirstName(), lastName, address, city, state, 
				zip, phone);
		li.replace(index, p);
		setChangedSinceLastSave(true);
	}
	public  String getFullNameOfPerson(int index) {
		String FullName="";
		FullName+=li.getValue(index).getFirstName()+" ";
		FullName+=li.getValue(index).getLastName();
		return FullName; 
	}
	public  void removePerson(int index) {
		li.delete(index);
		setChangedSinceLastSave(true);
	}
	public  void sortByZip() {
		li.sortZip();
		getAllInArray();
		setChangedSinceLastSave(true);
	}
	public  void sortByName() {
		li.sortName();
		getAllInArray();
		setChangedSinceLastSave(true);
	}
	public void getAllInArray() {
		ja=new JSONArray();
		for(int i=0;i<li.getSize();i++) {
			ja.add(i,getFullNameOfPerson(i) );
		}
	}
	public void getAllObjectInArray() {
		ja=new JSONArray();
		for(int i=0;i<li.getSize();i++) {
			ja.add(i,li.getValue(i) );
		}
	}
	public void setFile(File file) {
		this.file=file;
	}
	public  File getFile() {
		return file;
	}
}
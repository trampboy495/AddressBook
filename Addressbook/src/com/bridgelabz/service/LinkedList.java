package com.bridgelabz.service;

import org.json.simple.JSONArray;

public class LinkedList  {
	Node head;
	Node tail;
	int size=0;
	public void insert(Person p) {
		Node n=new Node();
		n.data=p;
		n.next=null;
		if(head==null) {
			head=n;
			tail=n;
		}
		else {
			tail.next=n;
			tail=n;
		}
		size++;
	}
	public Person getValue(int index) {
		Node pointer=head;
		Node curr=new Node();
		for(int i=0; i<=index; i++) {
			curr.data=pointer.data;
			pointer=pointer.next;
		}
		return curr.data;
	}
	public void replace(int index, Person p) {
		Node n=new Node();
		n.data=p;
		if(index==0) {
			n.next=head.next;
			head=n;
			return;
		}
		Node curr=new Node(); Node prev=new Node();
		Node pointer=head;
		for(int i=0;i<index;i++) {
			prev=pointer;
			curr=pointer.next;
			pointer=pointer.next;
		}
		n.next=curr.next;
		prev.next=n;
		curr=null;
	}
	public int getSize() {
		return size;
	}
	public void delete(int index) {
		if(index==0) {
			head=head.next;
		}
		else if(index==getSize()-1) {
			Node n=head;
			while(n!=tail) {
				n=n.next;
			}
			n.next=null;
			tail=n;
		}
		else {
			Node n=null;
			Node prev=new Node();
			prev.data=getValue(index-1);
			n=prev.next;
			prev.next=n.next;
			n=null;
		}
		size--;
	}
	public void sortZip () {
		JSONArray ja=new JSONArray();
		for(int i=0;i<getSize();i++) {
			ja.add(i, getValue(i));
		}
		for(int i=1;i<getSize();i++) {
			Person temp=(Person)ja.get(i);
			int j=i-1;
			Person temp1=(Person)ja.get(j);
			while(j>=0 && temp.getZip().compareTo(temp1.getZip())<0) {
				ja.set(j+1, ja.get(j));
				temp1=(Person)ja.get(j);
				j=j-1;
			}
			ja.set(j+1, temp);
		}
		int i=0;
		Node n=head;
		while(n!=null) {
			n.data=(Person)ja.get(i);
			n=n.next;
			i++;
		}
	}
	public void sortName() {
		JSONArray ja=new JSONArray();
		for(int i=0;i<getSize();i++) {
			ja.add(i, getValue(i));
		}
		for(int i=1;i<getSize();i++) {
			Person temp=(Person)ja.get(i);
			int j=i-1;
			Person temp1=(Person)ja.get(j);
			while(j>=0 && temp.getLastName().compareTo(temp1.getLastName())<0) {
				ja.set(j+1, ja.get(j));
				temp1=(Person)ja.get(j);
				j=j-1;
			}
			ja.set(j+1, temp);
		}
		int i=0;
		Node n=head;
		while(n!=null) {
			n.data=(Person)ja.get(i);
			n=n.next;
			i++;
		}
	}

}

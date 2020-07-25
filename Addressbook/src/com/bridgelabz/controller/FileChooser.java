package com.bridgelabz.controller;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;


public class FileChooser   {
	public static JFileChooser fc;

	
	public String showFileChooser(String operation) {
			if(fc==null) {
				fc=new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fc.setFileFilter(new FileFilter() {
				public String getDescription() {
					return "Just json files";
				}
			
				public boolean accept(File f) {
			
					if(f.isDirectory()) {
						return true;
					}
					String ext=getFileExtension(f);
					if(ext!=null) {
						if(ext.equals("json")) {
							return true;
						}
						else {return false;}
					}
					return false;
				}
				});
			}	
			if(operation.equals("Save As")) {	
				int returnValue=fc.showDialog(null, "Save As");
				if(returnValue==fc.APPROVE_OPTION) {
					return fc.getSelectedFile().getAbsolutePath();
				}
			}
			else {
				int returnValue=fc.showOpenDialog(null);
				if(returnValue==fc.APPROVE_OPTION) {
					return fc.getSelectedFile().getAbsolutePath();
				}
			}
			fc.setSelectedFile(null);
			return null;
	}
		
	
	public String getFileExtension(File f) {
		String ext=null;
		String s=f.getName();
		int i=s.lastIndexOf(".");
		if(i>0 && i<s.length()-1) {
			ext=s.substring(i+1).toLowerCase();
		}
		return ext;
	}
	
}
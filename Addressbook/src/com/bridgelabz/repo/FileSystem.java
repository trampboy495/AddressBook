package com.bridgelabz.repo;

import com.bridgelabz.service.AddressBook;
import com.bridgelabz.service.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class FileSystem {
	private File file;
	Gson gson=new GsonBuilder().setPrettyPrinting().create();
	public void saveFile(String filepath,AddressBook address ) throws IOException {
		file=new File(filepath);
		if(file.isFile()==false) {
			file.createNewFile();
		}
		FileWriter fw=new FileWriter(file);
		address.getAllObjectInArray();
		fw.write(gson.toJson(address.ja));
		fw.close();
		address.setFile(file);
		address.setChangedSinceLastSave(false);
	}
	public AddressBook readFile(String filepath) throws IOException{
		file=new File(filepath);
		FileReader fr=new FileReader(file);
		JsonArray  ja=(JsonArray)new JsonParser().parse(fr);
		AddressBook address=new AddressBook();
		for(JsonElement element:ja) {
			address.li.insert(gson.fromJson(element, Person.class));
		}
		address.setFile(file);
		address.setOpenStatus(true);
		return address;
	}
}

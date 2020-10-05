package model;

import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.*;

class FileManagement {
	Gson gson = new Gson();
	
	public void saveCourse(Course course,String filename) throws IOException {
		String json = gson.toJson(course);
		Files.write(Paths.get(filename),json.getBytes());
	}
	
	public Course loadCourse(String filename) {
		return null;
	}
}
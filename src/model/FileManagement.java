package model;

import java.io.*;
import java.nio.file.*;
import com.google.gson.*;

class FileManagement {
	Gson gson = new Gson();
	
	public void saveCourse(Course course,String filename) throws IOException {
		String json = gson.toJson(course);
		Files.write(Paths.get(filename),json.getBytes());
	}
	
	public Course loadCourse(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		Course course = gson.fromJson(reader.readLine(),Course.class);
		reader.close();
		return course;
	}
}
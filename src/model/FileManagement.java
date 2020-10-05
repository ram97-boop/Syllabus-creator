package model;

import java.io.*;
import java.nio.file.*;
import com.google.gson.*;

// Responsible for saving courses and loading courses from files.

class FileManagement {
	Gson gson = new Gson();
	
	/**
	 * Saves a course to a file.
	 * @param course - Course to save to a file.
	 * @param filename - Name of the target file.
	 * @throws IOException
	 */
	public void saveCourse(Course course,String filename) throws IOException {
		String json = gson.toJson(course);
		Files.write(Paths.get(filename),json.getBytes());
	}
	
	/**
	 * Reads in a course from a file.
	 * @param filename - Name of the target file.
	 * @return A course object.
	 * @throws IOException
	 */
	public Course loadCourse(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		Course course = gson.fromJson(reader.readLine(),Course.class);
		reader.close();
		return course;
	}
}
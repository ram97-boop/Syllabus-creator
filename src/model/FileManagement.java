package model;

import java.io.*;
import java.nio.file.*;
import com.google.gson.*;

// Responsible for saving courses and loading courses from files.

public class FileManagement {
	Gson gson = new Gson();
	
	/**
	 * Saves a course to a file.
	 * @param course - Course to save to a file.
	 * @param filename - Name/path of the target file.
	 * @throws IOException
	 */
	public void saveCourse(Course course, String filename) throws IOException {
		String json = gson.toJson(course);
		Files.write(Paths.get(filename),json.getBytes());
	}
	
	/**
	 * Reads in a course from a file.
	 * @param filename - Name/path of the target file.
	 * @return A course object.
	 * @throws IOException
	 * @throws JsonSyntaxException
	 */
	public Course loadCourse(String filename) throws IOException, JsonSyntaxException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		Course course = gson.fromJson(reader.readLine(),Course.class);
		reader.close();
		return course;
	}
}
package br.ufrn.imd.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Program {

	static String usersFile, playlistsFolder, tracksFile;
	static Scanner sc = new Scanner(System.in);
	static String projectPath = System.getProperty("user.dir");

	public static void main(String[] args) {
		try {

			BufferedReader reader = new BufferedReader(new FileReader(projectPath + "\\diretorios.txt"));
			BufferedWriter writer = null;
			String line = null;
			File folder = null;

			for (int i = 0; i < 3; i++) {
				line = reader.readLine();
				if (line == null || line.isBlank()) {
					generateFilesDir();
					
					break;
				}			
			}

			
			reader.close();
			
			/*
			 * UserController userController = new UserController(usersFile);
			 * userController.updateUsersList(); TrackController trackController = new
			 * TrackController(tracksFile); trackController.updateTracksList();
			 * 
			 * PlayerModel player = new PlayerModel(new QueueModel()); PlayerController
			 * playerController = new PlayerController(player);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}

	// generates files directory inside project main directory
	private static void generateFilesDir() throws IOException {
		BufferedWriter writer = null;

		File folder = new File(projectPath + File.separator + "files");
		if (!folder.exists()) {
			folder.mkdir();
		}

		String filesFolderPath = folder.getAbsolutePath();

		folder = new File(filesFolderPath + File.separator + "playlists");
		if (!folder.exists()) {
			folder.mkdir();
		}
		playlistsFolder = folder.getAbsolutePath();

		folder = new File(filesFolderPath + File.separator + "users.txt");
		folder.createNewFile();
		usersFile = folder.getAbsolutePath();

		folder = new File(filesFolderPath + File.separator + "tracks.txt");
		folder.createNewFile();

		writer = new BufferedWriter(new FileWriter(projectPath + "\\diretorios.txt"));
		tracksFile = folder.getAbsolutePath();
		writer.write(usersFile);
		writer.newLine();
		writer.write(playlistsFolder);
		writer.newLine();
		writer.write(tracksFile);
		writer.close();

	}

}

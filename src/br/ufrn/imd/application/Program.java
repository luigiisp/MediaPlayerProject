package br.ufrn.imd.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import br.ufrn.imd.controller.MediaPlayerController;
import br.ufrn.imd.controller.PlaylistController;
import br.ufrn.imd.controller.TrackController;
import br.ufrn.imd.controller.UserController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Program extends Application {

	static String PROJECTPATH = System.getProperty("user.dir");
	static String DIRETORIOSTXTPATH = PROJECTPATH + "\\diretorios.txt";
	static String usersFile, playlistsFolder, tracksFile;
	static Scanner sc = new Scanner(System.in);

	@Override
	public void start(Stage primaryStage) {

		Pane root;
		try {
			String fxmlFile = "/br/ufrn/imd/view/LoginScreen.fxml";

			root = FXMLLoader.load(getClass().getResource(fxmlFile));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("MP3 PLAYER");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {

			BufferedReader reader = null;
			String line = null;

			// if diretorios.txt has an empty line, generate files and overwrite
			File file = new File(DIRETORIOSTXTPATH);
			if (!file.exists()) {
				file.createNewFile();
			}

			reader = new BufferedReader(new FileReader(DIRETORIOSTXTPATH));
			line = reader.readLine();
			
			if (line == null || line.isBlank()) {
				generateFilesDir();
			}else {
				File check = new File(line);
				if (!check.exists()) {
					file.delete();
					file.createNewFile();
					generateFilesDir();
				}
			}
			
			readDirFile();
			UserController userController = new UserController(usersFile);
			userController.updateUsersList();
			TrackController trackController = new TrackController(tracksFile);
			trackController.updateTracksList();
			PlaylistController playlistController = new PlaylistController(playlistsFolder, userController,
					trackController);
			playlistController.updatePlaylistsList();

			MediaPlayerController.setTrackController(trackController);
			MediaPlayerController.setPlaylistController(playlistController);
			MediaPlayerController.setUserController(userController);

			// Execution

			launch(args); // start javafx

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}

	private static void readDirFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(DIRETORIOSTXTPATH));
		usersFile = reader.readLine();
		playlistsFolder = reader.readLine();
		tracksFile = reader.readLine();
		reader.close();
	}

	private static void updateDirFile() throws IOException {
		BufferedWriter writer = null;

		writer = new BufferedWriter(new FileWriter(DIRETORIOSTXTPATH));
		writer.write(usersFile);
		writer.newLine();
		writer.write(playlistsFolder);
		writer.newLine();
		writer.write(tracksFile);
		writer.close();
	}

	// generates files directory inside project main directory
	private static void generateFilesDir() throws IOException {
		File folder = new File(PROJECTPATH + File.separator + "files");
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
		tracksFile = folder.getAbsolutePath();

		updateDirFile();
	}
}

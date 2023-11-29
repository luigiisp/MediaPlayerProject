package br.ufrn.imd.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import br.ufrn.imd.controller.PlayerController;
import br.ufrn.imd.controller.TrackController;
import br.ufrn.imd.controller.UserController;
import br.ufrn.imd.model.PlayerModel;
import br.ufrn.imd.model.QueueModel;

public class Program {

	public static void main(String[] args) throws IOException {
		String usersFolder, playlistsFolder, tracksFolder;
		Scanner sc = new Scanner(System.in);
		BufferedReader reader = null;
		BufferedWriter writer = null;
		String projectPath = System.getProperty("user.dir");
		try {
			reader = new BufferedReader(new FileReader(projectPath + "\\diretorio.txt"));
			String line = reader.readLine();
			if (line == null) {
				File folder = new File(projectPath + File.separator + "files");
				folder.mkdir();

				String pathTemp = folder.getAbsolutePath();

				folder = new File(pathTemp + File.separator + "users");
				folder.mkdir();
				usersFolder = folder.getAbsolutePath();

				folder = new File(pathTemp + File.separator + "playlists");
				folder.mkdir();
				playlistsFolder = folder.getAbsolutePath();

				folder = new File(pathTemp + File.separator + "tracks");
				folder.mkdir();

				reader.close();

				writer = new BufferedWriter(new FileWriter(projectPath + "\\diretorio.txt"));
				tracksFolder = folder.getAbsolutePath();
				writer.write(usersFolder);
				writer.newLine();
				writer.write(playlistsFolder);
				writer.newLine();
				writer.write(tracksFolder);
				writer.close();
			} else {
				usersFolder = line;
				playlistsFolder = reader.readLine();
				tracksFolder = reader.readLine();
			}
			reader.close();
			UserController userController = new UserController(usersFolder + "usuarios");
			userController.updateUsersList();
			TrackController trackController = new TrackController(tracksFolder + "musicas");
			trackController.updateTracksList();

			PlayerModel player = new PlayerModel(new QueueModel());
			PlayerController playerController = new PlayerController(player);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}

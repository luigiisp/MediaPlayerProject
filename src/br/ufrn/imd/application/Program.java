package br.ufrn.imd.application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import br.ufrn.imd.controller.TrackController;

public class Program {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		String pathProject = System.getProperty("user.dir");
		try {
			reader = new BufferedReader(new FileReader(pathProject + "diretorio.txt"));

			reader.close();
		} catch (Exception e) {
		}
		TrackController trackController = new TrackController(pathProject + "//musicas");

		System.out.println(trackController.getPath());
	}
}

package br.ufrn.imd.application;

import br.ufrn.imd.controller.TrackController;
import javazoom.jl.player.Player;

public class Program {

	public static void main(String[] args) {
		String pathProject = System.getProperty("user.dir");

		TrackController trackController = new TrackController(pathProject + "//musicas");

		System.out.println(trackController.getPath());
	}
}

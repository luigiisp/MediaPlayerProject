package br.ufrn.imd.controller.gui;

import br.ufrn.imd.controller.MediaPlayerController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class PlaylistCreatorScreenController {
	@FXML
	private Label createStatusLabel;

	@FXML
	private TextField titleTextField;

	public void initialize() {
		titleTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					String title = titleTextField.getText();
					if (MediaPlayerController.createPlaylist(title) == 1) {
						createStatusLabel.setText("User already have a playlist with this title");
						return;
					} else if (MediaPlayerController.createPlaylist(title) == 0) {
						MediaPlayerController.createPlaylist(title);
						Stage stage = (Stage) ((Node) ke.getSource()).getScene().getWindow();
						stage.close();
					}
				}
			}
		});
	}
}
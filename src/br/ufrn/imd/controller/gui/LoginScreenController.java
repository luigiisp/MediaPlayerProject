package br.ufrn.imd.controller.gui;

import java.io.IOException;

import br.ufrn.imd.controller.MediaPlayerController;
import br.ufrn.imd.model.UserVipModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginScreenController {

	@FXML
	private Button loginButton;

	@FXML
	private Label loginReturnLabel;

	@FXML
	private TextField usernameTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	void onLoginButtonPressed(ActionEvent event) {
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();

		switch (MediaPlayerController.login(username, password)) {
		case 0:
			loginReturnLabel.setStyle("-fx-text-fill: green");
			loginReturnLabel.setText("Success. You are logged in.");
			try {
				Parent root;
				if(MediaPlayerController.getUserController().findUserByUsername(username) instanceof UserVipModel) {
					root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/view/MainScreen.fxml"));
				}else {
					root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/view/MainScreen2.fxml"));
				}
				
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 1:
			loginReturnLabel.setStyle("-fx-text-fill: black");
			loginReturnLabel.setText("You are already logged in. Logoff to change user");
			break;
		case 2:
			loginReturnLabel.setStyle("-fx-text-fill: red");
			loginReturnLabel.setText("No user registered with this username");
			break;
		case 3:
			loginReturnLabel.setStyle("-fx-text-fill: red");
			loginReturnLabel.setText("Wrong password for this username");
			break;
		}
	}

	@FXML
	void onRegisterButtonPressed(ActionEvent event) throws IOException {
		String registerScreenFxmlPath = "/br/ufrn/imd/view/RegisterScreen.fxml";
		Parent root = FXMLLoader.load(getClass().getResource(registerScreenFxmlPath));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

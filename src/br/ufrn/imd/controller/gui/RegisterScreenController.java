package br.ufrn.imd.controller.gui;

import java.io.IOException;

import br.ufrn.imd.controller.MediaPlayerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterScreenController {
	@FXML
	private Button registerButton;

	@FXML
	private Label registerReturnLabel;

	@FXML
	private TextField fullNameTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	private TextField usernameTextField;

	@FXML
	private CheckBox checkBoxVip;

	@FXML
	private Button returnLoginButton;

	@FXML
	void onRegisterButtonPressed(ActionEvent event) {
		String fullName = fullNameTextField.getText();
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		boolean vipUser = checkBoxVip.isSelected();

		if (fullName == "") {
			registerReturnLabel.setStyle("-fx-text-fill: red");
			registerReturnLabel.setText("Full name is needed");
		}
		if (username == "") {
			registerReturnLabel.setStyle("-fx-text-fill: red");
			registerReturnLabel.setText("Username is needed");
		}
		if (password == "") {
			registerReturnLabel.setStyle("-fx-text-fill: red");
			registerReturnLabel.setText("Password is needed");
		}
		switch (MediaPlayerController.register(fullName, username, password, vipUser)) {
		case 0:
			registerReturnLabel.setStyle("-fx-text-fill: green");
			registerReturnLabel.setText("Sucess! You have been registered and you already logged in");
			break;
		case 1:
			registerReturnLabel.setStyle("-fx-text-fill: red");
			registerReturnLabel.setText("Username already registered");
			break;
		 
		}
	}

	@FXML
	void onReturnLoginButtonButtonPressed(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/view/LoginScreen.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}

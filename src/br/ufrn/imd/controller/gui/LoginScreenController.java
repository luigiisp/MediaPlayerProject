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

	    	switch(MediaPlayerController.login(username, password)) {
	    	case 0:
	    		loginReturnLabel.setStyle("-fx-text-fill: green");
	    		loginReturnLabel.setText("Success. You are logged in.");
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
    	Parent root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/view/RegisterScreen.fxml"));
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    }
}

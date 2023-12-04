package br.ufrn.imd.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginScreenController {
    
	@FXML
    private Button loginButton;

    @FXML
    private Label loginReturnLabel;
	
    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    void loginButtonPressed(ActionEvent event) {
    	String username = usernameTextField.getText();
    	String password = passwordTextField.getText();

    	switch(MediaPlayerController.login(username, password)) {
    	case 0:
    		loginReturnLabel.setText("Success. You are logged in.");
    		break;
    	case 1:
    		loginReturnLabel.setText("You are already logged in. Logoff to change user");
    		break;
    	case 2:
    		loginReturnLabel.setText("No user registered with this username");
    		break;
    	case 3:
    		loginReturnLabel.setText("Wrong password for this username");
    		break;
    	}
    	
    }
}

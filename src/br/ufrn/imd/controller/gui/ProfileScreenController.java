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
import javafx.stage.Stage;

public class ProfileScreenController {

    @FXML
    private Label accountStatusLabel;

    @FXML
    private Label fullNameLabel;

    @FXML
    private Label usernameLabel;
    
    @FXML
    private Button logoutButton;
    
    public void initialize() {
    	fullNameLabel.setText(MediaPlayerController.getLoggedUser().getFullName());
    	usernameLabel.setText(MediaPlayerController.getLoggedUser().getUsername());
    	if(MediaPlayerController.isUserVip()) {
    		accountStatusLabel.setText("VIP");
    	} else {
    		accountStatusLabel.setText("COMMON");
    	}
    }
    
    @FXML
    void onLogoutButtonPressed(ActionEvent event) {
    	MediaPlayerController.logout();
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/br/ufrn/imd/view/LoginScreen.fxml"));
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

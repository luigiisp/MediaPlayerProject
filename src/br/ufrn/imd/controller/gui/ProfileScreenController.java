package br.ufrn.imd.controller.gui;

import br.ufrn.imd.controller.MediaPlayerController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfileScreenController {

    @FXML
    private Label accountStatusLabel;

    @FXML
    private Label fullNameLabel;

    @FXML
    private Label usernameLabel;
    
    public void initialize() {
    	fullNameLabel.setText(MediaPlayerController.getLoggedUser().getFullName());
    	usernameLabel.setText(MediaPlayerController.getLoggedUser().getUsername());
    	if(MediaPlayerController.isUserVip()) {
    		accountStatusLabel.setText("VIP");
    	} else {
    		accountStatusLabel.setText("COMMON");
    	}
    }
    
}

# MediaPlayer
  [![NPM](https://img.shields.io/npm/l/react)](https://github.com/luigiisp/MediaPlayerProject/blob/main/LICENSE)
  ![Java](https://img.shields.io/badge/Java-8-b07219)
# About the project
Project related to the Programming Language 2 discipline, aiming to build a fully functional MP3 player with features such as user system and playlist management.

# How to use
First, make sure you have JavaFx installed in your system and IDE. 

In the first execution, the file "diretorios.txt" will be generated. It will store the path for the files "tracks.txt", "users.txt" and "playlist". In the next execution, "diretorios.txt" will be read and the files will be located
(Important: Don't delete or change the content of "directories.txt" if the entities were already registered).

The folder "files" will also be generated on the first execution. Inside it will be created the following files: 

•"Playlists" directory in which will be stored one text file for each playlist, storing its tracks and owner informations inside it.

•"tracks.txt" will store all necessary information from tracks registered in the program.

•"users.txt"  will be contained the user registred in the program.
 
Everytime you open the application, you will be redirected to login screen. If you already have an account, you can login, otherwise you can register by clicking the register button. After the login/register screen, you will be redirected to the Main Screen. The Main Screen is where you can reproduce tracks, create and edit playlists, check your profile info, etc. Only VIP accounts can use playlists.
The application will have no tracks registered by default, therefore you have to add your own mp3 files from your desktop (File -> Add new track).
To play tracks, you have to add them to the queue and press the play button. You can do this by finding the track using the search bar at the top, and adding it to the queue (or to a playlist and then playing the playlist).

# Used technologies
- Java
- JavaFX

# Clone
git clone https://github.com/luigiisp/MediaPlayerProject.git

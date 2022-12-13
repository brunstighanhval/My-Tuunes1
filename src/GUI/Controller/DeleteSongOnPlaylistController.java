package src.GUI.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import src.BE.Playlist;
import src.BE.Song;
import src.BE.SongOnPlaylist;
import src.GUI.Model.SongModel;
import src.GUI.Model.SongOnPlaylistModel;

public class DeleteSongOnPlaylistController extends BaseController{
    private Song selectedSong;
    private SongOnPlaylistModel model;
    private Playlist playlist;

    public void setModelAndSongPlaylist(SongOnPlaylistModel model, Song song, Playlist playlist){
        this.model=model;
        this.selectedSong=song;
        this.playlist = playlist;
    }
    public void handleButtonYesDeleteSongOnPlaylist(ActionEvent event) throws Exception
    {
        model.deleteSongOnPlaylist(playlist,selectedSong);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void handleButtonNoDeleteSongOnPlaylist(ActionEvent event)
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}

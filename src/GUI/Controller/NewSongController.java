package src.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import src.BE.Song;
import src.GUI.Model.SongModel;

public class NewSongController extends BaseController{

    private SongModel songModel;
    private SongViewController songViewController;
    public ComboBox<String> cbxDropDown;
    public TextField txtfTitle, txtfFile, txtfTime, txtfArtist;
    public Button btnChoose, btnSave, btbCancle;
    public Label lblTitle, lblArtist, lblTime, lblFile, lblCategory;
    public String fileMusicPath = "lib/music";
    private Path target = Paths.get(fileMusicPath);
    private File mFile;


    public NewSongController()
    {
        try {
            songModel = new SongModel();
            songViewController = new SongViewController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize()
    {
        //Adds items/Categories to ComboBox
        cbxDropDown.getItems().addAll("Pop", "Hiphop", "Rock");
        cbxDropDown.getSelectionModel().select("---");
    }

    public void handleButtonChoose(ActionEvent actionEvent)
    {
        //Opens file browser to select a file
        Stage stage = new Stage();
        FileChooser mFileChooser = new FileChooser();
        mFile = mFileChooser.showOpenDialog(stage);
        if (fileMusicPath != null)
        {
            txtfFile.setText((fileMusicPath +"/"+ mFile.getName()).replace("\\", "/").replaceAll(" ", "%20"));
        }
        System.out.println("Selected file " + mFile);
        System.out.println(getSongLength(mFile).toString());
    }

    public void handleButtonSave(ActionEvent actionEvent)
    {
        String title = txtfTitle.getText();
        String artist = txtfArtist.getText();
        String category = cbxDropDown.getValue();
        String filepath = txtfFile.getText();
        Time duration = Time.valueOf(txtfTime.getText());
        try
        {
            Files.copy(mFile.toPath(), target.resolve(mFile.toPath().getFileName()));
            System.out.println("Song added: " + title + ", " + artist + ", " + category + ", " + "'"+filepath+"'" + ", " + duration);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Could not add song");
        }

        mFile = new File (fileMusicPath + "/" + mFile.getName());

        try
        {
            this.songModel.createNewSong(title,artist,category,filepath,duration);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        //songViewController.tblSongs.setItems(songModel.getObservableSongs());
        Node source = (Node) actionEvent.getSource();
        Stage mStage = (Stage) source.getScene().getWindow();
        mStage.close();
    }

    public void handleButtonCancle (ActionEvent actionEvent)
    {
        //Closes stage if Cancle button is clicked
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void handleTxtTtl(ActionEvent actionEvent)
    {
        this.txtfTitle.getText();
    }

    public void handleCategory(ActionEvent actionEvent)
    {

    }

    public Duration getSongLength(File file)
    {
        Media mMedia = new Media("file:///" + file.getPath().replace("\\", "/").replaceAll(" ", "%20"));
        return mMedia.getDuration();
    }

    public void handleCancleEdit (ActionEvent event)
    {

    }

    public void handleSaveEdit (ActionEvent event)
    {

    }



    public void setController(SongViewController songViewController)
    {
        this.songViewController=songViewController;
    }
}



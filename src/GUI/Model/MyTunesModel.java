package src.GUI.Model;

public class MyTunesModel {

    private SongModel songModel;

    public MyTunesModel() throws Exception {
        songModel = new SongModel();
    }

    public SongModel getSongModel() {
        return songModel;
    }

    public void setSongModel(SongModel songModel) {
        this.songModel = songModel;
    }
}

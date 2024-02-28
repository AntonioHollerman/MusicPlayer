public class DbManager {
    public Folder[] getFolders(){
        return null;
    }
    public Folder[] getFolders(int folderId){
        return null;
    }
    public Playlist[] getPlaylists(int folderId){
        return null;
    }
    public Song[] getSongs(int playlistId){
        return null;
    }
    public String getImgPath(int imgId){
        return null;
    }
    public int getNextSongId(){
        return 0;
    }
    public int getNextImgId(){
        return 0;
    }
    public void addSongToPlaylist(int songId, int playlistId){

    }
    public void insertNewSong(Song newSong){

    }
    public void insertNewPlaylist(Playlist newPlaylist){

    }
    public void insertNewFolder(Folder newFolder){

    }
    public void deleteSongFromPlaylist(int playlistId, int songId){

    }
    public void deleteSong(int songId){

    }
    public void deletePlaylist(int playlistId){

    }
    public void deleteFolder(int folderId){

    }
    public void setNextId(String idName, int nextId){

    }
    public void setSongName(int songId, String songName){

    }
    public void setPlaylistName(int playlistId, String playlistName){

    }
    public void setFolderName(int folderId, String folderName){

    }
}

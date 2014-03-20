package com.coursework2.alistair.Beans;

public class Data {
	private String searchTrack, searchArtist, searchAlbum;
	String playlistName, trackID;
	int PlaylistPos;
	
	public Data(){
		
	}
	
	public void setTrack(String Track)
	{
		searchTrack = Track;
	}
	
	public String getTrack()
	{
		return searchTrack;
	}
	
	public void setArtist(String Artist)
	{
		searchArtist = Artist;
	}
	
	public String getArtist()
	{
		return searchArtist;
	}
	
	public void setAlbum(String Album)
	{
		searchAlbum = Album;
	}
	
	public String getAlbum()
	{
		return searchAlbum;
	}
	
	public void setPlaylistName(String Playlist)
	{
		playlistName = Playlist;
	}
	
	public String getPlaylistName()
	{
		return playlistName;
	}
	
	public void setTrackID(String ID)
	{
		trackID = ID;
	}
	
	public String getTrackID()
	{
		return trackID;
	}
	
	public void setPlaylistPos(int i)
	{
		PlaylistPos = i;
	}
	
	public int getPlaylistPos()
	{
		return PlaylistPos;
	}
	
	public void reset()
	{
		searchTrack = null;
		searchArtist = null;
		searchAlbum = null;
	}
	
}

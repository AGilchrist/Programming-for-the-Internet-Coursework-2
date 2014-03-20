package com.coursework2.alistair.Beans;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CreatePlaylist {
	String username, playlistname, title, artist, album, id;
	boolean playlistexists;
	Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
	PreparedStatement statement;
	int playlistpos = 0;
	ResultSet rs;
	Object results;
	
	public CreatePlaylist(){
		
	}
	
	public void setUsername(String user)
	{
		username = user;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setPlaylistName(String playlist)
	{
		playlistname = playlist;
	}
	
	public String getPlaylistName()
	{
		return playlistname;
	}
	
	public void setPlaylistPos(int pos)
	{
		playlistpos = pos;
	}
	
	public int getPlatlistPos()
	{
		return playlistpos;
	}
	
	public void setTitle(String Title)
	{
		title = Title;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setArtist(String Artist)
	{
		artist = Artist;
	}
	
	public String getArtist()
	{
		return artist;
	}
	
	public void setAlbum(String Album)
	{
		album = Album;
	}
	
	public String getAlbum()
	{
		return album;
	}
	
	public void setSongID(String Songid)
	{
		id = Songid;
	}
	
	public String getSongID()
	{
		return id;
	}
	
	public void reset()
	{
		username = null;
		playlistname = null;
		playlistpos = 0;
		title = null;
		artist = null;
		album = null;
		id = null;
	}
	
	public void resetResults()
	{
		rs = null;
	}
	
	public void CreatePlaylist()
	{
		Session session = cluster.connect("UserDetails");
		statement = session.prepare("SELECT * FROM UserDetails.UserPlaylists");
		BoundStatement boundStatement = new BoundStatement(statement);
		rs = session.execute(boundStatement);
		for (Row row : rs) {
			if(this.username.equals(row.getString("Username"))){
				if(this.playlistname.equals(row.getString("PlaylistName"))){
					playlistexists = true;
				}
			}
		}
		if(playlistexists != true){
		   statement = session.prepare("INSERT INTO UserDetails.UserPlaylists (Username, PlaylistName, PlaylistPos, TrackTitle, Artist, Album, Trackid) VALUES(?, ?, 0, ?, ?, ?, ?)");
		   session.execute(statement.bind(this.username, this.playlistname, "", "", "", ""));
		}
		session.close();
	}
	
	public void AddSong()
	{
		Session session = cluster.connect("UserDetails");
		statement = session.prepare("SELECT * FROM UserDetails.UserPlaylists");
		BoundStatement boundStatement = new BoundStatement(statement);
		rs = session.execute(boundStatement);
		for (Row row : rs) {
			if(this.username.equals(row.getString("Username"))){
				if(this.playlistname.equals(row.getString("PlaylistName"))){
					playlistexists = true;
				}
			}
		}
		if(playlistexists == true){
			statement = session.prepare("INSERT INTO UserDetails.UserPlaylists (Username, PlaylistName, PlaylistPos, TrackTitle, Artist, Album, Trackid) VALUES(?, ?, ?, ?, ?, ?, ?)");
		   session.execute(statement.bind(this.username, this.playlistname, this.playlistpos, this.title, this.artist, this.album, this.id));
		}
		playlistexists = false;
		session.close();
	}
	
	public ResultSet getPlaylists()
	{
		Session session = cluster.connect("UserDetails");
		statement = session.prepare("SELECT PlaylistName FROM UserDetails.UserPlaylists WHERE Username = ? AND PlaylistPos = 0");
		ResultSet pl = session.execute(statement.bind(this.username));
		session.close();
		return pl;
	}
	
	public int getSongCount()
	{
		Session session = cluster.connect("UserDetails");
		statement = session.prepare("SELECT * FROM UserDetails.UserPlaylists");
		BoundStatement boundStatement = new BoundStatement(statement);
		rs = session.execute(boundStatement);
		for (Row row : rs) {
			if(this.username.equals(row.getString("Username"))){
				if(this.playlistname.equals(row.getString("PlaylistName"))){
					playlistexists = true;
					playlistpos = 0;
				}
			}
		}
		if(playlistexists == true){
			statement = session.prepare("SELECT * FROM UserDetails.UserPlaylists WHERE Username = ? AND PlaylistName = ?");
			rs = session.execute(statement.bind(this.username, this.playlistname));
			for (Row row : rs) {
			playlistpos = row.getInt("PlaylistPos");
			}
		}
		playlistexists = false;
		session.close();
		return playlistpos;
}
	
	public boolean getPlaylistExists()
	{
		return playlistexists;
	}
	
	public void setPlaylistExists(boolean state)
	{
		playlistexists = state;
	}
	
	public Object getResults()
	{
		return results;
	}
	
	public void setResults(Object Results)
	{
		results = Results;
	}
			
}

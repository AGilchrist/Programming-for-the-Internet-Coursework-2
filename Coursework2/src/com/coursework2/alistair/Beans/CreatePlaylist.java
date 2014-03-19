package com.coursework2.alistair.Beans;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CreatePlaylist {
	String username, playlistname, playlistpos, title, artist, album, id;
	boolean playlistexists;
	Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
	PreparedStatement statement;
	
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
	
	public void setPlaylistPos(String pos)
	{
		playlistpos = pos;
	}
	
	public String getPlatlistPos()
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
		playlistpos = null;
		title = null;
		artist = null;
		album = null;
		id = null;
	}
	
	public void CreatePlaylist()
	{
		Session session = cluster.connect("UserDetails");
		statement = session.prepare("SELECT * FROM UserDetails.UserPlaylists");
		BoundStatement boundStatement = new BoundStatement(statement);
		ResultSet rs = session.execute(boundStatement);
		for (Row row : rs) {
				if(this.playlistname.equals(row.getString("PlaylistName"))){
					playlistexists = true;
		   		}
		   }
		   if(playlistexists != true){
			   statement = session.prepare("INSERT INTO UserDetails.UserPlaylists (Username, PlaylistName, PlaylistPos) VALUES(?, ?, 1)");
			   session.execute(statement.bind(this.username, this.playlistname));
		   }
		   session.close();
	}
	
	public boolean getPlaylistExists()
	{
		return playlistexists;
	}
	
	public void setPlaylistExists(boolean state)
	{
		playlistexists = state;
	}
			
}

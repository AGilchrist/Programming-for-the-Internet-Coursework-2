package com.coursework2.alistair.Beans;

public class Data {
	String searchTrack, searchArtist, searchAlbum;
	
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
	
	public void reset()
	{
		searchTrack = null;
		searchArtist = null;
		searchAlbum = null;
	}
	
}

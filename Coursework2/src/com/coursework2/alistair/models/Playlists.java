package com.coursework2.alistair.models;



import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Playlists {
Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
public Playlists(){

}

public void setCluster(Cluster cluster){
this.cluster=cluster;
}

public void CreateDatabase() {
	
Session session = cluster.connect();
session.execute("CREATE KEYSPACE IF NOT EXISTS UserDetails WITH replication "
         + "= {'class':'SimpleStrategy', 'replication_factor':1};");
session.execute("CREATE TABLE IF NOT EXISTS UserDetails.Users (id uuid, Name text, Password text, PRIMARY KEY (id, Name));");
session.execute("CREATE TABLE IF NOT EXISTS UserDetails.UserPlaylists (id uuid, Username text, Playlistname text, TrackTitle text, Artist text, Album text, PRIMARY KEY (id, Username, Playlistname));");
session.close();
}
}
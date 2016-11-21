import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.commons.lang.StringEscapeUtils;

public class AddJson {
	
	//https://api.themoviedb.org/3/movie/popular?api_key=d53c99e97da849ea55b8ce31fd5e7666&language=en-FR
	//w92", "w154", "w185", "w342", "w500", "w780
	//http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg 
	// bfb2dac1747744eaa4532c62d7606ca0 id spotify
	// BQAriOrylvVUloW2_n3NbEpWuPZlg6pO oth2
    
	//https://api.spotify.com/v1/search?q=ok&type=track&limit=2
	
	private boolean busy = false;
	private boolean erreur = false;

	public AddJson (){
		
	}
	
	public int DL (String urlS){
		URL url = null;
	    try {
	        url = new URL (urlS);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        connection.connect();
	        /*while(HttpURLConnection.HTTP_OK != connection.getResponseCode()){
	        	System.out.println("co pas ok");
	        	try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }*/
	        System.out.println(connection.getResponseCode());
	        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
	        	System.out.println("co ok");
	        	copyInputStreamToFile(connection.getInputStream(),
	                    new File("temp.json"));
	        	return 0;
	        }
	        else if (connection.getResponseCode() == HttpURLConnection.HTTP_BAD_REQUEST){
	        	erreur = true;
	        	return 0;
	        }
	        //System.exit(-1);
	        
	    } catch (MalformedURLException e1) {
	        e1.printStackTrace();
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    }
	    return 1;
	}
	
	public String buildRequestBook(String subject) {
		String s = "https://www.googleapis.com/books/v1/volumes?q="+subject+"&&printType=books&orderBy=newest&maxResults=40&key=AIzaSyAT7eTEjPXHy8XGbk5-_thfHG638n_fcYY";
		System.out.println(s);
		return s;
	}
	
	public String buildRequestMusique(String subject) {
		String s = "https://api.spotify.com/v1/search?q="+subject+"&type=track&limit=2";
		System.out.println(s);
		return s;
	}
	
	public String buildRequestMoviePopular(String subject) {
		String s = "https://api.themoviedb.org/3/movie/popular?api_key=d53c99e97da849ea55b8ce31fd5e7666&language=en-FR&page="+subject;
		System.out.println(s);
		return s;
	}
	
	public JSONArray getArrayJS(){
		try {
			InputStream is = new FileInputStream("temp.json");
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			is.close();
			return new JSONArray(new String(buffer,"UTF-8"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JSONArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JSONArray();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JSONArray();
		}
	}
	
	public JSONObject getObjectJS(){
		try {
			InputStream is = new FileInputStream("temp.json");
			byte[] buffer = new byte[is.available()];
			is.read(buffer);
			is.close();
			return new JSONObject(new String(buffer,"UTF-8"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JSONObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JSONObject();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JSONObject();
		}
	}
    
    private void copyInputStreamToFile (InputStream in, File file){
        try {
            OutputStream ou =  new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len=in.read(buf))>0){
                ou.write(buf,0,len);
            }
            ou.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void sendFilm (String title, String description, String image, String date, String id, String language, String note ){
    	busy = true;
    	title = title.replace("'", "''");
    	description = description.replace("'", "''");
    	note = note.replace("'", "''");
    	image = image.replace("'", "''");
    	date = date.replace("'", "''");
    	id = id.replace("'", "''");
    	language = language.replace("'", "''");
    	String sqlinsert = "INSERT INTO VIDEO (CODE,TITRE,ANNEE,IMAGE,EMPRUNTABLE,EMPRUNTE,"
				+ "NBEMPRUNT,NBEXEMPLAIRE,DESCRIPTION,LANGUAGE,NOTE,DUREEEMPRUNT,TARIF) " +
				"VALUES ('" + id + "',"
				+ "'" + title + "',"
				+ "'" + date + "',"
				+ "'" + image + "',"
				+ "'" + 0 + "',"
				+ "'" + 0 + "',"
				+ "'" + 0 + "',"
				+ "'" + 0 + "',"
				+ "'" + description + "',"
				+ "'" + language + "',"
				+ "'" + note + "',"
				+ "'" + 0 + "',"
				+ "'" + 0 + "');"; 
				//System.out.println(sqlinsert);
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
			//System.out.println("Opened database successfully dz");
			Statement stmt = connexion.createStatement();
		//ResultSet rs = stmt.executeQuery(sqltest);
		
		//System.out.println(rs.getInt("sum"));
		//if (rs.getInt("sum")!=0){
		//  System.out.println("existe deja");
		// JOptionPane.showMessageDialog(null, "titre already exist", "Attention", JOptionPane.WARNING_MESSAGE);
		//} else {
			///System.out.println("preajout");
		
			stmt.executeUpdate(sqlinsert);
			//System.out.println("ajout");
		//}
			stmt.close();
			connexion.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		busy = false;
    }
    
    public static String escapeString (String str) {
		String results = StringEscapeUtils.escapeJava(str);
		System.out.println(results);
		return results;
    }
    
     
    public void sendBook (String title, String auteurs, String description, String image, String date, String id, String category){
    	busy = true;
    	title = title.replace("'", "''");
    	description = description.replace("'", "''");
    	auteurs = auteurs.replace("'", "''");
    	image = image.replace("'", "''");
    	date = date.replace("'", "''");
    	id = id.replace("'", "''");
    	category = category.replace("'", "''");
		//String sqltest = "SELECT COUNT(*) AS sum FROM Livre where TITRE='"+title+"'";
		String sqlinsert = "INSERT INTO LIVRE (CODE,TITRE,AUTEUR,ANNEE,CATEGORY,IMAGE,EMPRUNTABLE,EMPRUNTE,"
											+ "NBEMPRUNT,NBEXEMPLAIRE,DESCRIPTION,DUREEEMPRUNT,TARIF) " +
                   "VALUES ('" + id + "',"
                   		 + "'" + title + "',"
                   		 + "'" + auteurs + "',"
                   		 + "'" + date + "',"
                   		 + "'" + category + "',"
                   		 + "'" + image + "',"
                   		 + "'" + 0 + "',"
                   		 + "'" + 0 + "',"
                   		 + "'" + 0 + "',"
                   		 + "'" + 0 + "',"
                   		 + "'" + description + "',"
                   		 + "'" + 0 + "',"
                   		 + "'" + 0 + "');"; 
		System.out.println(sqlinsert);
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
		      System.out.println("Opened database successfully dz");
		      Statement stmt = connexion.createStatement();
		      //ResultSet rs = stmt.executeQuery(sqltest);
		      
		      //System.out.println(rs.getInt("sum"));
		      //if (rs.getInt("sum")!=0){
		    	//  System.out.println("existe deja");
		    	 // JOptionPane.showMessageDialog(null, "titre already exist", "Attention", JOptionPane.WARNING_MESSAGE);
		      //} else {
		    	  System.out.println("preajout");

		    	  stmt.executeUpdate(sqlinsert);
		    	  System.out.println("ajout");
		      //}
		      stmt.close();
		      connexion.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		busy = false;
    }
    
    public void sendMusic (String title, String auteur, String album, String image, String id){
    	busy = true;
    	title = title.replace("'", "''");
    	album = album.replace("'", "''");
    	image = image.replace("'", "''");
    	auteur = auteur.replace("'", "''");
    	id = id.replace("'", "''");
		//String sqltest = "SELECT COUNT(*) AS sum FROM Livre where TITRE='"+title+"'";
		String sqlinsert = "INSERT INTO AUDIO (CODE,TITRE,AUTEUR,ALBUM,IMAGE,EMPRUNTABLE,EMPRUNTE,"
											+ "NBEMPRUNT,NBEXEMPLAIRE,DUREEEMPRUNT,TARIF) " +
                   "VALUES ('" + id + "',"
                   		 + "'" + title + "',"
                   		 + "'" + auteur + "',"
                   		 + "'" + album + "',"
                   		 + "'" + image + "',"
                   		 + "'" + 0 + "',"
                   		 + "'" + 0 + "',"
                   		 + "'" + 0 + "',"
                   		 + "'" + 0 + "',"
                   		 + "'" + 0 + "',"
                   		 + "'" + 0 + "');"; 
		System.out.println(sqlinsert);
		try {
		      Class.forName("org.sqlite.JDBC");
		      Connection connexion = DriverManager.getConnection("jdbc:sqlite:biblio.db");
		      System.out.println("Opened database successfully dz");
		      Statement stmt = connexion.createStatement();
		      //ResultSet rs = stmt.executeQuery(sqltest);
		      
		      //System.out.println(rs.getInt("sum"));
		      //if (rs.getInt("sum")!=0){
		    	//  System.out.println("existe deja");
		    	 // JOptionPane.showMessageDialog(null, "titre already exist", "Attention", JOptionPane.WARNING_MESSAGE);
		      //} else {
		    	  System.out.println("preajout");

		    	  stmt.executeUpdate(sqlinsert);
		    	  System.out.println("ajout");
		      //}
		      stmt.close();
		      connexion.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		busy = false;
    }
    
    public void addBook (String nameS){
    	// faire dans un swing worker ralenti l'UI	
    	while (DL(this.buildRequestBook(nameS))!=0){
    		System.out.println("errreur");
	    	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		JSONObject js = this.getObjectJS();
		
		String title = "";
		String auteurs = "";
		String description = "";
		String image ="";
		String date ="";
		String id ="";
		String category ="";
		
		JSONArray Array;
		try {
			Array = js.getJSONArray("items");
			System.out.println("lenght " + Array.length());
			for (int i=0; i<Array.length(); i++){
				JSONObject item = Array.getJSONObject(i);
				//System.out.println(item.getString("id"));
				//System.out.println(item.getString("volumeInfo"));
				id = item.getString("id");
				JSONObject volumeInfo = item.getJSONObject("volumeInfo");
				
				title = volumeInfo.getString("title");
				description = volumeInfo.getString("description");
				date = volumeInfo.getString("publishedDate");
				JSONArray autors = volumeInfo.getJSONArray("authors");
				JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
				image = imageLinks.getString("thumbnail");
				//if (item.getJSONArray("categories") != null){
				JSONArray cat = volumeInfo.getJSONArray("categories");
				//JSONArray cat = volumeInfo.getJSONArray("categories");
				System.out.println("toto");
				if (cat != null){
				for (int k=0; k<cat.length(); k++){
					category = cat.getString(k) +" ";
				}}
				if (autors != null){
				for (int j=0; j<autors.length(); j++){
					auteurs+= autors.getString(j) +" ";
				}}
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			while (busy==true){
				try {
					System.out.println("erreur");
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			sendBook(title,auteurs,description,image,date,id,category);
			System.out.println("fin");
		
    }
    
    public void addVideo (int num){
    	while (DL(this.buildRequestMoviePopular(""+num))==1){
    		System.out.println("errreur");
	    	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		JSONObject js = this.getObjectJS();
		
		String title = "";
		String langage = "";
		String note = "";
		String description = "";
		String image ="";
		String date ="";
		String id ="";
		
		JSONArray Array = js.optJSONArray("results");
		System.out.println("lenght " + Array.length());
		for (int i=0; i<Array.length(); i++){
			JSONObject item = Array.optJSONObject(i);
			id = String.valueOf(item.optInt("id"));
			title = item.optString("original_title");
			description = item.optString("overview");
			date = item.optString("release_date");
			image = "http://image.tmdb.org/t/p/w185/" +String.valueOf(item.opt("backdrop_path"));
			note = String.valueOf(item.optDouble("vote_average"));
			langage = item.optString("original_language");
			while (busy==true){
				System.out.println("errreur");
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			sendFilm(title,description,image,date,id,langage,note);
			
		}
    }
    
    public void addMusic (String name){
    	System.out.println("MUSIQUE");
    	while (DL(this.buildRequestMusique(name))!=0){
    		System.out.println("errreur");
	    	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	//DL(this.buildRequestMusique(name));// faire dans un swing worker ralenti l'UI/	
		JSONObject js = this.getObjectJS();
		
		
		String title = "";
		String album = "";
		String auteur = "";
		String image ="";
		String id ="";
		if (erreur==true){
			System.out.println("ERRRRRRRRRREURR");
			erreur = false;}
		else{
		JSONObject track = js.optJSONObject("tracks");
		JSONArray Array;
		
		try {
			Array = track.getJSONArray("items");
			
		
		if (!Array.equals(null)){
		System.out.println("musique");
		for (int i=0; i<Array.length(); i++){
			JSONObject item = Array.optJSONObject(i);
			id = String.valueOf(item.optString("id"));
			title = item.optString("name");
			JSONObject albumO = item.optJSONObject("album");
			album = albumO.optString("name");
			JSONArray imageA = albumO.optJSONArray("images");
			image = imageA.optJSONObject(1).optString("url");
			JSONArray artistA = item.optJSONArray("artists");
			for (int j=0; j<artistA.length(); j++){
				auteur+=artistA.optJSONObject(j).optString("name")+", ";
			}
			while (busy==true){
				System.out.println("errreur");
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			sendMusic(title,auteur,album,image,id);
		}
		
		}
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    }
}

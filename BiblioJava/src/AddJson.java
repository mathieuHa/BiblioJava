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
	public AddJson (String urlS){
		URL url = null;
	    try {
	        url = new URL (urlS);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        connection.connect();
	        if (HttpURLConnection.HTTP_OK == connection.getResponseCode()){
	            copyInputStreamToFile(connection.getInputStream(),
	                    new File("temp.json"));
	        }
	    } catch (MalformedURLException e1) {
	        e1.printStackTrace();
	    } catch (IOException e1) {
	        e1.printStackTrace();
	    }
	}
	
	public static String buildRequestBook(String subject) {
		String s = "https://www.googleapis.com/books/v1/volumes?q="+subject+"&&printType=books&orderBy=newest&maxResults=40&key=AIzaSyAT7eTEjPXHy8XGbk5-_thfHG638n_fcYY";
		return s;
	}
	
	public static String buildRequestMoviePopular(String subject) {
		String s = "https://api.themoviedb.org/3/movie/popular?api_key=d53c99e97da849ea55b8ce31fd5e7666&language=en-FR&page="+subject;
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
    
    
    public static void sendFilm (String title, String description, String image, String date, String id, String language, String note ){
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
    }
    
    public static String escapeString (String str) {
		String results = StringEscapeUtils.escapeJava(str);
		System.out.println(results);
		return results;
    }
    
     
    public static void sendBook (String title, String auteurs, String description, String image, String date, String id, String category){
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
    }
    
    public static void addBook (){
    	AddJson addd = new AddJson(AddJson.buildRequestBook("flower"));// faire dans un swing worker ralenti l'UI	
		JSONObject js = addd.getObjectJS();
		
		String title = "";
		String auteurs = "";
		String description = "";
		String image ="";
		String date ="";
		String id ="";
		String category ="";
		
		try {
			JSONArray Array = js.getJSONArray("items");
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
				//if (item.optJSONArray("categories") != null){
				JSONArray cat = volumeInfo.optJSONArray("categories");
				//JSONArray cat = volumeInfo.getJSONArray("categories");
				System.out.println("toto");
				if (cat != null){
				for (int k=0; k<cat.length(); k++){
					category = cat.getString(k) +" ";
				}}
				for (int j=0; j<autors.length(); j++){
					auteurs+= autors.getString(j) +" ";
				}
				
				sendBook(title,auteurs,description,image,date,id,category);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void addVideo (){
    	AddJson addd = new AddJson(AddJson.buildRequestMoviePopular("1"));// faire dans un swing worker ralenti l'UI	
		JSONObject js = addd.getObjectJS();
		
		String title = "";
		String langage = "";
		String note = "";
		String description = "";
		String image ="";
		String date ="";
		String id ="";
		
		try {
			JSONArray Array = js.getJSONArray("results");
			System.out.println("lenght " + Array.length());
			for (int i=0; i<Array.length(); i++){
				JSONObject item = Array.getJSONObject(i);
				id = String.valueOf(item.getInt("id"));
				title = item.getString("original_title");
				description = item.getString("overview");
				date = item.getString("release_date");
				image = String.valueOf(item.get("backdrop_path"));
				note = String.valueOf(item.getDouble("vote_average"));
				langage = item.getString("original_language");
				sendFilm(title,description,image,date,id,langage,note);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

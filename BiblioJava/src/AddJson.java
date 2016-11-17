import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;

public class AddJson {
	
	//https://api.themoviedb.org/3/movie/popular?api_key=d53c99e97da849ea55b8ce31fd5e7666&language=en-FR
	public AddJson (){
		URL url = null;
	    try {
	        url = new URL ("http://binouze.fabrigli.fr/bieres.json");
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
}

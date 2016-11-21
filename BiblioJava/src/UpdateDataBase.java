import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.SwingWorker;

public class UpdateDataBase {

	private Scanner scanner;
	private AddJson ad = new AddJson();

	public UpdateDataBase() {
		// TODO Auto-generated constructor stub
		
		SwingWorker sw = new SwingWorker<Void,Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				// TODO Auto-generated method stub
				LectureFichier();
				DownloadItem a = new DownloadItem(1, ad, scanner);
				//
				//while (scanner.hasNextLine()){
					
				//}
				return null;
			}
			
			protected void done () {
				System.out.println("TOUT FINI");
			}
			
		};
		sw.execute();
	}
	
	public void LectureFichier (){
		File f = new File ("dico.dico");
		FileReader fr;
		try {
			fr = new FileReader (f);
			BufferedReader br = new BufferedReader (fr);
			scanner = new Scanner (br);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

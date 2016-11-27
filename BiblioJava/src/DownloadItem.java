import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class DownloadItem {

	public SwingWorker sw;
	public JProgressBar progressBar = new JProgressBar();
	public DownloadItem(int num, AddJson ad, Scanner scanner) {
		progressBar.setMaximum(10);
		JFrame jf = new JFrame();
		jf.add(progressBar);
		jf.setSize(500,80);
		jf.setVisible(true);
		sw = new SwingWorker<Void,Void>(){
			private String s;
			@Override
			protected Void doInBackground() throws Exception {
				// TODO Auto-generated method stub
				ad.getSqlHelper().connect();
				for (int i = 1; i<10; i++){
					for (int j = 1; j<1000; j++)
						s = scanner.nextLine();
					ad.addVideo(i);
					ad.addBook(s);
					System.out.println("after book");
					ad.addMusic(s);
					setProgress(i);
				}
				ad.getSqlHelper().disconnect();
				return null;
			}
			@Override
			protected void done() {
				jf.dispose();
			}
			
		};
		sw.addPropertyChangeListener(new PropertyChangeListener(){
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				if("progress".equals(arg0.getPropertyName())){
			        if(SwingUtilities.isEventDispatchThread())
			          progressBar.setValue(progressBar.getValue()+1);
			    }   
			}         
		});
		sw.execute();
	}
	
}

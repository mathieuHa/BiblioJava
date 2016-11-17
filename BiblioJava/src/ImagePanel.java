import java.awt.*;
import javax.swing.*;
public class ImagePanel extends JPanel
 {
   public Image image;
   BorderLayout borderLayout1 = new BorderLayout();
 
   public ImagePanel()
   {
     image = Toolkit.getDefaultToolkit().getImage("lib.jpg");
     System.out.println(image); 
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
   }
 
   public void paintComponent(Graphics g)
   {
     super.paintComponent(g);
 
     int imageWight = image.getWidth(this);
     int imageHeight = image.getHeight(this);
     
     g.drawImage (image, 0, 0, null);
     repaint();
   }
  private void jbInit() throws Exception
  {
    this.setLayout(borderLayout1);
  }
 }
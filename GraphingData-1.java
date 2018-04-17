/**
 * @author Ankush singh
 * Date of Creation: 16/04/2018
 */




import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;
import java.io.IOException;
public class GraphingData extends JPanel {
    //Enter data here
    File file = new File("a.txt");
    //long[] data = new long[100];
    long[] data =new long[20];
        
    int k;
    final int PAD = 20;
 
    protected void paintComponent(Graphics g)   {
       
        for(int i=0;i<20;i++){
            data[i]=file.length();
            try{Thread.sleep(10000);}catch(InterruptedException e){System.out.println(e);}  
}
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
        // Draw abcissa.
        g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));
        // Draw labels.
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();
        // Ordinate label.
        String s = "data";
        float sy = PAD + ((h - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
        for(int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw)/2;
            g2.drawString(letter, sx, sy);
            sy += sh;
        }
        // Abcissa label.
        s = "x axis";
        sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(s, frc).getWidth();
        float sx = (w - sw)/2;
        g2.drawString(s, sx, sy);
        // Draw lines.
        double xInc = (double)(w - 2*PAD)/(data.length-1);
        double scale = (double)(h - 2*PAD)/getMax();
        g2.setPaint(Color.green.darker());
        for(int i = 0; i < data.length-1; i++) {
            double x1 = PAD + i*xInc;
            double y1 = h - PAD - scale*data[i];
            double x2 = PAD + (i+1)*xInc;
            double y2 = h - PAD - scale*data[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        // Mark data points.
        g2.setPaint(Color.red);
        for(int i = 0; i < data.length; i++) {
            double x = PAD + i*xInc;
            double y = h - PAD - scale*data[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
    }
 
    private long getMax() {
        long max = -Integer.MAX_VALUE;
        for(int i = 0; i < data.length; i++) {
            if(data[i] > max)
                max = data[i];
        }
        return max;
    }
 
    public static void main(String[] args) throws InterruptedException, IOException {
    //     long[] data= new long[100];
    //     File file =new File("a.txt");
    //    for(int i=0;i<20;i++){
    //        data[i]= i;//file.length();
    //        System.out.println(data[i]);
    //    }
    File file = new File("a.txt");
    FileWriter fw=new FileWriter("a.txt");
    FileWriter fr= new FileWriter("b.txt"); 
    for(int i=0;i<2000;i++){
        fw.write("Some message");
    }
    fw.close();
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new GraphingData());
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}

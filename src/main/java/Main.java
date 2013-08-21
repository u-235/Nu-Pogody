import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;

import game.Canvas;

public class Main
{

    /**
     * @param args
     */
    public static void main(String[] args) {
        final Properties p=new Properties();
        
        try {
            p.load(new FileInputStream(new File("nu_pogody.ini")));
        }
        catch (FileNotFoundException e) {
            System.out.println("Input file not found");
            p.setProperty("score", "0");
            
            try {
                p.store(new FileOutputStream(new File("nu_pogody.ini")), null);
            }
            catch (FileNotFoundException e1) {
                System.out.println("Output file not found");
                e1.printStackTrace();
            }
            catch (IOException e1) {
                System.out.println("Error in input operation");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            p.setProperty("score", "0");
        }
        
        final Canvas c = new Canvas(p);

        final JFrame frame = new JFrame();
        frame.setTitle("НУ ПОГОДИ!!!");
        frame.add(c);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowListener() {
            
            @Override
            public void windowOpened(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void windowIconified(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void windowDeiconified(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void windowDeactivated(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                frame.dispose();                
            }
            
            @Override
            public void windowClosed(WindowEvent e) {
                c.onClose();
                
                try {
                    p.store(new FileOutputStream(new File("nu_pogody.ini")), null);
                }
                catch (FileNotFoundException e1) {
                }
                catch (IOException e1) {
                }
            }
            
            @Override
            public void windowActivated(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }
        });
        c.setPreferredSize(new Dimension(500, 400));
        frame.pack();
        frame.setVisible(true);
    }
}

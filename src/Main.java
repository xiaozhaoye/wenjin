import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class Main{
    public static void main(String []args)
    {
        final Frame f=new Frame("zhycheng");
        f.setSize(300, 400);
        f.setLocation(400, 300);
        f.setBackground(Color.CYAN);
        //TextField tf=new TextField(20);
        //f.add(tf,"North");
        final TextArea ta=new TextArea();
        f.add(ta);
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                super.windowClosing(e);
                System.exit(0);
            }
        });
        MenuBar mb=new MenuBar();
        Menu m1=new Menu("File");
        Menu m2=new Menu("Edit");
        //MenuItem mi1=new MenuItem("New");
        MenuItem mi2=new MenuItem("Open");
        mi2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                FileDialog fd=new FileDialog(f,"zhycheng",FileDialog.LOAD);
                fd.show();
                String strFile=fd.getDirectory()+fd.getFile();
                FileInputStream fis=null;
                try {
                    fis=new FileInputStream(strFile);
                    byte []buf=new byte[10*1024];
                    int len=fis.read(buf);
                    ta.append(new String(buf,0,len));
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                finally
                {
                    try {
                        assert fis != null;
                        fis.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }});
        MenuItem mi3=new MenuItem("Save");
        mi3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                FileDialog fd=new FileDialog(f,"zhycheng",FileDialog.SAVE);
                fd.show();
                String path=fd.getDirectory()+fd.getFile()+".txt";
                FileOutputStream fos=null;
                DataOutputStream dos=null;
                try {
                    fos=new FileOutputStream(path);
                    String sa=ta.getText();
                    dos=new DataOutputStream(fos);
                    dos.writeUTF(sa);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                finally
                {
                    try {
                        assert dos != null;
                        dos.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }});
        MenuItem mi4=new MenuItem("Exit");
        mi4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }});
        MenuItem mi5=new MenuItem("Copy");
        MenuItem mi6=new MenuItem("Paste");
        //m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi4);
        m2.add(mi5);
        m2.add(mi6);
        mb.add(m1);
        mb.add(m2);
        f.setMenuBar(mb);
    }



}

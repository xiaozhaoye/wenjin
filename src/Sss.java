import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;

public class Sss {

        public static void main(String[] args) {
            // write your code here
            Frame f=new Frame("Text Editor");
            f.setLocation(500,500);
            f.setSize(500,500);
            MenuBar menuBar=new MenuBar();
            f.setMenuBar(menuBar);

            Menu m1=new Menu("File");
            Menu m2=new Menu("Edit");
            Menu m3=new Menu("capabilitiy");
            Menu m4=new Menu("Other");

            menuBar.add(m1);
            menuBar.add(m2);
            menuBar.add(m3);
            menuBar.add(m4);

            MenuItem mi1=new MenuItem("New");
            MenuItem mi2=new MenuItem("Open");
            MenuItem mi3=new MenuItem("Save");
            MenuItem mi4=new MenuItem("Search");
            MenuItem mi5=new MenuItem("Exit");
            MenuItem mi6=new MenuItem("Select");
            MenuItem mi7=new MenuItem("Copy");
            MenuItem mi8=new MenuItem("Paste");
            MenuItem mi9=new MenuItem("Cut");
            MenuItem mi10=new MenuItem("T&D");
            MenuItem mi11=new MenuItem("About");
            MenuItem mi12=new MenuItem("Print");

            m1.add(mi1);
            m1.add(mi2);
            m2.add(mi3);
            m2.add(mi4);
            m2.add(mi5);
            m3.add(mi6);
            m3.add(mi7);
            m3.add(mi8);
            m3.add(mi9);
            m4.add(mi10);
            m4.add(mi11);
            m4.add(mi12);

            mi1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Frame frame = new Frame("新窗口");
                    frame.setLocation(100,50);
                    frame.setSize(500,500);
                    frame.setVisible(true);
                    frame.addWindowListener(new MyWindowListener());
                }
            });
            TextArea ta=new TextArea();
            ta.setSize(100,100);
            f.add(ta);
            mi2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FileDialog fd=new FileDialog(f,"open",FileDialog.LOAD);
                    fd.setVisible(true);
                    String dirpath = fd.getDirectory();
                    String fileName = fd.getFile();
                    if (dirpath == null || fileName == null)
                        return;
                    else
                        ta.setText(null);
                    File file = new File(dirpath, fileName);
                    try {
                        BufferedReader bufr = new BufferedReader(new FileReader(file));
                        String line;
                        while ((line = bufr.readLine()) != null) {
                            ta.append(line + "\r\n");
                        }
                        bufr.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            mi10.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    Frame f = new Frame();
                    TextArea txt = new TextArea();
                    txt.setText(new Date().toString());
                    f.add(txt);
                    f.pack();
//                    f.setVisible(true);
                }
            });

            f.pack();
            f.setVisible(true);
            f.addWindowListener(new MyWindowListener());
        }
    }


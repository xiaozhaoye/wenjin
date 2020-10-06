import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Frame f=new Frame("Text Editor");
        f.setLocation(500,500);
        f.setSize(1000,1000);
        MenuBar menuBar=new MenuBar();
        f.setMenuBar(menuBar);

        Menu m1=new Menu("File");
        Menu m2=new Menu("Search");
        Menu m3=new Menu("View");
        Menu m4=new Menu("Help");

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
        m1.add(mi3);
        m2.add(mi4);
        m2.add(mi5);
        m3.add(mi6);
        m3.add(mi7);
        m3.add(mi8);
        m3.add(mi9);
        m4.add(mi10);
        m4.add(mi11);
        m4.add(mi12);

        JTextArea ta=new JTextArea();
        ta.setSize(100,100);
        f.add(ta);
        mi1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame frame = new Frame("新窗口");
                f.setLocation(100,50);
                f.setSize(500,500);

                f.setVisible(true);
                f.addWindowListener(new MyWindowListener());
            }
        });

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
        mi3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fd=new FileDialog(f,"save",FileDialog.SAVE);
                fd.setVisible(true);
                File file;
                String dirpath = fd.getDirectory();
                String fileName = fd.getFile();
                if (dirpath == null || fileName == null)
                    return;
                else
                    file=new File(dirpath,fileName);
                try {
                    BufferedWriter bufw = new BufferedWriter(new FileWriter(file));
                    String text = ta.getText();
                    bufw.write(text);
                    bufw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        mi4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog findDialog=new JDialog();
                Container con=findDialog.getContentPane();
                con.setLayout(new FlowLayout(FlowLayout.LEFT));
                JLabel findContentLabel=new JLabel("look for:");
                JTextField findText=new JTextField(15);
                JButton findNextButton=new JButton("search");
                ButtonGroup bGroup=new ButtonGroup();
                JRadioButton upButton=new JRadioButton("up");
                JRadioButton downButton=new JRadioButton("down");
                upButton.setSelected(true);
                bGroup.add(upButton);
                bGroup.add(downButton);

                JPanel panel1=new JPanel();
                JPanel panel2=new JPanel();
                JPanel panel3=new JPanel();
                JPanel directionPanel=new JPanel();
                directionPanel.setBorder(BorderFactory.createTitledBorder("direction"));
                directionPanel.add(upButton);
                directionPanel.add(downButton);
                panel1.setLayout(new GridLayout(2,1));
                panel1.add(findNextButton);
                panel2.add(findContentLabel);
                panel2.add(findText);
                panel2.add(panel1);
                panel3.add(directionPanel);
                con.add(panel2);
                con.add(panel3);
                findDialog.setSize(410,180);
                findDialog.setLocation(230,280);
                findDialog.setVisible(true);
                findNextButton.addActionListener(new ActionListener()
                {   public void actionPerformed(ActionEvent e)
                {
                    int k=0;
                    final String strA,strB;
                    strA=ta.getText().toUpperCase();
                    strB=findText.getText().toUpperCase();
                    if(upButton.isSelected())
                    {
                        if(ta.getSelectedText()==null)
                            k=strA.lastIndexOf(strB,ta.getCaretPosition()-1);
                        else
                            k=strA.lastIndexOf(strB, ta.getCaretPosition()-findText.getText().length()-1);
                        if(k>-1)
                        {
                            ta.setCaretPosition(k);
                            ta.select(k,k+strB.length());
                        }
                        else
                        {   JOptionPane.showMessageDialog(null,"can't find！","",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else if(downButton.isSelected())
                    {   if(ta.getSelectedText()==null)
                        k=strA.indexOf(strB,ta.getCaretPosition()+1);
                    else
                        k=strA.indexOf(strB, ta.getCaretPosition()-findText.getText().length()+1);
                        if(k>-1)
                        {
                            ta.setCaretPosition(k);
                            ta.select(k,k+strB.length());
                        }
                        else
                        {   JOptionPane.showMessageDialog(null,"can't find!","",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
                });
            }
        });

        mi5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mi11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n"+
                                " team members:        student number: \n"+
                                "  Yingying Yu             19029843   \n"+
                                "  Hengyi Zhao             19029649    \n"+
                                "&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n",
                        "message",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        mi12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int state = fileChooser.showOpenDialog(null);
                if (state == fileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                    DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                    PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
                    PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
                    PrintService service = ServiceUI.printDialog(null, 200, 200,printService, defaultService, flavor, pras);
                    if (service != null) {
                        try {
                            DocPrintJob job = service.createPrintJob();
                            FileInputStream fis = new FileInputStream(file);
                            DocAttributeSet das = new HashDocAttributeSet();
                            Doc doc = new SimpleDoc(fis, flavor, das);
                            job.print(doc, pras);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });

        f.pack();
        f.setVisible(true);
        f.addWindowListener(new MyWindowListener());
    }
}

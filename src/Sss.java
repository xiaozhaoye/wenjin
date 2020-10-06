import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class Sss {

    public static void main(String[] args) {
        JTextArea textArea = new JTextArea();
        Toolkit toolKit = Toolkit.getDefaultToolkit(); //设置默认工具箱
        Clipboard clipboard = toolKit.getSystemClipboard();//调用文件工具箱下的系统剪贴板方法
            // write your code here
            Frame f=new Frame("Text Editor");
            f.setLocation(500,500);
            f.setSize(500,500);
            MenuBar menuBar=new MenuBar();
            TextArea ta=new TextArea();
            ta.setSize(100,100);
            f.add(ta);
            f.setMenuBar(menuBar);
//            f.add(textArea);
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
//                    if(ta.getText()!=null){
//
                        Frame frame = new Frame();
                    TextArea ta=new TextArea();
//                        TextArea txt = new TextArea();
//                        txt.setText("Do you want to save this one?");
//                        Button butn1=new Button();
//                        butn1.setLabel("Yes");
//                        Button butn2=new Button();
//                        butn2.setLabel("No");
//                        frame.add(txt);
////                        frame.add(butn1,1,6);
////                        frame.add(butn2,1,2);
//                        System.out.println("qfdfds");
//                        frame.pack();
//                        frame.setVisible(true);
//
//                    }
                    ta.setText("");  //设置文本框内值为空
//                    "新窗口");
//                    frame.setLocation(100,50);
//                    frame.setSize(500,500);
//                    frame.setVisible(true);
//                    frame.addWindowListener(new MyWindowListener());
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
            mi6.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ta.selectAll();  //文本区域全选
                }
            });
            mi7.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = ta.getSelectedText();  //将得到的选择文本内容存入text中
                    StringSelection selection= new StringSelection(text);
                    clipboard.setContents(selection,null);//将得到的内容放入到剪切板中
                }
            });
            mi8.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Transferable contents = clipboard.getContents(this);//设置剪切板中的内容存入可传输内容中
                    if(contents==null)  //如果内容为空
                        return;    //结束
                    String text;
                    text=""; //设置文本区域为空
                    try{
                        text = (String)contents.getTransferData(DataFlavor.stringFlavor);
                        // 检查内容是否是文本类型
                    }
                    catch(UnsupportedFlavorException ex){ }
                    catch(IOException ex){ }
                    ta.replaceRange(text,
                            ta.getSelectionStart(),ta.getSelectionEnd());
                }
            });

            mi9.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                String text = ta.getSelectedText(); //将得到的选择文本内容存入text中
                StringSelection selection = new StringSelection(text);
            clipboard.setContents(selection,null);//将得到的内容放入到剪切板中
            ta.replaceRange("",ta.getSelectionStart(),//将原有的内容进行替换 替换为空
                    ta.getSelectionEnd()); //得到文本区域的值
                    }
            });

            mi10.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ta.insert(new Date().toString(),ta.getCaretPosition());
                }
            });
            f.pack();
            f.setVisible(true);
            f.addWindowListener(new MyWindowListener());
        }


}


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;
import java.awt.datatransfer.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class NoteBookDemo extends JFrame implements ActionListener {

    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("文件"),   // 绘制文件按钮
            edit = new JMenu("编辑");    //绘制编辑按钮
    JMenuItem[] menuItem ={  //设置文件按钮下的按钮选项
            new JMenuItem("新建"),//绘制新建按钮
            new JMenuItem("打开"),//绘制打开按钮
            new JMenuItem("保存"),//绘制保存按钮
            new JMenuItem("退出"),//绘制退出按钮
            new JMenuItem("全选"),//绘制全选按钮
            new JMenuItem("复制"),//绘制复制按钮
            new JMenuItem("剪切"),//绘制剪切按钮
            new JMenuItem("粘贴"),//绘制粘贴按钮
            new JMenuItem("时间"),//绘制粘贴按钮
    };
    JTextArea textArea = new JTextArea();
    String fileName = "NoName";     //设置默认文件名
    Toolkit toolKit = Toolkit.getDefaultToolkit(); //设置默认工具箱
    Clipboard clipboard = toolKit.getSystemClipboard();//调用文件工具箱下的系统剪贴板方法


    private JFileChooser openFileDialog = new JFileChooser();//设置私有的JFileChooser 下的打开文件对话方法
    private JFileChooser saveFileDialog = new JFileChooser();//设置私有的JFileChooser 下的保存文件对话方法


    public NoteBookDemo() {

        setTitle("Java记事本"); //设置程序标题名
        setFont(new Font("Times New Roman",Font.PLAIN,15)); //设置字体及字体大小
        setBackground(Color.white);//设置程序背景色为白色
        setSize(500,500);//设置程序运行的窗口大小

        setJMenuBar(menuBar);
        menuBar.add(file);
        menuBar.add(edit);

        for(int i=0;i<4;i++) //循环4次
        {
            file.add(menuItem[i]); //file下添加4个选项
            edit.add(menuItem[i+5]);//edit下添加4个选项
        }

        this.getContentPane().add(textArea);//添加文本输入区域

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置退出按钮

        for(int i=0;i<menuItem.length;i++)   // 循环执行，取决于列表长度
        {
            menuItem[i].addActionListener(this);//设置监听
        }
    }


    public void openFile(String fileName){ //设置打开方法
        try{
            File file = new File(fileName);//读取打开的文件名
            FileReader readIn = new FileReader(file);
            int size = (int)file.length();//得到打开文件的长度
            int charsRead = 0;
            char[] content = new char[size];
            while(readIn.ready())
                charsRead += readIn.read(content,charsRead,size-charsRead);
            readIn.close();
            textArea.setText(new String(content,0,charsRead));
        }catch(Exception e)//异常处理
        {
            System.out.println("Error opening file!");//输出异常信息 错误打开文件
        }
    }


    public void writeFile(String fileName){ //异常处理
        try{
            File file = new File(fileName);
            FileWriter write = new FileWriter(file);
            write.write(textArea.getText());
            write.close();
        }catch(Exception e){//关闭时的异常处理
            System.out.println("Error closing file!");//输出异常处理信息
        }
    }




    public void actionPerformed(ActionEvent e) {

        Object eventSource = e.getSource();
        if(eventSource == menuItem[0])//新建文件事件
        {
            textArea.setText("");  //设置文本框内值为空
        }
        else if(eventSource == menuItem[1])//打开文件
        {
            openFileDialog.showOpenDialog(this);   //打开文件对话框
            fileName = openFileDialog.getSelectedFile().getPath();//得到文件名
            if(fileName != null)  //如果打开的文件不为空
            {
                openFile(fileName); //打开文件
            }
        }
        else if(eventSource ==menuItem[2]) //保存文件事件
        {
            saveFileDialog.showSaveDialog(this); //打开保存文件对话框
            fileName = saveFileDialog.getSelectedFile().getPath();//得到文件名（本地路径）
            if(fileName !=null)  //如果文件名不为空
            {
                writeFile(fileName); //写入文件
            }
        }
        else if(eventSource==menuItem[3])//设置退出事件
        {
            System.exit(0);//程序结束 退出
        }
        else if(eventSource == menuItem[4])  //设置全选事件
        {
            textArea.selectAll();  //文本区域全选
        }
        else if(eventSource == menuItem[5])  //设置复制事件
        {
            String text = textArea.getSelectedText();  //将得到的选择文本内容存入text中
            StringSelection selection= new StringSelection(text);
            clipboard.setContents(selection,null);//将得到的内容放入到剪切板中
        }
        else if(eventSource == menuItem[6]) //设置剪切事件
        {
            String text = textArea.getSelectedText(); //将得到的选择文本内容存入text中
            StringSelection selection = new StringSelection(text);
            clipboard.setContents(selection,null);//将得到的内容放入到剪切板中
            textArea.replaceRange("",textArea.getSelectionStart(),//将原有的内容进行替换 替换为空
                    textArea.getSelectionEnd()); //得到文本区域的值

        }
        else if(eventSource == menuItem[7])//设置删除事件
        {
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
            textArea.replaceRange(text,
                    textArea.getSelectionStart(),textArea.getSelectionEnd());
        }
        else if(eventSource == menuItem[8]) //设置时间事件
        {
            textArea.setText(new Date().toString());
        }

    }
    public static void main(String[] args) {

        NoteBookDemo noteBook = new NoteBookDemo();
        noteBook.setVisible(true);

    }
}


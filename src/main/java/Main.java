import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        final PrintJob  p=null;//声明一个要打印的对象

        final Graphics g=null;//要打印的对象JTextArea textArea = new JTextArea();
        Toolkit toolKit = Toolkit.getDefaultToolkit(); //设置默认工具箱
        final Clipboard clipboard = toolKit.getSystemClipboard();//调用文件工具箱下的系统剪贴板方法
        // write your code here
        final Frame f=new Frame("Text Editor");
        f.setLocation(500,500);
        f.setSize(500,500);
        MenuBar menuBar=new MenuBar();
        final TextArea ta=new TextArea();
        ta.setSize(100,100);
        f.add(ta);
        f.setMenuBar(menuBar);
//            f.add(textArea);
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
//                    System.out.println(r);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
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
                int n = JOptionPane.showConfirmDialog(null, "you want to save as a PDF file?", "",JOptionPane.YES_NO_OPTION);
                if (n==1) {
                    FileDialog fd = new FileDialog(f, "save", FileDialog.SAVE);
                    fd.setVisible(true);
                    File file;
                    String dirpath = fd.getDirectory();
                    String fileName = fd.getFile();
                    if (dirpath == null || fileName == null)
                        return;
                    else
                        file = new File(dirpath, fileName);
                    try {
                        System.out.println(file);
                        BufferedWriter bufw = new BufferedWriter(new FileWriter(file));
                        String text = ta.getText();
                        bufw.write(text);
                        bufw.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                else{
                    FileDialog fd1 = new FileDialog(f, "save", FileDialog.SAVE);
                    fd1.setVisible(true);
                    File file;
                    String dirpath1 = fd1.getDirectory();
                    String fileName = fd1.getFile();
                    if (dirpath1 == null || fileName == null)
                        return;
                    else
                        file = new File(dirpath1, fileName);
                    try {
                        Document document = new Document();
                        String text = ta.getText();
                        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
                        // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
                        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
//                        BaseFont fontChinese = BaseFont.createFont("C:/Windows/Fonts/STSONG.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                        // 3.打开文档
                        document.open();
//                        document.add(new Paragraph("你好，世界！", new Font(fontChinese, 12)));
                        // 4.添加一个内容段落
                        document.add(new Paragraph(text));
                        document.close();
                        // 5.关闭文档
                        writer.close();
                    } catch (IOException | DocumentException e1) {
                        e1.printStackTrace();
                    }
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
                final JTextField findText=new JTextField(15);
                JButton findNextButton=new JButton("search");
                ButtonGroup bGroup=new ButtonGroup();
                final JRadioButton upButton=new JRadioButton("up");
                final JRadioButton downButton=new JRadioButton("down");
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





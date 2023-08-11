import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener
{
    //declaring data members and properties of txteditor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;

    JMenuItem newFile,openFile,saveFile; //file menu items
    JMenuItem cut,copy,paste,selectAll,close; //edit menu item
    JTextArea textArea;//textarea
    TextEditor()
    {
        //initialize a frame
        frame=new JFrame();

        //initialize a menubar
        menuBar=new JMenuBar();

        //Intialize the text area
        textArea=new JTextArea();

        //initialize menus
        file=new JMenu("File");
        edit=new JMenu("Edit");

        //Initialize file menu item
        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");

        //add action listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add menu item to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize edit menu item
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);


        //Add to editmenu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);

        //set menubar to frame
        frame.setJMenuBar(menuBar);

       //create content pane
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //add text area t panel
        panel.add(textArea, BorderLayout.CENTER);
        //CREATE SCROLL pane
        JScrollPane scrollPane=new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);



        //set dimensions of frame
        frame.setBounds(100,100,400,400);
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if(actionEvent.getSource()==cut)
        {
            //perform cut operation
            textArea.cut();

        }
        if(actionEvent.getSource()==copy)
        {
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste)
        {
            //perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll)
        {
            //perform selectall operation
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close)
        {
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile)
        {
            //open file chooser
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showOpenDialog(null);

            //if we have clicked on open button
            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                //getting the selected file
                File file=fileChooser.getSelectedFile();

                String filePath=file.getPath();

                try{
                    //initialize file reader
                    FileReader fileReader=new FileReader(filePath);

                    //initialize buffered reader
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="", output="";

                    //Read contents of file line by line
                    while((intermediate=bufferedReader.readLine())!=null)
                    {
                        output+=intermediate+"\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);

                }

                catch (IOException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }

            }
        }
        if(actionEvent.getSource()==saveFile)
        {
            //initialize file picker
            JFileChooser fileChooser=new JFileChooser("C:");
            //get choose option from file chooser
            int chooseOption=fileChooser.showSaveDialog(null);

            //check if we clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                //create a new file with choosen directory path and file name
                File file= new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //initialize file writer
                    FileWriter fileWriter=new FileWriter(file);

                    //initialize buffer writer
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);

                    //writer contest of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();

                }
            }
        }
        if(actionEvent.getSource()==newFile)
        {
            TextEditor newtextEditor=new TextEditor();
        }
    }
    public static void main(String[] args)
    {
        TextEditor textEditor =new TextEditor();
    }
}
package view;

import java.io.File;

import javax.swing.JFileChooser;

public class MyCustomFileChooserRunner {


    public static String MyCustomFileChooserScreen(String option) {

        MyFileChooser f = new MyFileChooser(".txt",option);
        int result;
        String path = null;

        result = f.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = f.getSelectedFile();
            path = file.getAbsolutePath();
        }
        return path;
    }
}
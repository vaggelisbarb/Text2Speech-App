package view;

import java.io.File;

import javax.swing.JFileChooser;

public class MyFileChooser extends JFileChooser {

    private String filterString;
    
    
    public MyFileChooser(String filterString,String option) {
        super();
        this.filterString = filterString;
        
        if(option.equals("Dir"))
        	setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        else if(option.contentEquals("Files"))
        	setFileSelectionMode(JFileChooser.FILES_ONLY);
        setFileFilter(new MyFileFilter(filterString));
    }

    @Override
    public void approveSelection() {
        File file = this.getSelectedFile();
        if (!file.isDirectory() || file.getName().contains(filterString)) {
            super.approveSelection();           
        }   
    }
}
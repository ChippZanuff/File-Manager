package biz;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SelectedFiles
{
    private List<File> selectedFiles = new ArrayList<>();
    private int indexOfRepeatedFile = 0;

    public void addSelectedFileInList(File file)
    {
        if (this.sameFileChecking(file))
        {
            this.selectedFiles.add(file);
        }
        else
        {
            if (this.sameFileCheckingDelete(file))
            {
                this.selectedFiles.remove(this.indexOfRepeatedFile);
            }
        }
    }

    public List<File> getList()
    {
        return this.selectedFiles;
    }

    private boolean sameFileChecking(File file)
    {
        if(this.selectedFiles.size() == 0)
        {
            return true;
        }
        for (File added : this.selectedFiles)
        {
            if (file.getName().equals(added.getName()))
            {
                return false;
            }
        }
        return true;
    }

    private boolean sameFileCheckingDelete(File file)
    {
        for (File added : this.selectedFiles)
        {
            if (file.getName().equals(added.getName()))
            {
                this.indexOfRepeatedFile = this.selectedFiles.indexOf(added);
                return true;
            }
        }
        return false;
    }

    public void clearSelectedFiles()
    {
        this.selectedFiles.clear();
    }

    public boolean filesCheck()
    {
        return this.selectedFiles.size() != 0;
    }
}

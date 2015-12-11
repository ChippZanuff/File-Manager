package biz;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MetaData
{
    FilesLists filesLists = new FilesLists();
    File file;
    File[] getRoot;
    String path;
    int sizeOfString;
    private boolean isShowFiles = true;

    public MetaData(String path)
    {
        this.path = path;
        this.setPath(path);
    }

    public int getSizeOfString()
    {
        this.setSizeOfString();
        return this.sizeOfString;
    }

    public String LocalFilePath(String path)
    {
        this.sizeOfString = path.length();
        return path;
    }

    public String getPath()
    {
        return this.file.getPath();
    }

    public void setPath(String path)
    {
        this.file = new File(path);
    }

    public void back()
    {
        this.setPath(this.file.getParent());
    }

    public ArrayList<File> getRootDirectory()
    {
        return new ArrayList<>(Arrays.asList(this.getRoot = File.listRoots()));
    }

    public ArrayList<File> getFiles()
    {
        if (! this.isShowFiles) {
            return this.getRootDirectory();
        }
        return this.filesLists.getFileList(getPath());
    }

    public void showRoots()
    {
        this.isShowFiles = false;
    }

    public void showFiles()
    {
        this.isShowFiles = true;
    }

    public void setSizeOfString()
    {
        this.sizeOfString = this.path.length();
    }
}

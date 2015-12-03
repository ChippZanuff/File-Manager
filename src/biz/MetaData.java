package biz;

import com.googlecode.lanterna.screen.Screen;

import java.io.File;
import java.util.ArrayList;

public class MetaData
{
    Screen screen;
    FilesLists filesLists = new FilesLists();
    File file;
    File[] getRoot;
    String path;
    int sizeOfString;

    public MetaData(String path)
    {
        this.path = path;
        this.setPath(path);
    }

    public int getSizeOfString()
    {
        return this.sizeOfString;
    }

    public String LocalFilePathnLength(String path)
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

    public String back()
    {
        return this.file.getParent();
    }

    public File[] getRootDirectory()
    {
        this.getRoot = File.listRoots();
        return getRoot;
    }

    public void setScreen(Screen screen)
    {
        this.screen = screen;
    }

    public ArrayList<File> getFiles()
    {
        return this.filesLists.getFileList(getPath());
    }
}

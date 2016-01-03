package biz;

import ui.Cursor;

import java.io.File;
import java.util.List;

public class Directory
{
    private MetaData metaData;
    private List<File> files;
    private int skip = 0;
    private int limit;

    public Directory(MetaData metaData, int limit)
    {
        this.metaData = metaData;
        this.limit = limit;
        this.loadFiles();
    }

    public void increment()
    {
        if(this.getFileCount() > (this.skip + this.limit))
        {
            this.skip++;
        }
    }

    public void decrement()
    {
        if (this.skip != 0)
        {
            this.skip--;
        }
    }

    public int getFileCount()
    {
        return this.getAllFiles().size();
    }

    public List<File> getAllFiles()
    {
        return this.files;
    }

    public List<File> getFiles()
    {
        int size = (this.skip + this.limit);
        if (size > this.getFileCount()) {
            size = this.getFileCount();
        }
        return this.getAllFiles().subList(this.skip, size);
    }

    public File getFile(int index)
    {
        return this.getAllFiles().get(index);
    }

    public File getFile(Cursor cursor)
    {
        return this.getFile(this.getFilePosition(cursor));
    }

    public int getFilePosition(Cursor cursor)
    {
        int position = this.skip + cursor.getRowPosition();
        return (position > 0)? position - cursor.getDefaultTopRow(): position;
    }

    public void back()
    {
        this.metaData.back();
        this.loadFiles();
    }

    public void setPath(String path)
    {
        this.metaData.setPath(path);
        this.loadFiles();
    }

    public MetaData getMetaData()
    {
        return this.metaData;
    }

    public void loadFiles()
    {
        this.files = this.metaData.getFiles();
    }

    public void showFiles()
    {
        this.metaData.showFiles();
        this.loadFiles();
    }

    public void showRoots()
    {
        this.metaData.showRoots();
        this.loadFiles();
    }

    public void pagingDown()
    {
        for(int i = 0; i < 26; i++)
        {
            this.increment();
        }
    }

    public void pagingUp()
    {
        for(int i = 0; i < 26; i++)
        {
            this.decrement();
        }
    }
}

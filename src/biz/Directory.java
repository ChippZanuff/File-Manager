package biz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Directory
{
    private final FilesRepository fileRepository;
    protected File directory;

    public Directory(FilesRepository fileRepository)
    {
        this.fileRepository = fileRepository;
    }

    public void forward(String directoryPath)
    {
        this.directory = new File(directoryPath);
    }

    public void back()
    {
        this.directory = new File(this.directory.getParent());
    }

    public ArrayList<File> getFiles()
    {
        return this.fileRepository.getFileList(this.directory.getPath());
    }

    public void execute(File file)
    {
        try
        {
            String cmd = file.getPath();
            if (! file.canExecute())
            {
                cmd = "start " + cmd;
            }
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public int getPathLength()
    {
        return this.directory.getPath().length();
    }

    public String getPath()
    {
        return this.directory.getPath();
    }
}

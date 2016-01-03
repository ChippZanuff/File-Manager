package biz;

import java.io.File;
import java.io.IOException;

public class FileOperations
{
    public void execute(File file)
    {
        try
        {
            String [] commands = {
                    "cmd.exe", "/c", "start", "\"", file.getName() ,"\"", "\"" + file.getPath() + "\""
            };
            Runtime.getRuntime().exec(commands);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public boolean delete(File currentFile)
    {
        if(currentFile.isDirectory())
        {
            File[] files = currentFile.listFiles();
            if(null!=files)
            {
                for(int i=0; i<files.length; i++)
                {
                    if(files[i].isDirectory())
                    {
                        deleteDirectories(files[i]);
                    }
                    else
                    {
                        if(files[i].delete())
                        {
                            System.out.println(files[i].getName() + "is deleted succecfully");
                        }
                    }
                }
            }
            if(currentFile.delete())
            {
                System.out.println(currentFile.getName() + " is deleted succecfully");
            }
        }
        if(currentFile.delete())
        {
            System.out.println(currentFile.getName() + "is deleted succecfully");
            return currentFile.delete();
        }
        return currentFile.delete();
    }

    private boolean deleteDirectories(File file)
    {
        if(file.isDirectory())
        {
            this.delete(file);
        }
        else if((file.delete()))
        {
            System.out.println(file.getName() + " is deleted!");
        }
        return file.delete();
    }
}

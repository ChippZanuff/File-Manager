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
                        this.delete(files[i]);
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
        }
        return currentFile.delete();
    }

    public void deleteSelectedFiles(SelectedFiles selectedFiles)
    {
        for(File selected : selectedFiles.getList())
        {
            if(selected.isDirectory())
            {
                File[] files = selected.listFiles();
                if(null!=files)
                {
                    for(int i=0; i<files.length; i++)
                    {
                        if(files[i].isDirectory())
                        {
                            this.delete(files[i]);
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
                if(selected.delete())
                {
                    
                }
            }
            else if(selected.delete())
            {

            }
        }

    }
}

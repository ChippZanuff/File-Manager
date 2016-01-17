package biz;

import ui.FolderCreationNotify;

import java.io.File;
import java.io.IOException;

public class FileOperations
{
    private MetaData metaData;
    private FolderCreationNotify folderCreationNotify;

    public FileOperations(MetaData metaData, FolderCreationNotify folderCreationNotify)
    {
        this.metaData = metaData;
        this.folderCreationNotify = folderCreationNotify;
    }

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

    public void folderCreation()
    {
        File file = new File(folderCreationNotify.getModifiedMeta());
        if (!file.exists())
        {
            if (file.mkdir())
            {
                System.out.println("Successfully created");
            } else
            {

            }
        }
    }
}

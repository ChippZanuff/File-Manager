package biz;

import ui.FolderCreationNotify;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

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
            this.deleteFiles(currentFile);
        }
        return currentFile.delete();
    }

    public void delete(SelectedFiles selectedFiles)
    {
        for(File selected : selectedFiles.getList())
        {
            if(! selected.isDirectory())
            {
                selected.delete();
                continue;
            }
            this.deleteFiles(selected);
        }

    }

    public void deleteFiles(File source)
    {
        File[] files = source.listFiles();
        if (null == files)
        {
            return;
        }

        for(File file : files)
        {
            if(file.isDirectory())
            {
                this.delete(file);
            }
            else if(file.delete())
            {
                System.out.println(file.getName() + "is deleted succecfully");
            }
        }
    }

    public boolean folderCreation()
    {
        File file = new File(folderCreationNotify.getModifiedMeta());
        return ! file.exists() && file.mkdir();
    }

    public void copyFiles(List<File> sourceList, File dest) throws IOException
    {
        for (File source : sourceList)
        {
            this.copyFile(source, dest);
        }
    }

    public void copyFile(File source, File dest) throws IOException
    {
        File newDest = new File(dest.getPath() + source.getName());
        Files.copy(source.toPath(), newDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}

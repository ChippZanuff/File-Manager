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
}

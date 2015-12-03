package biz;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;
import ui.Cursor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileOperations
{
    Terminal terminal;
    Screen screen;
    Cursor cursor;
    FileSkipTake fileSkipTake = new FileSkipTake();
    //PanelDraw panelDraw = new PanelDraw(this.screen);
    ArrayList<File> getRoot = new ArrayList<>();


    public FileOperations(Terminal terminal, Screen screen, Cursor cursor)
    {
        this.terminal = terminal;
        this.screen = screen;
        this.cursor = cursor;
    }

    public ArrayList<File> getRootDirectories(MetaData metaData)
    {
        return new ArrayList<File>(Arrays.asList(metaData.getRootDirectory()));
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

    public void drawRootDirectories()
    {
        int defaultColumnPosition = 1;
        int topRowDefaultPosition = 1;
        for (File directory : getRoot.subList(fileSkipTake.getSkip(), fileSkipTake.getTake()))
        {
            Terminal.Color backgroundColor = Terminal.Color.DEFAULT;
            Terminal.Color selectedDirectoryColor = Terminal.Color.BLUE;
            if(getRoot.get(cursor.getFilePosition(fileSkipTake)).getPath().equals(directory.getPath()))
            {
                backgroundColor = selectedDirectoryColor;
            }
            screen.putString(defaultColumnPosition, topRowDefaultPosition++, directory.toString(), Terminal.Color.WHITE, backgroundColor, ScreenCharacterStyle.Bold);
        }
    }
}

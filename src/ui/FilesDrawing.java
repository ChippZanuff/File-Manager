package ui;


import biz.FileSkipTake;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;
import java.util.ArrayList;

public class FilesDrawing
{
    Screen screen;
    Cursor cursor;
    public FilesDrawing(Screen screen, Cursor cursor)
    {
        this.screen = screen;
        this.cursor = cursor;
    }

    public void filesDrawing(ArrayList<File> files, FileSkipTake fileSkipTake)
    {
        int defaultColumnPosition = 1;
        int topRowDefaultPosition = 1;
        for (File item : files.subList(fileSkipTake.getSkip(), fileSkipTake.getTake()))
        {
            Terminal.Color itemColor =  Terminal.Color.CYAN;
            Terminal.Color selectedItemColor =  Terminal.Color.BLUE;
            Terminal.Color backgroundColor =  Terminal.Color.DEFAULT;
            if(item.isFile())
            {
                itemColor = Terminal.Color.WHITE;
            }
            if (files.get(cursor.getFilePosition(fileSkipTake)).getPath().equals(item.getPath()))
            {
                backgroundColor = selectedItemColor;
            }
            if(! item.getName().equals(""))
            {
                screen.putString(defaultColumnPosition, topRowDefaultPosition++, item.getName(), itemColor, backgroundColor, ScreenCharacterStyle.Bold);
            }
            else
            {
                screen.putString(defaultColumnPosition, topRowDefaultPosition++, item.getPath(), itemColor, backgroundColor, ScreenCharacterStyle.Bold);
            }
        }
    }
}

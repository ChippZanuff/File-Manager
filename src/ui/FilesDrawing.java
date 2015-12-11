package ui;


import biz.Directory;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;

public class FilesDrawing
{
    private Screen screen;
    private Cursor cursor;

    public FilesDrawing(Screen screen, Cursor cursor)
    {
        this.screen = screen;
        this.cursor = cursor;
    }

    public void filesDrawingBasic(File selectedFile, Directory directory, boolean checkSelected, int column)
    {
        int row = this.cursor.getDefaultTopRow();

        for (File item : directory.getFiles())
        {
            Terminal.Color backgroundColor = this.getBackgroundItemColor(selectedFile, item, checkSelected);

            this.screen.putString(column, row++, this.getFileName(item), this.getItemColor(item), backgroundColor, ScreenCharacterStyle.Bold);
        }

    }

    public void filesDrawingBasicLeft(File selectedFile, Directory directory, boolean checkSelected)
    {
        this.filesDrawingBasic(selectedFile, directory, checkSelected, this.cursor.getDefaultColumnPosition());
    }

    public void filesDrawingBasicRight(File selectedFile, Directory directory, boolean checkSelected)
    {
        int column = (screen.getTerminalSize().getColumns() / 2) + this.cursor.getDefaultColumnPosition();
        this.filesDrawingBasic(selectedFile, directory, checkSelected, column);
    }

    public Terminal.Color getBackgroundItemColor(File file, File selectedFile, boolean checkSelected)
    {
        if (checkSelected && file.getPath().equals(selectedFile.getPath()))
        {
            return Terminal.Color.BLUE;
        }
        return Terminal.Color.DEFAULT;
    }

    public Terminal.Color getItemColor(File file)
    {
        if(file.isFile())
        {
            return Terminal.Color.WHITE;
        }
        return Terminal.Color.CYAN;
    }

    public String getFileName(File file)
    {
        if(file.getName().equals(""))
        {
            return file.getPath();
        }
        return file.getName();
    }
}

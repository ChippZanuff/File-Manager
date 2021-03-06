package ui;


import biz.Directory;
import biz.SelectedFiles;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;

public class FilesDrawing
{
    private Screen screen;
    private Cursor cursor;
    private SelectedFiles selectedFiles;

    public FilesDrawing(Screen screen, Cursor cursor, SelectedFiles selectedFiles)
    {
        this.screen = screen;
        this.cursor = cursor;
        this.selectedFiles = selectedFiles;
    }

    public void filesDrawingBasic(File cursorFile, Directory directory, boolean checkSelected, int column)
    {
        int row = this.cursor.getDefaultTopRow();

        for (File item : directory.getFiles())
        {
            Terminal.Color selectedFile = this.getItemColor(item);
            for(File select : this.selectedFiles.getList())
            {
                if(item.getName().equals(select.getName()))
                {
                    selectedFile = Terminal.Color.YELLOW;
                    break;
                }
                else
                {
                    selectedFile = this.getItemColor(item);
                }
            }

            Terminal.Color backgroundColor = this.getBackgroundItemColor(cursorFile, item, checkSelected);
            this.screen.putString(column, row++, this.getFileName(item), selectedFile, backgroundColor, ScreenCharacterStyle.Bold);
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
        int sizeOfString = (this.screen.getTerminalSize().getColumns() / 2) - 2;
        if(file.getName().equals(""))
        {
            return file.getPath();
        }
        if(file.getName().length() > sizeOfString)
        {
            return file.getName().substring(0, sizeOfString) + "..";
        }

        int length = (sizeOfString < file.getName().length()) ? file.getName().length() : sizeOfString - file.getName().length();

        char[] ch = new char[length];
        String horizontal = String.valueOf(ch).replace('\u0000', ' ');
        return file.getName() + horizontal;
    }

}

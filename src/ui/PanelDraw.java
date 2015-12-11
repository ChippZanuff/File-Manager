package ui;

import biz.MetaData;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;

public class PanelDraw
{
    Screen screen;
    MetaData metaData;
    File file;
    int sizeOfString = 0;
    int row = 0;

    public PanelDraw(Screen screen)
    {
        this.screen = screen;
    }

    public PanelDraw(File file)
    {
        this.file = file;
    }

    public void setSizeOfString(int sizeOfString)
    {
        this.sizeOfString = sizeOfString;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    public void setMetaData(MetaData metaData)
    {
        this.metaData = metaData;
    }

    public void panDraw(MetaData metaData)
    {
        char[] ch = new char[screen.getTerminalSize().getColumns() - 2];
        String horizontal = this.replaceString(String.valueOf(ch) ,'\u0000', '═');

        this.displayLine(0, "╔" +  horizontal + "╗");

        for(int i = 1; i < screen.getTerminalSize().getRows() - 1; i++)
        {
            this.displayLine(0, i, "║");
            this.displayLine(screen.getTerminalSize().getColumns() / 2 - 1, i, "║");
            this.displayLine(99, i, "║");
            this.displayLine(49, 0, "╦");
            this.displayLine(49, 29, "╩");
        }

        this.displayLine(screen.getTerminalSize().getRows() - 1, "╚" + horizontal + "╝");

        screen.putString((screen.getTerminalSize().getColumns()  - sizeOfString) / 2 - 2, row, metaData.LocalFilePath(metaData.getPath()), Terminal.Color.GREEN, Terminal.Color.DEFAULT, ScreenCharacterStyle.Bold);
        //screen.putString();
    }

    public void displayLine(int column, int position, String text)
    {
        screen.putString(column, position, text, Terminal.Color.DEFAULT, Terminal.Color.DEFAULT, ScreenCharacterStyle.Bold);
    }

    public void displayLine(int position, String text)
    {
        this.displayLine(0, position, text);
    }

    public String replaceString(String str, char from, char to)
    {
        return str.replace(from, to);
    }
}

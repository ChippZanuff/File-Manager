package ui;

import biz.MetaData;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.File;

public class PanelDraw
{
    private Screen screen;
    private MetaData metaData;
    private File file;
    private int sizeOfString = 0;
    private int row = 0;
    private int column = 0;
    private int columnMargin = 2;
    private int rowsMargin = 2;

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
        char[] ch = new char[screen.getTerminalSize().getColumns() - this.columnMargin];
        String horizontal = this.replaceString(String.valueOf(ch) ,'\u0000', '═');
        int metaSize = metaData.LocalFilePath(metaData.getPath()).length();

        this.displayLine(0, "╔" +  horizontal + "╗");

        for(int i = 1; i < this.screen.getTerminalSize().getRows() - this.rowsMargin; i++)
        {
            this.displayLine(column, i, "║");
            this.displayLine(this.screen.getTerminalSize().getColumns() / 2 - 1, i, "║");
            this.displayLine(this.screen.getTerminalSize().getColumns() - 1, i, "║");
            this.displayLine(49, 0, "╦");
        }

        this.displayLine(this.screen.getTerminalSize().getRows() - this.rowsMargin, "╚" + horizontal + "╝");
        this.displayLine(this.screen.getTerminalSize().getColumns() / 2 - 1, this.screen.getTerminalSize().getRows() - this.rowsMargin, "╩");
        this.screen.putString((this.screen.getTerminalSize().getColumns()  - metaSize) / 2, row, metaData.LocalFilePath(metaData.getPath()), Terminal.Color.GREEN, Terminal.Color.DEFAULT, ScreenCharacterStyle.Bold);
    }

    public void displayLine(int column, int row, String text)
    {
        screen.putString(column, row, text, Terminal.Color.DEFAULT, Terminal.Color.DEFAULT, ScreenCharacterStyle.Bold);
    }

    public void displayLine(int row, String text)
    {
        this.displayLine(this.column, row, text);
    }

    private String replaceString(String str, char from, char to)
    {
        return str.replace(from, to);
    }


}

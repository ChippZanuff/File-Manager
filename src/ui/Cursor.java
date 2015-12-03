package ui;

import biz.FileSkipTake;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.TerminalPosition;

import java.io.File;
import java.util.ArrayList;

public class Cursor
{
    int filePosition;
    Screen screen;
    TerminalPosition tp;
    int defaultTopRow = 0;
    int defaultBottomRow = 0;

    public Cursor(Screen screen, int topRow, int bottomRow)
    {
        this.defaultTopRow = topRow;
        this.defaultBottomRow = bottomRow;
        this.screen = screen;
        this.tp = screen.getCursorPosition();
        this.tp.setRow(topRow);
    }

    public void moveUp()
    {
        this.tp.setRow(this.tp.getRow() - 1);
    }

    public boolean isTop()
    {
        return tp.getRow() == this.defaultTopRow;
    }

    public void moveDown()
    {
        tp.setRow(tp.getRow() + 1);
    }

    public boolean isBottom()
    {
        return tp.getRow() == screen.getTerminalSize().getRows() - this.defaultBottomRow;
    }

    public int getRowPosition()
    {
        return this.tp.getRow();
    }

    public void resetRowPosition()
    {
        tp.setRow(this.defaultTopRow);
    }

    public int getDefaultTopRow()
    {
        return this.defaultTopRow;
    }

    public void moveDown(ArrayList<File> files, FileSkipTake fileSkipTake)
    {
        boolean isChild = (files.get(0) != null) && files.get(0).getName().equals("..");
        int fileSize = (isChild)?  files.size(): files.size();

        if(this.getRowPosition() < fileSize && !this.isBottom())
        {
            this.moveDown();
        }
        else if (fileSkipTake.getTake() < files.size())
        {
            fileSkipTake.increment();
        }
    }

    public void moveUp(FileSkipTake fileSkipTake)
    {
        if (!this.isTop())
        {
            this.moveUp();
        }
        else if (fileSkipTake.getSkip() > 0)
        {
            fileSkipTake.decrement();
        }
    }

    public int getFilePosition(FileSkipTake fileSkipTake)
    {
        int position = (this.getRowPosition() + fileSkipTake.getSkip());
        return this.filePosition = (position > 0)?position - this.getDefaultTopRow(): position;
    }
}
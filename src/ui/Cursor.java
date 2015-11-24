package ui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.TerminalPosition;

public class Cursor
{
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


    public Cursor(Screen screen, int topRow)
    {
        this.defaultTopRow = topRow;
        this.screen = screen;
        this.tp = screen.getCursorPosition();
        this.tp.setRow(topRow);
    }

    public Cursor(Screen screen)
    {
        this.screen = screen;
        this.tp = screen.getCursorPosition();
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

    public void setRowPosition(int position)
    {
        this.tp.setRow(position);
    }

    public void resetRowPosition()
    {
        tp.setRow(this.defaultTopRow);
    }
    public int getDefaultTopRow()
    {
        return this.defaultTopRow;

    }
}
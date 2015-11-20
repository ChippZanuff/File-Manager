package ui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.TerminalPosition;

public class Cursor
{
    Screen screen;
    TerminalPosition tp;

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
        return tp.getRow() == 0;
    }

    public void moveDown()
    {
        tp.setRow(tp.getRow() + 1);
    }

    public boolean isBottom()
    {
        return tp.getRow() == screen.getTerminalSize().getRows() - 1;
    }

    public int getRowPosition()
    {
        return this.tp.getRow();
    }

    public void setRowPosition(int position)
    {
        this.tp.setRow(position);
    }
}
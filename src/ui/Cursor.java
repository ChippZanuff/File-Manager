package ui;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.TerminalPosition;

public class Cursor
{
    private Screen screen;
    private TerminalPosition tp;
    private int defaultTopRow = 0;
    private int defaultBottomRow = 0;
    private int horizontalPosition = 0;
    private int defaultColumnPosition = 0;

    public Cursor(Screen screen, int topRow, int bottomRow, int columnPosition)
    {
        this.defaultTopRow = topRow;
        this.defaultBottomRow = bottomRow;
        this.screen = screen;
        this.tp = screen.getCursorPosition();
        this.tp.setRow(topRow);
        this.tp.setColumn(columnPosition);
        this.defaultColumnPosition = columnPosition;
        this.horizontalPosition = columnPosition;
    }

    public void moveUp()
    {
        if (this.tp.getRow() == this.defaultTopRow)
        {
            return;
        }
        this.tp.setRow(this.tp.getRow() - 1);
    }

    public boolean isTop()
    {
        return tp.getRow() == this.defaultTopRow;
    }

    public void moveDown()
    {
        if(!this.isBottom())
        {
            tp.setRow(tp.getRow() + 1);
        }
    }

    public boolean isBottom()
    {
        return this.getRowPosition() == screen.getTerminalSize().getRows() - this.defaultBottomRow;
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

    public void changeColumn()
    {
        if(! this.isPositionLeft())
        {
            this.horizontalPosition = this.defaultColumnPosition;
        }
        else
        {
            this.horizontalPosition = (screen.getTerminalSize().getColumns() / 2) + this.defaultColumnPosition;
        }

        this.tp.setColumn(this.horizontalPosition);
    }

    public int getDefaultColumnPosition()
    {
        return this.defaultColumnPosition;
    }

    public int getColumn()
    {
        return this.tp.getColumn();
    }

    public boolean isPositionLeft()
    {
        return this.horizontalPosition == this.defaultColumnPosition;
    }

    public boolean isPositionRight()
    {
        return ! this.isPositionLeft();
    }
}
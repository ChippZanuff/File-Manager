package biz;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import ui.Cursor;
import ui.FilesDrawing;
import ui.PanelDraw;

import java.io.File;

public class StringLlist
{
    Directory directoryLeft;
    Directory directoryRight;
    Screen screen;
    Terminal terminal;

    public StringLlist(Directory directoryLeft, Directory directoryRight,Screen screen, Terminal terminal)
    {
        this.directoryLeft = directoryLeft;
        this.directoryRight = directoryRight;
        this.screen = screen;
        this.terminal = terminal;
    }

    public void terminal()
    {
        int topRowPosition = 1;
        int bottomRowPosition = 3;
        int marginColumnPosition = 1;

        Key key = null;

        Cursor cursor = new Cursor(screen,topRowPosition, bottomRowPosition, marginColumnPosition);
        PanelDraw panelDraw = new PanelDraw(screen);
        FilesDrawing filesDrawing = new FilesDrawing(screen, cursor);
        FileOperations fileOperations = new FileOperations();

        Directory directory = this.directoryLeft;

        while (key == null || key.getCharacter() != 'Y')
        {
            if (key != null)
            {
                System.out.println("File pos:" + directory.getFilePosition(cursor));
                System.out.println("File row pos:" + cursor.getRowPosition());
                System.out.println(key.getKind().name());

                File rootDirectory = directory.getFile(0);
                File currentFile = directory.getFile(cursor);

                boolean isChild = (rootDirectory != null) && rootDirectory.getName().equals("..");

                switch (key.getKind())
                {
                    case ArrowDown:
                        if(cursor.isBottom())
                        {
                            directory.increment();
                        }
                        else if(directory.getFileCount() > cursor.getRowPosition())
                        {
                            cursor.moveDown();
                        }
                        break;
                    case ArrowUp:
                        if(cursor.isTop())
                        {
                            directory.decrement();
                        }
                        cursor.moveUp();
                        break;
                    case Enter:
                        if (cursor.getRowPosition() == topRowPosition && isChild)
                        {
                            directory.back();
                        } else if (currentFile.isDirectory())
                        {
                            directory.setPath(currentFile.getPath());
                        } else if (currentFile.isFile())
                        {
                            fileOperations.execute(currentFile);
                            break;
                        }
                        directory.showFiles();
                        cursor.resetRowPosition();
                        panelDraw.setSizeOfString(directory.getMetaData().getSizeOfString());
                        this.screen.clear();
                        break;
                    case F1:

                        directory.showRoots();
                        cursor.resetRowPosition();
                        this.screen.clear();
                        break;
                    case Tab:

                        cursor.changeColumn();

                        if (cursor.isPositionLeft())
                        {
                            directory = this.directoryLeft;
                        }
                        else if(cursor.isPositionRight())
                        {
                            directory = this.directoryRight;
                        }

                        cursor.resetRowPosition();

                        break;
                    case Delete:
                        fileOperations.delete(currentFile);
                }
                currentFile = directory.getFile(cursor);

                if(directory == this.directoryLeft)
                {
                    filesDrawing.filesDrawingBasicRight(currentFile, this.directoryRight, false);
                }
                else
                {
                    filesDrawing.filesDrawingBasicLeft(currentFile, this.directoryLeft, false);
                }

                filesDrawing.filesDrawingBasic(currentFile, directory, true, cursor.getColumn());

                panelDraw.panDraw(directory.getMetaData());
                screen.refresh();
                screen.getTerminal().setCursorVisible(false);
            }
            key = terminal.readInput();
        }
        screen.stopScreen();
    }
}

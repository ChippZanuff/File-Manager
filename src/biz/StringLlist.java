package biz;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import ui.*;

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
        int bottomRowPosition = 2;
        int marginColumnPosition = 1;

        Key key = null;

        Cursor cursor = new Cursor(screen,topRowPosition, bottomRowPosition, marginColumnPosition);
        PanelDraw panelDraw = new PanelDraw(screen);
        FilesDrawing filesDrawing = new FilesDrawing(screen, cursor);
        FileOperations fileOperations = new FileOperations();
        Bar bar = new Bar(screen);
        Notifications notifications = new Notifications(screen);

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
                        break;
                    case F1:

                        directory.showRoots();
                        cursor.resetRowPosition();
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

                this.screen.clear();
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

                bar.barDrawing();

                this.screen.refresh();
                this.screen.getTerminal().setCursorVisible(false);
            }
            key = terminal.readInput();
        }
        screen.stopScreen();
    }
}

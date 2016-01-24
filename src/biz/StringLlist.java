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
    FolderCreationNotify folderCreationNotify;
    FileOperations fileOperations;

    public StringLlist(Directory directoryLeft, Directory directoryRight,Screen screen, Terminal terminal, FolderCreationNotify folderCreationNotify, FileOperations fileOperations)
    {
        this.directoryLeft = directoryLeft;
        this.directoryRight = directoryRight;
        this.screen = screen;
        this.terminal = terminal;
        this.folderCreationNotify = folderCreationNotify;
        this.fileOperations = fileOperations;
    }

    public void terminal()
    {
        int topRowPosition = 1;
        int bottomRowPosition = 3;
        int marginColumnPosition = 1;
        int colForNotifications = ((screen.getTerminalSize().getRows() - 8) / 2) - 1;
        int rowForNotifications = ((screen.getTerminalSize().getColumns() - 30) / 2) - 1;

        boolean altIsPressed = false;
        boolean ctrlIsPressed = false;
        boolean notificationYesNo = false;
        boolean notificationExit = false;
        Key key = null;

        SelectedFiles selectedFiles = new SelectedFiles();
        Cursor cursor = new Cursor(screen,topRowPosition, bottomRowPosition, marginColumnPosition);
        PanelDraw panelDraw = new PanelDraw(screen);

        BarDrawer barDrawer = new BarDrawer(screen);
        Notifications notifications = new Notifications(screen, colForNotifications, rowForNotifications, folderCreationNotify);
        FilesDrawing filesDrawing = new FilesDrawing(screen, cursor, selectedFiles);
        Input input = new Input();

        Directory directory = this.directoryLeft;

        while (key == null || key.getCharacter() != 'Y')
        {
            if (key != null)
            {
                System.out.println("File pos:" + directory.getFilePosition(cursor));
                System.out.println("File row pos:" + cursor.getRowPosition());
                System.out.println("File col pos:" + cursor.getColumn());
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
                            this.fileOperations.execute(currentFile);
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
                        while (!notificationExit)
                        {
                            if (key != null)
                            {
                                switch (key.getKind())
                                {
                                    case ArrowLeft:
                                        notificationYesNo = true;
                                        break;
                                    case ArrowRight:
                                        notificationYesNo = false;
                                        break;
                                    case Enter:
                                        if(notificationYesNo)
                                        {
                                            if(selectedFiles.filesCheck())
                                            {
                                                fileOperations.deleteSelectedFiles(selectedFiles);
                                                directory.loadFiles();
                                                selectedFiles.clearSelectedFiles();
                                                notificationExit = true;
                                                cursor.moveUp();
                                            }
                                            else
                                            {
                                                fileOperations.delete(currentFile);
                                                directory.loadFiles();
                                                notificationExit = true;
                                                cursor.moveUp();
                                            }
                                        }
                                        else
                                        {
                                            notificationExit = true;
                                        }
                                        break;
                                }
                                notifications.deletion(notificationYesNo);

                                this.screen.refresh();
                                this.screen.getTerminal().setCursorVisible(false);
                            }
                            key = terminal.readInput();
                        }
                        screen.clear();
                        break;
                    case Insert:
                        selectedFiles.addSelectedFileInList(currentFile);
                        if(cursor.isBottom())
                        {
                            directory.increment();
                        }
                        else if(directory.getFileCount() > cursor.getRowPosition())
                        {
                            cursor.moveDown();
                        }
                        break;
                    case PageDown:
                        directory.paginationDown();
                        break;
                    case PageUp:
                        directory.paginationUp();
                        break;
                    case F7:
                        //folderCreationNotify.getModifiedMeta();
                        while (!notificationExit)
                        {
                            if (key != null)
                            {
                                switch (key.getKind())
                                {
                                    case ArrowLeft:
                                        notificationYesNo = true;
                                        break;
                                    case ArrowRight:
                                        notificationYesNo = false;
                                        break;
                                    case Backspace:
                                        folderCreationNotify.backSpace();
                                        break;
                                    case NormalKey:
                                        input.setChar(key.getCharacter());
                                        folderCreationNotify.inputString(input);
                                        break;
                                    case Enter:
                                        if(notificationYesNo)
                                        {
                                            fileOperations.folderCreation();
                                            directory.loadFiles();
                                            notificationExit = true;
                                        }
                                        else
                                        {
                                            notificationExit = true;
                                        }
                                        break;
                                }
                                notifications.makeFolder(notificationYesNo);

                                this.screen.refresh();
                                this.screen.getTerminal().setCursorVisible(false);
                            }
                            key = terminal.readInput();
                        }
                        screen.clear();
                        break;
                }

                if(altIsPressed)
                {
                    barDrawer.barDrawing(new AltBar());
                }
                else if(ctrlIsPressed)
                {
                    barDrawer.barDrawing(new CtrlBar());
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

                barDrawer.barDrawing(new DefaultBar());

                this.screen.refresh();
                notificationExit = false;
                this.screen.getTerminal().setCursorVisible(false);
            }
            key = terminal.readInput();
        }
        screen.stopScreen();
    }
}

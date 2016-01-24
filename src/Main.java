import biz.Directory;
import biz.FileOperations;
import biz.MetaData;
import biz.StringLlist;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import ui.FileCopy;
import ui.FolderCreationNotify;

public class Main
{
    public static void main(String[] args)
    {
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out);
        Screen screen = new Screen(terminal);
        MetaData metaData = new MetaData("E:\\");
        Directory directoryLeft = new Directory(new MetaData("E:\\"), screen.getTerminalSize().getRows() - 3);
        Directory directoryRight = new Directory(new MetaData("D:\\"), screen.getTerminalSize().getRows() - 3);
        FolderCreationNotify folderCreationNotify = new FolderCreationNotify(metaData);
        FileCopy fileCopy = new FileCopy(metaData);
        FileOperations fileOperations = new FileOperations(metaData, folderCreationNotify);
        screen.startScreen();
        StringLlist str = new StringLlist(directoryLeft, directoryRight, screen, terminal, folderCreationNotify, fileOperations, fileCopy);

        str.terminal();
    }
}

import biz.Directory;
import biz.MetaData;
import biz.StringLlist;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;

public class Main
{
    public static void main(String[] args)
    {
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out);
        Screen screen = new Screen(terminal);
        Directory directoryLeft = new Directory(new MetaData("E:\\"), screen.getTerminalSize().getRows());
        Directory directoryRight = new Directory(new MetaData("D:\\"), screen.getTerminalSize().getRows());
        screen.startScreen();
        StringLlist str = new StringLlist(directoryLeft, directoryRight, screen, terminal);

        //OldDirectory dir = new OldDirectory(new FilesRepository());
        //dir.forward("E:\\");
        //new FileManager().terminal(dir);

        str.terminal();
    }
}

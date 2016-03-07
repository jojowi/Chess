package eu.assault2142.hololol.chess.client.game;

import com.alee.laf.WebLookAndFeel;
import eu.assault2142.hololol.chess.client.menus.MainMenu;
import eu.assault2142.hololol.chess.client.translator.Translator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Locale;

/**
 *
 * @author hololol2
 */
public class Main {

    public static void main(String[] Args) throws URISyntaxException, FileNotFoundException, IOException, ClassNotFoundException {
        Settings.init("");
        //Öffnen des Hauptmenüs
        Translator.setLanguage(Locale.getDefault());
        WebLookAndFeel.install();
        new MainMenu().setVisible(true);
    }
}

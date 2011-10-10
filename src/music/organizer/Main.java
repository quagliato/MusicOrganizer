package music.organizer;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import music.organizer.control.MusicOrganizerControl;
import javax.swing.UIManager;

/**
 *
 * @author quagliato
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception ex) {
            System.out.println(ex);
        }

        new MusicOrganizerControl().show();
    }
}


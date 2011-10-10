package music.organizer.threads;

import music.organizer.view.MusicOrganizerForm;

/**
 *
 * @author quagliato
 */
public class ChangeConsoleThread extends Thread {

    private String message;
    private MusicOrganizerForm form;

    public ChangeConsoleThread(String message, MusicOrganizerForm form) {
        this.message = message;
        this.form = form;
    }

    @Override
    public void run() {
        form.changeConsole(message);
    }
}

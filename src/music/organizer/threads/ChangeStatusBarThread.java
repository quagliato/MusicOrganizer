package music.organizer.threads;

import music.organizer.view.MusicOrganizerForm;

/**
 *
 * @author quagliato
 */
public class ChangeStatusBarThread extends Thread {

    private String message;
    private String position;
    private int vlProgressBarSize;
    private int vlProgressBarTotalSize;
    private MusicOrganizerForm form;

    public ChangeStatusBarThread(String message, String position, int vlProgressBarSize, int vlProgressBarTotalSize, MusicOrganizerForm form) {
        this.message = message;
        this.position = position;
        this.vlProgressBarSize = vlProgressBarSize;
        this.vlProgressBarTotalSize = vlProgressBarTotalSize;
        this.form = form;
    }

    @Override
    public void run() {
        form.changeStatusBar(message, vlProgressBarSize, vlProgressBarTotalSize);
    }
}

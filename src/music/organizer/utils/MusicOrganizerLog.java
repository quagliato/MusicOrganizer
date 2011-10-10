package music.organizer.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;

/**
 *
 * @author quagliato
 */
public class MusicOrganizerLog {

    private StringBuffer log;

    private File outputDirectory;

    private static MusicOrganizerLog musicOrganizerLog;

    public static MusicOrganizerLog getInstance() {
        if (musicOrganizerLog == null) {
            musicOrganizerLog = new MusicOrganizerLog();
        }
        return musicOrganizerLog;
    }

    private MusicOrganizerLog() {
        log = new StringBuffer("");
    }

    public void setOutputDirectory(File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public void addToLog(String message) {
        message = message + "\n";
        this.log.append(message);
    }

    public String saveLog() {
        try {
            Date dt = new Date();
            String path = outputDirectory.getAbsolutePath()
                          + File.separator
                          + "error_log_"
                          + dt.getTime()
                          + ".log";
            FileWriter fileWriter = new FileWriter(path, true);
            fileWriter.write(log.toString());
            fileWriter.close();
            return path;
        } catch (Exception e) {
            return null;
        }
    }
}

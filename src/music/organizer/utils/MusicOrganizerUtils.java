package music.organizer.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author quagliato
 */
public class MusicOrganizerUtils {

    public static String capitalize(String oldone) {

        boolean flag = false;
        int chr = 0;
        String newone = new String();

        oldone = removeControls(oldone);

        for (int i = 0; i < oldone.length(); i++) {

            chr = oldone.charAt(i);

            if ((flag && !(chr >= 65 && chr <= 90)) || i == 0) {
                newone = newone + (oldone.toUpperCase().charAt(i));
            } else {
                newone = newone + oldone.charAt(i);
            }

            flag = false;

            if (chr == 32) {
                flag = true;
            }
        }

        return newone;

    }

    public static String removeControls(String oldone) {

        int chr = 0;
        String newone = new String();

        for (int i = 0; i < oldone.length(); i++) {

            chr = oldone.charAt(i);

            if ((chr >= 32 && chr <= 126)
                    || (chr >= 128 && chr <= 144)
                    || (chr >= 147 && chr <= 154)
                    || (chr >= 160 && chr <= 165)
                    || (chr >= 181 && chr <= 183)
                    || chr == 198
                    || chr == 199
                    || (chr >= 210 && chr <= 216)
                    || (chr >= 226 && chr <= 229)
                    || (chr >= 233 && chr <= 237)) {
                if (chr == 47) {
                    newone = newone + ' ';
                } else {
                    newone = newone + oldone.charAt(i);
                }
            }
        }

        return newone;
    }

    public static String fixTracknumber(String oldone) {

        int chr = 0;
        String newone = new String();

        for (int i = 0; i < oldone.length(); i++) {

            chr = oldone.charAt(i);

            if ((chr >= 49 && chr <= 57)) {
                newone = newone + oldone.charAt(i);
            } else {
                if (newone.length() < 2) {
                    newone = "0" + newone;
                }

                return newone;
            }
        }

        if (newone.length() < 2) {
            newone = "0" + newone;
        }

        return newone;
    }

    /*
     * 
     * @param String path 
     * 
     */
    public static boolean validatePath(String path, int type) {

        MusicOrganizerUtils.slashLess(path);

        File directory = new File(path);
        boolean flag = false;

        if (type == 0) {
            if (directory.exists()) {
                while (!flag) {
                    File inputFiles[] = directory.listFiles();
                    if (inputFiles.length != 0) {
                        for (int i = 0; i < inputFiles.length; i++) {
                            if (inputFiles[i].isDirectory()) {
                                return MusicOrganizerUtils.validatePath(inputFiles[i].getAbsolutePath(), type);
                            } else {
                                flag =  MusicOrganizerUtils.validateMp3(inputFiles[i].getName());
                            }
                        }
                    }
                }
                return flag;
            }
        } else {
            return directory.exists();
        }

        return false;
    }

    public static String slashLess(String path) {

        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
            return path;
        }

        return path;
    }

    public static String doLine(boolean logOrView, int type) {

        if (logOrView) {
            return "------------------------------------------------------------------------------";
        } else if (!logOrView && type == 0) {
            return "------------------------------------------------------------------------------";
        } else if (!logOrView && type == 1) {
            return "------------------------------------------------------------------------------\n\n";
        }

        return null;
    }

    public static boolean validateMp3(String filename) {

        if (filename.endsWith(".mp3")
                || filename.endsWith(".MP3")
                || filename.endsWith(".mP3")
                || filename.endsWith(".Mp3")) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean validateTags(String artist, String album, String tracknumber, String songname) {

        if (artist != null && album != null && tracknumber != null && songname != null
                && !"".equals(artist) && !"".equals(album) && !"".equals(tracknumber) && !"".equals(songname)) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean saveConsole(File outputFile, String consoleMessage) {

        String path = outputFile.getAbsolutePath() + ".txt";

        try {
            FileWriter fileWriter = new FileWriter(path, true);
            fileWriter.write(consoleMessage);
            fileWriter.close();
            return true;

        } catch (IOException ex) {
            return false;
        }

    }

    public static int showMessage(String message, boolean confirm) {

        if (!confirm) {
            JOptionPane.showMessageDialog(null,
                    message,
                    "MusicOrganizer - ATENÇÃO!!",
                    JOptionPane.INFORMATION_MESSAGE);
            return 99;

        } else {
            Object[] options = {"Sim", "Não"};
            int option = JOptionPane.showOptionDialog(null,
                    message,
                    "MusicOrganizer - ATENÇÃO!!",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options, options[1]);
            return option;
        }
    }

    public static String createErrorMessage(boolean logOrView, String filename, int reason, String position) {

        String error = null;
        String message = null;

        if (reason == 0) {
            message = "O ARQUIVO NÃO POSSUI TAGS";
        } else if (reason == 1) {
            message = "PROBLEMA NA LEITURA DAS TAGS";
        } else if (reason == 2) {
            message = "PROBLEMA NA CRIAÇÃO DO NOVO ARQUIVO";
        } else if (reason == 3) {
            message = "PROBLEMA NA TRANSFERÊNCIA DO ARQUIVO";
        } else if (reason == 4) {
            message = "PROBLEMA NA EXCLUSÃO DO ARQUIVO ORIGINAL";
        } else if (reason == 5) {
            message = "O ARQUIVO NÃO POSSUI TODAS AS TAGS NECESSARIAS";
        }

        if (logOrView) {
            error = "Posição:    " + position + "\n"
                    + "Arquivo:    " + filename + "\n"
                    + "Hora:       " + new Date().toString() + "\n"
                    + "Motivo:     " + message + "\n"
                    + MusicOrganizerUtils.doLine(true, 0);
        } else {
            error = "<b><font color='#FF0000'>Hora Fim:      " + new Date().toString() + "\n"
                    + MusicOrganizerUtils.doLine(false, 0) + "\n"
                    + "ERRO NA TRANSFERÊNCIA DO ARQUIVO!" + "\n"
                    + MusicOrganizerUtils.doLine(false, 1) + "</font></b>";
        }

        return error;
    }
}

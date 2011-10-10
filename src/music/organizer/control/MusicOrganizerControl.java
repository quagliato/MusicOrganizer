package music.organizer.control;

import music.organizer.utils.MusicOrganizerUtils;
import music.organizer.utils.MusicOrganizerLog;
import music.organizer.view.MusicOrganizerForm;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import music.organizer.threads.GenerateNewOrganizedFilesThread;
import org.farng.mp3.TagException;

/**
 *
 * @author quagliato
 */
public class MusicOrganizerControl {

    private File inputDirectory;

    private File outputDirectory;

    private File inputFiles[] = null;

    private MusicOrganizerForm form;

    private GenerateNewOrganizedFilesThread thread;

    public MusicOrganizerControl() {
        form = new MusicOrganizerForm(this);
    }

    public void show() {
        form.setVisible(true);
    }

    public String searchDirectory(int type) {
        boolean flag = false;
        File lastDirectory = null;
        JFileChooser fileChooser = new JFileChooser(lastDirectory);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        while (!flag) {
            fileChooser.setSelectedFile(lastDirectory);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                if (type == 0) {
                    inputDirectory = fileChooser.getSelectedFile();
                    if (MusicOrganizerUtils.validatePath(inputDirectory.getAbsolutePath(), type)) {
                        flag = true;
                        return inputDirectory.getAbsolutePath();
                    } else {
                        MusicOrganizerUtils.showMessage("O diretório de entrada escolhido não possui arquivos mp3, não existe ou não possui arquivos de qualquer tipo.\n"
                                                        + "Por favor, escolha outro diretório.", false);
                        lastDirectory = fileChooser.getSelectedFile();
                    }
                } else if (type == 1) {
                    outputDirectory = fileChooser.getSelectedFile();
                    if (MusicOrganizerUtils.validatePath(outputDirectory.getAbsolutePath(), type)) {
                        flag = true;
                        return outputDirectory.getAbsolutePath();
                    } else {
                        MusicOrganizerUtils.showMessage("O diretório de saída escolhido não existe.\n"
                                                        + "Por favor, escolha outro diretório.", false);
                    }
                }
            } else if (result == JFileChooser.CANCEL_OPTION) {
                flag = true;
            }
        }
        return null;
    }

    public void generateNewOrganizedFiles(boolean changeName, boolean deleteOriginal, boolean separateByAlbum) throws IOException, TagException, Exception {
        thread = new GenerateNewOrganizedFilesThread(changeName, deleteOriginal, separateByAlbum, form, inputDirectory, outputDirectory, inputFiles);
        thread.start();
    }

    public void dieThreadDie() {
        thread.dieThreadDie();
    }
}

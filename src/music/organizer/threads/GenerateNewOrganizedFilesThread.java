package music.organizer.threads;

import ccl.util.FileUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import music.organizer.utils.MusicOrganizerLog;
import music.organizer.utils.MusicOrganizerUtils;
import music.organizer.view.MusicOrganizerForm;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;

/**
 *
 * @author quagliato
 */
public class GenerateNewOrganizedFilesThread extends Thread {

    private File inputDirectory;
    private File previousInputDirectory;
    private File outputDirectory;
    private File inputFiles[] = null;
    private MusicOrganizerForm form;
    private boolean changeName;
    private boolean deleteOriginal;
    private boolean separateByAlbum;
    private int i;
    private boolean alive = true;

    public GenerateNewOrganizedFilesThread(boolean changeName,
            boolean deleteOriginal,
            boolean separateByAlbum,
            MusicOrganizerForm form,
            File inputDirectory,
            File outputDirectory,
            File inputFiles[]) {
        this.changeName = changeName;
        this.deleteOriginal = deleteOriginal;
        this.separateByAlbum = separateByAlbum;
        this.form = form;
        this.inputDirectory = inputDirectory;
        this.outputDirectory = outputDirectory;
        this.inputFiles = inputFiles;
    }

    @Override
    public void run() {
        try {
            generate();
        } catch (IOException ex) {
            Logger.getLogger(GenerateNewOrganizedFilesThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TagException ex) {
            Logger.getLogger(GenerateNewOrganizedFilesThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GenerateNewOrganizedFilesThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dieThreadDie() {
        alive = false;
    }

    public void generate() throws IOException, TagException, Exception {
        if (!MusicOrganizerUtils.validatePath(form.getPath(0), 0)) {
            throw new Exception("O diretório de entrada escolhido não existe, não possui arquivos mp3\n"
                    + "ou não possui arquivos de qualquer tipo.\n"
                    + "Por favor, escolha outro diretório.");
        } else if (!MusicOrganizerUtils.validatePath(form.getPath(1), 1)) {
            throw new Exception("O diretório de saída escolhido não existe.\n"
                    + "Por favor, escolha outro diretório.");
        } else {
            MusicOrganizerLog log = MusicOrganizerLog.getInstance();
            log.addToLog("Início do processo:       " + new Date().toString());
            log.addToLog(MusicOrganizerUtils.doLine(true, 0));
            log.addToLog("");

            String artist = null;
            String album = null;
            String tracknumber = null;
            String songname = null;

            inputDirectory = new File(MusicOrganizerUtils.slashLess(form.getPath(0)));
            outputDirectory = new File(MusicOrganizerUtils.slashLess(form.getPath(1)));
            log.setOutputDirectory(outputDirectory);

            log.addToLog("Diretório de entrada:     " + inputDirectory.getAbsolutePath() + "");
            log.addToLog("Diretório de saída:       " + outputDirectory.getAbsolutePath() + "");
            log.addToLog("");
            log.addToLog(MusicOrganizerUtils.doLine(true, 0));
            log.addToLog("--> O PROCESSO RESULTOU NOS SEGUINTES ERROS: ");
            log.addToLog("");

            inputFiles = inputDirectory.listFiles();

            int totalSize = inputFiles.length;
            int size = 1;

            boolean flag = false;
            boolean error = false;
            boolean createLog = false;
            boolean inside = false;
            String filename = null;
            String position = null;

            for (i = 0; i < inputFiles.length; i++) {
                if (!alive) {
                    break;
                }
                if (inputFiles[i].isDirectory()) {
                    previousInputDirectory = inputFiles[i];
                    inputDirectory = inputFiles[i];
                    inputFiles = inputDirectory.listFiles();
                    inside = true;
                }
                if (inputFiles[i].isFile() && MusicOrganizerUtils.validateMp3(inputFiles[i].getName())) {
                    filename = inputFiles[i].getName();
                    position = size + "/" + totalSize;
                    MP3File mp3File = new MP3File(inputFiles[i]);
                    new ChangeConsoleThread(MusicOrganizerUtils.doLine(false, 0) + "\n"
                            + "Arquivo atual: " + filename + "\n"
                            + MusicOrganizerUtils.doLine(false, 0) + "\n"
                            + "Hora Início:   " + new Date().toString(), form).start();

                    flag = false;

                    if (mp3File.hasID3v2Tag()) {
                        new ChangeStatusBarThread("Lendo tags do arquivo...", position, size, totalSize, form).start();
                        try {
                            artist = mp3File.getID3v2Tag().getLeadArtist();
                            album = mp3File.getID3v2Tag().getAlbumTitle();
                            tracknumber = mp3File.getID3v2Tag().getTrackNumberOnAlbum();
                            songname = mp3File.getID3v2Tag().getSongTitle();

                            flag = true;

                        } catch (Exception ex) {
                            new ChangeConsoleThread(MusicOrganizerUtils.createErrorMessage(false, filename, 6, position), form);
                            new ChangeStatusBarThread("Erro na transferência do arquivo...", position, size, totalSize, form).start();
                            log.addToLog(MusicOrganizerUtils.createErrorMessage(true, filename, 1, position));
                            createLog = true;
                            error = true;
                        }
                    } else if (mp3File.hasID3v1Tag()) {
                        new ChangeStatusBarThread("Lendo tags do arquivo...", position, size, totalSize, form).start();
                        try {
                            artist = mp3File.getID3v1Tag().getArtist();
                            album = mp3File.getID3v1Tag().getAlbumTitle();
                            tracknumber = mp3File.getID3v1Tag().getTrackNumberOnAlbum();
                            songname = mp3File.getID3v1Tag().getSongTitle();

                            flag = true;

                        } catch (Exception ex) {
                            new ChangeConsoleThread(MusicOrganizerUtils.createErrorMessage(false, filename, 6, position), form);
                            new ChangeStatusBarThread("Erro na transferência do arquivo..", position, size, totalSize, form).start();
                            log.addToLog(MusicOrganizerUtils.createErrorMessage(true, filename, 1, position));
                            createLog = true;
                            error = true;
                        }
                    }

                    if (flag) {

                        artist = MusicOrganizerUtils.capitalize(artist);

                        album = MusicOrganizerUtils.capitalize(album);

                        tracknumber = MusicOrganizerUtils.fixTracknumber(tracknumber);

                        songname = MusicOrganizerUtils.capitalize(songname);

                        if (MusicOrganizerUtils.validateTags(artist, album, tracknumber, songname)) {
                            File outputPath;
                            if (separateByAlbum) {
                                new ChangeStatusBarThread("Gerando pastas de saída...", position, size, totalSize, form).start();
                                outputPath = new File(outputDirectory.getAbsolutePath() + File.separator + artist);
                                if (!outputPath.exists()) {
                                    outputPath.mkdir();
                                }
                                outputPath = new File(outputPath.getAbsolutePath() + File.separator + album);
                                if (!outputPath.exists()) {
                                    outputPath.mkdir();
                                }
                            } else {
                                new ChangeStatusBarThread("Gerando pastas de saída...", position, size, totalSize, form).start();
                                outputPath = new File(outputDirectory.getAbsolutePath() + File.separator + artist);
                                if (!outputPath.exists()) {
                                    outputPath.mkdir();
                                }
                            }

                            File newMp3File;
                            if (changeName) {
                                new ChangeStatusBarThread("Renomeando arquivo...", position, size, totalSize, form).start();
                                if (separateByAlbum) {
                                    newMp3File = new File(outputPath.getPath() + File.separator + tracknumber + " " + songname + ".mp3");
                                } else {
                                    newMp3File = new File(outputPath.getPath() + File.separator + artist + " - " + album + " - " + tracknumber + " - " + songname + ".mp3");
                                }
                            } else {
                                newMp3File = new File(outputPath.getPath() + File.separator + filename);
                            }

                            try {
                                if (newMp3File.exists()) {
                                    newMp3File = new File(newMp3File.getAbsolutePath().substring(0, new Long(newMp3File.length() - 4).intValue()) + "(" + i + ").mp3");
                                }
                                newMp3File.createNewFile();
                            } catch (Exception ex) {
                                new ChangeConsoleThread(MusicOrganizerUtils.createErrorMessage(false, filename, 6, position), form);
                                new ChangeStatusBarThread("Erro na transferência do arquivo...", position, size, totalSize, form).start();
                                log.addToLog(MusicOrganizerUtils.createErrorMessage(true, filename, 2, position));
                                createLog = true;
                            }

                            InputStream inputStream = new FileInputStream(inputFiles[i]);
                            OutputStream outputStream = new FileOutputStream(newMp3File);
                            new ChangeStatusBarThread("Transferindo arquivo...", position, size, totalSize, form).start();
                            try {
                                FileUtil.copy(inputStream, outputStream);
                            } catch (Exception ex) {
                                new ChangeConsoleThread(MusicOrganizerUtils.createErrorMessage(false, filename, 6, position), form);
                                new ChangeStatusBarThread("Erro na transferência do arquivo...", position, size, totalSize, form).start();
                                log.addToLog(MusicOrganizerUtils.createErrorMessage(true, filename, 3, position));
                                createLog = true;
                            } finally {
                                inputStream.close();
                                outputStream.close();
                            }

                            if (deleteOriginal) {
                                try {
                                    new ChangeStatusBarThread("Excluindo arquivo original...", position, size, totalSize, form).start();
                                    inputFiles[i].delete();
                                } catch (Exception ex) {
                                    new ChangeConsoleThread(MusicOrganizerUtils.createErrorMessage(false, filename, 6, position), form);
                                    new ChangeStatusBarThread("Erro na transferência do arquivo...", position, size, totalSize, form).start();
                                    log.addToLog(MusicOrganizerUtils.createErrorMessage(true, filename, 4, position));
                                    createLog = true;
                                }
                            }

                            new ChangeConsoleThread("Hora Fim:     " + new Date().toString() + "\n"
                                    + MusicOrganizerUtils.doLine(false, 0) + "\n"
                                    + "ARQUIVO TRANSFERIDO COM SUCESSO!" + "\n"
                                    + MusicOrganizerUtils.doLine(false, 0), form).start();
                            new ChangeStatusBarThread("Transferência do arquivo concluída.", position, size, totalSize, form).start();
                            new ChangeStatusBarThread("Arquivo movido com sucesso!", position, size, totalSize, form).start();
                        } else {
                            new ChangeConsoleThread(MusicOrganizerUtils.createErrorMessage(false, filename, 6, position), form);
                            new ChangeStatusBarThread("Erro na transferência do arquivo!", position, size, totalSize, form).start();
                            log.addToLog(MusicOrganizerUtils.createErrorMessage(true, filename, 5, position));
                            createLog = true;
                        }
                    } else if (!error || !flag) {
                        new ChangeConsoleThread(MusicOrganizerUtils.createErrorMessage(false, filename, 6, position), form);
                        new ChangeStatusBarThread("Erro na transferência do arquivo!", position, size, totalSize, form).start();
                        log.addToLog(MusicOrganizerUtils.createErrorMessage(true, filename, 0, position));
                        createLog = true;
                    }
                }
                size++;
            }
            size--;
            new ChangeStatusBarThread("Processo concluído com sucesso!", position, size, totalSize, form).start();
            if (createLog) {
                String finalLogPath = log.saveLog();
                if (finalLogPath != null) {
                    MusicOrganizerUtils.showMessage("Processo concluído, porém houveram erros na transferência de alguns arquivos.\n"
                            + "Para mais informações, leia o arquivo de log que está disponível em \n" + finalLogPath, false);
                }
            } else {
                MusicOrganizerUtils.showMessage("O processo foi concluído com total sucesso!", false);
            }
            if (MusicOrganizerUtils.showMessage("Deseja salvar o console o conteúdo do console?", true) == 0) {
                boolean saveConsole = false;
                while (!saveConsole) {
                    JFileChooser fc = new JFileChooser(outputDirectory);
                    if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        if (MusicOrganizerUtils.saveConsole(fc.getSelectedFile(), form.getConsole())) {
                            MusicOrganizerUtils.showMessage("Console salvo com sucesso!", false);
                            saveConsole = true;
                        } else {
                            MusicOrganizerUtils.showMessage("Ocorreram problemas na gravação do console.\nPor favor, tente novamente!", false);
                        }
                    }
                }
            }
        }
    }
}

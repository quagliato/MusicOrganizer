package music.organizer.view;

import music.organizer.control.MusicOrganizerControl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.farng.mp3.TagException;

/**
 *
 * @author quagliato
 */
public class MusicOrganizerForm extends javax.swing.JDialog {

    private MusicOrganizerControl control;

    public MusicOrganizerForm(MusicOrganizerControl control) {
        this.control = control;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("MusicOrganizer  (Versão 11.09.30)");
    }

    public void changeStatusBar(String message, int vlProgressBarSize, int vlProgressBarTotalSize) {
        lblStatus.setText(message);
        lblStatus.repaint();
        lblTotalArquivos.setText(vlProgressBarSize + "/" + vlProgressBarTotalSize);
        lblTotalArquivos.repaint();
        progressBarStatus.setMaximum(vlProgressBarTotalSize);
        progressBarStatus.setValue(vlProgressBarSize);
        progressBarStatus.repaint();
    }

    public void changeConsole(String message) {
        txtConsole.setText(txtConsole.getText() + "\n" + message);
        txtConsole.setCaretPosition(txtConsole.getText().length());
        txtConsole.repaint();
    }

    public void changeEnabledComponents(boolean flag) {
        txtLocalEntrada.setEditable(flag);
        txtLocalSaida.setEditable(flag);
        btnBuscarLocalEntrada.setEnabled(flag);
        btnBuscarLocalSaida.setEnabled(flag);
        chkRenomear.setEnabled(flag);
        chkApagar.setEnabled(flag);
        chkAlbum.setEnabled(flag);
        btnIniciar.setEnabled(flag);
    }

    public String getPath(int type) {
        if (type == 0) {
            return txtLocalEntrada.getText();
        } else if (type == 1) {
            return txtLocalSaida.getText();
        }
        return null;
    }

    public void cleanForm() {
        txtLocalEntrada.setText("");
        txtLocalSaida.setText("");
        chkRenomear.setSelected(false);
        chkApagar.setSelected(false);
        chkAlbum.setSelected(false);
        txtConsole.setText("");
        lblStatus.setText("");
        lblTotalArquivos.setText("");
        progressBarStatus.setValue(0);
    }

    public String getConsole() {
        return txtConsole.getText();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlArquivos = new javax.swing.JPanel();
        lblLocalEntrada = new javax.swing.JLabel();
        txtLocalEntrada = new javax.swing.JTextField();
        btnBuscarLocalEntrada = new javax.swing.JButton();
        lblLocalSaida = new javax.swing.JLabel();
        txtLocalSaida = new javax.swing.JTextField();
        btnBuscarLocalSaida = new javax.swing.JButton();
        pnlOpcoes = new javax.swing.JPanel();
        chkRenomear = new javax.swing.JCheckBox();
        chkApagar = new javax.swing.JCheckBox();
        chkAlbum = new javax.swing.JCheckBox();
        btnIniciar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlConsole = new javax.swing.JPanel();
        scrollConsole = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        pnlStatusBar = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        progressBarStatus = new javax.swing.JProgressBar();
        lblTotalArquivos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("");
        setMinimumSize(new java.awt.Dimension(650, 500));

        pnlArquivos.setBorder(javax.swing.BorderFactory.createTitledBorder("Arquivos"));

        lblLocalEntrada.setText("Local de Entrada:");

        btnBuscarLocalEntrada.setText("Buscar");
        btnBuscarLocalEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarLocalEntradaActionPerformed(evt);
            }
        });

        lblLocalSaida.setText("Local de Saída:");

        btnBuscarLocalSaida.setText("Buscar");
        btnBuscarLocalSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarLocalSaidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlArquivosLayout = new javax.swing.GroupLayout(pnlArquivos);
        pnlArquivos.setLayout(pnlArquivosLayout);
        pnlArquivosLayout.setHorizontalGroup(
            pnlArquivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlArquivosLayout.createSequentialGroup()
                .addGroup(pnlArquivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLocalSaida, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLocalEntrada, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlArquivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlArquivosLayout.createSequentialGroup()
                        .addComponent(txtLocalEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarLocalEntrada))
                    .addGroup(pnlArquivosLayout.createSequentialGroup()
                        .addComponent(txtLocalSaida, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarLocalSaida)))
                .addContainerGap())
        );
        pnlArquivosLayout.setVerticalGroup(
            pnlArquivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlArquivosLayout.createSequentialGroup()
                .addGroup(pnlArquivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLocalEntrada)
                    .addComponent(txtLocalEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarLocalEntrada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlArquivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLocalSaida)
                    .addComponent(txtLocalSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarLocalSaida)))
        );

        pnlOpcoes.setBorder(javax.swing.BorderFactory.createTitledBorder("Opções"));

        chkRenomear.setText("Renomear arquivos utilizando as Tags?");

        chkApagar.setText("Apagar os arquivos originais após a migração?");

        chkAlbum.setText("Separar os arquivos também por Albúm?");

        javax.swing.GroupLayout pnlOpcoesLayout = new javax.swing.GroupLayout(pnlOpcoes);
        pnlOpcoes.setLayout(pnlOpcoesLayout);
        pnlOpcoesLayout.setHorizontalGroup(
            pnlOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkApagar)
                    .addComponent(chkAlbum)
                    .addComponent(chkRenomear))
                .addContainerGap(181, Short.MAX_VALUE))
        );
        pnlOpcoesLayout.setVerticalGroup(
            pnlOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcoesLayout.createSequentialGroup()
                .addComponent(chkRenomear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkApagar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkAlbum)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        pnlConsole.setBorder(javax.swing.BorderFactory.createTitledBorder("Console"));

        scrollConsole.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollConsole.setAutoscrolls(true);

        txtConsole.setBackground(new java.awt.Color(0, 0, 0));
        txtConsole.setColumns(20);
        txtConsole.setEditable(false);
        txtConsole.setFont(new java.awt.Font("DejaVu Sans", 0, 11));
        txtConsole.setForeground(new java.awt.Color(204, 255, 204));
        txtConsole.setRows(5);
        scrollConsole.setViewportView(txtConsole);

        javax.swing.GroupLayout pnlConsoleLayout = new javax.swing.GroupLayout(pnlConsole);
        pnlConsole.setLayout(pnlConsoleLayout);
        pnlConsoleLayout.setHorizontalGroup(
            pnlConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollConsole, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
        );
        pnlConsoleLayout.setVerticalGroup(
            pnlConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollConsole, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
        );

        pnlStatusBar.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        pnlStatusBar.setFocusable(false);

        lblStatus.setFont(new java.awt.Font("DejaVu Sans", 0, 11));
        lblStatus.setText(" ");
        lblStatus.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lblTotalArquivos.setFont(new java.awt.Font("DejaVu Sans", 0, 11));
        lblTotalArquivos.setText(" ");
        lblTotalArquivos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout pnlStatusBarLayout = new javax.swing.GroupLayout(pnlStatusBar);
        pnlStatusBar.setLayout(pnlStatusBarLayout);
        pnlStatusBarLayout.setHorizontalGroup(
            pnlStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStatusBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalArquivos, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBarStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlStatusBarLayout.setVerticalGroup(
            pnlStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatusBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblStatus)
                    .addComponent(progressBarStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalArquivos))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlConsole, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlStatusBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlArquivos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(pnlOpcoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlArquivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlConsole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        if (txtLocalEntrada.getText() != null && txtLocalSaida.getText() != null) {
            try {
                changeEnabledComponents(false);
                control.generateNewOrganizedFiles(chkRenomear.isSelected(), chkApagar.isSelected(), chkAlbum.isSelected());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível realizar a geração dos arquivos!");
                Logger.getLogger(MusicOrganizerForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TagException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível realizar a leitura das tags dos arquivos!");
                Logger.getLogger(MusicOrganizerForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.toString().replaceAll("java.lang.Exception: ", ""));
                Logger.getLogger(MusicOrganizerForm.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                changeEnabledComponents(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "É preciso escolher os diretórios de entrada e saída antes de iniciar o processo.");
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        control.dieThreadDie();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarLocalEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarLocalEntradaActionPerformed
        txtLocalEntrada.setText(control.searchDirectory(0));
    }//GEN-LAST:event_btnBuscarLocalEntradaActionPerformed

    private void btnBuscarLocalSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarLocalSaidaActionPerformed
        txtLocalSaida.setText(control.searchDirectory(1));
    }//GEN-LAST:event_btnBuscarLocalSaidaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarLocalEntrada;
    private javax.swing.JButton btnBuscarLocalSaida;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JCheckBox chkAlbum;
    private javax.swing.JCheckBox chkApagar;
    private javax.swing.JCheckBox chkRenomear;
    private javax.swing.JLabel lblLocalEntrada;
    private javax.swing.JLabel lblLocalSaida;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTotalArquivos;
    private javax.swing.JPanel pnlArquivos;
    private javax.swing.JPanel pnlConsole;
    private javax.swing.JPanel pnlOpcoes;
    private javax.swing.JPanel pnlStatusBar;
    private javax.swing.JProgressBar progressBarStatus;
    private javax.swing.JScrollPane scrollConsole;
    private javax.swing.JTextArea txtConsole;
    private javax.swing.JTextField txtLocalEntrada;
    private javax.swing.JTextField txtLocalSaida;
    // End of variables declaration//GEN-END:variables
}

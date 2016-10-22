package Vista;

import ModeloControlador.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GUI_Yahoo extends javax.swing.JFrame {

    private static final String URL = "https://es.finance.yahoo.com/actives?e=mc";

    private ArrayList<EmpresaBolsa> arrayDatosTabla;
    private ArrayList<EmpresaBolsa> arrayDatosYahoo;

    private double diferencia = 0.01;

    public GUI_Yahoo() {
        initComponents();
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                arrayDatosTabla = visuaizarPorConsola();

                arrayDatosTabla.stream().forEach((empresa) -> {
                    System.out.println("ROW: \t" + empresa.getNombre() + "-\t" + empresa.getCambio() + "-\t" + empresa.getUltimoCambio() + "-\t" + empresa.getVolumen());
                });

                JTableYahoo.setModel(new ModeloTabla(arrayDatosTabla));

                while (true) {

                    try {
                        actualizarCambios();
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        hilo.start();
    }

    private void actualizarCambios() {
        ArrayList<EmpresaBolsa> datosActualizados = visuaizarPorConsola();
        Calendar calendario = Calendar.getInstance();
        informacion.setText("");

        for (int iterador = 0; iterador < datosActualizados.size(); iterador++) {
            if (iterador == datosActualizados.size()) {
                break;
            }
            int hora = calendario.get(Calendar.HOUR_OF_DAY);
            int minutos = calendario.get(Calendar.MINUTE);
            int segundos = calendario.get(Calendar.SECOND);
            double valor1 = Double.parseDouble(arrayDatosTabla.get(iterador).getCambio().substring(0, 5)) - Double.parseDouble(datosActualizados.get(iterador).getCambio().substring(0, 5));
            double valor2 = Double.parseDouble(datosActualizados.get(iterador).getCambio().substring(0, 5)) - Double.parseDouble(arrayDatosTabla.get(iterador).getCambio().substring(0, 5));

            if (valor1 <= -diferencia) {
                informacion.append("HORA: " + hora + ":" + minutos + ":" + segundos + "\n");
                informacion.append("La empresa: " + arrayDatosTabla.get(iterador).getNombre() + " ha sufrido un cambio de -" + String.format("%.2f", valor1) + "\n");

            } else if (valor2 >= diferencia) {
                informacion.append("HORA: " + hora + ":" + minutos + ":" + segundos + "\n");
                informacion.append("La empresa: " + arrayDatosTabla.get(iterador).getNombre() + " ha sufrido un cambio de " + String.format("%.2f", valor2) + "\n");
            }
        }

        java.awt.EventQueue.invokeLater(() -> {
            JTableYahoo.setModel(new ModeloTabla(datosActualizados));
        });
    }

    public ArrayList<EmpresaBolsa> visuaizarPorConsola() {
        
        arrayDatosYahoo = new ArrayList<>();
        try {
            Document document = Jsoup.connect(URL).get();
            Element table = document.select("table").get(4);
            Elements tableRows = table.select("tbody tr");

            tableRows.stream().map((element) -> element.select("td")).map((tableRowData) -> {
                System.out.println(tableRowData.get(3).toString());
                return tableRowData;
            }).forEach((tableRowData) -> {
                String valor = tableRowData.get(3).text().replace(",", ".");
                if (tableRowData.get(3).toString().contains("#008800")) {
                    valor = "+" + valor;
                } else {
                    valor = "-" + valor;
                }
                arrayDatosYahoo.add(new EmpresaBolsa(tableRowData.get(1).text(), tableRowData.get(2).text(), valor, tableRowData.get(4).text()));
            });
            Collections.sort(arrayDatosYahoo);
            return arrayDatosYahoo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        JTableYahoo = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        informacion = new javax.swing.JTextArea();
        lblCabecera = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JTableYahoo.setAutoCreateRowSorter(true);
        JTableYahoo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTableYahoo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(JTableYahoo);

        informacion.setEditable(false);
        informacion.setBackground(java.awt.Color.lightGray);
        informacion.setColumns(20);
        informacion.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        informacion.setRows(5);
        informacion.setFocusable(false);
        jScrollPane2.setViewportView(informacion);

        lblCabecera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Yahoo-Finance-Logo.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCabecera, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
       
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Yahoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Yahoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Yahoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Yahoo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
       

       
        java.awt.EventQueue.invokeLater(() -> {
            new GUI_Yahoo().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTableYahoo;
    private javax.swing.JTextArea informacion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCabecera;
    // End of variables declaration//GEN-END:variables
}

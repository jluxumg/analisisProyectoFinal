package reporte;

import facturador.db.Conexion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.util.Map;
import java.util.HashMap;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteNuevo {

    private static ReporteNuevo instancia;

    public static ReporteNuevo getInstancia() {
        if (instancia == null) {
            instancia = new ReporteNuevo();
        }
        return instancia;
    }
    
    private Conexion conexion;


    public void JReportViewParametros(String nombreReporte, Map parametros) {
        try {
            conexion = Conexion.getInstancia();
            JasperReport reporteMaestro = (JasperReport) JRLoader.loadObject(nombreReporte);
            JasperPrint print = JasperFillManager.fillReport(reporteMaestro, parametros, conexion.getConnection());
            JasperViewer visor = new JasperViewer(print, false);
            visor.setTitle("Visualizar Reporte");
            //visor.setIconImage(new ImageIcon(getClass().getResource(imagen.getAbsolutePath())).getImage());
            visor.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

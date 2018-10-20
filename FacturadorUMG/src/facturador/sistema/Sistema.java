package facturador.sistema;
import facturador.ventanas.VentanaPrincipal;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.watermark.SubstanceImageWatermark;
public class Sistema {

    public static void main(String[] args) {

        VentanaPrincipal.setDefaultLookAndFeelDecorated(true); //que nos permite dejar a Substance la decoracion ( por asi decirlo)
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        try{
            SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.MistAquaSkin");
            SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceLightAquaTheme");
            SubstanceLookAndFeel.setCurrentWatermark(new SubstanceImageWatermark("imagenes/f6.JPG"));
            SubstanceLookAndFeel.setImageWatermarkOpacity(new Float(0.5f));
        }catch(Exception e){
        }
        VentanaPrincipal ventanaPrincipal = VentanaPrincipal.getInstancia();
    }

}

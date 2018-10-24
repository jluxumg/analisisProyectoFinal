package facturador.catalogos;
import facturador.ventanas.VentanaPrincipal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Hilo extends Thread{
    public void run (){
        
        while (true){
            Date hora = new Date();
            SimpleDateFormat formHora = new
            SimpleDateFormat
            ("' 'h:mm a",
            new Locale ("es","GT"));
            String fechaConFormato = formHora.format(hora);
            VentanaPrincipal.getInstancia().lblSoloHora.setText("Hora Del Sistema: "+fechaConFormato);
            
        }


    }
}

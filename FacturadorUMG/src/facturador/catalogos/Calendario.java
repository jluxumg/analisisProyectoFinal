package facturador.catalogos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.*;
import com.toedter.calendar.JCalendar;


public class Calendario extends JDialog implements ActionListener{
    public JCalendar calendario;
    public JTextField fecha;
    private static Calendario instancia;
    private JButton b;

    public Calendario (){
        calendario=new JCalendar();
        fecha=new JTextField(30);
        fecha.setEditable(false);
        

        b=new JButton("Aceptar");
        b.addActionListener(this);
        
            /*@Override
            public void actionPerformed(ActionEvent e) {
                String año = Integer.toString(calendario.getCalendar().get(java.util.Calendar.YEAR));
                String mes = Integer.toString(calendario.getCalendar().get(java.util.Calendar.MONTH) + 1);
                String dia = Integer.toString(calendario.getCalendar().get(java.util.Calendar.DATE));
                fecha.setText(dia+"/"+mes+"/"+año);
                
            }
        });*/
        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        JPanel pfecha=new JPanel();
        pfecha.add(new JLabel("Fecha Seleccionada"));
        pfecha.add(fecha);
        pfecha.add(b);
        pfecha.setSize(200, 200);
        p.add(pfecha);
        p.add(calendario);
        
        this.add(p);
        this.setTitle("Fecha");
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b){
            String año = Integer.toString(calendario.getCalendar().get(java.util.Calendar.YEAR));
            String mes = Integer.toString(calendario.getCalendar().get(java.util.Calendar.MONTH) + 1);
            String dia = Integer.toString(calendario.getCalendar().get(java.util.Calendar.DATE));
            int a = mes.length();
            if (a == 1){
                   //JOptionPane.showMessageDialog(null,"1 Digito");
                   fecha.setText(dia+"/0"+mes+"/"+año);
            }else{
                //JOptionPane.showMessageDialog(null,"2 Digitos");
                fecha.setText(dia+"/"+mes+"/"+año);
            }
            
            
            AgregarRecordatorio.getInstancia().txtFecha.setText(fecha.getText());
            this.setVisible(false);
            
        }

    }
    public static Calendario getInstancia(){
        if(instancia == null){
            instancia = new Calendario();
        }
        return instancia;
    }


    
}

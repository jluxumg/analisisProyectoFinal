package util;

import facturador.beans.Item;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JComboBox;

public class ComboBoxItem implements ActionListener {

    public ComboBoxItem(Vector model) {

        JComboBox comboBox;
        //  Most flexible approach is to create a custom render
        //  to diplay the Item data
        comboBox = new JComboBox(model);
        comboBox.setRenderer(new ItemRenderer());
        comboBox.addActionListener(this);
        
    }

    public void actionPerformed(ActionEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        Item item = (Item) comboBox.getSelectedItem();
        System.out.println(item.getIdInt() + " : " + item.getDescription());
    }

}

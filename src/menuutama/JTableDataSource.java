/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menuutama;

/**
 *
 * @author alfar
 */
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class JTableDataSource implements JRDataSource {
    private JTable table;
    private int rowIndex = -1;

    public JTableDataSource(JTable table) {
        this.table = table;
    }

    @Override
    public boolean next() throws JRException {
        rowIndex++;
        return rowIndex < table.getRowCount();
    }

    @Override
    public Object getFieldValue(JRField field) throws JRException {
        String fieldName = field.getName();
        int colIndex = table.getColumnModel().getColumnIndex(fieldName);
        return table.getValueAt(rowIndex, colIndex);
    }
}

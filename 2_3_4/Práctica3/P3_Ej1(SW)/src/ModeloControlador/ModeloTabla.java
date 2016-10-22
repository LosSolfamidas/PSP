package ModeloControlador;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ModeloTabla implements TableModel {

	private static String[] nombreColumnas ={"Nombre Empresa","Ultimo Cambio","Cambio","Volumen"};
	
        private static int totalColumnas = nombreColumnas.length;
	
	private ArrayList<EmpresaBolsa> datosTabla;
	
	public ModeloTabla(ArrayList<EmpresaBolsa> datos) {
		this.datosTabla=datos;
	}
	@Override
	public int getRowCount() {
		return datosTabla.size();
	}

	@Override
	public int getColumnCount() {
		return totalColumnas;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return nombreColumnas[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex){
		case 0: return datosTabla.get(rowIndex).getNombre();
		case 1: return datosTabla.get(rowIndex).getUltimoCambio();
		case 2: return datosTabla.get(rowIndex).getCambio();
		case 3: return datosTabla.get(rowIndex).getVolumen();
		}
		return "Error Logico";
	}

	
        
        
        
        
        
        
        @Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
	}

}

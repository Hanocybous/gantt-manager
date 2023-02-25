package app.gui.jtableview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

import app.SimpleRasterModel;
import domtoapp.SimpleTableModel;


/*
 * Shamelessly stolen from
 * https://stackoverflow.com/questions/22864095/reading-data-from-a-specific-csv-file-and-displaying-it-in-a-jtable
 * 
 */

public class JTableViewer extends JPanel {

		private static final long serialVersionUID = 1L;
		private final JTable table;

	    public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public JTable getTable() {
			return table;
		}

		public JTableViewer(SimpleTableModel tableModel) {
	    	super(new BorderLayout(3, 3));	        
	        this.table = new JTable(tableModel);
	    }//end constructor
	
		public JTableViewer(SimpleRasterModel rasterModel) {
	    	super(new BorderLayout(3, 3));	        
	        
	        this.table = new JTable(rasterModel){
	            /**
				 * 
				 */
				private static final long serialVersionUID = -4739863709303727277L;

				@Override
	            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
	            	Color darkGreen = new Color(0, 128, 0, 255);
	                Component comp = super.prepareRenderer(renderer, row, col);
	                String value = rasterModel.getValueAt(row, col);
	                if (value==null) {
	                	comp.setBackground(Color.white);
	                	comp.setForeground(Color.black);
	                }
	                else {
	                    if (value.equals("**")) {
	                        comp.setBackground(darkGreen);
	                        comp.setForeground(darkGreen);
	                    } else if (value.equals("*")) {
	                        comp.setBackground(Color.green);
	                        comp.setForeground(Color.green);
	                    } else {
	                        comp.setBackground(Color.white);
	                        comp.setForeground(Color.black);
	                    }
	                }
	                return comp;
	            }
	        };
	    }//end constructor
		
	    public void createAndShowJTable() {
	        	
	        this.table.setPreferredScrollableViewportSize(new Dimension(800, 600));
	        this.table.setFillsViewportHeight(true);
	        JPanel ButtonOpen = new JPanel(new FlowLayout(FlowLayout.CENTER));
	        add(ButtonOpen, BorderLayout.SOUTH);
	        
	        // Create the scroll pane and add the table to it.
	        JScrollPane scrollPane = new JScrollPane(table);
	        
	        // Add the scroll pane to this panel.
	        add(scrollPane, BorderLayout.CENTER);
	        
	        // add a nice border
	        setBorder(new EmptyBorder(5, 5, 5, 5));

	    }
}

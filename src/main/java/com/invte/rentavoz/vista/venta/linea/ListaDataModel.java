
package com.invte.rentavoz.vista.venta.linea;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import co.com.rentavoz.logica.jpa.entidades.Linea;
import co.com.rentavoz.logica.jpa.fachadas.LineaFacade;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class ListaDataModel
 * @date 3/06/2013
 * 
 */
public abstract class ListaDataModel extends LazyDataModel<Linea> {

	private static final long serialVersionUID = 1L;

            private  List<Linea> players;
            private  List<Linea> players1;
	

	public abstract LineaFacade getFacade();

	
	@Override
	public void setRowIndex(int rowIndex) {
	    /*
	     * The following is in ancestor (LazyDataModel):
	     * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
	     */
	    if (rowIndex == -1 || getPageSize() == 0) {
	        super.setRowIndex(-1);
	    }
	    else
	        super.setRowIndex(rowIndex % getPageSize());
	}
	@Override
	public List<Linea> load(int startingAt, int maxPerPage, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		try {
			try {

				// with datatable pagination limits
				players = getFacade().findPlayers(startingAt, maxPerPage);
                                players1=players;
                                setWrappedData(players);

			} finally {

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("aqui");
		}

		// set the total of players
		if (getRowCount() <= 0) {
			setRowCount(getFacade().countPlayersTotal());
		}

		// set the page dize
		setPageSize(maxPerPage);

		return players;
	}


  
            @Override
            public Object getRowKey(Linea player) {
                return player.getIdLinea();
            }

     
            @Override
            public Linea getRowData(String rowKey) {
                return getFacade().find(Integer.valueOf(rowKey));
            }
          
            
    
    
}

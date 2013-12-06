/**
 * 
 */
package co.com.rentavoz.logica.owner;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.com.rentavoz.logica.jpa.entidades.Tercero;
import co.com.rentavoz.logica.jpa.fachadas.TerceroFacade;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.core
 * @class OwnerManager
 * @date 4/09/2013
 * 
 */
@Stateless
public class OwnerManager implements Serializable {

	/**
	 * co.com.rentavoz.logica.owner
	 * co.com.rentavoz.model.core
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private static final String COMMA = ",";
	/**
	 * co.com.rentavoz.logica.owner co.com.rentavoz.model.core
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private static final long serialVersionUID = -8313051457172324682L;
	
	
	@EJB
	private TerceroFacade terceroFacade;

	/**
	 * 
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param idCentrope
	* @return
	 */
	public String getOwnerByCentrope(int idCentrope) {

		StringBuilder builder = new StringBuilder();

		List<Tercero> terceros = terceroFacade.findByCentrope(idCentrope);

		for (Tercero tercero : terceros) {
			if (tercero.getUsuario()!=null) {
				
				builder.append(tercero.getUsuario());
				builder.append(COMMA);
			}
		}

		return builder.toString();

	}
}

package co.com.rentavoz.logica.jpa.entidades.bodega;

import java.io.Serializable;

import javax.persistence.*;

import co.com.rentavoz.logica.jpa.entidades.Sucursal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bodega_salida database table.
 * 
 */
@Entity
@Table(name="bodega_salida")
public class BodegaSalida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_salida")
	private Date fechaSalida;

	@Column(name="nro_factura", length=155)
	private String nroFactura;
	
	
	@Column(name="recibe")
	private String recibe;

	@Column(length=255)
	private String observacion;

	@JoinColumn(name="sucursal_destino")
	private Sucursal sucursalDestino;

	@JoinColumn(name="sucursal_origen")
	private Sucursal sucursalOrigen;

	//bi-directional many-to-one association to BodegaSalidaReferencia
	@OneToMany(mappedBy="bodegaSalida",cascade={CascadeType.ALL})
	private List<BodegaSalidaReferencia> bodegaSalidaReferencias=new ArrayList<BodegaSalidaReferencia>();

	public BodegaSalida() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getNroFactura() {
		return this.nroFactura;
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Sucursal getSucursalDestino() {
		return this.sucursalDestino;
	}

	public void setSucursalDestino(Sucursal sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
	}

	public Sucursal getSucursalOrigen() {
		return this.sucursalOrigen;
	}

	public void setSucursalOrigen(Sucursal sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	public List<BodegaSalidaReferencia> getBodegaSalidaReferencias() {
		return this.bodegaSalidaReferencias;
	}

	public void setBodegaSalidaReferencias(List<BodegaSalidaReferencia> bodegaSalidaReferencias) {
		this.bodegaSalidaReferencias = bodegaSalidaReferencias;
	}

	public BodegaSalidaReferencia addBodegaSalidaReferencia(BodegaSalidaReferencia bodegaSalidaReferencia) {
		bodegaSalidaReferencia.setBodegaSalida(this);
		getBodegaSalidaReferencias().add(bodegaSalidaReferencia);
		

		return bodegaSalidaReferencia;
	}

	public BodegaSalidaReferencia removeBodegaSalidaReferencia(BodegaSalidaReferencia bodegaSalidaReferencia) {
		getBodegaSalidaReferencias().remove(bodegaSalidaReferencia);
		bodegaSalidaReferencia.setBodegaSalida(null);

		return bodegaSalidaReferencia;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the recibe
	 */
	public String getRecibe() {
		return recibe;
	}
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param recibe the recibe to set
	 */
	public void setRecibe(String recibe) {
		this.recibe = recibe;
	}
	
}
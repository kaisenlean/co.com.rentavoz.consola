package co.com.rentavoz.logica.jpa.entidades.bodega;

import java.io.Serializable;

import javax.persistence.*;

import co.com.rentavoz.logica.jpa.entidades.Sucursal;
import co.com.rentavoz.logica.jpa.entidades.Tercero;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bodega_ingreso database table.
 * 
 */
@Entity
@Table(name="bodega_ingreso")
public class BodegaIngreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Lob
	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_ingreso")
	private Date fechaIngreso;

	@Column(name="hora_ingreso")
	private Time horaIngreso;

	@Column(name="nro_factura", length=45)
	private String nroFactura;

	@Column(name="persona_compra", length=200)
	private String personaCompra;

	@Column(name="precio_compra", precision=10, scale=2)
	private BigDecimal precioCompra;

	@ManyToOne
	@JoinColumn(name="proveedor")
	private Tercero proveedor;
	
	@Column(name="consecutivo_factura")
	private String consecutivoFactura;
	

	//bi-directional many-to-one association to BodegaExistencia
	@OneToMany(mappedBy="bodegaIngreso", cascade={CascadeType.ALL})
	private List<BodegaExistencia> bodegaExistencias= new ArrayList<BodegaExistencia>();

	@JoinColumn(name="sucursal")
	private Sucursal sucursal;
	public BodegaIngreso() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Time getHoraIngreso() {
		return this.horaIngreso;
	}

	public void setHoraIngreso(Time horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public String getNroFactura() {
		return this.nroFactura;
	}

	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}

	public String getPersonaCompra() {
		return this.personaCompra;
	}

	public void setPersonaCompra(String personaCompra) {
		this.personaCompra = personaCompra;
	}

	public BigDecimal getPrecioCompra() {
		return this.precioCompra;
	}

	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Tercero getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Tercero proveedor) {
		this.proveedor = proveedor;
	}

	public List<BodegaExistencia> getBodegaExistencias() {
		return this.bodegaExistencias;
	}

	public void setBodegaExistencias(List<BodegaExistencia> bodegaExistencias) {
		this.bodegaExistencias = bodegaExistencias;
	}

	public BodegaExistencia addBodegaExistencia(BodegaExistencia bodegaExistencia) {
		bodegaExistencia.setBodegaIngreso(this);
		getBodegaExistencias().add(bodegaExistencia);
		return bodegaExistencia;
	}

	public BodegaExistencia removeBodegaExistencia(BodegaExistencia bodegaExistencia) {
		getBodegaExistencias().remove(bodegaExistencia);
		bodegaExistencia.setBodegaIngreso(null);
		return bodegaExistencia;
	}
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the sucursal
	 */
	public Sucursal getSucursal() {
		return sucursal;
	}
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the consecutivoFactura
	 */
	public String getConsecutivoFactura() {
		return consecutivoFactura;
	}
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param consecutivoFactura the consecutivoFactura to set
	 */
	public void setConsecutivoFactura(String consecutivoFactura) {
		this.consecutivoFactura = consecutivoFactura;
	}

}
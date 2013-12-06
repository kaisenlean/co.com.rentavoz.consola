package co.com.rentavoz.logica.jpa.entidades.bodega;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The persistent class for the bodega_item database table.
 * 
 */
@Entity
@Table(name = "bodega_item")
public class BodegaItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	@Column(length = 200)
	private String descripcion;

	@Column(length = 255)
	private String foto;

	@Column(nullable = false, length = 200)
	private String nombre;

	@Column(length = 200)
	private String referencia;

	@Column
	private String color;

	@Column(name = "precio_venta")
	private Double precioVenta;

	@Column(name = "dias_garantia")
	private int diasGarantia;
	

	@Column(name = "cantidad_imei")
	private int cantidadImei;


	
	
	// bi-directional many-to-one association to BodegaExistencia
	@OneToMany(mappedBy = "bodegaItemBean")
	private List<BodegaExistencia> bodegaExistencias;
	
	
	@Transient
	private List<BodegaExistencia> existenciasPorSucursal;

	
	@Transient
	private double valorTotalEstimado;
	
	@Transient
	private int contadorImei;

	
	@PostLoad
	public void init(){
		 contadorImei=0;
		existenciasPorSucursal=new ArrayList<BodegaExistencia>();
	}
	
	public BodegaItem() {
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

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public List<BodegaExistencia> getBodegaExistencias() {
		return this.bodegaExistencias;
	}

	public void setBodegaExistencias(List<BodegaExistencia> bodegaExistencias) {
		this.bodegaExistencias = bodegaExistencias;
	}

	public BodegaExistencia addBodegaExistencia(
			BodegaExistencia bodegaExistencia) {
		getBodegaExistencias().add(bodegaExistencia);
		bodegaExistencia.setBodegaItemBean(this);

		return bodegaExistencia;
	}

	public BodegaExistencia removeBodegaExistencia(
			BodegaExistencia bodegaExistencia) {
		getBodegaExistencias().remove(bodegaExistencia);
		bodegaExistencia.setBodegaItemBean(null);

		return bodegaExistencia;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the precio_venta
	 */
	public Double getPrecioVenta() {
		return precioVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param precio_venta
	 *            the precio_venta to set
	 */
	public void setPrecioVenta(Double precio_venta) {
		this.precioVenta = precio_venta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the diasGarantia
	 */
	public int getDiasGarantia() {
		return diasGarantia;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param diasGarantia
	 *            the diasGarantia to set
	 */
	public void setDiasGarantia(int diasGarantia) {
		this.diasGarantia = diasGarantia;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder().append(nombre).toString();
	}
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the existenciasPorSucursal
	 */
	public List<BodegaExistencia> getExistenciasPorSucursal() {
		return existenciasPorSucursal;
	}
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param existenciasPorSucursal the existenciasPorSucursal to set
	 */
	public void setExistenciasPorSucursal(
			List<BodegaExistencia> existenciasPorSucursal) {
		this.existenciasPorSucursal = existenciasPorSucursal;
	}
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the cantidadImei
	 */
	public int getCantidadImei() {
		return cantidadImei;
	}
	
	
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param cantidadImei the cantidadImei to set
	 */
	public void setCantidadImei(int cantidadImei) {
		this.cantidadImei = cantidadImei;
	}
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the contadorImei
	 */
	public int getContadorImei() {
		return contadorImei;
	}
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param contadorImei the contadorImei to set
	 */
	public void setContadorImei(int contadorImei) {
		this.contadorImei = contadorImei;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the valorTotalEstimado
	 */
	public double getValorTotalEstimado() {
		return valorTotalEstimado;
	}
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param valorTotalEstimado the valorTotalEstimado to set
	 */
	public void setValorTotalEstimado(double valorTotalEstimado) {
		this.valorTotalEstimado = valorTotalEstimado;
	}
}
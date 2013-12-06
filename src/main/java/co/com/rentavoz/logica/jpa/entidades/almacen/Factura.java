///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.com.rentavoz.logica.jpa.entidades.almacen;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//
///**
// * 
// * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
// * @project co.com.rentavoz.model.jpa
// * @class Factura
// * @date 14/07/2013
// * 
// */
//@Entity
//@Table(name = "banco")
//public class Factura implements Serializable {
//	private static final long serialVersionUID = 1L;
//	@Id
//	@Basic(optional = false)
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "numero")
//	private Integer numero;
//	@Basic(optional = false)
//	@NotNull
//	@Size(max = 10)
//	@Column(name = "numero_linea")
//	private String numeroLinnea;
//
//	@Basic(optional = false)
//	@Column(name = "fecha")
//	@Temporal(TemporalType.DATE)
//	private Date fecha;
//
//	@Basic(optional = true)
//	@Column(name = "iva")
//	private Double iva;
//
//	@Basic(optional = true)
//	@Column(name = "saldo")
//	private Double saldo;
//
//	@Basic(optional = true)
//	@Column(name = "extra")
//	private Double extra;
//
//	@Basic(optional = true)
//	@Column(name = "total")
//	private Double total;
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 14/07/2013
//	 */
//	public Factura() {
//		super();
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 14/07/2013
//	 * @param numero
//	 */
//	public Factura(Integer numero) {
//		super();
//		this.numero = numero;
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 14/07/2013
//	 * @param numero
//	 * @param numeroLinnea
//	 * @param fecha
//	 * @param iva
//	 * @param saldo
//	 * @param extra
//	 * @param total
//	 */
//	public Factura(Integer numero, String numeroLinnea, Date fecha, Double iva,
//			Double saldo, Double extra, Double total) {
//		super();
//		this.numero = numero;
//		this.numeroLinnea = numeroLinnea;
//		this.fecha = fecha;
//		this.iva = iva;
//		this.saldo = saldo;
//		this.extra = extra;
//		this.total = total;
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 2/06/2013
//	 * @return the numero
//	 */
//	public Integer getNumero() {
//		return numero;
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 2/06/2013
//	 * @param numero
//	 *            the numero to set
//	 */
//	public void setNumero(Integer numero) {
//		this.numero = numero;
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 2/06/2013
//	 * @return the numeroLinnea
//	 */
//	public String getNumeroLinnea() {
//		return numeroLinnea;
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 2/06/2013
//	 * @param numeroLinnea
//	 *            the numeroLinnea to set
//	 */
//	public void setNumeroLinnea(String numeroLinnea) {
//		this.numeroLinnea = numeroLinnea;
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 2/06/2013
//	 * @return the fecha
//	 */
//	public Date getFecha() {
//		return fecha;
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 2/06/2013
//	 * @param fecha
//	 *            the fecha to set
//	 */
//	public void setFecha(Date fecha) {
//		this.fecha = fecha;
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 2/06/2013
//	 * @return the iva
//	 */
//	public Double getIva() {
//		return iva;
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 2/06/2013
//	 * @param iva
//	 *            the iva to set
//	 */
//	public void setIva(Double iva) {
//		this.iva = iva;
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 2/06/2013
//	 * @return the saldo
//	 */
//	public Double getSaldo() {
//		return saldo;
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 2/06/2013
//	 * @param saldo
//	 *            the saldo to set
//	 */
//	public void setSaldo(Double saldo) {
//		this.saldo = saldo;
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 2/06/2013
//	 * @return the extra
//	 */
//	public Double getExtra() {
//		return extra;
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 2/06/2013
//	 * @param extra
//	 *            the extra to set
//	 */
//	public void setExtra(Double extra) {
//		this.extra = extra;
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 2/06/2013
//	 * @return the total
//	 */
//	public Double getTotal() {
//		return total;
//	}
//
//	/**
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 2/06/2013
//	 * @param total
//	 *            the total to set
//	 */
//	public void setTotal(Double total) {
//		this.total = total;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see java.lang.Object#hashCode()
//	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
//		result = prime * result
//				+ ((numeroLinnea == null) ? 0 : numeroLinnea.hashCode());
//		return result;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see java.lang.Object#equals(java.lang.Object)
//	 */
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Factura other = (Factura) obj;
//		if (numero == null) {
//			if (other.numero != null)
//				return false;
//		} else if (!numero.equals(other.numero))
//			return false;
//		if (numeroLinnea == null) {
//			if (other.numeroLinnea != null)
//				return false;
//		} else if (!numeroLinnea.equals(other.numeroLinnea))
//			return false;
//		return true;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see java.lang.Object#toString()
//	 */
//	@Override
//	public String toString() {
//		return "Factura [numero=" + numero + ", numeroLinnea=" + numeroLinnea
//				+ ", fecha=" + fecha + ", iva=" + iva + ", saldo=" + saldo
//				+ ", extra=" + extra + ", total=" + total + "]";
//	}
//
//}

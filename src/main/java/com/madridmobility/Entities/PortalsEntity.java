package com.madridmobility.Entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.Point;

@Entity
@Table(name = "prueba_portales_2")
public class PortalsEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2952011946324415917L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "geom")
	private MultiPoint coordinates;
	
	@Column(name = "CDMUNI")
	private String cdMuni;
	
	@Column(name = "NUME_CVIA")
	private String numeCvia;
	
	@Column(name = "NUME_TNUM")
	private String numeTnum;
	
	@Column(name = "NUME_NUME")
	private String numeNume;
	
	@Column(name = "NUME_CALI")
	private String numeCali;
	
	@Column(name = "TIPOPK")
	private String tipoPk;
	
	@Column(name = "ETINUM")
	private String etinum;
	
	@Column(name = "PKM")
	private String pkm;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MultiPoint getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(MultiPoint coordinates) {
		this.coordinates = coordinates;
	}

	public String getCdMuni() {
		return cdMuni;
	}

	public void setCdMuni(String cdMuni) {
		this.cdMuni = cdMuni;
	}

	public String getNumeCvia() {
		return numeCvia;
	}

	public void setNumeCvia(String numeCvia) {
		this.numeCvia = numeCvia;
	}

	public String getNumeTnum() {
		return numeTnum;
	}

	public void setNumeTnum(String numeTnum) {
		this.numeTnum = numeTnum;
	}

	public String getNumeNume() {
		return numeNume;
	}

	public void setNumeNume(String numeNume) {
		this.numeNume = numeNume;
	}

	public String getNumeCali() {
		return numeCali;
	}

	public void setNumeCali(String numeCali) {
		this.numeCali = numeCali;
	}

	public String getTipoPk() {
		return tipoPk;
	}

	public void setTipoPk(String tipoPk) {
		this.tipoPk = tipoPk;
	}

	public String getEtinum() {
		return etinum;
	}

	public void setEtinum(String etinum) {
		this.etinum = etinum;
	}

	public String getPkm() {
		return pkm;
	}

	public void setPkm(String pkm) {
		this.pkm = pkm;
	}

}

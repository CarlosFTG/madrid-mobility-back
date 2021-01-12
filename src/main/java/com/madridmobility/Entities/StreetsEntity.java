package com.madridmobility.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;

@Entity
@Table(name = "callejero_madrid_muni")
public class StreetsEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6686432980774344131L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "geom")
	private MultiLineString geom;

	@Column(name = "cdmuni")
	private String cdMuni;
	
	@Column(name = "cdvial")
	private String cdVial;
	
	@Column(name = "cmun")
	private String cMun;
	
	@Column(name = "codvi")
	private String codvi;
	
	@Column(name = "via_tvia")
	private String viaTvia;
	
	@Column(name = "via_part")
	private String viaPart;
	
	@Column(name = "via_lite")
	private String viaLite;
	
	@Column(name = "nombre")
	private String name;
	
	@Column(name = "historico")
	private String historic;
	
	@Column(name = "via_cine")
	private String viaCine;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="nume_cvia", referencedColumnName="cdvial")
	private List<PortalsEntity> portals;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCdMuni() {
		return cdMuni;
	}

	public void setCdMuni(String cdMuni) {
		this.cdMuni = cdMuni;
	}

	public String getCdVial() {
		return cdVial;
	}

	public void setCdVial(String cdVial) {
		this.cdVial = cdVial;
	}

	public String getcMun() {
		return cMun;
	}

	public void setcMun(String cMun) {
		this.cMun = cMun;
	}

	public String getCodvi() {
		return codvi;
	}

	public void setCodvi(String codvi) {
		this.codvi = codvi;
	}

	public String getViaTvia() {
		return viaTvia;
	}

	public void setViaTvia(String viaTvia) {
		this.viaTvia = viaTvia;
	}

	public String getViaPart() {
		return viaPart;
	}

	public void setViaPart(String viaPart) {
		this.viaPart = viaPart;
	}

	public String getViaLite() {
		return viaLite;
	}

	public void setViaLite(String viaLite) {
		this.viaLite = viaLite;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHistoric() {
		return historic;
	}

	public void setHistoric(String historic) {
		this.historic = historic;
	}

	public String getViaCine() {
		return viaCine;
	}

	public void setViaCine(String viaCine) {
		this.viaCine = viaCine;
	}

	public List<PortalsEntity> getPortals() {
		return portals;
	}

	public void setPortals(List<PortalsEntity> portals) {
		this.portals = portals;
	}
	
	public MultiLineString getGeom() {
		return geom;
	}

	public void setGeom(MultiLineString geom) {
		this.geom = geom;
	}
}

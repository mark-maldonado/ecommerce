// CREATED BY MARK MALDOANDO

package de.thi.shop.artikel.beans;

import java.io.Serializable;
 
public class ArtikelBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private int preis;
	private float preisFloat;
	private String preisString;
	private byte[] bild;
	private String bildName;
	private Long kategorieId;
	private String kategorieName;
	
	public ArtikelBean() {	
	}
	
	public ArtikelBean(Long id, String name, int preis, Long kategorieId, String kategorieName) {
		super();
		this.id = id;
		this.name = name;
		this.preis = preis;
		this.kategorieId = kategorieId;
		this.kategorieName = kategorieName;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPreis() {
		return preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}
	
	public float getPreisFloat() {
		return preisFloat;
	}
	
	public void setPreisFloat(float preisFloat) {
		this.preisFloat = preisFloat;
	}
	
	public String getPreisString() {
		return preisString;
	}
	
	public void setPreisString(String preisString) {
		this.preisString = preisString;
	}

	public byte[] getBild() {
		return bild;
	}

	public void setBild(byte[] bild) {
		this.bild = bild;
	}
	
	public String getBildName() {
		return bildName;
	}
	public void setBildName(String bildName) {
		this.bildName = bildName;
	}
	
	public Long getKategorieId() {
		return kategorieId;
	}

	public void setKategorieId(Long idKategorie) {
		this.kategorieId = idKategorie;
	}
	
	public String getKategorieName() {
		return kategorieName;
	}
	
	public void setKategorieName(String kategorieName) {
		this.kategorieName = kategorieName;
	}
}

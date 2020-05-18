// CREATED BY MARK MALDOANDO

package de.thi.shop.einkaufswagen.beans;

import java.io.Serializable;

public class EinkaufswagenBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long artikelId;
	private String name;
	private int preis;
	private String preisString;
	private String bildName;
	private Long kategorieId;
	private String kategorieName;
	private int menge;
	private Long userId;
	
	public EinkaufswagenBean() {	
	}
	
	public EinkaufswagenBean(Long artikelId, String name, int preis, Long kategorieId, String kategorieName, int menge, Long userId) {
		super();
		this.artikelId = artikelId;
		this.name = name;
		this.preis = preis;
		this.kategorieId = kategorieId;
		this.kategorieName = kategorieName;
		this.menge = menge;
		this.userId = userId;
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
	
	public void setArtikelId(Long artikelId) {
		this.artikelId = artikelId;
	}
	
	public Long getArtikelId() {
		return artikelId;
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
	
	public String getPreisString() {
		return preisString;
	}
	
	public void setPreisString(String preisString) {
		this.preisString = preisString;
	}
	
	public String getbildName() {
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
	
	public int getMenge() {
		return menge;
	}
	
	public void setMenge(int menge) {
		this.menge = menge;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}

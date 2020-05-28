//CREATED BY SEYIT ARAR

package de.thi.shop.kategorie.beans;

import java.io.Serializable;

public class KategorieBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String kategorieName;
	private byte[] bild;
	private String bildName;

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getKategorieName() {
		return kategorieName;
	}

	public void setKategorieName(String kategorieName) {
		this.kategorieName = kategorieName;
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
	
}

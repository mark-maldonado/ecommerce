// CREATED BY MARK MALDOANDO

package de.thi.shop.user.beans;

import java.io.Serializable;

public class RegistrierenBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String mail;
	private String passwort;
	private String ort;
	private String strasse;
	private Long hausnummer;
	private String iban;
	private boolean isAdmin;
	
	public RegistrierenBean() {	
	}
	
	public RegistrierenBean(String mail, String passwort, String ort, String strasse, Long hausnummer, String iban, boolean isAdmin) {
		super();
		this.mail = mail;
		this.passwort = passwort;
		this.ort = ort;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.iban = iban;
		this.isAdmin = isAdmin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	
	public Long getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(Long hausnummer) {
		this.hausnummer = hausnummer;
	}
	
	public String getIban() {
		return iban;
	}
	
	public void setIban(String iban) {
		this.iban = iban;
	}
	
	public boolean getIsAdmin() {
		return isAdmin;
	}
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}

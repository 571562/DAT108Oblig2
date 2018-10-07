package no.hvl.dat108;
/**
 * 
 * @author herbo & sondr
 *
 */
public class Vare {
	
	private String namn;
	
	public Vare(String namn) {
		this.namn = namn;
	}
	//Henter navnet på ein vare med stor forbokstav for estetikk
	public String getNamn() {
		return namn = Character.toUpperCase(namn.charAt(0)) + namn.substring(1);
	}
	
	@Override
	public boolean equals(Object o) {
		return namn.equals((((Vare) o).getNamn()));
	}

}

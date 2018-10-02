package no.hvl.dat108;

public class Vare {
	
	private String namn;
	
	public Vare(String namn) {
		this.namn = namn;
	}

	public String getNamn() {
		return namn = Character.toUpperCase(namn.charAt(0)) + namn.substring(1);
	}
	
	@Override
	public boolean equals(Object o) {
	//	return (((Vare) o).getNamn()).equals(namn);
		
		
		
		return namn.equals((((Vare) o).getNamn()));
	}

}

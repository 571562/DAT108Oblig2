package no.hvl.dat108;

import java.util.ArrayList;

/**
 * 
 * @author herbo & sondr
 *
 */
public class HandleVogn {

	private ArrayList<Vare> varer;

	public HandleVogn() {
		varer = new ArrayList<Vare>();
	}

	public void addVare(String namn) {
		varer.add(new Vare(namn));
	}
	
	public void removeVare(String namn) {
		varer.removeIf(vare -> vare.getNamn().equals(namn));
	}

	public ArrayList<Vare> getVarer() {
		return varer;
	}

	@Override
	public String toString() {
		return "HandleVogn [varer=" + varer.toString() + "]";
	}
	//Størrelse og hent er lagd for å gjøre testingen enklere
	public int storLeik() {
		return varer.size();
	}
	
	public String hent(int index) {
		return varer.get(index).getNamn();
	}
	

}

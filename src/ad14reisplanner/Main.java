package ad14reisplanner;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Ik moet bekennen dat ik ben begonnen na de geest maar niet de letter
 * van de opdracht opgenomen te hebben, en het is niet in me opgekomen
 * een int[][] aan te maken om een sparse graph op te slaan.
 * Zelfs voor een non-sparse graph geeft dit geen snelheidswinst als de kanten
 * alleen worden opgevraagd wanneer een iterator ze oplevert
 * @author Sal
 *
 */
public class Main {
	//HashMap als arbitraire implementatie van Dictionary
	public static final String sporenfile = "sporen.txt";
	
	private HashMap<Integer,Station> netwerk;
	
	public static void main(String[] args){
		new Main();
	}
	
	public Main() {
		try {
			LeesSporen spoorData = new LeesSporen(sporenfile);
		} catch (Exception e) {
			System.out.println("Fout: kan " + sporenfile + "niet lezen.");
			e.printStackTrace();
		}
		netwerk = new HashMap<Integer,Station>();
	}
	
	
}

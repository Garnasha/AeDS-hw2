package ad14reisplanner;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Een Stationklasse, compleet met 
 * @author Sal
 *
 */
public class Station 
		implements Dijkstrabaar<Station,Integer>{
	private int nummer;
	private int afstand;
	private Station voorloper;
	private boolean bezocht;
	
	/**
	 * claim: het is onmogelijk in minder dan O(n) alle n/2 stations met
	 * een lager nummer te vragen of dit station er mee verbonden is
	 * argument: dit vereist calls naar O(n) objecten.
	 * conclusie: een ruimtebesparing van een factor 2 levert tijdsverlies
	 * van een factor n op. Pessimalisatie (ondoordachte optimalisatie).
	 * @author Sal
	 *
	 */
	private class Verbinding{
		private Station bestemming;
		private int lengte;
		private Verbinding(Station naar,int ver){
			bestemming=naar;
			lengte=ver;
		}
	};
	
	private ArrayList<Verbinding> kanten;
	
	public Station(int id){
		nummer = id;
		afstand = Integer.MAX_VALUE; //Chosen as INF
		voorloper = null;
		kanten = new ArrayList<Verbinding>();
		bezocht = false;
	}
	/**
	 *  herstelt dit station naar de begintoestand voor Dijkstra's algoritme
	 */
	public void reset(){
		afstand = Integer.MAX_VALUE;
		voorloper = null;
		bezocht = false;
	}
	
	public void verbind(Station naar,int ver){
		kanten.add(new Verbinding(naar,ver));
	}
	
	public void bezoek(HashSet<Station> verkend){
		for(Verbinding i : kanten){
			if(!verkend.contains(i)) verkend.add(i.bestemming);
			i.bestemming.verken(this,i.lengte,verkend);
		}
	}
	
	public void verken(Station ref, int lengte, HashSet<Station> verkend) {
		if(bezocht) return;
		if(ref.getAfstand() + lengte < afstand){
			voorloper = ref;
			afstand = ref.getAfstand() + lengte;
		}
	}
	
	public int compareTo(Station ander) {
		return afstand-ander.getAfstand();
	}
	
	public int getAfstand() {
		return afstand;
	}
	
	public int getID(){
		return nummer;
	}
	
	
}
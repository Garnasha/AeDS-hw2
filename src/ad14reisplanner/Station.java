package ad14reisplanner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	public void dijkstrareset(){
		afstand = Integer.MAX_VALUE;
		voorloper = null;
		bezocht = false;
	}
	
	public void verbind(Station naar,int ver){
		kanten.add(new Verbinding(naar,ver));
	}
	
	public void bezoek(Collection<Station> verkend){
		for(Verbinding i : kanten){
			if(!verkend.contains(i)) verkend.add(i.bestemming);
			i.bestemming.verken(this,i.lengte + afstand,verkend);
		}
	}
	
	public void verken(Station verkenner, Integer viaAfstand, Collection<Station> verkend) {
		if(bezocht) return;
		if(viaAfstand < afstand){
			voorloper = verkenner;
			afstand = viaAfstand;
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
	@Override
	public void dijkstrastart() {
		dijkstrareset();
		afstand = 0;
	}
	
	public ArrayList<Station> route(){
		if(voorloper == null) return new ArrayList<Station>();
		ArrayList<Station> trace = voorloper.route();
		trace.add(this);
		return trace;
		
	}
}
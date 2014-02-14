package ad14reisplanner;

import java.util.List;

/**
 * Dit zou moeten werken, maar de HashMap die ik als dictionary gebruik
 * lijkt zich niet te vullen. TreeMap lost het ook niet op, en een Map is toch
 * nodig om een beetje handig Stations terug te vinden. Documentatie is als 
 * laatste stap niet gelukt in tijd. (En ja, ik heb er wel degelijk >6 uur aan gewerkt)
 * @author Sal
 *
 */
public class Main {
	//sporenfile gaf wat moeite om zich te laten vinden, vandaar een absoluut pad.
	public static final String sporenfile = "/Users/Sal/Documents/GitHub/AeDS-hw2/bin/ad14reisplanner/sporen.txt";
	
	private DijkstraNet<Integer,Station,Integer> netwerk;
	
	public static void main(String[] args){
		new Main();
	}
	
	public Main() {
		LeesSporen spoorData=null;
		try {
			spoorData = new LeesSporen(sporenfile);
		} catch (Exception e) {
			System.out.println("Fout: kan " + sporenfile + " niet lezen.");
			e.printStackTrace();
			return;
		}
		int aantalStations = spoorData.getAantalStations();
		netwerk = new DijkstraNet<Integer,Station,Integer>(2*aantalStations);
		for(int i = 1;i <= aantalStations;++i){
			netwerk.put(i,new Station(i)); //dit zou netwerk moeten laden, doet het niet.
		}
		
		System.out.println(netwerk.keySet().toString());
		
		for(int[] spoor : spoorData.getSporen()){
			System.out.println("verbinding: " + Integer.toString(spoor[0]) +
					"," + Integer.toString(spoor[1]) +
					"," + Integer.toString(spoor[2]));
			netwerk.getNode(spoor[0]).verbind(
					netwerk.getNode(spoor[1]), 
					spoor[2]);
		}
		nijmegenNaarHaag();
	}
	
	private void nijmegenNaarHaag(){
		List<Station> route = netwerk.route(14, 10);
		System.out.println("De kortste route van Nijmegen naar Den Haag is:");
		for(Station i : route){
			System.out.println(i.toString());
		}
	}
	
}

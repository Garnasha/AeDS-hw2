package ad14reisplanner;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Houdt een Dictionary bij met nodes en hun naam voor de buitenwereld.
 * Implementeert Dijkstra's algoritme op deze nodes.
 * @author Sal
 *
 * @param <K>
 * @param <N>
 */
public class DijkstraNet<K,N extends Dijkstrabaar<N,D>,D>{
	private HashMap<K,N> nodes;
	private HashSet<N> bezocht;
	private HashSet<N> verkend;
	private N vertrekpunt;
	
	public DijkstraNet(){
		nodes = new HashMap<K,N>();
		bezocht = new HashSet<N>();
		verkend = new HashSet<N>();
		vertrekpunt = null;
	}
	
	public DijkstraNet(int cap){
		nodes = new HashMap<K,N>(cap);
		bezocht = new HashSet<N>(cap);
		verkend = new HashSet<N>(cap);
		vertrekpunt = null; 
	}
	
	public Set<K> keySet(){
		return nodes.keySet();
	}
	
	public N getNode(K key){
		return nodes.get(key);
	}
	
	public void put(K key,N node){
		System.out.println("newentry:" + key.toString() + "->" + node.toString());
		nodes.put(key, node);
	}
	
	private void dijkstrasetup(N van){
		//wis enige eerdere zoektocht
		for(N i : bezocht) i.dijkstrareset();
		for(N i : verkend) i.dijkstrareset();
		bezocht = new HashSet<N>(nodes.size());
		verkend = new HashSet<N>(nodes.size());
		//zet de nieuwe zoektocht op
		vertrekpunt = van;
		van.dijkstrastart();
		verkend.add(van);
	}
	
	public List<N> route(K van,K naar){
		if(vertrekpunt != nodes.get(van)){
			dijkstrasetup(nodes.get(van));
		}
		if(!bezocht.contains(nodes.get(naar))){
			dijkstraZoekTot(nodes.get(naar));
		}
		return nodes.get(naar).route();
	}

	private void dijkstraZoekTot(N doel) {
		N volgende = Collections.min(verkend);
		while (volgende != doel){
			volgende.bezoek(verkend);
			bezocht.add(volgende);
			if(verkend.isEmpty()) return;//doel is niet bereikbaar
			volgende = Collections.min(verkend);
		}
		//zoektocht klaar, bezoek volgende(==doel) nog om vlaggen goed te zetten.
		volgende.bezoek(verkend);
		return;
	}
}

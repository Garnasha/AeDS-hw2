package ad14reisplanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
		bezocht = new HashSet<>(cap);
		verkend = new HashSet<N>(cap);
		vertrekpunt = null; 
	}
	
	public void put(K key,N node){
		nodes.put(key, node);
	}
	
	private void dijkstrasetup(N van,N naar){
		for(N i : verkend) i.dijkstrareset();
		bezocht = new HashSet<N>(nodes.size());
		verkend = new HashSet<N>(nodes.size());
		vertrekpunt = van;
		nodes.get(van).dijkstrastart();
		verkend.add(van);
	}
	
	public List<N> route(K van,K naar){
		if(!(vertrekpunt == nodes.get(van))){
			dijkstrasetup(nodes.get(van),nodes.get(naar));
		}
		if(!bezocht.contains(nodes.get(naar))){
			dijkstraZoekTot(nodes.get(naar));
		}
		return nodes.get(naar).route();
	}

	private void dijkstraZoekTot(N doel) {
		// TODO Auto-generated method stub
		
	}
}

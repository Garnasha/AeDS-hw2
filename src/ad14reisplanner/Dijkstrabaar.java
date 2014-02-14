package ad14reisplanner;

import java.util.Collection;
import java.util.List;

/**
 * Bevat de vereisten om Dijkstra's algoritme op een graaf met als nodes
 * objecten van een implementerend type los te laten.
 * @author Sal
 *
 * @param <N> Het type van een node
 * @param <D> Het type van een afstand
 */
public interface Dijkstrabaar<N,D> extends Comparable<N>{
	/**
	 * Wist de metadata die voor Dijkstra's algoritme is opgeslagen:
	 * door dit op alle nodes (of in ieder geval alle verkende/bezochte nodes)
	 * toe te passen kan Dijkstra's algoritme opnieuw worden gestart,
	 * mogelijk met een ander startpunt
	 */
	void dijkstrareset();
	
	/**
	 * TODO: vul JavaDoc in
	 * @param verkenner 
	 * @param viaAfstand
	 * @param verkend
	 */
	void verken(N verkenner, D viaAfstand, Collection<N> verkend);
	
	/**
	 * bezoekt een node:
	 * legt de afstand naar en route naar de node vast, en verkent alle
	 * nodes die vanuit deze node bereikbaar zijn.
	 * Ongedefinieerd gedrag als een node wordt bezocht die niet de kortste
	 * voorlopige afstand naar de start heeft van alle niet-bezochte nodes.
	 * @param verkend : de verzameling nodes waarvoor een afstand geschat
	 * maar niet definitief bekend is.
	 */
	void bezoek(Collection<N> verkend);
	
	/**
	 * Vertelt de node dat het het startpunt voor Dijkstra's algoritme is.
	 */
	void dijkstrastart();
	
	List<N> route();
}

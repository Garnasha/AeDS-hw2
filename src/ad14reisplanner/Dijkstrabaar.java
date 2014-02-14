package ad14reisplanner;

import java.util.Collection;
import java.util.List;

/**
 * Bevat de vereisten om Dijkstra's algoritme op een graaf met als nodes
 * objecten van een implementerend type los te laten. Merk op dat het
 * feitelijk zijn van een node in een graaf een kwestie van privates is,
 * en dus niet hier wordt afgedwongen.
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
	 * Controleert of deze node via de node die nu bezocht wordt sneller te
	 * bereiken is dan via de huidige route
	 * @param verkenner de node die nu bezocht wordt
	 * @param viaAfstand de afstand naar deze node, via de verkenner
	 * @param verkend de verzameling nodes waar verken minstens eens op is
	 * aanroepen, maar die niet bezocht zijn. Hier voegt de node zich aan
	 * toe als hij er nog niet in staat en niet bezocht is
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

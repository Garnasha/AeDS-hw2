package ad14reisplanner;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Klasse die een netwerk van verbindingen uit een file leest.
 * Eerste regel van die file bevat het aantal stations.
 * iedere volgende regel bevat een verbinding in de vorm van 3 integers:
 * van naar afstand
 * @author Pieter Koopman
 */
public class LeesSporen
{
	ArrayList<int[]> sporen;
    /**
     * Constructor leest de file en zal de informatie afdrukken ter controle
     * @param fileName de naam van de file met verbindingen tussen stattions
     * @throws java.lang.Exception als er iets fout gaat met openen of lezen van de file
     * 
     * per regel twee stations (van en naar) een een afstand. Alles als integer.
     */
    public LeesSporen(String fileName) throws Exception
    {
        File file = new File(fileName);
        Scanner scanner = new Scanner(new FileReader(file));
        sporen = new ArrayList<int[]>();

        int stations = scanner.nextInt();
        System.out.println("Dit netwerk heeft " + stations + " stations");
        scanner.nextLine();
        
        for (int s = 1; scanner.hasNextLine(); s += 1)
        {
            int van     = scanner.nextInt(); 
            int naar    = scanner.nextInt(); 
            int afstand = scanner.nextInt(); 
            int[] spoor = {van,naar,afstand}; //seemingly, looped decl. is OK.
            
            System.out.println("Er is een spoor " + s + " van " + van +
                               " naar " + naar + " met lengte " + afstand);
            sporen.add(spoor);
            scanner.nextLine();
            
        }
        scanner.close();
    }
}

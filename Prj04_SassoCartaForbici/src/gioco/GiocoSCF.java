package gioco;

import java.util.Scanner;

public class GiocoSCF {

	static int modeSelect = 4;
	static int numOfMatches = 10;
	static String result = " ----- ";
	static String finalResult = " ----- ";
	static int vittP1 = 0;
	static int vittP2 = 0;
	static String gameWinner = " -----";

	public static void main(String[] args) {

		gameSettings();
		avviaPartita(modeSelect, numOfMatches);
	}

	private static void gameSettings() {
		System.out.println("Ciao!  Benvenuto nel gioco Sasso, Carta, Forbici:");
		modeSelect = scegliModalità();
		numOfMatches = durataPartita();
	}

	private static int scegliModalità() {

		System.out.println("Scegli la modalità di gioco. Premi:");

		System.out.println("1 - Singolo	(Player VS PC)");
		System.out.println("2 - Sfida (Player VS PLAYER)");
		System.out.println("3 - Simulazione	(PC VS PC)");
		//System.out.println("4 - Menu Principale");
		System.out.println("0 - Abbandona il gioco");

		Scanner tastiera = new Scanner(System.in);
		int scelta = tastiera.nextInt();

		switch (scelta) {

		case 1:
			System.out.println("Hai scelto la modalità Singolo (Player VS PC)");
			break;

		case 2:
			System.out.println("Hai scelto la modalità Sfida	(Player VS PLAYER) ");
			break;

		case 3:
			System.out.println("Hai scelto la modalità Simulazione	(Per PC VS PC)");
			break;

		case 4:
			// go to main menu
			break;

		case 0:
			System.out.println();
			System.out.println("Ciao, grazie per aver giocato. Alla prossima...!");
			System.out.println();
			System.out.println(" - Programma terminato -");
			System.exit(0);
			break;

		default:
			System.out.println("Opzione non disponibile");
			break;
		}
		return scelta;
	}

	private static int durataPartita() {

		System.out.println("Inserisci il numero di match da disputare:");
		Scanner tastiera = new Scanner(System.in);
		return numOfMatches = tastiera.nextInt();
	}

	private static String sceltaUtente() {

		System.out.println("Scegli uno dei tre oggetti, scrivi in minuscolo:");
		Scanner scelta = new Scanner(System.in);
		String sceltaUtente = scelta.next();
		return sceltaUtente;
	}

	private static String sceltaPC() {

		String sceltaRandom = null;
		double casuale = Math.random(); // da 0.0 a minore di 1

		if (casuale < 0.33) {
			sceltaRandom = "pietra";
		} else if (casuale < 0.66) {
			sceltaRandom = "forbici";
		} else {
			sceltaRandom = "carta";
		}
		System.out.println("Il pc ha scelto " + sceltaRandom);
		return sceltaRandom;
	}

	private static String valutaScelte(String sceltaP1, String sceltaP2) {

		String result = null;

		if (sceltaP1.equals(sceltaP2)) {
			result = "PAREGGIO";
		}
		else {
			
			if (sceltaP1.equals("pietra")) {

				if (sceltaP2.equals("forbici")) {
					result = "GIOCATORE 1";
				} else {
					result = "GIOCATORE 2";
				}
			}

			if (sceltaP1.equals("forbici")) {
				
				if (sceltaP2.equals("carta")) {
					result = "GIOCATORE 1";
				} else {
					result = "GIOCATORE 2";
				}
			}

			if (sceltaP1.equals("carta")) {

				if (sceltaP2.equals("forbici")) {
					result = "GIOCATORE 1";
				} else {
					result = "GIOCATORE 2";
				}
			}

		if (result == "GIOCATORE 1") {
			vittP1++;
		} else if (result == "GIOCATORE 2") {
			vittP2++;
		}
		}
		return result;
	}
	
	private static void stampaRisultatoParziale(String result) {
		
		System.out.println();
		System.out.println(result);
		System.out.println("Vittorie P1= " + vittP1);
		System.out.println("Vittorie P2= " + vittP2);
	}
	
	private static void avviaPartita(int modalità, int numOfMatches) {

		String sceltaP1;
		String sceltaP2;
		String matchWinner = null;
		gameWinner = null;
		
		for (int i = 0; i < numOfMatches; i++) {

		// 1) chiedi al P1 di scegliere un valore (S, C o F)
		if (modeSelect == 1 || modeSelect == 2) {
			sceltaP1 = sceltaUtente();
		} else
			sceltaP1 = sceltaPC();

		// 2) chiedi al P2 di scegliere tra S C F;
		if (modeSelect == 1 || modeSelect == 3) {
			sceltaP2 = sceltaPC();
		} else
			sceltaP2 = sceltaUtente();

		// 3) controlla le scelte di P1 e P2
		matchWinner = valutaScelte(sceltaP1, sceltaP2);

		// 4) stampa risultato del match
		stampaRisultatoParziale("Vince " + matchWinner);
		}
		
		// 5) calcola e stampa il risultato della partita
		stampaRisultatoFinale(vittP1, vittP2);
		
		System.out.println();
		System.out.println("- Partita terminata -");
	}

	private static void stampaRisultatoFinale(int vittP1, int vittP2) {
		
		System.out.println();
		
		if(vittP1 == vittP2) {
			finalResult = " - FINE PARTITA - \n PAREGGIO!! ";
		}
		else if(vittP1 > vittP2) {
			gameWinner = "GIOCATORE1";
			finalResult = " - FINE PARTITA - \n VINCE IL " + gameWinner + "!! CONGRATULAZIONI!!";
		}
		else {
			gameWinner = "GIOCATORE2";
			finalResult = " - FINE PARTITA - \n VINCE IL " + gameWinner + " !! CONGRATULAZIONI!!";
		}
		
		System.out.println();
		System.out.println("RISULTATO:");
		System.out.println("Player 1= " + vittP1);
		System.out.println("Player 2= " + vittP2);
		System.out.println();
		System.out.println(finalResult);
	}
}
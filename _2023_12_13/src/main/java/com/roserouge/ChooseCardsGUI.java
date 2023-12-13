package com.roserouge;

import java.security.SecureRandom;

public class ChooseCardsGUI {	
	private static final String CARD_BACK = "??";
	private static final String SPADE = "\u2660";
	private static final String HEART = "\u2665";
	private static final String DIAMOND = "\u2666";
	private static final String CLUB = "\u2663";

	private static final String[] SUITS = {SPADE, HEART, DIAMOND, CLUB};
	private static final String[] RANKS
			= {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"J", "Q", "K"};

	public static void main(String[] args) {
		int[] deck = new int[52];
		boolean[] isFaceUp = new boolean[52];  // all false by default
			
		// Initialize cards
		for (int i = 0; i < deck.length; i++)
			deck[i] = i;
		
		shuffle(deck);  // shuffle the cards

		// While there are more cards to choose...
		int numFaceUp = 0;
		while (numFaceUp < 49) {
			layOutCards(deck, isFaceUp);
			
			// Ask player to choose a card
			int card = playerChooseCard(deck, "Choose a card");
			
			// Mark the card as face up
			if (!isFaceUp[card]) {  // not face up previously
				isFaceUp[card] = true;
				numFaceUp++;
			}  // do nothing if already face up
		}

		// All cards chosen, lay out cards for the last time
		layOutCards(deck, isFaceUp);
		
		TheiCardGUI.showMessage("Close the main window to quit");
	}
	
	public static int playerChooseCard(int[] deck, String prompt) {
		int card;
		
		do {  // until a card is chosen and taken successfully
			String s = TheiCardGUI.getInputString(prompt).trim();
			card = getCard(s);
			if (card < 0) {
				TheiCardGUI.showMessage("Invalid input, please try again.");
			}
		} while (card < 0);
		
		return card;
	}
	
	public static void shuffle(int[] deck) {
		SecureRandom randomNumbers = new SecureRandom();
		
		for (int i = 0; i < deck.length; i++) {
			// Generate an index randomly
			int index = randomNumbers.nextInt(deck.length);
			int temp = deck[i];
			deck[i] = deck[index]; 
			deck[index] = temp;
		}
	}
	
	public static void layOutCards(int[] deck, boolean[] isFaceUp) {
		TheiCardGUI.clearScreen();
		TheiCardGUI.printf("%7c%6c%6c%6c%6c%6c%6c%n",
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z');  // print col heading

		// Lay out the first 7 x 7 = 49 cards
		for (int card = 0, i = 1; i <= 7; i++) {
			TheiCardGUI.print(i);  // print row heading
			for (int j = 1; j <= 7; j++, card++) {
				if (isFaceUp[card]) {
					TheiCardGUI.printf("%6s", RANKS[deck[card] % 13]
							+ SUITS[deck[card] / 13]);
				} else {
					TheiCardGUI.printf("%6s", CARD_BACK);
				}
			}
			TheiCardGUI.println();
		}

	    // Display the remaining 3 cards
		TheiCardGUI.print("\n3 more cards: ");
		TheiCardGUI.printf(RANKS[deck[49] % 13]
						+ SUITS[deck[49] / 13]);
		TheiCardGUI.printf("%6s",
						RANKS[deck[50] % 13]
						+ SUITS[deck[50] / 13]);
		TheiCardGUI.printf("%6s%n",
						RANKS[deck[51] % 13]
						+ SUITS[deck[51] / 13]);
	}
	
	private static int getCard(String coord) {
		if (coord.length() != 2) {
			return -1;  // invalid coord
		}

		char col, row;
			
		if ('T' <= (col = Character.toUpperCase(coord.charAt(0)))
					&& col <= 'Z') {
			row = coord.charAt(1);
		} else if ('T' <= (col = Character.toUpperCase(coord.charAt(1))) 
				&& col <= 'Z') {
			row = coord.charAt(0);
		} else {
			return -1;  // neither char is valid col
		}
		
		// col confirmed valid
		if (!('1' <= row && row <= '7')) {  // now check row
			return -1;  // invalid row
		}
		
		// Both col and row valid
		return (row - '1') * 7 + col - 'T';
	}
}
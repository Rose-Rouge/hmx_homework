package com.roserouge;

import java.util.Arrays;
import java.security.SecureRandom;

public class CardsLayoutHiddenGUI {	
	private static final String CARD_BACK = "??";
	private static final String SPADE = "\u2660";
	private static final String HEART = "\u2665";
	private static final String DIAMOND = "\u2666";
	private static final String CLUB = "\u2663";

	private static final String[] SUITS = {SPADE, HEART, DIAMOND, CLUB};
	private static final String[] RANKS
			= {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"J", "Q", "K"};
				
	private static boolean isDebugMode;

	public static void main(String[] args) {
		// Scanner input = new Scanner(System.in);   
		
		isDebugMode = args.length > 0 && args[0].equals("-debug");

		int[] deck = new int[52];
			
		// Initialize cards
		for (int i = 0; i < deck.length; i++)
			deck[i] = i;
		
		shuffle(deck);  // shuffle the cards
		layOutCards(deck);  // lay out cards all facing down

		// Ask for choices of 2 cards
		boolean isInvalidInput;
		int c1, c2;
		
		do {
			String s = TheiCardGUI.getInputString("Choose a card").trim();
			c1 = getCard(s);
			if (isInvalidInput = (c1 < 0)) {
				TheiCardGUI.showMessage("Invalid input.  Please try again.");
			}
		} while (isInvalidInput);
		
		layOutCards(deck, c1);
		
		do {
			String s = TheiCardGUI.getInputString("Choose one more card").trim();
			c2 = getCard(s);
			if (isInvalidInput = (c2 < 0)) {
				TheiCardGUI.showMessage("Invalid input.  Please try again.");
			}
		} while (isInvalidInput);
		TheiCardGUI.showMessage("You choose "
					+ RANKS[deck[c1] % 13] + SUITS[deck[c1] / 13]
					+ " and " + RANKS[deck[c2] % 13] + SUITS[deck[c2] / 13]);
		layOutCards(deck, c1, c2);

			/*
			TheiCardGUI.printf("You choose %s%n",
					ranks[deck[card] % 13] + suits[deck[card] / 13]);
			*/
		
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
	
	public static void layOutCards(int[] deck, int... cardsToShow) {
		Arrays.sort(cardsToShow);
		TheiCardGUI.clearScreen();
		TheiCardGUI.printf("%7c%6c%6c%6c%6c%6c%6c%n",
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z');  // print col heading

		// Lay out the first 7 x 7 = 49 cards
		for (int card = 0, show = 0, i = 1; i <= 7; i++) {
			TheiCardGUI.print(i);  // print row heading
			for (int j = 1; j <= 7; j++, card++) {
				String mark; 
				if (show < cardsToShow.length
						&& card == cardsToShow[show]) {
							mark = isDebugMode ? "*": "";
					TheiCardGUI.printf("%6s", mark
							+ RANKS[deck[card] % 13] + SUITS[deck[card] / 13]);
					show++;
				} else {
					TheiCardGUI.printf("%6s", isDebugMode ?
							RANKS[deck[card] % 13] + SUITS[deck[card] / 13] :
							CARD_BACK);
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
	
	public static int getCard(String coord) {
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
package com.roserouge;

import java.util.Scanner;
import java.security.SecureRandom;

public class CardsLayoutGUI {
	public static void main(String[] args) {
		final String SPADE = "\u2660";
		final String HEART = "\u2665";
		final String DIAMOND = "\u2666";
		final String CLUB = "\u2663";
		
		Scanner input = new Scanner(System.in);
		SecureRandom randomNumbers = new SecureRandom();          

		int[] deck = new int[52];
		String[] suits = {SPADE, HEART, DIAMOND, CLUB};
		String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"J", "Q", "K"};
			
		// Initialize cards
		for (int i = 0; i < deck.length; i++)
			deck[i] = i;
		
		// Shuffle the cards
		for (int i = 0; i < deck.length; i++) {
			// Generate an index randomly
			int index = randomNumbers.nextInt(deck.length);
			int temp = deck[i];
			deck[i] = deck[index]; 
			deck[index] = temp;
		}

		// Lay out the first 7 x 7 = 49 cards
		TheiCardGUI.printf("%7c%6c%6c%6c%6c%6c%6c%n",
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
		for (int card = 0, i = 1; i <= 7; i++) {
			TheiCardGUI.print(i);
			for (int j = 1; j <= 7; j++) {
				TheiCardGUI.printf("%6s",
						ranks[deck[card] % 13]
						+ suits[deck[card] / 13]);
				card++;
			}
			TheiCardGUI.println();
		}
		
		// Display the remaining 3 cards
		TheiCardGUI.print("\n3 more cards: ");
		TheiCardGUI.printf(ranks[deck[49] % 13]
						+ suits[deck[49] / 13]);
		TheiCardGUI.printf("%6s",
						ranks[deck[50] % 13]
						+ suits[deck[50] / 13]);
		TheiCardGUI.printf("%6s%n",
						ranks[deck[51] % 13]
						+ suits[deck[51] / 13]);
		
		// Ask for choice of card
		String s = TheiCardGUI.getInputString("Choose a card: ").trim();
		int card = getCard(s);
		if (card >= 0) {
			TheiCardGUI.printf("You choose %s%n",
					ranks[deck[card] % 13] + suits[deck[card] / 13]);
		}
		else {
			TheiCardGUI.println("Invalid input");
		}
		
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
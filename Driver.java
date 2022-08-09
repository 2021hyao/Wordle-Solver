import java.io.*;
import java.util.*;


public class Driver {
	public static void main(String[] args) throws Exception{
		File words = new File("WordleWords.txt");
		ArrayList <String> wordBank = new ArrayList <String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(words))) {
			String str;
			while((str = br.readLine()) != null){
				wordBank.add(str);
			}
		}
		String answer = wordBank.get((int)(Math.random()*2315));
		String guess = "crane";
		System.out.println("answer: " + answer);
		System.out.println("guess: "+ guess);
		
		ArrayList <String> guessedWords = new ArrayList <String>();
		guessedWords.add(guess);
	
		ArrayList <Character>[] grayLet = new ArrayList[5];
		for(int i = 0; i<5; i++) {
			grayLet[i] = new ArrayList<Character>();
		}

		ArrayList <Integer> greenLet = new ArrayList <Integer> ();//index of green letters
		ArrayList <Character>[] yellowLet = new ArrayList[5];//yellow letters
		ArrayList <Character> yellowLetters = new ArrayList<Character>();
		for(int i = 0; i<5; i++) {
			yellowLet[i] = new ArrayList<Character>();
		}
		
		boolean goodGuess = true;
		while(!guess.equals(answer)) {
			
			for(int i = 0; i<wordBank.size(); i++) {
				for(int j = 0; j<5; j++) {
					if(guess.charAt(j) == answer.charAt(j)) {
						greenLet.add(j);
					}
					else if(answer.contains(Character.toString(guess.charAt(j)))) {
						yellowLet[j].add(guess.charAt(j));
						yellowLetters.add(guess.charAt(j));
					}
					else {
						grayLet[j].add(guess.charAt(j));
					}

				}
				goodGuess = true;
				for(int j = 0; j<greenLet.size(); j++) {//checks that guess uses green letters
					if(wordBank.get(i).charAt(greenLet.get(j)) != answer.charAt(greenLet.get(j))) {
						goodGuess = false;
						break;
					}
				}
				
				for(int j = 0; j<5; j++) {
					if(yellowLet[j].contains(wordBank.get(i).charAt(j))){//checks if guess switched placement of yellow letters
						goodGuess = false;
						break;
					}
				}
				
				for(int j = 0; j<yellowLetters.size(); j++) {
					if(!wordBank.get(i).contains(Character.toString(yellowLetters.get(j)))) {//checks that guess at least uses all yellow letters
						goodGuess = false;
						break;
					}
				}
				
				for(int j = 0; j<5; j++) {
					if(grayLet[j].contains(wordBank.get(i).charAt(j))){//checks that guess doesn't use gray letters
						goodGuess = false;
						break;
					}
				}
				
				if(goodGuess && !guessedWords.contains(wordBank.get(i))) {
					guess = wordBank.get(i);
					System.out.println("guess: "+ guess);
					guessedWords.add(guess);
				}
			}
		}
	}
	
}

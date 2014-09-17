package fr.appsolutions.limo;

import java.util.Random;

/**
 * 
 * @author Clément Mouline <clement.mouline@app-solutions.fr>
 * @version 1.0
 * 
 */
public class Generator {
	
	
	/**
	 * This function will generate a random key
	 * @param length - lenght of the key you wish
	 * @return the key
	 */
	public String generateRandomString(int length){
		
		String temp = "";
		
		for(int i=0; i<length; i++){
			temp += Utilitaires.origin[(new Random().nextInt(Utilitaires.origin.length - 1))];
		}
		
		return temp;
	}
	

}

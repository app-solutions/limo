package fr.appsolutions.limo;

/**
 * 
 * @author Clément Mouline <clement.mouline@app-solutions.fr>
 * @version 1.0
 * 
 */
public class Cryptosysteme {


	private String sentence;
	private char[] sentenceArray;
	private String key;
	private char[] keyArray;
	private String keyAle;
	private char[] keyAleArray;

	/**
	 * Empty constructor
	 */
	public Cryptosysteme(){
	}

	/**
	 * Default constructor, with sentence (crypted or uncrypted) and key
	 * 
	 * @param sentence set the crypted or uncrypted sentence
	 * @param key set the key
	 */
	public Cryptosysteme(String sentence, String key){
		this.sentence = sentence;
		this.sentenceArray = sentence.toCharArray();
		this.key = key;
		this.keyArray = key.toCharArray();
	}

	/**
	 * This Function will crypt a sentence with the Vernam technologie
	 * @return Cryptosystem himself
	 * 
	 */
	public Cryptosysteme crytpInVernam(){

		this.keyAle = new Generator().generateRandomString(sentenceArray.length);
		keyAleArray = this.keyAle.toCharArray();

		sentence = wrap(sentenceArray, keyAleArray);
		sentence += wrap(keyAleArray, keyArray);

		return this;
	}


	/**
	 * This function will decrypt a vernam's crypted sentence
	 * @return Cryptosystem himself
	 */
	public Cryptosysteme decryptFromVernam(){

		String temp = "";
		String temp2 = "";

		for(int i=sentenceArray.length/2; i<sentenceArray.length ; i++){
			temp += sentenceArray[i];
		}
		
		for(int i=0; i<sentenceArray.length/2; i++){
			temp2 += sentenceArray[i];
		}

		this.sentence = temp2;
		this.sentenceArray = temp2.toCharArray();
		
		this.keyAle = unwrap(temp.toCharArray(), keyArray);
		this.keyAleArray = this.keyAle.toCharArray();
		this.sentence = unwrap(sentenceArray, keyAleArray);


		return this;
	}


	/**
	 * Private function to wrap a sentence
	 * @param sentence uncrypted sentence
	 * @param key key for wrap
	 * @return crypted sentence
	 */
	private String wrap(char[] sentence, char[] key){

		int tempSPos = 0;
		int tempKPos = 0;
		String temp = "";

		for(int i=0, j=0; i<sentence.length; i++, j++){

			if(j>= key.length)
				j = 0;

			for(int k=0; k< Utilitaires.origin.length; k++){

				if(sentence[i] == Utilitaires.origin[k]){
					tempSPos = k;
				}

				if(key[j] == Utilitaires.origin[k]){
					tempKPos = k;
				}

			}

			temp += Utilitaires.origin[(tempSPos + tempKPos) % Utilitaires.origin.length];

		}

		return temp;

	}

	/**
	 * private function to unwrap a sentence
	 * @param sentence crypted sentence
	 * @param key key for unwrap
	 * @return uncrypted sentence
	 */
	private String unwrap(char[] sentence, char[] key){

		String temp = "";
		int tempSPos = 0;
		int tempKPos = 0;

		for(int i=0, j=0; i<sentence.length; i++, j++){

			if(j>= key.length)
				j = 0;

			for(int k=0; k< Utilitaires.origin.length; k++){

				if(sentence[i] == Utilitaires.origin[k]){
					tempSPos = k;
				}

				if(key[j] == Utilitaires.origin[k]){
					tempKPos = k;
				}

			}

			try{
				temp += Utilitaires.origin[(tempSPos - tempKPos) % Utilitaires.origin.length];
			}catch(ArrayIndexOutOfBoundsException e){
				temp += Utilitaires.origin[(tempSPos - tempKPos) + Utilitaires.origin.length];
			}
		}

		return temp;

	}

	/**
	 * 
	 * @return the sentence crypted or uncrypted
	 */
	public String getResult(){
		return sentence;
	}
	
	/**
	 * Set the sentence crypted or uncrypted
	 * @param sentence
	 */
	public void setSentence(String sentence){
		this.sentence = sentence;
		this.sentenceArray = sentence.toCharArray();
	}

	/**
	 * Set the key
	 * @param key
	 */
	public void setKey(String key){
		this.key = key;
		this.keyArray = key.toCharArray();
	}
}

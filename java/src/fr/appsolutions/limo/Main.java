package fr.appsolutions.limo;

public class Main {

	public static void main(String[] args) {

		/**
		 *  E.g. 1 how to use Limo
		 */
		System.out.println(new Cryptosysteme("HelloWorld", "toto").crytpInVernam().getResult());
		System.out.println(new Cryptosysteme("&ÎGHñsßò°ÙÿùOK'*^Õ%Œ", "toto").decryptFromVernam().getResult());

		
		/**
		 * E.g. 2
		 */
		Cryptosysteme crypt = new Cryptosysteme();
		crypt.setSentence("HelloWorld");
		crypt.setKey("toto");
		crypt.crytpInVernam();

		System.out.println(crypt.getResult());

	}
}

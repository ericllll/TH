package listes;

public class TesteListes {
	/**.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Liste l = new Liste();
		l.ajouteTete("coucou");
		l.ajouteTete("suivant");
		System.out.println(l.toString());
	}

}

package parser.calc02;

/*
Comment� Par: Christopher Lopes
Nom du fichier: Main.java
Pour l'utiliser: apr�s que le scanneur, lcalc.flex, et le parseur, ycalc.cup,
aient �t� cr��s, javac Main.java
Pour le lancer: java Main test.txt
o� test.txt est le fichier de test de notre calculatrice.
*/

/* Import des classes n�cessaires. La classe que nous avons cr�� pour le
parseur, la classe de runtime standard de java et une classe d'e/s.
*/
//import parser;
import java_cup.runtime.Symbol;
import java.io.*;

class Main {
 static boolean do_debug_parse = false;
 static public void main(String argv[]) {
 /* lancer le parseur */
 try {
 //parser p = new parser(new Lexer(new
//FileReader(argv[0])));
 //Object result = p.parse().value;

 } catch (Exception e) {
 /* nettoyer � cet endroit -- �ventuellement relancer e */
 } finally {
 /* terminer proprement ici */
 }
 }
}
package rules;

import java.io.FileReader;

//import rules.Lexer;
import rules.parser;

public class Essai {

	public static void main(String[] args) {
		try {
			parser p = new parser(new Lexer(new FileReader("src/rules/input.txt")));
			Object result = p.parse().value;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package CodingChallenges;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    public final static String PATH = "src/test/java/resources/";
    public static void main( String[] args ) throws Exception {
        if (args.length < 1){
            throw new Exception("Please input file for parsing");
        }
        String filePath = PATH + args[args.length-1];
        Lexer lexer = new Lexer(filePath);
        List<Token> tokens = lexer.parseIntoTokens();

        Parser parser = new Parser();
        parser.parseObject(tokens);
        System.out.println("Successfully Parsed");

    }

    public void printToken(List<Token> tokens){
        for (Token token : tokens) {
            System.out.println("token type: " + token.getTokenType());
            System.out.println("String: " + token.getString());
        }
    }
}

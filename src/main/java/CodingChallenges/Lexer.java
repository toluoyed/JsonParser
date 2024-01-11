package CodingChallenges;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Lexer {
    private final String fileItems;
    private List<Token> tokens;

    public Lexer(String fileName) throws FileNotFoundException {

        File file = new File(fileName);
        tokens = new ArrayList<Token>();
        Scanner sc = new Scanner(file);
        StringBuffer sb = new StringBuffer();
        while(sc.hasNext()) {
            sb.append(sc.nextLine());
        }
        fileItems = sb.toString().replaceAll("\\s", "");

    }

    public List<Token> parseIntoTokens(){
        int current = 0;
        Character[] digits = new Character[]{'0','1','2','3','4','5','6','7','8','9'};

        while(current < fileItems.length()){
            char currChar = fileItems.charAt(current);

            if (currChar == '{'){
                tokens.add(new Token(TokenTypes.LEFT_BRACE));
            }
            else if (currChar == '}'){
                tokens.add(new Token(TokenTypes.RIGHT_BRACE));
            }
            else if (currChar == '['){
                tokens.add(new Token(TokenTypes.LEFT_BRACKET));
            }
            else if (currChar == ']'){
                tokens.add(new Token(TokenTypes.RIGHT_BRACKET));
            }
            else if (currChar == '"'){
                String word = "";
                current++;
                while (fileItems.charAt(current) != '"'){
                    word = word + fileItems.charAt(current);
                    current++;
                }
                tokens.add(new Token(TokenTypes.STRING,word));
            }
            else if (currChar == ':'){
                tokens.add(new Token(TokenTypes.COLON));
            }
            else if (currChar == ','){
                tokens.add(new Token(TokenTypes.COMMA));
            }
            else if(currChar == ' '){
                tokens.add(new Token(TokenTypes.SPACE));
            }
            else if (currChar == 't' && fileItems.charAt(current+1) == 'r' && fileItems.charAt(current+2) == 'u' && fileItems.charAt(current+3) == 'e'){
                tokens.add(new Token(TokenTypes.BOOLEAN_TRUE));
                current+=3;
            }
            else if (currChar == 'f' && fileItems.charAt(current+1) == 'a' && fileItems.charAt(current+2) == 'l' && fileItems.charAt(current+3) == 's' && fileItems.charAt(current+4) == 'e'){
                tokens.add(new Token(TokenTypes.BOOLEAN_FALSE));
                current+=4;
            }
            else if (currChar == 'n' && fileItems.charAt(current+1) == 'u' && fileItems.charAt(current+2) == 'l' && fileItems.charAt(current+3) == 'l'){
                tokens.add(new Token(TokenTypes.NULL));
                current+=3;
            }
            else if(Arrays.asList(digits).contains(currChar)){
                int curr = current;
                String word = "";
                while (Arrays.asList(digits).contains(fileItems.charAt(curr))){
                    word = word + fileItems.charAt(curr);
                    curr++;
                }
                tokens.add(new Token(TokenTypes.NUMBER,word));
                current+=word.length()-1;
            }
            else{
                tokens.add(new Token(TokenTypes.UNKNOWN));
            }
            current++;
        }
        return tokens;
    }

}

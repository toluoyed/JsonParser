package CodingChallenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public void parseObject(List<Token> tokens){

        int current = 0;

        assertHasMoreTokens(tokens, current);
        assertTokenType(tokens.get(current).getTokenType(),TokenTypes.LEFT_BRACE);
        current++;
        while(tokens.get(current).getTokenType() != TokenTypes.RIGHT_BRACE){
            assertTokenType(tokens.get(current).getTokenType(), TokenTypes.STRING);
            current++;
            assertTokenType(tokens.get(current).getTokenType(),TokenTypes.COLON);
            current++;
            assertTokenTypes(tokens.get(current).getTokenType(), Arrays.asList(TokenTypes.STRING, TokenTypes.NULL, TokenTypes.BOOLEAN_TRUE, TokenTypes.BOOLEAN_FALSE, TokenTypes.NUMBER, TokenTypes.LEFT_BRACE, TokenTypes.LEFT_BRACKET));
            if(tokens.get(current).getTokenType() == TokenTypes.LEFT_BRACE){
                current +=parseJson(tokens.subList(current,tokens.size()));
            }
            if (tokens.get(current).getTokenType() == TokenTypes.LEFT_BRACKET){
                current += parseArray(tokens.subList(current,tokens.size()));
            }
            current++;
            if ((tokens.size()-1) - current > 1){
                assertTokenType(tokens.get(current).getTokenType(), TokenTypes.COMMA);
                current++;
            }

        }
    }

    private int parseJson(List<Token> tokens){
        int count = 0;
        assertHasMoreTokens(tokens, count);
        assertTokenType(tokens.get(count).getTokenType(),TokenTypes.LEFT_BRACE);
        count++;
        while(tokens.get(count).getTokenType() != TokenTypes.RIGHT_BRACE){
            assertTokenType(tokens.get(count).getTokenType(), TokenTypes.STRING);
            count++;
            assertTokenType(tokens.get(count).getTokenType(),TokenTypes.COLON);
            count++;
            assertTokenTypes(tokens.get(count).getTokenType(), Arrays.asList(TokenTypes.STRING, TokenTypes.NULL, TokenTypes.BOOLEAN_TRUE, TokenTypes.BOOLEAN_FALSE, TokenTypes.NUMBER, TokenTypes.LEFT_BRACE, TokenTypes.LEFT_BRACKET));
            if(tokens.get(count).getTokenType() == TokenTypes.LEFT_BRACE){
                count +=parseJson(tokens.subList(count,tokens.size()));
            }
            if (tokens.get(count).getTokenType() == TokenTypes.LEFT_BRACKET){
                count +=parseArray(tokens.subList(count,tokens.size()));
            }
            count++;
            if (tokens.get(count).getTokenType() != TokenTypes.RIGHT_BRACE){
                assertTokenType(tokens.get(count).getTokenType(), TokenTypes.COMMA);
                count++;
            }

        }
        return count;
    }

    private int parseArray(List<Token> tokens){
        int count = 0;
        assertHasMoreTokens(tokens, count);
        assertTokenType(tokens.get(count).getTokenType(),TokenTypes.LEFT_BRACKET);
        count++;
        while(tokens.get(count).getTokenType() != TokenTypes.RIGHT_BRACKET){
            assertTokenTypes(tokens.get(count).getTokenType(), Arrays.asList(TokenTypes.STRING, TokenTypes.NULL, TokenTypes.BOOLEAN_TRUE, TokenTypes.BOOLEAN_FALSE, TokenTypes.NUMBER, TokenTypes.LEFT_BRACKET, TokenTypes.LEFT_BRACE));
            if(tokens.get(count).getTokenType() == TokenTypes.LEFT_BRACE){
                count +=parseJson(tokens.subList(count,tokens.size()));
            }
            if (tokens.get(count).getTokenType() == TokenTypes.LEFT_BRACKET){
                count +=parseArray(tokens.subList(count,tokens.size()));
            }
            count++;
            if (tokens.get(count).getTokenType() != TokenTypes.RIGHT_BRACKET){
                assertTokenType(tokens.get(count).getTokenType(), TokenTypes.COMMA);
                count++;
            }
        }
        return count;
    }

    private void assertTokenType(TokenTypes token, TokenTypes expectedToken){
        if(token != expectedToken){
            System.err.println("Token mismatch: Expected: " + expectedToken + " but found " + token);
            System.exit(1);
        }
    }

    private void assertTokenTypes(TokenTypes token, List<TokenTypes> expectedToken){
        if(!expectedToken.contains(token)){
            System.err.println("Token mismatch: Expected: " + expectedToken + " but found " + token);
            System.exit(1);
        }
    }

    private void assertHasMoreTokens(List<Token> tokens, int current){
        if (current >= tokens.size()){
            System.err.println("Invalid Json");
            System.exit(1);
        }
    }

}

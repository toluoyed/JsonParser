package CodingChallenges;

public class Token {

    private final TokenTypes tokenTypes;
    private String value = null;

    public Token(TokenTypes tokenTypes, String value){
        this.tokenTypes = tokenTypes;
        this.value = value;
    }

    public Token(TokenTypes tokenTypes){
        this.tokenTypes = tokenTypes;
    }

    public TokenTypes getTokenType(){
        return tokenTypes;
    }

    public String getString(){
        return value;
    }

}

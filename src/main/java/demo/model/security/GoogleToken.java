package demo.model.security;


public class GoogleToken {

    public String getIdToken() {
        return idToken;
    }

    public GoogleToken setIdToken(String idToken) {
        this.idToken = idToken;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public GoogleToken setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public GoogleToken setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public String getTokenType() {
        return tokenType;
    }

    public GoogleToken setTokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public GoogleToken setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    private String idToken;
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private int expiresIn;

}

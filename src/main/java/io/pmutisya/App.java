package io.pmutisya;

import com.unboundid.ldap.sdk.*;

/**
 * Hello world!
 */
public class App {
    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_PORT = 389;
    private static final String DEFAULT_USERNAME = "username";
    private static final String DEFAULT_PASSWORD = "password";

    public static void main(String[] args) throws LDAPException {

        String host = getLdapHost();
        int port = getLdapPort();
        String username = getLdapUsername();
        String password = getLdapPassword();

        System.out.println("Hello. About to authenticate user given " +
                "\nhost : " + host +
                "\nport : " + port +
                "\nusername : " + username +
                "\npassword : " + password);

        boolean authenticated = authenticateUser(host, port, username, password);
        System.out.println("Authenticated : " + authenticated);
    }

    private static boolean authenticateUser(String host, int port, String username, String password) throws LDAPException {
        LDAPConnection ldapConnection = new LDAPConnection(host, port);
        BindRequest bindRequest = new SimpleBindRequest(username, password);

        BindResult bindResult = ldapConnection.bind(bindRequest);

        if (bindResult.getResultCode().equals(ResultCode.SUCCESS)) {
            System.out.println("Successfully authenticated user");
            System.out.println("Result " + bindRequest);
            return true;
        }
        ldapConnection.close();
        return false;
    }

    private static String getLdapHost() {
        String host = System.getProperty("host");

        if (host == null || host.trim().isEmpty()) {
            return DEFAULT_HOST;
        }
        return host;
    }

    private static int getLdapPort() {
        String port = System.getProperty("port");
        if (port != null) {
            try {
                return Integer.parseInt(port);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return DEFAULT_PORT;
    }

    private static String getLdapUsername() {
        String username = System.getProperty("username");

        if (username == null || username.trim().isEmpty()) {
            return DEFAULT_USERNAME;
        }

        return username;
    }

    private static String getLdapPassword() {
        String password = System.getProperty("password");

        if (password == null || password.trim().isEmpty()) {
            return DEFAULT_PASSWORD;
        }

        return password;
    }

}

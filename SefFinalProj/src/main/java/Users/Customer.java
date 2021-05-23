package Users;

public class Customer extends User {
    /**
     * @param username
     * @param password
     * @param fullName
     * @param role
     */
    public Customer(String username, String password, String fullName, String role) {
        super(username, password, fullName, role);
    }
}

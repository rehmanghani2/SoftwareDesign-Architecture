

/**
 *
 * @author Ghani
 */
public class UserApplication {
    public static void main(String[] args) {
        System.out.println("This is");
        UserController uc = new UserController();
        uc.createUser(null);
        System.out.println(uc.getAllUsers());
    }
}
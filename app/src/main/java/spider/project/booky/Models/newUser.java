package spider.project.booky.Models;

/**
 * Created by praba1110 on 20/6/16.
 */
public class newUser {

    public newUser(String email,String name,String password){
        this.email=email;
        this.name=name;
        this.encrypted_password=password;
    }

    String email;
    String name;
    String encrypted_password;
}

package es.uniovi.miw.ws.usersws.webservices;

import es.uniovi.miw.ws.usersws.models.User;
import es.uniovi.miw.ws.usersws.repositories.UserRepository;

import javax.jws.WebService;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@WebService(endpointInterface = "es.uniovi.miw.ws.usersws.webservices.IUsersWS")
public class UsersWS implements IUsersWS {

    @Override
    public User register(String username, String password, String address) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAddress(address);
        user.setAnonymous(false);
        return new UserRepository().addUser(user);
    }

    @Override
    public User registerAnonymous(String address) {
        User user = new User();
        Random random = new Random();
        int randomId = random.nextInt(100000);
        String username = "Anonymous" + randomId;
        while (new UserRepository().findByUsername(username) == null) {
            username = "Anonymous" + randomId;
            randomId = random.nextInt(100000);
        }
        user.setUsername("Anonymous" + randomId);
        user.setPassword("Password" + randomId);
        user.setAddress(address);
        user.setAnonymous(true);
        return new UserRepository().addUser(user);
    }

    @Override
    public User updateUser(String username, String password, String address, boolean isAnonymous) {
        if (new UserRepository().findByUsername(username) == null) {
            return null;
        }
        return new UserRepository().updateUser(username, password, address, isAnonymous);
    }

    @Override
    public User deleteUser(Long id) {
        if (new UserRepository().findById(id) == null) {
            return null;
        }
        return new UserRepository().deleteUser(id);
    }

    @Override
    public List<User> getUsers() {
        return new UserRepository().findAll();
    }

    @Override
    public User getUser(String username) {
        return new UserRepository().findByUsername(username);
    }

    @Override
    public User login(String username, String password) {
        User user = new UserRepository().findByUsername(username);
        if (user == null) {
            return null;
        }
        return Objects.equals(user.getPassword(), password) ? user : null;
    }
}

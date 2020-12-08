package com.example.demo.repository;

import com.example.demo.lockedException;
import com.example.demo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private final Map<Integer, User> usersDatabase;

    public UserRepository() {
        usersDatabase = new HashMap<>();

        usersDatabase.put(1, new User("cracker", "cracker1234", true, 0));
        usersDatabase.put(2, new User("marry", "marietta!#09", true, 0));
        usersDatabase.put(3, new User("silver", "$silver$", true, 0));
    }

    public boolean checkLogin(final String login, final String password) throws lockedException {
        // TODO: Prosze dokonczyc implementacje...

        for(int i = 1; i <= usersDatabase.size(); i++){
            if (usersDatabase.get(i).getLogin().equals(login)){
                if (usersDatabase.get(i).getPassword().equals(password)){
                    if (usersDatabase.get(i).isActive()) {
                        return true;
                    }else{
                        throw new lockedException();
                    }
            }else {
                    usersDatabase.get(i).setIncorrectLoginCounter(usersDatabase.get(i).getIncorrectLoginCounter() + 1);

                    if (usersDatabase.get(i).getIncorrectLoginCounter() == 3) {
                        usersDatabase.get(i).setActive(false);
                    }
                    return false;
                }
            }
        }
        return false;
    }
}

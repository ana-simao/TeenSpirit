package org.academiadecodigo.bootcamp.services;

import org.academiadecodigo.bootcamp.fakeDatabase.FakeDatabase;
import org.academiadecodigo.bootcamp.model.ImagePost;
import org.academiadecodigo.bootcamp.model.User;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private Map<Integer, User> userMap;

    public UserService(){
        FakeDatabase database = new FakeDatabase();
        userMap = database.populateMap();
    }


    public Map<Integer, User> getUserMap() {
        return userMap;
    }


    public void addUser(User user){
        userMap.put(user.getId(), user);
    }


    public void removeUser(User user){
        userMap.remove(user.getId());
    }

    public User findUser(Integer id){
       return userMap.get(id);
    }


}

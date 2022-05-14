package com.pat.exam.service;

import com.pat.exam.service.dto.UserDTO;
import com.pat.exam.service.dto.UserdocDTO;
import com.pat.exam.table.UserTable;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {

    //LEFT JOIN - se obtienen los users y, si tienen, sus docs
    List<UserdocDTO> getUserDocs();

    //GET ALL USERS FROM USERS TABLE
    List<UserDTO> getUsers();

    //ADD NEW USER
    UserTable addUser(UserTable userTable);

    //UPDATE USERNAME
    UserTable updateUser(Long userId,UserTable userTable);
}

package com.pat.exam.service.impl;

import com.pat.exam.repository.UserRepository;
import com.pat.exam.service.UserService;
import com.pat.exam.service.dto.UserDTO;
import com.pat.exam.service.dto.UserdocDTO;
import com.pat.exam.table.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserdocDTO> getUserDocs() {

        List<UserdocDTO>userdocDTOS = new ArrayList<>();
        try {
            String query =
                    """
                    SELECT U.USER, D.DOC 
                    FROM USERS U
                    LEFT JOIN DOCUMENTS D
                    ON U.USER_ID = D.USER_ID;
                    """;

            userdocDTOS =jdbcTemplate.query(query,
                    (rs,rowNum) ->
                            new UserdocDTO(
                                    rs.getString("USER"),
                                    rs.getString("DOC")
                            ));
        }catch(Exception e){
            e.printStackTrace();
        }

        //Devuelve un array vacío en el caso de error
        return userdocDTOS;
    }

    @Override
    public List<UserDTO> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(),false)
                .map(obj -> new UserDTO(
                        obj.getUserId(),
                        obj.getUser(),
                        obj.getComment()))
                .toList();
    }

    @Override
    public UserTable addUser(UserTable userTable) {
        UserTable userTable1 = new UserTable();
        userTable1.setUser(userTable.getUser());
        userTable1.setComment(userTable.getComment());
        System.out.println("SE HA RELLENADO EL USER TABLE ANTES DE LLAMAR A REPOSITORY");
        UserTable newUser = userRepository.save(userTable1);
        return newUser;
    }

    @Override
    public UserTable updateUser(Long userId, UserTable userTable) {
        if (userRepository.existsById(userId)){
            return userRepository.save(userTable);
        }else{
            return null;
        }
    }
}

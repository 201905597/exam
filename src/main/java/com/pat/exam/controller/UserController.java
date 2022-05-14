package com.pat.exam.controller;

import com.pat.exam.service.UserService;
import com.pat.exam.service.dto.UserDTO;
import com.pat.exam.service.dto.UserdocDTO;
import com.pat.exam.table.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userdocs")
    public ResponseEntity<HashMap<String, List<String>>> getUserDocs(){
        List<UserdocDTO> userdocDTOS = new ArrayList<>();
        userdocDTOS = userService.getUserDocs(); // List de objetos con user, doc

        HashMap<String,List<String>> userDocsHm = new HashMap<>();
        List<String> docs = new ArrayList<>();

        for (UserdocDTO userdocDTO : userdocDTOS){
            String user = userdocDTO.getUser();
            String doc = userdocDTO.getDoc();
            if (doc == null){
                //Si ese usuario no tiene ningún documento, añadimos "No tiene" a su lista
                doc = "No tiene documentos";
            }

            if (userDocsHm.containsKey(user)){
                //Si el hashmap ya tiene una entrada para ese usuario, saco su lista de docs y la actualizo
                docs = userDocsHm.get(user); //Saco sus docs
                docs.add(doc); //Los actualizo
                userDocsHm.replace(user,docs); //Cambio la antigua lista de docs por la nueva
            }else{
                //Si el hashmap no tiene aún una entrada para ese usuario, la creo y añado su doc a su lista
                docs = new ArrayList<>(); //Reinicio el hashmap para rellenarlo desde 0
                docs.add(doc); //Añado el documento
                userDocsHm.put(user,docs); //Añado la entrada user -> lista de docs al hashmap
            }
        }

        return ResponseEntity.ok().body(userDocsHm);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> userDTOS = new ArrayList<>();
        userDTOS = userService.getUsers();
        if (userDTOS.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(userDTOS,HttpStatus.OK);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<UserTable> addUser(@RequestBody UserTable userTable){
        try{
            UserTable newUser = userService.addUser(userTable);
            System.out.println("SE HA CREADO EL NEWUSER VACIO EN EL CONTROLLER");
            return new ResponseEntity<>(newUser,HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserTable> updateUser(@PathVariable Long id, @RequestBody UserTable userTable){
        UserTable newUser = userService.updateUser(id,userTable);
        if (newUser == null){
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.ok().body(newUser);
    }
}

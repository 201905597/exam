package com.pat.exam.controller;

import com.pat.exam.repository.UserRepository;
import com.pat.exam.service.dto.UserDTO;
import com.pat.exam.service.dto.UserdocDTO;
import com.pat.exam.table.UserTable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerE2ETest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void usersE2ETest(){

        //Given
        List<UserDTO> users = StreamSupport.stream(userRepository.findAll().spliterator(),false)
                .map(obj -> new UserDTO(
                        obj.getUserId(),
                        obj.getUser(),
                        obj.getComment()))
                .toList();

        //When
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/users";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<UserDTO>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<UserDTO>>() {}
        );

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(users);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void userdocsJoinE2ETest(){

        //Given
        String query = "SELECT U.USER, D.DOC FROM USERS U LEFT JOIN DOCUMENTS D ON U.USER_ID = D.USER_ID";

        List<UserdocDTO> userdocDTOS =jdbcTemplate.query(query,
                (rs,rowNum) ->
                        new UserdocDTO(
                                rs.getString("USER"),
                                rs.getString("DOC")
                        ));

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

        //When
        String url = "http://localhost:" + Integer.toString(port) + "/api/v1/userdocs";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<HashMap<String, List<String>>> result = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<HashMap<String, List<String>>>() {}
        );

        //Then
        then(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(result.getBody()).isEqualTo(userDocsHm);
    }
}

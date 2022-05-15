# SIMULACRO DE EXAMEN DE PAT

## PARTE 1 [DONE, con HTML]
### Hacer un proyecto de Spring Boot tal que, dado el modelo de datos, se pinten en un HTML los usuarios y los documentos a los que tienen acceso, en forma de tabla. Si no tienen acceso a ninguno, deberá aparecer "No tiene documentos" en la tabla. 
Para esta primera parte se implementa un DTO específico (UserdocDTO) para lo que se pide, que solo incluye el nombre de usuario y sus documentos. Las clases que se han desarrollado para esta primera parte son:
- UserdocDTO
- DocTable
- UserTable
- UserService y UserServiceImpl (método getUserDocs)
- UserController (@GetMapping("/userdocs"))
- userdocs.html
- funciones.js (función showUserDocs)

## PARTE 2 [DONE, con HTML]
### Implementar los métodos necesarios para añadir un nuevo usuario a la tabla USERS.
Se implementa un @PostMapping en el UserController.

## PARTE 3 [DONE, sin HTML]
### Implementar los métodos necesarios para eliminar un documento de la base de datos.
Se implementa un @DeleteMapping en el DocumentController.

## PARTE 4 [DONE, sin HTML]
### Implementar los métodos necesarios para actualizar el nombre de usuario de un usuario en particular.
Se implementa un @UpdateMapping en el UserController.

## PARTE 5 [DONE]
### Implementar prueba E2E
Se implementa
- Test E2E del @GetMapping de usuarios (all users)
- Test E2E del @GetMapping de user + doc join

## Dependencias de Spring utilizadas
- Lombok
- Spring Web
- Spring Data JDBC
- Validation
- H2
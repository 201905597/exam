//PARTE 1: LEFT JOIN de USERS y DOCS
async function showUserDocs(table){

    event.preventDefault();

    //Fetch de los datos
    let res = await fetch("/api/v1/userdocs",{
        method : 'GET',
        headers : {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        }
    });

    if (res.status == 200){

        //Saco los datos de la api
        const data = await res.json();
        console.log(data);

        //Borro lo anterior
        table.innerHTML = "";

        //Creo el header y el cuerpo de la tabla
        let thead = document.createElement('thead');
        let tbody = document.createElement('tbody');
        table.appendChild(thead);
        table.appendChild(tbody);

        //Primera fila de la tabla
        let row_1 = document.createElement('tr');
        let heading_1 = document.createElement('th');
        heading_1.innerHTML = "USER";
        let heading_2 = document.createElement('th');
        heading_2.innerHTML = "DOCUMENTS";
        row_1.appendChild(heading_1);
        row_1.appendChild(heading_2);
        thead.appendChild(row_1);

        //Body de la tabla
        for (var [key, value] of Object.entries(data)) {
            console.log(key + ' ' + value);
            //USER (primera columna)
            let row = document.createElement('tr');
            let row_data1 = document.createElement('td');
            row_data1.innerHTML = key;
            //DOCUMENTS (segunda columna)
            let row_data2 = document.createElement('td');
            row_data2.innerHTML = value;

            row.appendChild(row_data1);
            row.appendChild(row_data2);

            tbody.appendChild(row);
        }

    }else{
        alert("¡Vaya! No se ha podido resolver tu petición");
    }
}

//PARTE 2: AÑADIR UN USUARIO
async function addUser(user,comment){

    event.preventDefault();
    user = user.toString();
    console.log(user);
    comment = comment.toString();
    console.log(comment);

    if (user == "" || comment == ""){
        alert("Por favor, completa todos los campos");
    }else{
        //Comprobación de validez del nombre de usuario
        let uservalid = await validUser(user);
        console.log(uservalid);

        if(uservalid){

            //No se incluye el ID porque se crea automáticamente siguiendo el orden
            const dataObj = {
                "user" : user,
                "comment" : comment,
            };

            // Inserción del nuevo usuario
            let res = await fetch("/api/v1/users",{
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(dataObj)
            });


        }else{
            validu.innerHTML = '<p style="color:red;">Ya existe un usuario con este nombre</p>';
        }
    }

}

async function validUser(user){

    let res = await fetch("/api/v1/users",{
        method : 'GET',
        headers : {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        }
    });

    let valid = true;

    if (res.status == 200){
        const data = await res.json();
        console.log(data);
        for (let i = 0; i<data.length; i++){
            let userInfo = data[i];
            let userName = userInfo["user"];
            if (userName == user){
                valid = false;
            }
        }
        return valid;
    }else{
        alert("¡Vaya! No se ha podido resolver tu petición");
        return false;
    }
}
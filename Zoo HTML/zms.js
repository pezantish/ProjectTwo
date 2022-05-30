'use strict'

let masterDiv = document.querySelector("#master");
let contentsDiv = document.querySelector("#contents");
let table = document.querySelector("#allTable");
let popupUpdate = document.querySelector("#popupUpdate");
let popupCreate = document.querySelector("#popupCreate");
let searchForm = document.querySelector("#searchForm");
let searchBar = document.querySelector("#searchBar");

// Buttons
let getAllBtn = document.querySelector("#GetAll");
let addNewBtn = document.querySelector("#addNew");
let submitUpdateBtn = document.querySelector("#update");
let submitCreateBtn = document.querySelector("#create");
let searchBtn = document.querySelector("#searchBut");


let getByID = () => {
    let id = searchForm.elements[0].value;
    axios.get(`http://localhost:8080/animal/readById/${id}`)
        .then((response) => {
            table.innerHTML = "<tr><th>ID</th><th>Name</th><th>Age</th><th>Species</th><th>Enclosure</th><th></th></tr>";
            console.log("BOO");
            let entry = response.data;
            view(entry);
        })
        .catch((err) => {
            console.error(err);
        });
}

let getAll = () => {
    console.log("HELLO")
    axios.get("http://localhost:8080/animal/readAll")
        .then((response) => {
            table.innerHTML = "<tr><th>ID</th><th>Name</th><th>Age</th><th>Species</th><th>Enclosure</th><th></th></tr>";
            for (let entry of response.data) {
                view(entry);
            }
        })
        .catch((err) => {
            console.error(err);
        });
}

let view = (entry) => {
    let tableRow = document.createElement("tr");
    let cellID = document.createElement("td");
    let cellName = document.createElement("td");
    let cellSpecies = document.createElement("td");
    let cellAge = document.createElement("td");
    let cellEnclosureId = document.createElement("td");
    let editBtn = document.createElement("Button");
    editBtn.innerHTML = "Edit";
    editBtn.addEventListener("click", function () { openEdit(entry.id); });
    editBtn.id = `Edit${entry.id}`;
    let deleteBtn = document.createElement("Button");
    deleteBtn.innerHTML = "Delete";
    deleteBtn.addEventListener("click", function () { deleteID(entry.id); });
    deleteBtn.id = `Delete${entry.id}`;
    cellID.innerHTML = `${entry.id}`;
    cellName.innerHTML = `${entry.name}`;
    cellSpecies.innerHTML = `${entry.species}`;
    cellAge.innerHTML = `${entry.age}`;
    cellEnclosureId.innerHTML = `${entry.enclosureId}`;
    tableRow.appendChild(cellID);
    tableRow.appendChild(cellName);
    tableRow.appendChild(cellAge);
    tableRow.appendChild(cellSpecies);
    tableRow.appendChild(cellEnclosureId);
    tableRow.appendChild(editBtn);
    tableRow.appendChild(deleteBtn);
    table.appendChild(tableRow);
    contentsDiv.appendChild(table);
    masterDiv.appendChild(contentsDiv);

}

let deleteID = (idVal) => {
    let str = `http://localhost:8080/animal/delete/${idVal}`;
    axios.delete(str)
        .then((response) => {
            getAll();
        })
        .catch((err) => {
            console.error(err);
        });
}

let openEdit = (idVal) => {
    let idLabel = document.getElementById("idInput");
    idLabel.innerHTML = idVal;
    popupUpdate.style.display = "block";
    popupCreate.style.display = "none";
}

let openCreate = () => {
    popupCreate.style.display = "block";
    popupUpdate.style.display = "none";
}

let submitUpdate = () => {
    let input = document.getElementById("formUpdate");
    let idLabel = document.getElementById("idInput");
    let activeID = idLabel.innerHTML;
    let newVal = {
        "name": input.elements[0].value,
        "age": input.elements[2].value,
        "species": input.elements[1].value,
        "enclosureId": input.elements[3].value
    }
    let str = `http://localhost:8080/animal/replace/${activeID}`;
    axios.put(str, newVal)
        .then((response) => {
            getAll();
            popupUpdate.style.display = "none";
            input.reset();
        })
        .catch((err) => {
            console.error(err);
        });

}

let submitCreate = () => {
    let input = document.getElementById("formCreate");
    let newVal = {
        "name": input.elements[0].value,
        "age": input.elements[2].value,
        "species": input.elements[1].value,
        "enclosureId": input.elements[3].value
    }
    let str = `http://localhost:8080/animal/create`;
    axios.post(str, newVal)
        .then((response) => {
            getAll();
            popupCreate.style.display = "none";
            input.reset();
        })
        .catch((err) => {
            console.error(err);
        });

}

getAll();
searchBtn.addEventListener("click", getByID);
getAllBtn.addEventListener("click", getAll);
addNewBtn.addEventListener("click", openCreate);
submitUpdateBtn.addEventListener("click", submitUpdate);
submitCreateBtn.addEventListener("click", submitCreate);
// FOR H2:
// INSERT INTO "animal" ("name", "species", "age", "enclosure_id") VALUES('George', 'Gorilla', 7, 'LBX'), ('Mary', 'Gorilla', 8, 'LBX'); 
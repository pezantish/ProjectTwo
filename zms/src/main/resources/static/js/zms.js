'use strict'

let masterDiv = document.querySelector("#mainContent");
let contentsDiv = document.querySelector("#contents");
let table = document.querySelector("#allTable");
let popupUpdate = document.querySelector("#popupUpdate");
let popupCreate = document.querySelector("#popupCreate");
let searchForm = document.querySelector("#searchForm");
let searchBar = document.querySelector("#searchBar");
let formUpdate = document.getElementById("formUpdate");
let formCreate = document.getElementById("formCreate");

// Buttons
let getAllBtn = document.querySelector("#GetAll");
let addNewBtn = document.querySelector("#addNew");
let submitUpdateBtn = document.querySelector("#update");
let submitCreateBtn = document.querySelector("#create");
let searchBtn = document.querySelector("#searchBut");


let getByID = () => {
    let id = searchForm.elements[0].value;
    let typeReg = /^[0-9]+$/;
    if (!id.match(typeReg)) {
        searchForm.elements[0].style.borderColor = "red";
        alert(`Make sure your inputs match the required format: 
            \n- Search by ID: Number`);
    } else {
        searchForm.elements[0].style.borderColor = "rgb(220, 99, 0)";
        axios.get(`http://localhost:8080/animal/readById/${id}`)
            .then((response) => {
                table.innerHTML = "<tr><th>ID</th><th>Name</th><th>Age</th><th>Species</th><th>Enclosure</th><th></th></tr>";
                console.log("BOO");
                let entry = response.data;
                view(entry);
            })
            .catch((err) => {
                console.error(err);
                alert(`An error has occured. It might be that your ID does not exist!`)
            });
    }
}

let getAll = () => {
    console.log("Getting all results");
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
    editBtn.classList.add("buttons")
    let deleteBtn = document.createElement("Button");
    deleteBtn.innerHTML = "Delete";
    deleteBtn.addEventListener("click", function () { deleteID(entry.id); });
    deleteBtn.classList.add("buttons")
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
    console.log("Update pressed");
    formCreate.reset();
    let newVal = {};
    let idLabel = document.getElementById("idInput");
    let activeID = idLabel.innerHTML;
    let typeList = [/^[a-z]+$/i, /^[0-9]+$/, /^[a-z]+$/i, /^[a-z]{3}$/i];
    let valid = true;
    for (let i = 0; i < formUpdate.elements.length - 1; i++) {
        if (formUpdate.elements[i].value && !formUpdate.elements[i].value.match(typeList[i])) {
            formUpdate.elements[i].style.borderColor = "red";
            valid = false;
        } else {
            formCreate.elements[i].style.borderColor = "rgb(220, 99, 0)";
        }
    }
    if (!valid) {
        alert(`Make sure your inputs match the required format: 
        \n- Name: String
        \n- Age: Number
        \n- Species: String
        \n- Enclosure: String of Length 3`);
    } else {
        axios.get(`http://localhost:8080/animal/readById/${activeID}`)
            .then((response) => {
                table.innerHTML = "<tr><th>ID</th><th>Name</th><th>Age</th><th>Species</th><th>Enclosure</th><th></th></tr>";
                let vals = response.data;
                newVal["name"] = vals.name;
                newVal["age"] = vals.age;
                newVal["species"] = vals.species;
                newVal["enclosureId"] = vals.enclosureId;
                let name = formUpdate.elements[0].value;
                let age = formUpdate.elements[1].value;
                let species = formUpdate.elements[2].value;
                let enclosureId = formUpdate.elements[3].value;
                console.log(`ONE ${newVal["enclosureId"]}`);
                if (name) newVal["name"] = name;
                if (age) newVal["age"] = age;
                if (species) newVal["species"] = species;
                if (enclosureId) newVal["enclosureId"] = enclosureId;
                console.log(`TWO ${newVal["enclosureId"]}`);
                console.log(`THREE ${newVal}`);
                console.log(`FOUR ${newVal["name"]}`);
                let str = `http://localhost:8080/animal/replace/${activeID}`;
                axios.put(str, newVal)
                    .then((response) => {
                        getAll();
                        formUpdate.reset();
                    })
                    .catch((err) => {
                        console.error(err);
                    });

            })
            .catch((err) => {
                console.error(err);
                alert(`An error has occured. It might be that your ID does not exist!`)
            });

    }
}

let submitCreate = () => {
    formUpdate.reset();
    let typeList = [/^[a-z]+$/i, /^[0-9]+$/, /^[a-z]+$/i, /^[a-z]{3}$/i];
    let valid = true;
    for (let i = 0; i < formCreate.elements.length - 1; i++) {
        if (!formCreate.elements[i].value.match(typeList[i])) {
            formCreate.elements[i].style.borderColor = "red";
            valid = false;
        } else {
            formCreate.elements[i].style.borderColor = "rgb(220, 99, 0)";
        }
    }
    console.log(newVal);
    if (!valid) {
        alert(`Make sure your inputs match the required format: 
        \n- Name: String
        \n- Age: Number
        \n- Species: String
        \n- Enclosure: String of Length 3`);
    } else {
        let newVal = {
            "name": formCreate.elements[0].value,
            "age": formCreate.elements[1].value,
            "species": formCreate.elements[2].value,
            "enclosureId": formCreate.elements[3].value.toUpperCase()
        }

        let str = `http://localhost:8080/animal/create`;
        axios.post(str, newVal)
            .then((response) => {
                getAll();
                formCreate.reset();
            })
            .catch((err) => {
                console.error(err);
            });
    }
}

getAll();
searchBtn.addEventListener("click", getByID);
getAllBtn.addEventListener("click", getAll);
addNewBtn.addEventListener("click", openCreate);
submitUpdateBtn.addEventListener("click", submitUpdate);
submitCreateBtn.addEventListener("click", submitCreate);

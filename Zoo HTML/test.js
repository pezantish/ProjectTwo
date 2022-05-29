let mainDiv = document.querySelector("#Contents");
let table = document.querySelector("#allTable");

// Buttons
let getAllBtn = document.querySelector("#GetAll");
let addNewBtn = document.querySelector("#addNew");

let getAll = () => {
    console.log("HELLO")
    axios.get("http://localhost:8080/animal/readAll")
        .then((response) => {
            table.innerHTML = "<tr><th>ID</th><th>Name</th><th>Age</th><th>Species</th><th>Enclosure</th><th></th></tr>";
            for (let entry of response.data) {
                let tableRow = document.createElement("tr");
                let cellID = document.createElement("td");
                let cellName = document.createElement("td");
                let cellSpecies = document.createElement("td");
                let cellAge = document.createElement("td");
                let cellEnclosureId = document.createElement("td");
                let editBtn = document.createElement("Button");
                editBtn.innerHTML = "Edit";
                editBtn.addEventListener("click", function() { updateID(entry.id); });
                editBtn.id = `Edit${entry.id}`;
                let deleteBtn = document.createElement("Button");
                deleteBtn.innerHTML = "Delete";
                deleteBtn.addEventListener("click", function() { deleteID(entry.id); });
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
                mainDiv.appendChild(table);
            }
        })
        .catch((err) => {
            console.error(err);
        });
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

let updateID = (idVal) => {
    let newVal = {
        "name":"Greg",
        "age" : "12",
        "species":"Gorilla",
        "enclosureId":"POO"
    }
    let str = `http://localhost:8080/animal/replace/${idVal}`;
    axios.put(str, newVal)
        .then((response) => {
            getAll();
        })
        .catch((err) => {
            console.error(err);
        });
}

let addID = () => {
    let newVal = {
        "name":"Greg",
        "age" : "14",
        "species":"Gorilla",
        "enclosureId":"WEE"
    }
    let str = "http://localhost:8080/animal/create";
    axios.post(str, newVal)
        .then((response) => {
            getAll();
        })
        .catch((err) => {
            console.error(err);
        });
}

getAll();
getAllBtn.addEventListener("click", getAll);
addNewBtn.addEventListener("click", addID);

// FOR H2:
// INSERT INTO "animal" ("name", "species", "age", "enclosure_id") VALUES('George', 'Gorilla', 7, 'LBX'), ('Mary', 'Gorilla', 8, 'LBX'); 
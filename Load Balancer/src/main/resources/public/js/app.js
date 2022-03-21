const endpoint = `http://localhost:4567/log`;
function saveLog() {
  let value = $(`#value`).val();
  $.ajax({
    method: 'POST',
    url: endpoint,
    data: value,
    success: function() {
      getLogs();
    },
    error: function(error) {
      $(`#result`).text('Se produjo un error');
    }
  })
}
function getLogs(){
  $.ajax({
    method: 'GET',
    url: endpoint,
    success: function(data) {
      createTable(JSON.parse(data));
    },
    error: function(error) {
      $(`#result`).text('Se produjo un error');
    }
  })
}

function createTable(data) {
  //Create a HTML Table element.
  let table = document.createElement("table");
  table.border = "1";

  //Get the count of columns.
  let columnCount = 2;

  //Add the header row.
  let row = table.insertRow(-1);
  for (let i = 0; i < columnCount; i++) {
      let headerCell = document.createElement("th");
      headerCell.innerHTML = Object.keys(data[0])[i];
      row.appendChild(headerCell);
  }

  //Add the data rows.
  for (let i = 1; i < data.length; i++) {
      row = table.insertRow(-1);
      for (let j = 0; j < columnCount; j++) {
          let cell = row.insertCell(-1);
          cell.innerHTML = Object.values(data[i])[j];
      }
  }

  let tableContainer = document.getElementById("tableContainer");
  tableContainer.innerHTML = "";
  tableContainer.appendChild(table);
}
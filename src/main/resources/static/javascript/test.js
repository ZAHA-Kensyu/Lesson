'use strict';
document.addEventListener('DOMContentLoaded', function () {
  fetch('/api/products')
    .then(response => {
      if (response.status === 400) {
        console.log('失敗');
      } else {
        console.log('成功');
        response.json().then(data => {
          addTable(data);
        });
      }
    });

    const addButton = document.getElementById('addButton');
    addButton.addEventListener('click',()=>{
        const productName = document.getElementById('productNameInput').value;
        const productPrice = document.getElementById('productPriceInput').value;

        console.log(productName);
        console.log(productPrice);

        const formData = {
        productName: productName,
        productPrice: productPrice
        };


        console.log(formData.productName);
        console.log(formData.productPrice);
        const jsonPayLoad = JSON.stringify(formData);
        console.log(jsonPayLoad);

        fetch('/api/product',{
            method:'POST',
            headers:{
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response =>{
            console.log("POSTリクエストを行いました。");
        })
    })

    function addTable(data) {
        var table = document.createElement('table');
      
        var thead = document.createElement('thead');
        var headerRow = document.createElement('tr');
      
        var headers = ['id', 'name', 'price','選択'];
      
        headers.forEach(function (header) {
          var th = document.createElement('th');
          th.textContent = header;
          headerRow.appendChild(th);
        });
      
        thead.appendChild(headerRow);
        table.appendChild(thead);
      
        var tbody = document.createElement('tbody');
        data.forEach(function (rowData) {
          var row = document.createElement('tr');
          headers.forEach(function (header) {
            var cell = document.createElement('td');
            if (header === '選択') {
              var radio = document.createElement('input');
              radio.type = 'radio';
              radio.name = 'product';
              radio.value = rowData['id'];
              cell.appendChild(radio);
            } else {
              cell.textContent = rowData[header];
            }
            row.appendChild(cell);
          });
          tbody.appendChild(row);
        });
        table.appendChild(tbody);
      
        var tableParent = document.getElementById('table-userlist');
        tableParent.appendChild(table);

        table.addEventListener('change', function (event) {
        var selectedRadio = event.target;
            if (selectedRadio.type === 'radio' && selectedRadio.name === 'product') {
            var selectedValue = selectedRadio.value;
            console.log('選択されたラジオボタンの値:', selectedValue);
        }
        });
    }
});
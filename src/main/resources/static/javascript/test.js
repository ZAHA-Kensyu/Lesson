'use strict';
document.addEventListener('DOMContentLoaded', function () {

  access();
  function access() {
    fetch('/api/products')
      .then(response => {
        if (response.status === 400) {
          console.log('失敗');
        } else {
          console.log('成功');
          response.json().then(data => {
            createTable(data);
          });
        }
      });
  }

  //テーブル作成
  function createTable(data) {
    const headers = ['id', 'name', 'price', '選択'];
    const table = document.createElement('table');//tableタグを作成
    table.setAttribute('id', 'table');
    table.setAttribute('class', 'table');
    createTbHeder(table, headers);
    createTbBody(table, data, headers);
    selectRadio(table);
  };

  //テーブル更新
  function updateTable(updateData) {
    const myTable = document.getElementById('table');
    if (myTable) {
      myTable.remove(); // 既存のテーブル要素を削除
    }
    
    const headers = ['id', 'name', 'price', '選択']; // ヘッダー情報を定義
    const table = document.createElement('table');
    table.setAttribute('id', 'table');
    table.setAttribute('class', 'table');
    createTbHeder(table, headers);
    createTbBody(table, updateData, headers); // updateData を渡す
    selectRadio(table);
    
    const tableParent = document.getElementById('table-userlist');
    tableParent.appendChild(table);

  }

  //テーブルヘッダー作成
  function createTbHeder(table, headers) {
    const thead = document.createElement('thead');
    const headerRow = document.createElement('tr');

    headers.forEach(function (header) {
      const th = document.createElement('th');
      th.textContent = header;
      headerRow.appendChild(th);
    });

    thead.appendChild(headerRow);
    table.appendChild(thead);

    const tableParent = document.getElementById('table-userlist');
    tableParent.appendChild(table);
  };

  //テーブルボディ作成
  function createTbBody(table, data, headers) {
    const tbody = document.createElement('tbody');

    data.forEach(function (rowData) {
      const row = document.createElement('tr');
      headers.forEach(function (header) {
        const cell = document.createElement('td');
        if (header === '選択') {
          const radio = document.createElement('input');
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
  }

  //ラジオボタンを選択した時に編集項目に反映
  function selectRadio(table) {
    table.addEventListener('change', function (event) {
      var selectedRadio = event.target;
      if (selectedRadio.type === 'radio' && selectedRadio.name === 'product') {
        var row = selectedRadio.parentNode.parentNode;

        const idData = row.cells[0].textContent;
        const nameData = row.cells[1].textContent;
        const priceData = row.cells[2].textContent;
        const editIdField = document.getElementById('editProductIdInput');
        const editNameField = document.getElementById('editProductNameInput');
        const editPriceField = document.getElementById('editProductPriceInput');

        editIdField.value = idData;
        editNameField.value = nameData;
        editPriceField.value = priceData;
      }
    });
  }

  //追加ボタン
  const addButton = document.getElementById('addButton');
  addButton.addEventListener('click', () => {
    const productName = document.getElementById('productNameInput').value;
    const productPrice = document.getElementById('productPriceInput').value;

    const formData = {
      id: null,
      name: productName,
      price: productPrice
    };

    fetch('/api/product', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(formData)
    })
      .then(response => {
        console.log("POSTリクエストを行いました。");
        if (response.status === 200) {
          // レスポンスが成功した場合、updateTableを呼び出し
          fetch('/api/products')
            .then(response => {
              if (response.status === 400) {
                console.log('失敗');
              } else {
                console.log('成功');
                response.json().then(data => {
                  updateTable(data);
                });
              }
            });
        }
      })
      .catch(error => {
        console.log("エラーが発生しました:", error);
      });
  });

  //編集ボタン
  const editButton = document.getElementById('editButton');
  editButton.addEventListener('click', () => {
    const result = window.confirm("更新してもよろしいでしょうか？");
    console.log(`ダイアログ結果${result}`);
    if (result) {
      const editFormId = document.getElementById('editProductIdInput').value;
      const editFormName = document.getElementById('editProductNameInput').value;
      const editFormPrice = document.getElementById('editProductPriceInput').value;

      const updateData = {
        id: editFormId,
        name: editFormName,
        price: editFormPrice
      };

      fetch(`/api/product/${editFormId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(updateData)
      })
        .then(response => {
          console.log("PUTリクエストを行いました。");
          if (response.status === 200) {
            // レスポンスが成功した場合、updateTableを呼び出し
            fetch('/api/products')
              .then(response => {
                if (response.status === 400) {
                  console.log('失敗');
                } else {
                  console.log('成功');
                  response.json().then(data => {
                    updateTable(data);
                  });
                }
              });
          }
        })
        .catch(error => {
          console.log("エラーが発生しました:", error);
        });
    }
  });

  //削除ボタン
  const deleteButton = document.getElementById('deleteButton');
  deleteButton.addEventListener('click', () => {
    const result = window.confirm("削除してもよろしいでしょうか？");
    if (result) {
      const deleteId = document.getElementById('editProductIdInput').value;

      fetch(`/api/product/${deleteId}`, {
        method: 'DELETE'
      })
        .then(response => {
          console.log("DELETEリクエストを行いました。");
          if (response.status === 200) {
            // レスポンスが成功した場合、updateTableを呼び出し
            fetch('/api/products')
              .then(response => {
                if (response.status === 400) {
                  console.log('失敗');
                } else {
                  console.log('成功');
                  response.json().then(data => {
                    updateTable(data);
                  });
                }
              });
          }
        });
    }
  });
});
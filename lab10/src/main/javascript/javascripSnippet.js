// Inefficient loop handling and excessive DOM manipulation
function updateList(items) {
    let list = document.getElementById("itemList");
    list.innerHTML = "";
    for (let i = 0; i < items.length; i++) {
        let listItem = document.createElement("li");
        listItem.innerHTML = items[i];
        list.appendChild(listItem);
    }
}

//Código mejorado
function updateList(items) {
    const list = document.getElementById("itemList");

    // usar DocumentFragment para evitar múltiples reflows y repaints
    const fragment = document.createDocumentFragment();

    items.forEach(item => { //Usar forEach para mejorar la legibilidad
        const listItem = document.createElement("li");
        listItem.textContent = item; // Usar textContent, es más rápido y seguro cuando solo necesitas insertar texto
        fragment.appendChild(listItem);
    });

    list.innerHTML = ""; // Actualizar solo uan vez el DOM
    list.appendChild(fragment); // Agregar todo en una sola operación
}

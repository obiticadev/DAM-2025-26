function openTab(evt, tabName) {
    const tabContents = document.getElementsByClassName("tab-content");
    for (let i = 0; i < tabContents.length; i++) {
        tabContents[i].classList.remove("active");
    }
    const tabBtns = document.getElementsByClassName("tab-btn");
    for (let i = 0; i < tabBtns.length; i++) {
        tabBtns[i].classList.remove("active");
    }
    document.getElementById(tabName).classList.add("active");
    evt.currentTarget.classList.add("active");
}

// Actividad 1: Particiones
function updatePartition() {
    const age = parseInt(document.getElementById('ageSlider').value);
    document.getElementById('ageDisplay').textContent = age;
    const result = document.getElementById('partitionResult');

    // Reset table rows
    document.querySelectorAll('#partitionTable tbody tr').forEach(tr => tr.classList.remove('active-row'));

    let rowId = '';
    let message = '';
    let type = 'success';

    if (age < 0) {
        rowId = 'row-invalid-low';
        message = "Valor Inválido: La edad no puede ser negativa.";
        type = 'error';
    } else if (age <= 12) {
        rowId = 'row-child';
        message = "Partición Válida (Niño): Se aplica descuento del 50%.";
    } else if (age <= 64) {
        rowId = 'row-adult';
        message = "Partición Válida (Adulto): Tarifa estándar.";
    } else if (age <= 120) {
        rowId = 'row-senior';
        message = "Partición Válida (Senior): Se aplica descuento del 30%.";
    } else {
        rowId = 'row-invalid-high';
        message = "Valor Inválido: Edad fuera del rango lógico humano.";
        type = 'error';
    }

    if (rowId) {
        document.getElementById(rowId).classList.add('active-row');
    }

    result.textContent = message;
    result.className = `result-box ${type}`;
}

// Actividad 2: Valores Límite
function updateBoundaryVisual() {
    const val = parseInt(document.getElementById('boundarySlider').value);
    document.getElementById('boundaryDisplay').textContent = val;
    const visualResult = document.getElementById('boundaryVisualResult');

    if (val >= 10 && val <= 50) {
        visualResult.textContent = "ACEPTADO";
        visualResult.style.color = "var(--success)";
    } else {
        visualResult.textContent = "RECHAZADO";
        visualResult.style.color = "var(--error)";
    }
}

function checkBoundaries() {
    const inputs = [
        parseInt(document.getElementById('b1').value),
        parseInt(document.getElementById('b2').value),
        parseInt(document.getElementById('b3').value),
        parseInt(document.getElementById('b4').value),
        parseInt(document.getElementById('b5').value),
        parseInt(document.getElementById('b6').value)
    ];

    // Límites esperados: 9, 10, 11 y 49, 50, 51
    const correctValues = [9, 10, 11, 49, 50, 51];
    const userValues = inputs.filter(n => !isNaN(n)).sort((a, b) => a - b);

    // Check if user found all unique correct values
    const uniqueUserValues = [...new Set(userValues)];
    const allFound = correctValues.every(val => uniqueUserValues.includes(val));

    const result = document.getElementById('boundaryResult');

    if (allFound && uniqueUserValues.length === 6) {
        result.textContent = "¡Correcto! Has identificado perfectamente los valores frontera (n-1, n, n+1).";
        result.className = "result-box success";
    } else {
        result.textContent = "Incorrecto. Busca los valores justo en el borde y sus vecinos inmediatos (ej: si el límite es 10, prueba 9, 10 y 11).";
        result.className = "result-box error";
    }
}

// Actividad 3: Tabla de Decisión
function updateDecisionTable() {
    const userExists = document.getElementById('userExists').checked;
    const passCorrect = document.getElementById('passCorrect').checked;
    const accountActive = document.getElementById('accountActive').checked;
    const result = document.getElementById('decisionResult');

    // Reset rows
    document.querySelectorAll('#decisionTable tbody tr').forEach(tr => tr.classList.remove('active-row'));

    let activeRule = '';
    let outcome = '';

    if (!userExists) {
        activeRule = 'rule-1';
        outcome = "Error: Usuario no encontrado.";
    } else if (!passCorrect) {
        activeRule = 'rule-2';
        outcome = "Error: Contraseña incorrecta.";
    } else if (!accountActive) {
        activeRule = 'rule-3';
        outcome = "Error: Cuenta bloqueada o inactiva.";
    } else {
        activeRule = 'rule-4';
        outcome = "Éxito: Acceso permitido.";
    }

    if (activeRule) {
        document.getElementById(activeRule).classList.add('active-row');
    }

    result.textContent = outcome;
    result.className = activeRule === 'rule-4' ? "result-box success" : "result-box error";
}

// Actividad 4: Estados
let currentState = "s-new";

function updateStateUI() {
    document.querySelectorAll('.state-node').forEach(n => n.classList.remove('active'));
    const node = document.getElementById(currentState);
    if (node) node.classList.add('active');

    const result = document.getElementById('stateResult');
    result.textContent = `Estado actual: ${currentState.replace('s-', '').toUpperCase()}`;
    result.className = "result-box info";
}

function triggerAction(action) {
    if (action === 'reset') {
        currentState = "s-new";
        updateStateUI();
        return;
    }

    let nextState = null;
    let errorMsg = "";

    // Lógica de transición
    switch (currentState) {
        case "s-new":
            if (action === "pay") nextState = "s-paid";
            else if (action === "cancel") { alert("Pedido Cancelado"); currentState = "s-new"; updateStateUI(); return; }
            break;
        case "s-paid":
            if (action === "ship") nextState = "s-shipped";
            else if (action === "cancel") { alert("Pedido Cancelado y Reembolsado"); currentState = "s-new"; updateStateUI(); return; }
            break;
        case "s-shipped":
            if (action === "deliver") nextState = "s-delivered";
            break;
        case "s-delivered":
            // Estado final
            break;
    }

    if (nextState) {
        currentState = nextState;
        updateStateUI();
        const result = document.getElementById('stateResult');
        result.className = "result-box success";
        result.textContent = `Transición exitosa a: ${nextState.replace('s-', '').toUpperCase()}`;
    } else {
        const result = document.getElementById('stateResult');
        result.textContent = `Acción inválida: No puedes ejecutar '${action}' desde el estado '${currentState.replace('s-', '').toUpperCase()}'.`;
        result.className = "result-box error";
    }
}

// Actividad 5: Bugs
let bugsFound = new Set();
function huntBugs() {
    const val = document.getElementById('bugInput').value;
    const list = document.getElementById('bugList');
    const status = document.getElementById('bugResult');

    let newBug = null;

    if (val === "") newBug = "Bug #1: Input vacío (NullPointer/Validation Error)";
    else if (val.length > 20) newBug = "Bug #2: Buffer Overflow (String demasiado largo)";
    else if (val.includes("<script>") || val.includes("alert(")) newBug = "Bug #3: XSS (Cross Site Scripting)";

    if (newBug) {
        if (!bugsFound.has(newBug)) {
            bugsFound.add(newBug);
            const li = document.createElement('li');
            li.textContent = newBug;
            list.appendChild(li);
            status.textContent = `Bugs encontrados: ${bugsFound.size}/3`;
            status.className = "result-box success";
        } else {
            status.textContent = "¡Ya encontraste este bug! Intenta otro.";
            status.className = "result-box info";
        }
    } else {
        status.textContent = "El sistema manejó esta entrada correctamente. Sigue intentando romperlo.";
        status.className = "result-box error";
    }

    if (bugsFound.size === 3) {
        status.textContent = "¡Felicidades! Has encontrado todas las vulnerabilidades.";
        status.className = "result-box success";
    }
}

// Inicialización
document.addEventListener('DOMContentLoaded', () => {
    updatePartition();
    updateDecisionTable();
    updateStateUI();
    updateBoundaryVisual();
});

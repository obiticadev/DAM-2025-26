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
function checkPartition() {
    const age = parseInt(document.getElementById('ageInput').value);
    const result = document.getElementById('partitionResult');
    const segments = document.querySelectorAll('.bar-segment');
    
    segments.forEach(s => s.classList.remove('active'));
    
    if (isNaN(age)) {
        result.textContent = "Por favor, introduce un número válido.";
        result.className = "result-box error";
        return;
    }

    if (age < 0 || age > 120) {
        result.textContent = "Valor inválido (Fuera de rango lógico).";
        result.className = "result-box error";
    } else if (age <= 12) {
        result.textContent = "Partición: Niño. Descuento aplicado: 50%.";
        result.className = "result-box success";
        document.getElementById('p-child').classList.add('active');
    } else if (age <= 64) {
        result.textContent = "Partición: Adulto. Sin descuento.";
        result.className = "result-box success";
        document.getElementById('p-adult').classList.add('active');
    } else {
        result.textContent = "Partición: Senior. Descuento aplicado: 30%.";
        result.className = "result-box success";
        document.getElementById('p-senior').classList.add('active');
    }
}

// Actividad 2: Valores Límite
function checkBoundaries() {
    // Rango 10 - 50
    // Límites: 9, 10, 11 ... 49, 50, 51
    const inputs = [
        parseInt(document.getElementById('b1').value),
        parseInt(document.getElementById('b2').value),
        parseInt(document.getElementById('b3').value),
        parseInt(document.getElementById('b4').value),
        parseInt(document.getElementById('b5').value),
        parseInt(document.getElementById('b6').value)
    ];

    const correctValues = [9, 10, 11, 49, 50, 51];
    const userValues = inputs.filter(n => !isNaN(n)).sort((a,b) => a-b);
    
    const isCorrect = correctValues.every(val => userValues.includes(val)) && userValues.length === 6;
    const result = document.getElementById('boundaryResult');

    if (isCorrect) {
        result.textContent = "¡Correcto! Has identificado todos los valores frontera críticos.";
        result.className = "result-box success";
    } else {
        result.textContent = "Incorrecto. Recuerda: Límite-1, Límite, Límite+1 para ambos extremos (10 y 50).";
        result.className = "result-box error";
    }
}

// Actividad 3: Tabla de Decisión
function checkDecision() {
    const userExists = document.getElementById('userExists').checked;
    const passCorrect = document.getElementById('passCorrect').checked;
    const accountActive = document.getElementById('accountActive').checked;
    const prediction = document.getElementById('loginPrediction').value;
    const result = document.getElementById('decisionResult');

    let actualOutcome = "error";
    if (userExists && passCorrect) {
        if (accountActive) {
            actualOutcome = "success";
        } else {
            actualOutcome = "locked";
        }
    } else {
        actualOutcome = "error";
    }

    if (prediction === actualOutcome) {
        result.textContent = "¡Correcto! Tu predicción coincide con la lógica del sistema.";
        result.className = "result-box success";
    } else {
        result.textContent = `Incorrecto. El sistema devolvió: ${actualOutcome.toUpperCase()}. Revisa las condiciones.`;
        result.className = "result-box error";
    }
}

// Actividad 4: Estados
let currentState = "s-new";
const validTransitions = {
    "s-new": { action: "pay", next: "s-paid" },
    "s-paid": { action: "ship", next: "s-shipped" },
    "s-shipped": { action: "deliver", next: "s-delivered" },
    // Cancelaciones
    "s-new": { action: "cancel", next: "s-cancelled" },
    "s-paid": { action: "cancel", next: "s-cancelled" }
};

function updateStateUI() {
    document.querySelectorAll('.state-node').forEach(n => n.classList.remove('active'));
    const node = document.getElementById(currentState);
    if(node) node.classList.add('active');
    
    const result = document.getElementById('stateResult');
    result.textContent = `Estado actual: ${currentState.replace('s-', '').toUpperCase()}`;
    result.className = "result-box";
}

function triggerAction(action) {
    // Manejo especial para transiciones múltiples desde un estado
    let nextState = null;
    
    // Lógica simple de transición
    if (currentState === "s-new" && action === "pay") nextState = "s-paid";
    else if (currentState === "s-new" && action === "cancel") nextState = "s-new"; // Reset visual demo
    else if (currentState === "s-paid" && action === "ship") nextState = "s-shipped";
    else if (currentState === "s-shipped" && action === "deliver") nextState = "s-delivered";
    else if (action === "cancel" && (currentState === "s-new" || currentState === "s-paid")) {
        alert("Pedido Cancelado (Fin del flujo)");
        currentState = "s-new";
        updateStateUI();
        return;
    }

    if (nextState) {
        currentState = nextState;
        updateStateUI();
    } else {
        const result = document.getElementById('stateResult');
        result.textContent = `Acción inválida: No puedes hacer '${action}' desde el estado actual.`;
        result.className = "result-box error";
        setTimeout(updateStateUI, 2000);
    }
}
updateStateUI();

// Actividad 5: Bugs
let bugsFound = new Set();
function huntBugs() {
    const val = document.getElementById('bugInput').value;
    const list = document.getElementById('bugList');
    const status = document.getElementById('bugResult');
    
    let newBug = null;

    if (val === "") newBug = "Bug #1: Input vacío no controlado (NullPointer)";
    else if (val.length > 20) newBug = "Bug #2: Desbordamiento de buffer (String muy largo)";
    else if (val.includes("<script>")) newBug = "Bug #3: Vulnerabilidad XSS detectada";
    
    if (newBug && !bugsFound.has(newBug)) {
        bugsFound.add(newBug);
        const li = document.createElement('li');
        li.textContent = newBug;
        list.appendChild(li);
    }

    status.textContent = `Bugs encontrados: ${bugsFound.size}/3`;
    if (bugsFound.size === 3) {
        status.className = "result-box success";
        status.textContent += " ¡Felicidades! Eres un QA experto.";
    }
}

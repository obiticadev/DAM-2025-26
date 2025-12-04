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

// Actividad 1: Sentencias
function runStatement() {
    const x = parseInt(document.getElementById('stmtSlider').value);
    document.getElementById('stmtDisplay').textContent = x;

    const lines = [
        document.getElementById('l1'),
        document.getElementById('l2'),
        document.getElementById('l3'),
        document.getElementById('l4'),
        document.getElementById('l5'),
        document.getElementById('l6')
    ];

    // Reset
    lines.forEach(l => l.classList.remove('executed'));

    // Ejecución simulada
    lines[0].classList.add('executed'); // function def
    lines[1].classList.add('executed'); // if check

    const result = document.getElementById('stmtResult');

    if (x > 100) {
        lines[2].classList.add('executed'); // console.log
        lines[3].classList.add('executed'); // close if
        result.textContent = "¡Éxito! Has ejecutado la línea crítica (Statement Coverage 100%).";
        result.className = "result-box success";
    } else {
        result.textContent = "Línea crítica no alcanzada. Intenta un valor mayor.";
        result.className = "result-box info";
    }

    lines[4].classList.add('executed'); // return
    lines[5].classList.add('executed'); // close func
}

// Actividad 2: Ramas
let branchTrueCovered = false;
let branchFalseCovered = false;

function runBranch() {
    const age = parseInt(document.getElementById('branchSlider').value);
    document.getElementById('branchDisplay').textContent = age;

    if (age >= 18) {
        branchTrueCovered = true;
        document.getElementById('branchTrue').classList.add('covered');
    } else {
        branchFalseCovered = true;
        document.getElementById('branchFalse').classList.add('covered');
    }

    const coverage = (branchTrueCovered && branchFalseCovered) ? 100 : 50;
    const result = document.getElementById('branchResult');
    result.textContent = `Cobertura Actual: ${coverage}%`;

    if (coverage === 100) {
        result.className = "result-box success";
        result.textContent += " ¡Excelente! Has cubierto ambas decisiones.";
    } else {
        result.className = "result-box info";
    }
}

// Actividad 3: Complejidad
function checkComplexity(val) {
    const result = document.getElementById('complexResult');
    // 3 if/while + 1 = 4
    if (val === 4) {
        result.textContent = "¡Correcto! V(G) = 3 predicados + 1 = 4.";
        result.className = "result-box success";
    } else {
        result.textContent = "Incorrecto. Recuerda: V(G) = P + 1 (donde P es el número de nodos predicado).";
        result.className = "result-box error";
    }
}

// Actividad 4: Caminos
let pathsFound = new Set();
function runPath() {
    const a = document.getElementById('pathA').checked;
    const b = document.getElementById('pathB').checked;

    // Update Visual Graph
    const nodeA = document.getElementById('node-A');
    const nodeB = document.getElementById('node-B');

    nodeA.style.borderColor = a ? "var(--success)" : "var(--error)";
    nodeA.style.backgroundColor = a ? "rgba(46, 160, 67, 0.1)" : "rgba(218, 54, 51, 0.1)";

    nodeB.style.borderColor = b ? "var(--success)" : "var(--error)";
    nodeB.style.backgroundColor = b ? "rgba(46, 160, 67, 0.1)" : "rgba(218, 54, 51, 0.1)";

    const pathId = `p-${a ? 't' : 'f'}${b ? 't' : 'f'}`;

    if (!pathsFound.has(pathId)) {
        pathsFound.add(pathId);
        document.getElementById(pathId).classList.add('earned');
    }

    const result = document.getElementById('pathResult');
    result.textContent = `Caminos descubiertos: ${pathsFound.size}/4`;

    if (pathsFound.size === 4) {
        result.className = "result-box success";
        result.textContent += " ¡Has cubierto todos los caminos posibles!";
    }
}

// Actividad 5: Bucles
function runLoop() {
    const limit = parseInt(document.getElementById('loopSlider').value);
    document.getElementById('loopDisplay').textContent = limit;
    const result = document.getElementById('loopResult');

    // Reset
    document.getElementById('l-zero').classList.remove('hit');
    document.getElementById('l-one').classList.remove('hit');
    document.getElementById('l-many').classList.remove('hit');

    // Escenarios
    if (limit <= 0) {
        document.getElementById('l-zero').classList.add('hit');
        result.textContent = "Caso 0 iteraciones: El bucle nunca se ejecuta.";
    } else if (limit === 1) {
        document.getElementById('l-one').classList.add('hit');
        result.textContent = "Caso 1 iteración: El bucle se ejecuta exactamente una vez.";
    } else {
        document.getElementById('l-many').classList.add('hit');
        result.textContent = `Caso N iteraciones: El bucle se ejecuta ${limit} veces.`;
    }
    result.className = "result-box info";
}

// Inicialización
document.addEventListener('DOMContentLoaded', () => {
    runStatement();
    runBranch();
    runPath();
    runLoop();
});

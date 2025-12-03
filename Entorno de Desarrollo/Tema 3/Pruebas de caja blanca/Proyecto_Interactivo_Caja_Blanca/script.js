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
    const x = parseInt(document.getElementById('stmtInput').value);
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

    if (x > 100) {
        lines[2].classList.add('executed'); // console.log
        lines[3].classList.add('executed'); // close if
        document.getElementById('stmtResult').textContent = "¡Éxito! Has ejecutado la línea crítica (Statement Coverage 100%).";
        document.getElementById('stmtResult').className = "result-box success";
    } else {
        document.getElementById('stmtResult').textContent = "Línea crítica no alcanzada. Intenta otro valor.";
        document.getElementById('stmtResult').className = "result-box";
    }

    lines[4].classList.add('executed'); // return
    lines[5].classList.add('executed'); // close func
}

// Actividad 2: Ramas
let branchTrueCovered = false;
let branchFalseCovered = false;

function runBranch() {
    const age = parseInt(document.getElementById('branchInput').value);

    if (isNaN(age)) return;

    if (age >= 18) {
        branchTrueCovered = true;
        document.getElementById('branchTrue').classList.add('covered');
    } else {
        branchFalseCovered = true;
        document.getElementById('branchFalse').classList.add('covered');
    }

    const coverage = (branchTrueCovered && branchFalseCovered) ? 100 : 50;
    const result = document.getElementById('branchResult');
    result.textContent = `Cobertura: ${coverage}%`;

    if (coverage === 100) {
        result.className = "result-box success";
        result.textContent += " ¡Excelente! Has cubierto ambas decisiones.";
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
        result.textContent = "Incorrecto. Cuenta los 'if' y 'while' y suma 1.";
        result.className = "result-box error";
    }
}

// Actividad 4: Caminos
let pathsFound = new Set();
function runPath() {
    const a = document.getElementById('pathA').checked;
    const b = document.getElementById('pathB').checked;

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
    const limit = parseInt(document.getElementById('loopInput').value);

    if (isNaN(limit)) return;

    // Escenarios
    if (limit <= 0) {
        document.getElementById('l-zero').classList.add('hit');
    } else if (limit === 1) {
        document.getElementById('l-one').classList.add('hit');
    } else {
        document.getElementById('l-many').classList.add('hit');
    }
}

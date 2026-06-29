/****************************************************************************************
 *  DAM2 · Panel de Control en Google Sheets & Sincronización Bidireccional con Google Tasks
 *  ---------------------------------------------------------------------------------
 *  Este script gestiona un panel de control profesional en Google Sheets y lo sincroniza
 *  de manera BIDIRECCIONAL con Google Tasks (que a su vez se integra en Google Calendar).
 *
 *  CÓMO INSTALARLO:
 *   1. Abre una hoja de cálculo nueva en Google Sheets.
 *   2. Ve al menú superior: Extensiones -> Apps Script.
 *   3. Borra el código de plantilla y pega TODO el contenido de este archivo.
 *   4. Añade el servicio "Tasks API":
 *      - En el menú izquierdo, haz clic en el icono "+" de "Servicios".
 *      - Busca "Tasks API" -> Añadir.
 *   5. Selecciona arriba la función inicializarSpreadsheetDAM2 y haz clic en "Ejecutar".
 *   6. Otorga los permisos a tu cuenta de Google.
 *   7. Vuelve a Google Sheets: verás el Dashboard y el Plan creados y formateados.
 *   8. Ejecuta la sincronización bidireccional desde el nuevo menú "🎓 DAM2 Sync" arriba.
 *
 *  AUTOMATIZACIÓN:
 *   Para sincronizar automáticamente cada 15 minutos sin abrir el documento:
 *   - En Apps Script, haz clic en el icono de reloj (Activadores) en el menú izquierdo.
 *   - Añadir activador -> elegir función: sincronizarBidireccional
 *   - Evento: Según tiempo -> Temporizador de minutos -> Cada 15 minutos -> Guardar.
 ****************************************************************************************/

// Constantes de configuración
const NOMBRE_LISTA_TASKS = 'DAM2';
const TAB_DASHBOARD = '📊 Panel de Control';
const TAB_DATA = '📅 Plan de Trabajo';
const EMOJI_PRIORIDAD = { P0: '🟥', P1: '🟦', P2: '🟩' };

/****************************************************************************************
 *  1. FUNCIÓN DE INICIALIZACIÓN - Crea y formatea todo el Spreadsheet automáticamente
 ****************************************************************************************/
function inicializarSpreadsheetDAM2() {
  const ss = SpreadsheetApp.getActiveSpreadsheet();
  
  // Limpiar sheets existentes o crearlas
  let sheetDash = ss.getSheetByName(TAB_DASHBOARD);
  if (sheetDash) ss.deleteSheet(sheetDash);
  sheetDash = ss.insertSheet(TAB_DASHBOARD, 0);
  
  let sheetData = ss.getSheetByName(TAB_DATA);
  if (sheetData) ss.deleteSheet(sheetData);
  sheetData = ss.insertSheet(TAB_DATA, 1);
  
  // Ocultar líneas de cuadrícula en el Dashboard para estética premium
  sheetDash.setHiddenGridlines(true);
  sheetData.setHiddenGridlines(false);
  
  // Generar estructuras
  disenarDashboard(sheetDash);
  poblarPlanDeTrabajo(sheetData);
  
  Logger.log('Spreadsheet inicializado con éxito. Formatos de alta calidad y fórmulas aplicadas.');
}

// Estructura visual del Dashboard (Panel de Control)
function disenarDashboard(sheet) {
  // Ajustar anchos de columna
  sheet.setColumnWidth(1, 180); // Módulo
  sheet.setColumnWidth(2, 80);  // Total
  sheet.setColumnWidth(3, 110); // Completados
  sheet.setColumnWidth(4, 90);  // Pendientes
  sheet.setColumnWidth(5, 120); // Progreso
  sheet.setColumnWidth(6, 40);  // Separador
  sheet.setColumnWidth(7, 180); // KPI Label
  sheet.setColumnWidth(8, 120); // KPI Value
  
  // Título Principal
  sheet.getRange('A1:H1').merge().setValue('🎓 PLAN DE ESTUDIO VERANO 2DAM - MASTERCLASS')
       .setFontColor('#FFFFFF').setBackgroundColor('#2F3640')
       .setFontSize(16).setFontWeight('bold').setHorizontalAlignment('center').setVerticalAlignment('middle');
  sheet.setRowHeight(1, 45);
  
  // Tarjetas de Métricas Rápidas (KPIs) en columnas G e H
  const kpis = [
    ['Total Ejercicios', `=COUNTA('${TAB_DATA}'!A4:A)`],
    ['Completados 🟢', `=COUNTIF('${TAB_DATA}'!I4:I, "HECHO")`],
    ['En Proceso 🟡', `=COUNTIF('${TAB_DATA}'!I4:I, "EN PROCESO")`],
    ['Pendientes 🔴', `=COUNTIF('${TAB_DATA}'!I4:I, "PENDIENTE")`],
    ['Progreso Global', `=IF(H3>0, H4/H3, 0)`]
  ];
  
  // Dibujar tarjetas KPI
  for (let i = 0; i < kpis.length; i++) {
    const rLabel = sheet.getRange(3 + i, 7).setValue(kpis[i][0]).setFontWeight('bold').setBackgroundColor('#F5F6FA').setBorder(true, true, true, true, false, false, '#DFE4EA', SpreadsheetApp.BorderStyle.SOLID);
    const rVal = sheet.getRange(3 + i, 8);
    rVal.setFormula(kpis[i][1]).setFontWeight('bold').setHorizontalAlignment('right').setBorder(true, true, true, true, false, false, '#DFE4EA', SpreadsheetApp.BorderStyle.SOLID);
    
    if (i === 4) {
      rVal.setNumberFormat('0.0%').setFontSize(12).setFontColor('#2ECC71');
      rLabel.setBackgroundColor('#E1F5FE');
    } else {
      rVal.setNumberFormat('#,##0');
    }
  }
  
  // Tabla de progreso por módulos (Materia de estudio de 2DAM)
  sheet.getRange('A3:E3').setValues([['MÓDULO', 'TOTAL', 'COMPLETADOS', 'PENDIENTES', 'PROGRESO']])
       .setFontColor('#FFFFFF').setBackgroundColor('#57606F').setFontWeight('bold').setHorizontalAlignment('center');
  
  const modulos = ['Java base', 'AD (0486)', 'PSP (0490)', 'DI (0488)', 'PMDM (0489)', 'SGE (0491)'];
  for (let i = 0; i < modulos.length; i++) {
    const row = 4 + i;
    sheet.getRange(row, 1).setValue(modulos[i]).setFontWeight('bold').setBackgroundColor('#F8F9FA');
    sheet.getRange(row, 2).setFormula(`=COUNTIF('${TAB_DATA}'!B4:B, "${modulos[i]}")`).setHorizontalAlignment('center');
    sheet.getRange(row, 3).setFormula(`=COUNTIFS('${TAB_DATA}'!B4:B, "${modulos[i]}", '${TAB_DATA}'!I4:I, "HECHO")`).setHorizontalAlignment('center');
    sheet.getRange(row, 4).setFormula(`=B${row}-C${row}`).setHorizontalAlignment('center');
    sheet.getRange(row, 5).setFormula(`=IF(B${row}>0, C${row}/B${row}, 0)`).setNumberFormat('0.0%').setHorizontalAlignment('right');
  }
  
  // Aplicar bordes suaves a la tabla de módulos
  sheet.getRange(3, 1, 1 + modulos.length, 5).setBorder(true, true, true, true, true, true, '#DFE4EA', SpreadsheetApp.BorderStyle.SOLID);
  
  // Mini-gráfico de barra horizontal usando SPARKLINE para progreso
  sheet.getRange('A12:E12').merge().setValue('📊 Barra de Progreso Visual:')
       .setFontWeight('bold').setFontSize(10).setFontColor('#7F8C8D');
  sheet.getRange('A13:E13').merge().setFormula(`=SPARKLINE(H7, {"charttype", "bar"; "max", 1; "color1", "#2ecc71"; "empty", "ignore"})`)
       .setBorder(true, true, true, true, false, false, '#7F8C8D', SpreadsheetApp.BorderStyle.SOLID);
  sheet.setRowHeight(13, 30);
  
  // Cuadro explicativo / Ayuda de Sincronización
  const infoRow = 15;
  sheet.getRange(infoRow, 1, 1, 8).merge().setValue('ℹ️ ¿CÓMO FUNCIONA ESTE PANEL?')
       .setFontWeight('bold').setFontColor('#2C3E50').setBackgroundColor('#E1F5FE').setHorizontalAlignment('center');
  
  const instrucciones = [
    '• Modifica el estado en la pestaña "' + TAB_DATA + '" a HECHO cuando pases el test verde y sincroniza.',
    '• Si marcas una tarea como completada en tu móvil (Google Tasks), al sincronizar se pondrá en HECHO aquí.',
    '• Puedes agendar fechas en la pestaña de datos y se actualizarán automáticamente en Google Calendar.',
    '• Para ejecutar la sincronización manual, usa el menú superior "🎓 DAM2 Sync" -> "🔄 Sincronizar Bidireccional".'
  ];
  
  for (let i = 0; i < instrucciones.length; i++) {
    sheet.getRange(infoRow + 1 + i, 1, 1, 8).merge().setValue(instrucciones[i])
         .setFontSize(9).setFontColor('#57606F').setVerticalAlignment('middle');
  }
}

// Creación de la base de datos de ejercicios del plan de verano
function poblarPlanDeTrabajo(sheet) {
  // Encabezados
  const headers = [
    'Código', 'Módulo', 'RA', 'Bloque', 'Ruta del Archivo', 
    'Prio', 'Concepto', 'Fecha Prevista', 'Estado', 'Comando de Test', 
    'ID Tarea Google Tasks', 'Última Sincronización'
  ];
  
  sheet.getRange('A3:L3').setValues([headers])
       .setFontColor('#FFFFFF').setBackgroundColor('#2F3542').setFontWeight('bold').setHorizontalAlignment('center');
  sheet.setRowHeight(3, 26);
  sheet.setFrozenRows(3);
  
  // Listado oficial de ejercicios (con nombres corregidos en disco, sin guiones bajos)
  const items = obtenerEjerciciosPlan();
  
  // Preparar matriz de datos a insertar
  const dataValues = [];
  for (const item of items) {
    const testClass = item.archivo.split('/').pop().replace(/\.java$/, '') + 'Test';
    const testCmd = `mvn test -Dtest=${testClass}`;
    
    dataValues.push([
      item.codigo,
      item.modulo,
      item.ra,
      item.bloque,
      item.archivo,
      item.prioridad,
      item.concepto,
      item.fecha,
      'PENDIENTE', // Estado inicial
      testCmd,
      '', // ID Tarea
      ''  // Última Sync
    ]);
  }
  
  // Insertar filas masivamente
  sheet.getRange(4, 1, dataValues.length, 12).setValues(dataValues);
  
  // Formatear alineaciones y anchos
  sheet.setColumnWidth(1, 60);  // Código
  sheet.setColumnWidth(2, 90);  // Módulo
  sheet.setColumnWidth(3, 50);  // RA
  sheet.setColumnWidth(4, 100); // Bloque
  sheet.setColumnWidth(5, 300); // Ruta
  sheet.setColumnWidth(6, 50);  // Prio
  sheet.setColumnWidth(7, 260); // Concepto
  sheet.setColumnWidth(8, 100); // Fecha
  sheet.setColumnWidth(9, 110); // Estado
  sheet.setColumnWidth(10, 240); // Comando
  sheet.setColumnWidth(11, 180); // ID Tasks (ocultable si se desea)
  sheet.setColumnWidth(12, 140); // Última Sync
  
  sheet.getRange('A4:A').setHorizontalAlignment('center');
  sheet.getRange('B4:D').setHorizontalAlignment('center');
  sheet.getRange('F4:F').setHorizontalAlignment('center');
  sheet.getRange('H4:I').setHorizontalAlignment('center');
  sheet.getRange('K4:L').setHorizontalAlignment('center');
  
  // Validaciones de datos (Dropdowns)
  const cellEstado = sheet.getRange('I4:I' + (3 + items.length));
  const ruleEstado = SpreadsheetApp.newDataValidation().requireValueInList(['PENDIENTE', 'EN PROCESO', 'HECHO'], true).build();
  cellEstado.setDataValidation(ruleEstado);
  
  const cellPrio = sheet.getRange('F4:F' + (3 + items.length));
  const rulePrio = SpreadsheetApp.newDataValidation().requireValueInList(['P0', 'P1', 'P2'], true).build();
  cellPrio.setDataValidation(rulePrio);
  
  // Aplicar formato de fecha
  sheet.getRange('H4:H' + (3 + items.length)).setNumberFormat('yyyy-mm-dd');
  
  // Formato Condicional para el Estado (Pendiente = Rojo claro, En Proceso = Amarillo, Hecho = Verde claro)
  const rangeEstado = sheet.getRange('I4:I' + (3 + items.length));
  
  const ruleHecho = SpreadsheetApp.newConditionalFormatRule().whenTextEqualTo('HECHO')
                               .setBackground('#C8E6C9').setFontColor('#1B5E20').setRanges([rangeEstado]).build();
  const ruleProceso = SpreadsheetApp.newConditionalFormatRule().whenTextEqualTo('EN PROCESO')
                                 .setBackground('#FFF9C4').setFontColor('#F57F17').setRanges([rangeEstado]).build();
  const rulePendiente = SpreadsheetApp.newConditionalFormatRule().whenTextEqualTo('PENDIENTE')
                                   .setBackground('#FFCDD2').setFontColor('#B71C1C').setRanges([rangeEstado]).build();
                                   
  // Formato Condicional para Prioridad (P0 = Rojo suave, P1 = Azul suave, P2 = Verde suave)
  const rangePrio = sheet.getRange('F4:F' + (3 + items.length));
  const ruleP0 = SpreadsheetApp.newConditionalFormatRule().whenTextEqualTo('P0')
                               .setBackground('#FFEBEE').setFontColor('#C62828').setRanges([rangePrio]).build();
  const ruleP1 = SpreadsheetApp.newConditionalFormatRule().whenTextEqualTo('P1')
                               .setBackground('#E3F2FD').setFontColor('#1565C0').setRanges([rangePrio]).build();
  const ruleP2 = SpreadsheetApp.newConditionalFormatRule().whenTextEqualTo('P2')
                               .setBackground('#E8F5E9').setFontColor('#2E7D32').setRanges([rangePrio]).build();
                               
  const rules = sheet.getConditionalFormatRules();
  rules.push(ruleHecho, ruleProceso, rulePendiente, ruleP0, ruleP1, ruleP2);
  sheet.setConditionalFormatRules(rules);
}

/****************************************************************************************
 *  2. MOTOR DE SINCRONIZACIÓN BIDIRECCIONAL (Sheets <-> Google Tasks API)
 ****************************************************************************************/
function sincronizarBidireccional() {
  const ss = SpreadsheetApp.getActiveSpreadsheet();
  const sheet = ss.getSheetByName(TAB_DATA);
  if (!sheet) {
    SpreadsheetApp.getUi().alert('Error: No se encontró la pestaña "' + TAB_DATA + '".');
    return;
  }
  
  // 1) Obtener o crear la lista de tareas en Google Tasks
  const listId = obtenerIdListaDAM2();
  
  // 2) Descargar todas las tareas existentes de Google Tasks para mapeo rápido (Evita llamadas excesivas)
  const tasksMap = construirMapaTasks(listId);
  
  // 3) Leer datos de la hoja
  const lastRow = sheet.getLastRow();
  if (lastRow < 4) return; // No hay datos
  
  const range = sheet.getRange(4, 1, lastRow - 3, 12);
  const rows = range.getValues();
  const formulas = sheet.getRange(4, 10, lastRow - 3, 1).getFormulas(); // Conservar fórmulas del comando de test
  
  let actualizadosData = 0;
  let actualizadosTasks = 0;
  
  const nowStr = Utilities.formatDate(new Date(), Session.getScriptTimeZone(), 'yyyy-MM-dd HH:mm:ss');
  
  for (let idx = 0; idx < rows.length; idx++) {
    const rowIdx = 4 + idx;
    const r = rows[idx];
    
    const codigo = r[0];
    const modulo = r[1];
    const ra = r[2];
    const bloque = r[3];
    const archivo = r[4];
    const prio = r[5];
    const concepto = r[6];
    const fechaPrevista = r[7];
    let estado = r[8];
    const testCmd = r[9];
    let taskId = r[10];
    
    // Si la fecha prevista no es válida o está vacía, saltar
    if (!fechaPrevista) continue;
    const dateObj = new Date(fechaPrevista);
    const dueStr = dateObj.toISOString().replace(/\.\d{3}Z$/, '.000Z');
    
    // Estructurar el título y notas para Google Tasks
    const emoji = EMOJI_PRIORIDAD[prio] || '';
    const title = `${emoji} ${bloque.split('_')[0]}·Ej${codigo} ${concepto} [${abreviaModulo(modulo)}${ra !== '—' ? '·' + ra : ''}]`;
    
    const testClass = archivo.split('/').pop().replace(/\.java$/, '') + 'Test';
    const notes = [
      `Módulo: ${modulo} · ${ra}`,
      `Bloque: ${bloque}`,
      `Archivo: ${archivo}`,
      `Prioridad: ${prio}`,
      `Test: mvn test -Dtest=${testClass}`,
      `Hecho = test en verde. Se sincroniza bidireccionalmente con tu Google Sheets.`
    ].join('\n');
    
    // CASO A: La fila NO tiene Task ID -> Crear la tarea en Google Tasks
    if (!taskId || taskId === '') {
      const taskObj = {
        title: title,
        notes: notes,
        due: dueStr
      };
      
      try {
        const nuevaTarea = Tasks.Tasks.insert(taskObj, listId);
        sheet.getRange(rowIdx, 11).setValue(nuevaTarea.id); // Guardar el ID
        sheet.getRange(rowIdx, 12).setValue(nowStr);        // Timestamp
        
        // Si el estado en Sheets era HECHO, marcarla de inmediato
        if (estado === 'HECHO') {
          nuevaTarea.status = 'completed';
          Tasks.Tasks.update(nuevaTarea, listId, nuevaTarea.id);
        }
        
        actualizadosTasks++;
        Utilities.sleep(100); // Ritmo de escritura seguro
      } catch (e) {
        Logger.log(`Error creando tarea para Ej${codigo}: ${e}`);
      }
    } 
    // CASO B: La fila YA tiene Task ID -> Sincronizar estados
    else {
      const task = tasksMap[taskId];
      
      if (task) {
        let necesitaActualizacionTask = false;
        
        // 1. Sincronización de ESTADO (Bidireccional)
        const taskCompletada = (task.status === 'completed');
        
        if (taskCompletada && estado !== 'HECHO') {
          // Marcado en Google Tasks pero no en Sheets -> Actualizar Sheets a HECHO
          sheet.getRange(rowIdx, 9).setValue('HECHO');
          sheet.getRange(rowIdx, 12).setValue(nowStr);
          actualizadosData++;
        } 
        else if (!taskCompletada && estado === 'HECHO') {
          // Marcado en Sheets como HECHO pero no en Google Tasks -> Completar en Tasks
          task.status = 'completed';
          necesitaActualizacionTask = true;
        }
        else if (taskCompletada && estado === 'PENDIENTE') {
          // El usuario reabrió la tarea en Sheets (la puso PENDIENTE de nuevo) -> Reabrir en Tasks
          task.status = 'needsAction';
          task.completed = null;
          necesitaActualizacionTask = true;
        }
        
        // 2. Sincronización de Fecha Prevista (Sheets es el origen de fechas)
        // Comparamos solo la parte yyyy-MM-dd
        const taskDueStr = task.due ? task.due.split('T')[0] : '';
        const sheetDueStr = Utilities.formatDate(dateObj, Session.getScriptTimeZone(), 'yyyy-MM-dd');
        
        if (taskDueStr !== sheetDueStr) {
          task.due = dueStr;
          necesitaActualizacionTask = true;
        }
        
        // 3. Sincronización de Título / Notas si cambiaron
        if (task.title !== title || task.notes !== notes) {
          task.title = title;
          task.notes = notes;
          necesitaActualizacionTask = true;
        }
        
        // Ejecutar actualización en Google Tasks si aplica
        if (necesitaActualizacionTask) {
          try {
            Tasks.Tasks.update(task, listId, taskId);
            sheet.getRange(rowIdx, 12).setValue(nowStr);
            actualizadosTasks++;
            Utilities.sleep(100);
          } catch (e) {
            Logger.log(`Error actualizando tarea Ej${codigo}: ${e}`);
          }
        }
      } 
      // Si la tarea fue borrada físicamente en Google Tasks, recrearla
      else {
        const taskObj = {
          title: title,
          notes: notes,
          due: dueStr
        };
        try {
          const nuevaTarea = Tasks.Tasks.insert(taskObj, listId);
          sheet.getRange(rowIdx, 11).setValue(nuevaTarea.id);
          sheet.getRange(rowIdx, 12).setValue(nowStr);
          if (estado === 'HECHO') {
            nuevaTarea.status = 'completed';
            Tasks.Tasks.update(nuevaTarea, listId, nuevaTarea.id);
          }
          actualizadosTasks++;
          Utilities.sleep(100);
        } catch (e) {
          Logger.log(`Error recreando tarea Ej${codigo}: ${e}`);
        }
      }
    }
  }
  
  // Mensaje final
  const msg = `Sincronización completa.\n• Celdas de Sheets actualizadas: ${actualizadosData}\n• Tareas de Google Tasks modificadas/creadas: ${actualizadosTasks}`;
  SpreadsheetApp.getActiveSpreadsheet().toast(msg, '🔄 DAM2 Sync');
}

/****************************************************************************************
 *  MÉTODOS AUXILIARES Y DE CONFIGURACIÓN
 ****************************************************************************************/

// Busca o crea la lista DAM2 en Google Tasks y devuelve su ID
function obtenerIdListaDAM2() {
  const listas = Tasks.Tasklists.list().items || [];
  let lista = listas.find(l => l.title === NOMBRE_LISTA_TASKS);
  if (lista) return lista.id;
  
  lista = Tasks.Tasklists.insert({ title: NOMBRE_LISTA_TASKS });
  return lista.id;
}

// Descarga todas las tareas de la lista para no consultar la API una por una en bucles
function construirMapaTasks(listId) {
  const map = {};
  let pageToken = null;
  do {
    const resp = Tasks.Tasks.list(listId, {
      maxResults: 100,
      showCompleted: true,
      showHidden: true,
      pageToken: pageToken
    });
    const items = resp.items || [];
    items.forEach(t => {
      map[t.id] = t;
    });
    pageToken = resp.nextPageToken;
  } while (pageToken);
  return map;
}

function abreviaModulo(m) {
  if (m.indexOf('AD') === 0) return 'AD';
  if (m.indexOf('PSP') === 0) return 'PSP';
  if (m.indexOf('DI') === 0) return 'DI';
  if (m.indexOf('PMDM') === 0) return 'PMDM';
  if (m.indexOf('SGE') === 0) return 'SGE';
  return 'Java';
}

// Resetea todos los IDs de la columna K (Por si se desea regenerar las tareas desde cero)
function limpiarIdsTareas() {
  const ui = SpreadsheetApp.getUi();
  const respuesta = ui.alert('⚠️ Cuidado', '¿Estás seguro de que quieres borrar todos los IDs de tareas asociados? Esto no borrará las tareas de tu móvil, pero hará que la siguiente sincronización vuelva a duplicar los ejercicios en tu cuenta de Tasks.', ui.ButtonSet.YES_NO);
  
  if (respuesta === ui.Button.YES) {
    const sheet = SpreadsheetApp.getActiveSpreadsheet().getSheetByName(TAB_DATA);
    if (!sheet) return;
    const lastRow = sheet.getLastRow();
    if (lastRow >= 4) {
      sheet.getRange(4, 11, lastRow - 3, 2).clearContent();
      ui.alert('IDs limpiados. El plan de Sheets ha quedado desacoplado de Google Tasks.');
    }
  }
}

/****************************************************************************************
 *  3. MENÚ PERSONALIZADO EN GOOGLE SHEETS
 ****************************************************************************************/
function onOpen() {
  const ui = SpreadsheetApp.getUi();
  ui.createMenu('🎓 DAM2 Sync')
    .addItem('🔄 Sincronizar Bidireccional', 'sincronizarBidireccional')
    .addSeparator()
    .addItem('⚙️ Inicializar Dashboard (Sobrescribe)', 'inicializarSpreadsheetDAM2')
    .addItem('🧹 Limpiar IDs de Tareas (Desacoplar)', 'limpiarIdsTareas')
    .addToUi();
}

/****************************************************************************************
 *  DATASET COMPLETO DEL PLAN DE VERANO (Sincronizado y corregido con RAs de 2DAM)
 ****************************************************************************************/
function ej(codigo, archivo, bloque, modulo, ra, prioridad, concepto, fecha) {
  return { codigo, archivo, bloque, modulo, ra, prioridad, concepto, fecha };
}

function obtenerEjerciciosPlan() {
  return [
    // Semana 1: 1-7 Jul (Base Java Moderno)
    ej('011','b01_java/Ej011Records.java','b01_java','Java base','—','P0','record inmutable como DTO','2026-07-01'),
    ej('012','b01_java/Ej012OptionalSafeAccess.java','b01_java','Java base','—','P0','Optional sin NullPointer','2026-07-01'),
    ej('013','b01_java/Ej013StreamsBasics.java','b01_java','Java base','—','P0','map/filter/collect','2026-07-02'),
    ej('014','b01_java/Ej014StreamsAdvanced.java','b01_java','Java base','—','P0','reduce, flatMap, Collectors','2026-07-02'),
    ej('015','b01_java/Ej015GenericsRepository.java','b01_java','Java base','—','P0','genéricos y T acotados','2026-07-03'),
    ej('017','b01_java/Ej017FunctionalInterfaces.java','b01_java','Java base','—','P0','Function/Predicate/Supplier','2026-07-03'),
    ej('019','b01_java/Ej019ExceptionsAndTryWith.java','b01_java','Java base','—','P0','excepciones y try-with-resources','2026-07-04'),
    ej('020','b01_java/Ej020DateTimeApi.java','b01_java','Java base','—','P0','java.time','2026-07-04'),
    ej('022','b01_java/Ej022EqualsHashCodeContracts.java','b01_java','Java base','—','P0','contratos equals/hashCode','2026-07-05'),
    ej('016','b01_java/Ej016WildcardsVariance.java','b01_java','Java base','—','P2','? extends / ? super','2026-07-05'),
    ej('018','b01_java/Ej018SealedPatternMatching.java','b01_java','Java base','—','P2','sealed + switch patrones','2026-07-06'),
    ej('021','b01_java/Ej021ConcurrencyBasics.java','b01_java','Java base','—','P2','aperitivo de PSP','2026-07-06'),

    // Semana 2: 8-14 Jul (AD RA1 Ficheros)
    ej('207','b26_io/Ej207ByteStreams.java','b26_io','AD (0486)','RA1','P0','InputStream/OutputStream, EOF','2026-07-08'),
    ej('208','b26_io/Ej208CharStreams.java','b26_io','AD (0486)','RA1','P0','Reader/Writer, Charset','2026-07-08'),
    ej('209','b26_io/Ej209RandomAccessFile.java','b26_io','AD (0486)','RA1','P0','acceso aleatorio, seek','2026-07-09'),
    ej('210','b26_io/Ej210ObjectSerialization.java','b26_io','AD (0486)','RA1','P0','Serializable, transient','2026-07-09'),
    ej('211','b26_io/Ej211Nio2PathFiles.java','b26_io','AD (0486)','RA1','P0','Path/Files: CRUD de ficheros','2026-07-10'),
    ej('212','b26_io/Ej212Nio2ReadWriteWalk.java','b26_io','AD (0486)','RA1','P0','readAllLines, walk/find','2026-07-10'),
    ej('214','b26_io/Ej214FormatConversion.java','b26_io','AD (0486)','RA1','P0','texto<->binario, Base64/hex','2026-07-11'),
    ej('143','b16_xml/Ej143JaxbBinding.java','b16_xml','AD (0486)','RA1','P0','JAXB objeto<->XML','2026-07-11'),
    ej('144','b16_xml/Ej144JacksonXml.java','b16_xml','AD (0486)','RA1','P0','Jackson XML','2026-07-12'),
    ej('148','b16_xml/Ej148CsvImportExport.java','b16_xml','AD (0486)','RA1','P0','import/export CSV','2026-07-12'),
    ej('213','b26_io/Ej213TempFilesAndChannels.java','b26_io','AD (0486)','RA1','P2','FileChannel/ByteBuffer','2026-07-13'),
    ej('145','b16_xml/Ej145DomSaxParsing.java','b16_xml','AD (0486)','RA1','P2','DOM vs SAX','2026-07-13'),
    ej('146','b16_xml/Ej146XmlEndpoint.java','b16_xml','AD (0486)','RA1','P2','endpoint que produce XML','2026-07-14'),
    ej('147','b16_xml/Ej147FileBackedRepository.java','b16_xml','AD (0486)','RA1','P2','repo persistido en fichero','2026-07-14'),
    ej('311','b40_media/Ej311ImageLoadSave.java','b40_media','PMDM (0489)','RA3','P1','formatos por magic number','2026-07-14'),
    ej('255','b32_fxbase/Ej255AppLifecycle.java','b32_fxbase','DI (0488)','RA1','P1','ciclo de vida JavaFX','2026-07-14'),

    // Semana 3: 15-21 Jul (AD RA2 JDBC)
    ej('093','b11_jdbc/Ej093ConnectionDriverManager.java','b11_jdbc','AD (0486)','RA2','P0','Connection, drivers','2026-07-15'),
    ej('094','b11_jdbc/Ej094StatementVsPrepared.java','b11_jdbc','AD (0486)','RA2','P0','inyección SQL, PreparedStatement','2026-07-15'),
    ej('095','b11_jdbc/Ej095ResultSetMapping.java','b11_jdbc','AD (0486)','RA2','P0','ResultSet -> objeto','2026-07-16'),
    ej('096','b11_jdbc/Ej096CrudDao.java','b11_jdbc','AD (0486)','RA2','P0','DAO CRUD con JDBC puro','2026-07-16'),
    ej('097','b11_jdbc/Ej097TransactionsCommitRollback.java','b11_jdbc','AD (0486)','RA2','P0','transacciones manuales','2026-07-17'),
    ej('099','b11_jdbc/Ej099ConnectionPooling.java','b11_jdbc','AD (0486)','RA2','P0','pool (HikariCP)','2026-07-17'),
    ej('100','b11_jdbc/Ej100JdbcTemplate.java','b11_jdbc','AD (0486)','RA2','P0','JdbcTemplate de Spring','2026-07-18'),
    ej('249','b31_oodb/Ej249CallableStatement.java','b31_oodb','AD (0486)','RA2.k','P0','CallableStatement IN/OUT','2026-07-18'),
    ej('250','b31_oodb/Ej250StoredFunctionResult.java','b31_oodb','AD (0486)','RA2.k','P0','funciones almacenadas','2026-07-19'),
    ej('098','b11_jdbc/Ej098BatchOperations.java','b11_jdbc','AD (0486)','RA2','P2','inserción por lotes','2026-07-19'),
    ej('101','b11_jdbc/Ej101RowMapperAndExtractor.java','b11_jdbc','AD (0486)','RA2','P2','RowMapper/ResultSetExtractor','2026-07-20'),
    ej('102','b11_jdbc/Ej102NamedParameterJdbc.java','b11_jdbc','AD (0486)','RA2','P2','NamedParameterJdbcTemplate','2026-07-20'),
    ej('251','b31_oodb/Ej251ObjectRelationalTypes.java','b31_oodb','AD (0486)','RA4','P2','tipos ARRAY (objeto-relacional)','2026-07-21'),
    ej('252','b31_oodb/Ej252PersistObjectGraph.java','b31_oodb','AD (0486)','RA4','P2','persistir grafo de objetos','2026-07-21'),
    ej('253','b31_oodb/Ej253OqlStyleQueries.java','b31_oodb','AD (0486)','RA4','P2','consultas estilo OQL','2026-07-21'),
    ej('254','b31_oodb/Ej254TransactionsOnObjects.java','b31_oodb','AD (0486)','RA4','P2','transacciones sobre objetos','2026-07-21'),
    ej('331','b43_erp/Ej331ErpConcepts.java','b43_erp','SGE (0491)','RA1','P1','modelo de datos del ERP','2026-07-21'),
    ej('325','b42_mobile/Ej325MobileEnvOverview.java','b42_mobile','PMDM (0489)','RA1','P1','entorno de desarrollo móvil','2026-07-21'),

    // Semana 4: 22-28 Jul (AD RA3 JPA Core)
    ej('103','b12_jpa/Ej103EntityMapping.java','b12_jpa','AD (0486)','RA3','P0','@Entity/@Id/@Column','2026-07-22'),
    ej('104','b12_jpa/Ej104IdGenerationStrategies.java','b12_jpa','AD (0486)','RA3','P0','@GeneratedValue','2026-07-22'),
    ej('105','b12_jpa/Ej105JpaRepository.java','b12_jpa','AD (0486)','RA3','P0','JpaRepository CRUD','2026-07-23'),
    ej('106','b12_jpa/Ej106DerivedQueryMethods.java','b12_jpa','AD (0486)','RA3','P0','queries por nombre de método','2026-07-23'),
    ej('107','b12_jpa/Ej107JpqlQueries.java','b12_jpa','AD (0486)','RA3','P0','@Query JPQL','2026-07-24'),
    ej('110','b12_jpa/Ej110EntityLifecycleCallbacks.java','b12_jpa','AD (0486)','RA3','P0','@PrePersist/@PreUpdate','2026-07-24'),
    ej('111','b12_jpa/Ej111EnumAndEmbeddable.java','b12_jpa','AD (0486)','RA3','P0','@Enumerated/@Embeddable','2026-07-25'),
    ej('115','b13_rel/Ej115OneToOne.java','b13_rel','AD (0486)','RA3','P0','@OneToOne','2026-07-25'),
    ej('116','b13_rel/Ej116OneToManyManyToOne.java','b13_rel','AD (0486)','RA3','P0','@OneToMany/@ManyToOne','2026-07-26'),
    ej('117','b13_rel/Ej117ManyToManyJoinTable.java','b13_rel','AD (0486)','RA3','P0','@ManyToMany','2026-07-26'),
    ej('120','b13_rel/Ej120FetchLazyEager.java','b13_rel','AD (0486)','RA3','P0','LAZY vs EAGER','2026-07-27'),
    ej('121','b13_rel/Ej121NPlusOneProblem.java','b13_rel','AD (0486)','RA3','P0','diagnosticar N+1','2026-07-27'),
    ej('108','b12_jpa/Ej108NativeQueries.java','b12_jpa','AD (0486)','RA3','P2','SQL nativo','2026-07-28'),
    ej('109','b12_jpa/Ej109ModifyingQueries.java','b12_jpa','AD (0486)','RA3','P2','@Modifying update/delete','2026-07-28'),
    ej('112','b12_jpa/Ej112PersistenceContext.java','b12_jpa','AD (0486)','RA3','P2','contexto de persistencia, flush','2026-07-28'),
    ej('113','b12_jpa/Ej113EqualsHashCodeEntities.java','b12_jpa','AD (0486)','RA3','P2','identidad de entidades','2026-07-28'),
    ej('114','b12_jpa/Ej114DtoConstructorProjection.java','b12_jpa','AD (0486)','RA3','P2','proyección por constructor','2026-07-28'),
    ej('118','b13_rel/Ej118BidirectionalSync.java','b13_rel','AD (0486)','RA3','P2','sincronizar lados de relación','2026-07-28'),
    ej('119','b13_rel/Ej119CascadeTypes.java','b13_rel','AD (0486)','RA3','P2','cascade y orfandad','2026-07-28'),
    ej('122','b13_rel/Ej122JoinFetchAndEntityGraph.java','b13_rel','AD (0486)','RA3','P2','JOIN FETCH, EntityGraph','2026-07-28'),
    ej('312','b40_media/Ej312ImageFilters.java','b40_media','PMDM (0489)','RA3','P1','filtros por píxel','2026-07-28'),
    ej('326','b42_mobile/Ej326ActivityLifecycle.java','b42_mobile','PMDM (0489)','RA2','P1','ciclo de vida Activity','2026-07-28'),

    // Semana 5: 29 Jul - 4 Ago (AD RA3 JPA Avanzado)
    ej('123','b14_jpaadv/Ej123TransactionPropagation.java','b14_jpaadv','AD (0486)','RA3','P0','propagación de transacciones','2026-07-29'),
    ej('124','b14_jpaadv/Ej124IsolationLevels.java','b14_jpaadv','AD (0486)','RA3','P0','niveles de aislamiento','2026-07-29'),
    ej('125','b14_jpaadv/Ej125OptimisticLocking.java','b14_jpaadv','AD (0486)','RA3','P0','@Version','2026-07-30'),
    ej('128','b14_jpaadv/Ej128Auditing.java','b14_jpaadv','AD (0486)','RA3','P0','@CreatedDate/@LastModifiedBy','2026-07-30'),
    ej('129','b14_jpaadv/Ej129SoftDelete.java','b14_jpaadv','AD (0486)','RA3','P0','borrado lógico','2026-07-31'),
    ej('133','b15_query/Ej133Pagination.java','b15_query','AD (0486)','RA3','P0','Pageable/Page','2026-07-31'),
    ej('134','b15_query/Ej134Sorting.java','b15_query','AD (0486)','RA3','P0','Sort multinivel','2026-08-01'),
    ej('136','b15_query/Ej136DynamicFiltering.java','b15_query','AD (0486)','RA3','P0','filtros por query params','2026-08-01'),
    ej('137','b15_query/Ej137Specifications.java','b15_query','AD (0486)','RA3','P0','Specification (Criteria)','2026-08-02'),
    ej('141','b15_query/Ej141AggregationsGroupBy.java','b15_query','AD (0486)','RA3','P0','agregaciones y GROUP BY','2026-08-02'),
    ej('126','b14_jpaadv/Ej126PessimisticLocking.java','b14_jpaadv','AD (0486)','RA3','P2','LockModeType','2026-08-03'),
    ej('127','b14_jpaadv/Ej127SecondLevelCache.java','b14_jpaadv','AD (0486)','RA3','P2','caché de 2º nivel','2026-08-03'),
    ej('130','b14_jpaadv/Ej130InheritanceStrategies.java','b14_jpaadv','AD (0486)','RA3','P2','herencia de entidades','2026-08-04'),
    ej('131','b14_jpaadv/Ej131FlushModesBatching.java','b14_jpaadv','AD (0486)','RA3','P2','flush, batch JPA','2026-08-04'),
    ej('132','b14_jpaadv/Ej132FlywayMigrations.java','b14_jpaadv','AD (0486)','RA3','P2','migraciones de esquema','2026-08-04'),
    ej('135','b15_query/Ej135SliceVsPage.java','b15_query','AD (0486)','RA3','P2','Slice vs Page','2026-08-04'),
    ej('138','b15_query/Ej138CriteriaApi.java','b15_query','AD (0486)','RA3','P2','Criteria API tipada','2026-08-04'),
    ej('139','b15_query/Ej139QueryByExample.java','b15_query','AD (0486)','RA3','P2','Query by Example','2026-08-04'),
    ej('140','b15_query/Ej140InterfaceProjections.java','b15_query','AD (0486)','RA3','P2','proyecciones por interfaz','2026-08-04'),
    ej('142','b15_query/Ej142KeysetPagination.java','b15_query','AD (0486)','RA3','P2','paginación por cursor','2026-08-04'),
    ej('314','b40_media/Ej314AudioPlayback.java','b40_media','PMDM (0489)','RA3','P1','máquina de estados del reproductor','2026-08-04'),
    ej('299','b38_fxreports/Ej299ReportDataModel.java','b38_fxreports','DI (0488)','RA5','P1','JasperReports: modelo datos','2026-08-04'),

    // Semana 6: 5-11 Ago (AD Mongo + Componentes)
    ej('149','b17_nosql/Ej149MongoDocumentMapping.java','b17_nosql','AD (0486)','RA5','P0','@Document/@Id','2026-08-05'),
    ej('150','b17_nosql/Ej150MongoRepository.java','b17_nosql','AD (0486)','RA5','P0','MongoRepository CRUD','2026-08-05'),
    ej('151','b17_nosql/Ej151MongoTemplateQueries.java','b17_nosql','AD (0486)','RA5','P0','MongoTemplate, Query','2026-08-06'),
    ej('153','b17_nosql/Ej153AggregationPipeline.java','b17_nosql','AD (0486)','RA5','P0','pipeline de agregación','2026-08-06'),
    ej('351','b46_datacomp/Ej351BeanProperties.java','b46_datacomp','AD (0486)','RA6','P0','JavaBean: propiedades por reflexión','2026-08-07'),
    ej('352','b46_datacomp/Ej352PropertyChangeEvents.java','b46_datacomp','AD (0486)','RA6','P0','PropertyChangeSupport (eventos)','2026-08-07'),
    ej('353','b46_datacomp/Ej353ComponentSerialization.java','b46_datacomp','AD (0486)','RA6','P0','serializar componente, transient','2026-08-08'),
    ej('354','b46_datacomp/Ej354DataAccessComponent.java','b46_datacomp','AD (0486)','RA6','P0','componente DAO reutilizable (núcleo)','2026-08-08'),
    ej('152','b17_nosql/Ej152EmbeddedVsReferences.java','b17_nosql','AD (0486)','RA5','P2','embebido vs referencias','2026-08-09'),
    ej('154','b17_nosql/Ej154MongoRestEndpoint.java','b17_nosql','AD (0486)','RA5','P2','API REST sobre Mongo','2026-08-09'),
    ej('355','b46_datacomp/Ej355ComponentPackaging.java','b46_datacomp','AD (0486)','RA6','P2','empaquetar como JAR (Manifest)','2026-08-10'),
    ej('356','b46_datacomp/Ej356ComponentIntegration.java','b46_datacomp','AD (0486)','RA6','P2','integrar el componente','2026-08-10'),
    ej('332','b43_erp/Ej332CsvXmlImportExport.java','b43_erp','SGE (0491)','RA5','P1','ETL import/export','2026-08-11'),
    ej('329','b42_mobile/Ej329SensorsModel.java','b42_mobile','PMDM (0489)','RA2','P1','sensores en móvil','2026-08-11'),
    ej('333','b43_erp/Ej333ErpApiClient.java','b43_erp','SGE (0491)','RA4','P1','cliente API ERP','2026-08-11'),

    // Semana 7: 12-18 Ago (PSP RA2 Concurrencia + RA1 Procesos)
    ej('215','b27_concur/Ej215ThreadRunnable.java','b27_concur','PSP (0490)','RA2','P0','Thread vs Runnable, start/join','2026-08-12'),
    ej('216','b27_concur/Ej216ThreadStates.java','b27_concur','PSP (0490)','RA2','P0','estados del hilo, sleep, interrupción','2026-08-12'),
    ej('217','b27_concur/Ej217RaceConditionSynchronized.java','b27_concur','PSP (0490)','RA2','P0','condición de carrera y synchronized','2026-08-13'),
    ej('218','b27_concur/Ej218WaitNotify.java','b27_concur','PSP (0490)','RA2','P0','wait/notify: productor-consumidor','2026-08-13'),
    ej('219','b27_concur/Ej219ExecutorService.java','b27_concur','PSP (0490)','RA2','P0','ExecutorService, pools, shutdown','2026-08-14'),
    ej('220','b27_concur/Ej220CallableFuture.java','b27_concur','PSP (0490)','RA2','P0','Callable, Future, get/timeout','2026-08-14'),
    ej('221','b27_concur/Ej221Locks.java','b27_concur','PSP (0490)','RA2','P0','ReentrantLock, ReadWriteLock','2026-08-15'),
    ej('222','b27_concur/Ej222Semaphores.java','b27_concur','PSP (0490)','RA2','P0','Semaphore, Latch, Barrier','2026-08-15'),
    ej('224','b27_concur/Ej224DeadlockLivelock.java','b27_concur','PSP (0490)','RA2','P0','deadlock: provocar, evitar, detectar','2026-08-16'),
    ej('227','b28_proc/Ej227ProcessBuilderBasics.java','b28_proc','PSP (0490)','RA1','P0','ProcessBuilder, waitFor, exit code','2026-08-16'),
    ej('229','b28_proc/Ej229ProcessPipesIPC.java','b28_proc','PSP (0490)','RA1','P0','IPC por pipes','2026-08-17'),
    ej('231','b28_proc/Ej231ParallelProcesses.java','b28_proc','PSP (0490)','RA1','P0','procesos en paralelo','2026-08-17'),
    ej('223','b27_concur/Ej223AtomicAndConcurrentCollections.java','b27_concur','PSP (0490)','RA2','P2','atómicos y colecciones concurrentes','2026-08-18'),
    ej('225','b27_concur/Ej225CompletableFutureAdvanced.java','b27_concur','PSP (0490)','RA2','P2','CompletableFuture (composición async)','2026-08-18'),
    ej('226','b27_concur/Ej226ThreadPriorityAndContext.java','b27_concur','PSP (0490)','RA2','P2','prioridades, ThreadLocal, daemon','2026-08-18'),
    ej('228','b28_proc/Ej228ProcessIO.java','b28_proc','PSP (0490)','RA1','P2','redirección stdin/stdout/stderr','2026-08-18'),
    ej('230','b28_proc/Ej230ProcessTimeoutAndDestroy.java','b28_proc','PSP (0490)','RA1','P2','waitFor(timeout), destroy','2026-08-18'),
    ej('232','b28_proc/Ej232ProcessEnvAndDir.java','b28_proc','PSP (0490)','RA1','P2','environment(), dir de trabajo','2026-08-18'),
    ej('320','b41_anim/Ej320AnimationTimerLoop.java','b41_anim','PMDM (0489)','RA4','P1','game loop','2026-08-18'),
    ej('337','b44_nui/Ej337NuiOverview.java','b44_nui','DI (0488)','RA2','P1','pipeline NUI','2026-08-18'),
    ej('293','b37_fxcustom/Ej293CustomControlCompose.java','b37_fxcustom','DI (0488)','RA3','P1','componente visual compuesto','2026-08-18'),

    // Semana 8: 19-25 Ago (PSP Sockets + Cripto)
    ej('233','b29_sockets/Ej233TcpEchoServer.java','b29_sockets','PSP (0490)','RA3','P0','ServerSocket/Socket, eco TCP','2026-08-19'),
    ej('234','b29_sockets/Ej234TcpClient.java','b29_sockets','PSP (0490)','RA3','P0','cliente TCP, setSoTimeout','2026-08-19'),
    ej('235','b29_sockets/Ej235MultiClientThreadedServer.java','b29_sockets','PSP (0490)','RA3','P0','multicliente: un hilo por conexión','2026-08-20'),
    ej('236','b29_sockets/Ej236ApplicationProtocol.java','b29_sockets','PSP (0490)','RA3','P0','protocolo de aplicación propio','2026-08-20'),
    ej('237','b29_sockets/Ej237UdpDatagrams.java','b29_sockets','PSP (0490)','RA3','P0','UDP: DatagramSocket/Packet','2026-08-21'),
    ej('241','b30_crypto/Ej241Hashing.java','b30_crypto','PSP (0490)','RA5','P0','MessageDigest (SHA-256)','2026-08-21'),
    ej('242','b30_crypto/Ej242PasswordHashingSalt.java','b30_crypto','PSP (0490)','RA5','P0','salt + PBKDF2','2026-08-22'),
    ej('243','b30_crypto/Ej243SymmetricAes.java','b30_crypto','PSP (0490)','RA5','P0','AES (GCM/CBC, IV)','2026-08-22'),
    ej('244','b30_crypto/Ej244AsymmetricRsa.java','b30_crypto','PSP (0490)','RA5','P0','RSA pública/privada','2026-08-23'),
    ej('245','b30_crypto/Ej245DigitalSignature.java','b30_crypto','PSP (0490)','RA5','P0','firma digital, no repudio','2026-08-23'),
    ej('238','b29_sockets/Ej238ObjectOverSocket.java','b29_sockets','PSP (0490)','RA3','P2','objetos serializados por socket','2026-08-24'),
    ej('239','b29_sockets/Ej239ServerWithThreadPool.java','b29_sockets','PSP (0490)','RA3','P2','servidor con ExecutorService','2026-08-24'),
    ej('240','b29_sockets/Ej240GracefulShutdownAndTimeouts.java','b29_sockets','PSP (0490)','RA3','P2','cierre ordenado, timeouts','2026-08-25'),
    ej('246','b30_crypto/Ej246HmacAndSecureRandom.java','b30_crypto','PSP (0490)','RA5','P2','HMAC, SecureRandom','2026-08-25'),
    ej('247','b30_crypto/Ej247KeyStore.java','b30_crypto','PSP (0490)','RA5','P2','KeyStore PKCS12/JCEKS','2026-08-25'),
    ej('248','b30_crypto/Ej248TlsSecureChannel.java','b30_crypto','PSP (0490)','RA5','P2','TLS (SSLSocket)','2026-08-25'),
    ej('349','b45_juego3d/Ej349GameEngineArchitecture.java','b45_juego3d','PMDM (0489)','RA4','P1','mini-ECS, arquitectura de motor','2026-08-25'),
    ej('338','b44_nui/Ej338VoiceCommandGrammar.java','b44_nui','DI (0488)','RA2','P1','gramática de voz','2026-08-25'),

    // Semana 9: 26-31 Ago (PSP RA4/RA5 Spring + Repaso)
    ej('001','b00_http/Ej001HttpRequestParser.java','b00_http','PSP (0490)','RA4','P0','anatomía de la request HTTP','2026-08-26'),
    ej('004','b00_http/Ej004HttpMethodsSemantics.java','b00_http','PSP (0490)','RA4','P0','idempotencia y seguridad de verbos','2026-08-26'),
    ej('045','b05_web/Ej045HelloController.java','b05_web','PSP (0490)','RA4','P0','@RestController/@GetMapping','2026-08-27'),
    ej('048','b05_web/Ej048RequestBodyPost.java','b05_web','PSP (0490)','RA4','P0','@RequestBody, 201 Created','2026-08-27'),
    ej('053','b05_web/Ej053CrudInMemory.java','b05_web','PSP (0490)','RA4','P0','CRUD REST completo (cierra RA4)','2026-08-28'),
    ej('155','b18_sec/Ej155SecurityFilterChain.java','b18_sec','PSP (0490)','RA5','P0','config Spring Security','2026-08-28'),
    ej('157','b18_sec/Ej157PasswordEncoder.java','b18_sec','PSP (0490)','RA5','P0','BCrypt','2026-08-29'),
    ej('159','b18_sec/Ej159JwtIssue.java','b18_sec','PSP (0490)','RA5','P0','emisión de JWT','2026-08-29'),
    ej('161','b18_sec/Ej161RoleBasedAccess.java','b18_sec','PSP (0490)','RA5','P0','roles, @PreAuthorize (cierra RA5)','2026-08-30'),
    ej('290','b36_fxstyle/Ej290AccessibilityA11y.java','b36_fxstyle','DI (0488)','RA4','P1','accesibilidad/WCAG','2026-08-30'),
    ej('357','b47_pruebas/Ej357TestStrategyPlan.java','b47_pruebas','DI (0488)','RA8','P1','plan/estrategia de pruebas','2026-08-31'),
    ej('335','b43_erp/Ej335BiAggregations.java','b43_erp','SGE (0491)','RA3','P1','BI/KPIs con Streams','2026-08-31'),
    ej('358','b47_pruebas/Ej358RegressionBaseline.java','b47_pruebas','DI (0488)','RA8','P1','pruebas de regresión','2026-08-31'),
    ej('350','b45_juego3d/Ej350MiniGame3D.java','b45_juego3d','PMDM (0489)','RA5','P1','mini-juego 3D','2026-08-31')
  ];
}

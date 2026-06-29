/**
 * Plan flexible verano 2026 para preparar 2º DAM.
 *
 * Requisitos en Apps Script:
 * - Pegar este archivo en un proyecto vinculado a Google Sheets o independiente.
 * - Activar Servicios avanzados de Google: Tasks API.
 * - Ejecutar inicializarPlanVerano2026() y autorizar permisos.
 * - Opcional: ejecutar crearTriggersVerano2026() para sincronizar cada 15 minutos.
 */

const PLAN_2026 = {
  spreadsheetName: 'Plan Verano 2DAM 2026 - Flexible',
  taskListName: 'Verano 2DAM 2026',
  timezone: 'Europe/Madrid',
  sheets: {
    dashboard: 'Dashboard',
    sessions: 'Sesiones',
    catalog: 'CatalogoRA',
    config: 'Config',
    log: 'SyncLog'
  },
  statuses: ['pendiente', 'en_curso', 'completada', 'saltada', 'bloqueada', 'reprogramada', 'descartada'],
  headers: [
    'session_id', 'google_task_id', 'orden', 'fecha_planificada', 'fecha_actual', 'estado', 'prioridad',
    'modulo', 'ra', 'bloque', 'ejercicios', 'teoria', 'duracion_min', 'energia', 'titulo', 'objetivo',
    'por_que_importa', 'actividad', 'entregable', 'comando_test', 'dependencias', 'permitir_replanificacion',
    'reprogramar_siguientes', 'notas_alumno', 'resultado_real', 'ultima_sync', 'sync_hash'
  ]
};

var PLAN_2026_SYNC = {
  maxRowsPerRun: 18,
  maxMillisPerRun: 240000,
  continuationAfterMs: 60 * 1000
};

var PLAN_2026_LOG_BUFFER = [];

function onOpen() {
  SpreadsheetApp.getUi()
    .createMenu('Verano 2DAM 2026')
    .addItem('Inicializar plan', 'inicializarPlanVerano2026')
    .addItem('Sincronizar Sheets <-> Tasks', 'sincronizarBidireccionalVerano2026')
    .addItem('Crear triggers 15 min', 'crearTriggersVerano2026')
    .addSeparator()
    .addItem('Replanificar desde selección', 'replanificarDesdeSeleccion')
    .addToUi();
}

function inicializarPlanVerano2026() {
  const ss = getOrCreateSpreadsheet_();
  const sessionsSheet = resetSheet_(ss, PLAN_2026.sheets.sessions);
  const dashboardSheet = resetSheet_(ss, PLAN_2026.sheets.dashboard);
  const catalogSheet = resetSheet_(ss, PLAN_2026.sheets.catalog);
  const configSheet = resetSheet_(ss, PLAN_2026.sheets.config);
  const logSheet = resetSheet_(ss, PLAN_2026.sheets.log);

  setupSessionsSheet_(sessionsSheet);
  setupDashboardSheet_(dashboardSheet);
  setupCatalogSheet_(catalogSheet);
  setupConfigSheet_(configSheet);
  setupLogSheet_(logSheet);

  const taskList = getOrCreateTaskList_();
  setConfigValue_(configSheet, 'task_list_id', taskList.id);
  setConfigValue_(configSheet, 'spreadsheet_id', ss.getId());
  setConfigValue_(configSheet, 'last_init', new Date());

  SpreadsheetApp.getUi().alert('Plan Verano 2DAM 2026 inicializado. Ahora ejecuta sincronizarBidireccionalVerano2026() para crear/actualizar Tasks por lotes. Sheet: ' + ss.getUrl());
}

function sincronizarBidireccionalVerano2026() {
  const startedAt = Date.now();
  PLAN_2026_LOG_BUFFER = [];
  const ss = getOrCreateSpreadsheet_();
  const sheet = ss.getSheetByName(PLAN_2026.sheets.sessions);
  if (!sheet) throw new Error('No existe la pestaña Sesiones. Ejecuta inicializarPlanVerano2026().');

  const taskList = getOrCreateTaskList_();
  const allRows = readRows_(sheet);
  const startIndex = Math.max(0, Number(getConfigValue_(ss, 'sync_next_index') || 0));
  const rows = allRows.slice(startIndex, startIndex + PLAN_2026_SYNC.maxRowsPerRun);
  const taskIndex = indexTasksBySessionId_(taskList.id);
  const updates = [];
  let processed = 0;

  for (const row of rows) {
    if (Date.now() - startedAt > PLAN_2026_SYNC.maxMillisPerRun) break;
    const beforeHash = row.sync_hash || '';
    const task = row.google_task_id ? safeGetTask_(taskList.id, row.google_task_id) : taskIndex[row.session_id];
    const sheetHash = computeSessionHash_(row);

    if (!task) {
      const created = createTaskFromRow_(taskList.id, row);
      row.google_task_id = created.id;
      row.ultima_sync = new Date();
      row.sync_hash = computeSessionHash_(row);
      updates.push(row);
      logSync_('create_task', row.session_id, 'Creada tarea Google Tasks');
      processed++;
      continue;
    }

    row.google_task_id = task.id;
    const taskState = normalizeTaskState_(task);
    const taskUpdated = task.updated ? new Date(task.updated) : null;
    const lastSync = row.ultima_sync ? new Date(row.ultima_sync) : null;
    const taskChangedAfterSync = taskUpdated && (!lastSync || taskUpdated.getTime() > lastSync.getTime() + 1000);
    const sheetChangedAfterSync = sheetHash !== beforeHash;

    if (taskChangedAfterSync && !sheetChangedAfterSync) {
      applyTaskToRow_(row, taskState);
      row.ultima_sync = new Date();
      row.sync_hash = computeSessionHash_(row);
      updates.push(row);
      logSync_('task_to_sheet', row.session_id, 'Aplicado cambio reciente de Google Tasks');
      processed++;
      continue;
    }

    if (sheetChangedAfterSync || !beforeHash) {
      updateTaskFromRow_(taskList.id, task.id, row, taskState);
      row.ultima_sync = new Date();
      row.sync_hash = computeSessionHash_(row);
      updates.push(row);
      logSync_('sheet_to_task', row.session_id, 'Aplicado cambio de Google Sheets');
      processed++;
      continue;
    }

    row.ultima_sync = lastSync || new Date();
    updates.push(row);
    processed++;
  }

  if (updates.length) writeRowsAt_(sheet, startIndex + 2, updates);
  const nextIndex = startIndex + processed;
  const configSheet = ss.getSheetByName(PLAN_2026.sheets.config);
  if (configSheet) setConfigValue_(configSheet, 'sync_next_index', nextIndex >= allRows.length ? 0 : nextIndex);
  const changeEvents = PLAN_2026_LOG_BUFFER.length;
  logSync_('sync_run', 'SYNC', 'Ejecucion completada. Procesadas ' + processed + '/' + allRows.length + ' sesiones en este lote; eventos de cambio: ' + changeEvents + '; siguiente indice: ' + (nextIndex >= allRows.length ? 0 : nextIndex));
  flushSyncLog_(ss);
  refreshDashboard_(ss);

  if (nextIndex < allRows.length) {
    scheduleSyncContinuation_();
    SpreadsheetApp.getActive().toast('Sincronizadas ' + nextIndex + '/' + allRows.length + ' sesiones. Continuara automaticamente.', 'Verano 2DAM 2026', 8);
  } else {
    SpreadsheetApp.getActive().toast('Sincronizacion completada: ' + allRows.length + ' sesiones.', 'Verano 2DAM 2026', 8);
  }
}

function crearTriggersVerano2026() {
  ScriptApp.getProjectTriggers()
    .filter(function(trigger) { return trigger.getHandlerFunction() === 'sincronizarBidireccionalVerano2026'; })
    .forEach(function(trigger) { ScriptApp.deleteTrigger(trigger); });

  ScriptApp.newTrigger('sincronizarBidireccionalVerano2026')
    .timeBased()
    .everyMinutes(15)
    .create();

  SpreadsheetApp.getUi().alert('Trigger creado: sincronización cada 15 minutos.');
}

function replanificarDesdeSeleccion() {
  const ss = SpreadsheetApp.getActiveSpreadsheet();
  const sheet = ss.getSheetByName(PLAN_2026.sheets.sessions);
  if (!sheet) throw new Error('No existe la pestaña Sesiones.');

  const range = sheet.getActiveRange();
  if (!range || range.getRow() < 2) throw new Error('Selecciona una fila de sesión.');

  const ui = SpreadsheetApp.getUi();
  const response = ui.prompt('Replanificar', 'Días a desplazar desde la sesión seleccionada:', ui.ButtonSet.OK_CANCEL);
  if (response.getSelectedButton() !== ui.Button.OK) return;

  const days = Number(response.getResponseText());
  if (!Number.isFinite(days) || days === 0) throw new Error('Introduce un número de días distinto de 0.');

  const startRow = range.getRow();
  const values = sheet.getRange(startRow, 1, sheet.getLastRow() - startRow + 1, PLAN_2026.headers.length).getValues();
  const fechaActualIdx = PLAN_2026.headers.indexOf('fecha_actual');
  const estadoIdx = PLAN_2026.headers.indexOf('estado');
  const permitirIdx = PLAN_2026.headers.indexOf('permitir_replanificacion');
  const reprogramarIdx = PLAN_2026.headers.indexOf('reprogramar_siguientes');

  values.forEach(function(row) {
    const estado = String(row[estadoIdx] || '').toLowerCase();
    const permitir = String(row[permitirIdx] || '').toUpperCase() !== 'FALSE';
    const reprogramar = String(row[reprogramarIdx] || '').toUpperCase() !== 'FALSE';
    if (!permitir || !reprogramar || estado === 'completada' || estado === 'descartada') return;
    const current = row[fechaActualIdx] ? new Date(row[fechaActualIdx]) : null;
    if (!current || Number.isNaN(current.getTime())) return;
    current.setDate(current.getDate() + days);
    row[fechaActualIdx] = current;
    if (estado === 'pendiente') row[estadoIdx] = 'reprogramada';
  });

  sheet.getRange(startRow, 1, values.length, PLAN_2026.headers.length).setValues(values);
  sincronizarBidireccionalVerano2026();
}

function getOrCreateSpreadsheet_() {
  const active = SpreadsheetApp.getActiveSpreadsheet();
  if (active) return ensureSpreadsheetTimezone_(active);
  const files = DriveApp.getFilesByName(PLAN_2026.spreadsheetName);
  if (files.hasNext()) return ensureSpreadsheetTimezone_(SpreadsheetApp.open(files.next()));
  return ensureSpreadsheetTimezone_(SpreadsheetApp.create(PLAN_2026.spreadsheetName));
}

function ensureSpreadsheetTimezone_(ss) {
  if (ss.getSpreadsheetTimeZone() !== PLAN_2026.timezone) {
    ss.setSpreadsheetTimeZone(PLAN_2026.timezone);
  }
  return ss;
}

function resetSheet_(ss, name) {
  let sheet = ss.getSheetByName(name);
  if (!sheet) sheet = ss.insertSheet(name);
  sheet.clear();
  return sheet;
}

function setupSessionsSheet_(sheet) {
  const rows = buildSessions2026_().map(sessionToRow_);
  sheet.getRange(1, 1, 1, PLAN_2026.headers.length).setValues([PLAN_2026.headers]);
  sheet.getRange(2, 1, rows.length, PLAN_2026.headers.length).setValues(rows);
  sheet.setFrozenRows(1);
  sheet.autoResizeColumns(1, PLAN_2026.headers.length);

  const estadoCol = PLAN_2026.headers.indexOf('estado') + 1;
  const prioridadCol = PLAN_2026.headers.indexOf('prioridad') + 1;
  const energiaCol = PLAN_2026.headers.indexOf('energia') + 1;
  sheet.getRange(2, estadoCol, Math.max(rows.length, 1), 1).setDataValidation(
    SpreadsheetApp.newDataValidation().requireValueInList(PLAN_2026.statuses, true).build()
  );
  sheet.getRange(2, prioridadCol, Math.max(rows.length, 1), 1).setDataValidation(
    SpreadsheetApp.newDataValidation().requireValueInList(['P0', 'P1', 'P2'], true).build()
  );
  sheet.getRange(2, energiaCol, Math.max(rows.length, 1), 1).setDataValidation(
    SpreadsheetApp.newDataValidation().requireValueInList(['baja', 'media', 'alta'], true).build()
  );
}

function setupDashboardSheet_(sheet) {
  sheet.clear();
  sheet.getRange('A1:H1').merge().setValue('PLAN VERANO 2DAM 2026');
  sheet.getRange('A2:H2').merge().setValue('Dashboard vivo: formulas internas sobre Sesiones, sin depender del trigger de sincronizacion');

  sheet.getRange('A4:B12').setValues([
    ['Regla diaria', '90-120 minutos maximo'],
    ['Unidad real', 'Sesion flexible, no bloque'],
    ['Prioridad P0', 'AD + PSP profundos'],
    ['Prioridad P1', 'DI + PMDM + SGE cobertura'],
    ['Prioridad P2', 'Retos extra / ampliaciones'],
    ['Si fallas un dia', 'Reprograma, no borres'],
    ['Saltada/descartada', 'Trazabilidad sin completar Tasks'],
    ['Lista Tasks', PLAN_2026.taskListName],
    ['Ultima revision', '=IFERROR(SyncLog!A2;"Sin sincronizar")']
  ]);

  sheet.getRange('D4:E11').setValues([
    ['Estado', 'Sesiones'],
    ['pendiente', '=COUNTIF(Sesiones!F:F;D5)'],
    ['en_curso', '=COUNTIF(Sesiones!F:F;D6)'],
    ['completada', '=COUNTIF(Sesiones!F:F;D7)'],
    ['saltada', '=COUNTIF(Sesiones!F:F;D8)'],
    ['bloqueada', '=COUNTIF(Sesiones!F:F;D9)'],
    ['reprogramada', '=COUNTIF(Sesiones!F:F;D10)'],
    ['descartada', '=COUNTIF(Sesiones!F:F;D11)']
  ]);

  sheet.getRange('G4:H10').setValues([
    ['Modulo', 'Sesiones'],
    ['AD', '=COUNTIF(Sesiones!H:H;G5)'],
    ['PSP', '=COUNTIF(Sesiones!H:H;G6)'],
    ['DI', '=COUNTIF(Sesiones!H:H;G7)'],
    ['PMDM', '=COUNTIF(Sesiones!H:H;G8)'],
    ['SGE', '=COUNTIF(Sesiones!H:H;G9)'],
    ['Java', '=COUNTIF(Sesiones!H:H;G10)']
  ]);

  sheet.getRange('A15:H15').merge().setValue('Uso rapido: cambia estado, fecha_actual, notas_alumno y resultado_real. No cambies session_id ni columnas.');

  sheet.getRange('A17:B23').setValues([
    ['Metrica', 'Valor'],
    ['Total sesiones', '=COUNTA(Sesiones!A2:A)'],
    ['Completadas utiles', '=COUNTIF(Sesiones!F:F;"completada")'],
    ['Progreso real', '=IFERROR(B19/B18;0)'],
    ['P0 pendientes', '=COUNTIFS(Sesiones!G:G;"P0";Sesiones!F:F;"<>completada";Sesiones!F:F;"<>descartada")'],
    ['Minutos pendientes', '=SUMIFS(Sesiones!M:M;Sesiones!F:F;"<>completada";Sesiones!F:F;"<>descartada")'],
    ['Proxima fecha', '=IFERROR(MIN(FILTER(Sesiones!E2:E;Sesiones!F2:F<>"completada";Sesiones!F2:F<>"descartada"));"")']
  ]);
  sheet.getRange('B20').setNumberFormat('0.0%');

  sheet.getRange('D17:H17').setValues([['Proximas sesiones vivas', 'Fecha', 'Modulo', 'Prioridad', 'Estado']]);
  sheet.getRange('D18').setFormula('=SI.ERROR(QUERY(ORDENAR(FILTRAR({Sesiones!O2:O\Sesiones!E2:E\Sesiones!H2:H\Sesiones!G2:G\Sesiones!F2:F};Sesiones!F2:F<>"completada";Sesiones!F2:F<>"descartada");2;VERDADERO);"select Col1,Col2,Col3,Col4,Col5 limit 8";0);"")');

  sheet.getRange('A28:H28').merge().setValue('Lectura: si una formula muestra error tras cambiar idioma/localizacion, revisa separadores de formula de tu Google Sheets.');

  sheet.setFrozenRows(2);
  sheet.autoResizeColumns(1, 8);
}

function setupCatalogSheet_(sheet) {
  const rows = [
    ['modulo', 'ra', 'bloques', 'prioridad', 'criterio'],
    ['AD', 'RA1', 'b26_io, b16_xml', 'P0', 'Ficheros, XML, NIO.2 y persistencia estructurada'],
    ['AD', 'RA2', 'b11_jdbc, b31_oodb e249-e250', 'P0', 'JDBC, SQL, transacciones y llamadas avanzadas'],
    ['AD', 'RA3', 'b12_jpa, b13_rel, b14_jpaadv, b15_query', 'P0', 'ORM, entidades, relaciones, transacciones y consultas'],
    ['AD', 'RA4', 'b31_oodb', 'P0', 'Objeto-relacional/OO y persistencia especializada'],
    ['AD', 'RA5', 'b17_nosql', 'P0', 'MongoDB/documental y criterios de uso'],
    ['AD', 'RA6', 'b46_datacomp', 'P0', 'Componentes de acceso a datos JavaBean'],
    ['PSP', 'RA1', 'b28_proc', 'P0', 'Procesos, IPC y ejecucion externa'],
    ['PSP', 'RA2', 'b27_concur', 'P0', 'Threads, sincronizacion, pools y seguridad concurrente'],
    ['PSP', 'RA3', 'b29_sockets', 'P0', 'TCP/UDP, protocolos, servidores y clientes'],
    ['PSP', 'RA4', 'b00_http, b05_web', 'P0', 'Servicios HTTP y fundamentos web'],
    ['PSP', 'RA5', 'b30_crypto, b18_sec', 'P0', 'Criptografia, seguridad y JWT basico'],
    ['DI', 'Cobertura', 'b32, b36, b37, b38, b39, b44, b47', 'P1', 'JavaFX, accesibilidad, informes, distribucion y pruebas'],
    ['PMDM', 'Cobertura', 'b40, b41, b42, b45', 'P1', 'Multimedia, juegos, movil y 3D'],
    ['SGE', 'Cobertura', 'b43', 'P1', 'ERP/CRM, ETL e integracion']
  ];
  sheet.getRange(1, 1, rows.length, rows[0].length).setValues(rows);
  sheet.setFrozenRows(1);
  sheet.autoResizeColumns(1, rows[0].length);
}

function setupConfigSheet_(sheet) {
  const rows = [
    ['clave', 'valor'],
    ['task_list_name', PLAN_2026.taskListName],
    ['timezone', PLAN_2026.timezone],
    ['sync_policy', 'bidireccional: gana el cambio posterior a ultima_sync'],
    ['skip_policy', 'saltada/descartada no completan Tasks; se refleja en titulo y notas'],
    ['max_daily_minutes', '120'],
    ['default_session_minutes', '105'],
    ['sync_next_index', '0'],
    ['max_sync_log_rows', '124'],
    ['secret_policy', 'No pegar tokens ni secretos en la hoja ni en el script']
  ];
  sheet.getRange(1, 1, rows.length, rows[0].length).setValues(rows);
  sheet.autoResizeColumns(1, 2);
}

function setupLogSheet_(sheet) {
  sheet.getRange(1, 1, 1, 4).setValues([['timestamp', 'accion', 'session_id', 'detalle']]);
  sheet.getRange('A:A').setNumberFormat('dd/mm/yyyy hh:mm:ss');
  sheet.setFrozenRows(1);
}

function getOrCreateTaskList_() {
  const lists = Tasks.Tasklists.list().items || [];
  const found = lists.find(function(list) { return list.title === PLAN_2026.taskListName; });
  if (found) return found;
  return Tasks.Tasklists.insert({ title: PLAN_2026.taskListName });
}

function indexTasksBySessionId_(taskListId) {
  const index = {};
  let pageToken;
  do {
    const response = Tasks.Tasks.list(taskListId, {
      showCompleted: true,
      showHidden: true,
      maxResults: 100,
      pageToken: pageToken
    });
    (response.items || []).forEach(function(task) {
      const id = extractSessionId_(task.notes || '');
      if (id) index[id] = task;
    });
    pageToken = response.nextPageToken;
  } while (pageToken);
  return index;
}

function createTaskFromRow_(taskListId, row) {
  return Tasks.Tasks.insert(buildTaskResource_(row), taskListId);
}

function updateTaskFromRow_(taskListId, taskId, row, taskState) {
  const resource = buildTaskResource_(row);
  if (row.estado === 'completada') resource.status = 'completed';
  if (row.estado !== 'completada' && taskState.status === 'completed') resource.status = 'needsAction';
  return Tasks.Tasks.patch(resource, taskListId, taskId);
}

function buildTaskResource_(row) {
  const titlePrefix = row.estado === 'saltada' || row.estado === 'descartada' || row.estado === 'bloqueada'
    ? '[' + row.estado.toUpperCase() + '] '
    : '';
  return {
    title: titlePrefix + row.prioridad + ' · ' + row.modulo + ' · ' + row.titulo,
    notes: [
      'session_id: ' + row.session_id,
      'estado: ' + row.estado,
      'ra: ' + row.ra,
      'bloque: ' + row.bloque,
      'ejercicios: ' + row.ejercicios,
      'objetivo: ' + row.objetivo,
      'actividad: ' + row.actividad,
      'entregable: ' + row.entregable,
      'test: ' + row.comando_test,
      'notas_alumno: ' + (row.notas_alumno || '')
    ].join('\n'),
    due: toTaskDue_(row.fecha_actual || row.fecha_planificada)
  };
}

function applyTaskToRow_(row, taskState) {
  if (taskState.status === 'completed') row.estado = 'completada';
  if (taskState.due) row.fecha_actual = fromTaskDue_(taskState.due);
}

function normalizeTaskState_(task) {
  return {
    status: task.status || 'needsAction',
    due: task.due || '',
    updated: task.updated || ''
  };
}

function safeGetTask_(taskListId, taskId) {
  try {
    return Tasks.Tasks.get(taskListId, taskId);
  } catch (error) {
    return null;
  }
}

function readRows_(sheet) {
  const values = sheet.getDataRange().getValues();
  const headers = values.shift();
  return values
    .filter(function(row) { return row[0]; })
    .map(function(valuesRow) {
      const row = {};
      headers.forEach(function(header, index) { row[header] = valuesRow[index]; });
      return row;
    });
}

function writeRows_(sheet, rows) {
  writeRowsAt_(sheet, 2, rows);
}

function writeRowsAt_(sheet, startRow, rows) {
  if (!rows.length) return;
  const values = rows.map(function(row) { return PLAN_2026.headers.map(function(header) { return row[header] || ''; }); });
  sheet.getRange(startRow, 1, values.length, PLAN_2026.headers.length).setValues(values);
}

function sessionToRow_(session) {
  const row = {};
  PLAN_2026.headers.forEach(function(header) { row[header] = session[header] || ''; });
  row.google_task_id = '';
  row.estado = row.estado || 'pendiente';
  row.fecha_actual = row.fecha_actual || row.fecha_planificada;
  row.permitir_replanificacion = row.permitir_replanificacion === false ? 'FALSE' : 'TRUE';
  row.reprogramar_siguientes = row.reprogramar_siguientes === false ? 'FALSE' : 'TRUE';
  row.ultima_sync = '';
  row.sync_hash = '';
  return PLAN_2026.headers.map(function(header) { return row[header] || ''; });
}

function computeSessionHash_(row) {
  const relevant = PLAN_2026.headers
    .filter(function(header) { return ['google_task_id', 'ultima_sync', 'sync_hash'].indexOf(header) === -1; })
    .map(function(header) { return header + '=' + normalizeHashValue_(row[header]); })
    .join('|');
  const digest = Utilities.computeDigest(Utilities.DigestAlgorithm.SHA_256, relevant, Utilities.Charset.UTF_8);
  return digest.map(function(byte) { return ('0' + (byte & 0xff).toString(16)).slice(-2); }).join('');
}

function normalizeHashValue_(value) {
  if (value instanceof Date) return Utilities.formatDate(value, PLAN_2026.timezone, 'yyyy-MM-dd');
  return String(value || '').trim();
}

function toTaskDue_(dateValue) {
  const date = dateValue instanceof Date ? dateValue : new Date(dateValue);
  return Utilities.formatDate(date, 'UTC', "yyyy-MM-dd'T'00:00:00.000'Z'");
}

function fromTaskDue_(due) {
  return Utilities.formatDate(new Date(due), PLAN_2026.timezone, 'yyyy-MM-dd');
}

function extractSessionId_(notes) {
  const match = String(notes || '').match(/session_id:\s*(SUM26-[0-9]{3})/);
  return match ? match[1] : '';
}

function setConfigValue_(sheet, key, value) {
  const values = sheet.getDataRange().getValues();
  for (let i = 0; i < values.length; i++) {
    if (values[i][0] === key) {
      sheet.getRange(i + 1, 2).setValue(value);
      return;
    }
  }
  sheet.appendRow([key, value]);
}

function logSync_(action, sessionId, detail) {
  PLAN_2026_LOG_BUFFER.push([new Date(), action, sessionId, detail]);
}

function flushSyncLog_(ss) {
  if (!PLAN_2026_LOG_BUFFER.length) return;
  const sheet = ss.getSheetByName(PLAN_2026.sheets.log);
  if (!sheet) return;

  sheet.getRange(sheet.getLastRow() + 1, 1, PLAN_2026_LOG_BUFFER.length, 4).setValues(PLAN_2026_LOG_BUFFER);
  sheet.getRange('A:A').setNumberFormat('dd/mm/yyyy hh:mm:ss');
  trimSyncLog_(ss, sheet);
  PLAN_2026_LOG_BUFFER = [];
}

function trimSyncLog_(ss, sheet) {
  const configured = Number(getConfigValue_(ss, 'max_sync_log_rows') || 124);
  const maxRows = Number.isFinite(configured) && configured > 0 ? configured : 124;
  const dataRows = Math.max(0, sheet.getLastRow() - 1);
  if (dataRows > 1) {
    sheet.getRange(2, 1, dataRows, 4).sort({ column: 1, ascending: false });
  }
  const extraRows = Math.max(0, sheet.getLastRow() - 1 - maxRows);
  if (extraRows > 0) sheet.deleteRows(maxRows + 2, extraRows);
}

function getConfigValue_(ss, key) {
  const sheet = ss.getSheetByName(PLAN_2026.sheets.config);
  if (!sheet) return '';
  const values = sheet.getDataRange().getValues();
  for (let i = 0; i < values.length; i++) {
    if (values[i][0] === key) return values[i][1];
  }
  return '';
}

function scheduleSyncContinuation_() {
  ScriptApp.newTrigger('sincronizarBidireccionalVerano2026')
    .timeBased()
    .after(PLAN_2026_SYNC.continuationAfterMs)
    .create();
}

function refreshDashboard_(ss) {
  // El Dashboard es deliberadamente formula-driven. La sincronizacion no debe
  // escribir contadores aqui para evitar pisar formulas o mezclar layouts.
}

function buildSessions2026_() {
  return [
    s(1,'2026-07-01','P0','Java','Base','b01_java','011-012','teoria/01_Java_Moderno_para_APIs.md','media','Java moderno esencial','Reactivar Java 21 aplicado a APIs antes de AD/PSP.','Records, Optional, streams y colecciones son base de los bloques posteriores.','Leer teoria y resolver e011-e012 core sin retos extra.','Tests verdes y notas de conceptos fragiles.','mvn -pl b01_java test','',true,true),
    s(2,'2026-07-02','P0','Java','Base','b01_java','013-014','teoria/01_Java_Moderno_para_APIs.md','media','Errores y generics','Consolidar generics, excepciones y modelado simple.','Reduce bloqueos al entrar en JDBC, JPA y concurrencia.','Resolver dos ejercicios ligeros y registrar dudas.','Commit/local notes con tests verdes.','mvn -pl b01_java test','SUM26-001',true,true),
    s(3,'2026-07-03','P0','AD','RA1','b26_io','207','teoria/26_IO_Ficheros_NIO2.md','media','NIO.2 base','Dominar Path, Files y lectura/escritura segura.','AD RA1 exige persistencia en ficheros con control de errores.','Resolver e207 completo con casos limite.','Test e207 verde y resumen de API usada.','mvn -pl b26_io test','SUM26-001',true,true),
    s(4,'2026-07-04','P0','AD','RA1','b26_io','208','teoria/26_IO_Ficheros_NIO2.md','media','Directorios y traversal','Recorrer arboles de ficheros sin romper rutas.','Necesario para importadores, backups y procesos batch.','Resolver e208 y probar rutas inexistentes/permisos.','Tests verdes y checklist de errores.','mvn -pl b26_io test','SUM26-003',true,true),
    s(5,'2026-07-05','P0','AD','RA1','b26_io','209','teoria/26_IO_Ficheros_NIO2.md','alta','CSV robusto','Leer/escribir CSV validando formato y datos.','Muchos sistemas empresariales intercambian CSV antes que APIs.','Resolver e209 con validacion y errores recuperables.','Parser probado con casos buenos y malos.','mvn -pl b26_io test','SUM26-004',true,true),
    s(6,'2026-07-06','P0','AD','RA1','b26_io','210','teoria/26_IO_Ficheros_NIO2.md','media','Serializacion controlada','Practicar persistencia binaria/objetos con cautela.','Aclara riesgos y alternativas frente a formatos abiertos.','Resolver e210 y anotar tradeoffs.','Tests verdes y comparativa breve.','mvn -pl b26_io test','SUM26-005',true,true),
    s(7,'2026-07-07','P0','AD','RA1','b26_io','211-212','teoria/26_IO_Ficheros_NIO2.md','media','Buffers y canales','Trabajar con IO eficiente y streams.','Base para ficheros grandes y componentes de datos.','Resolver uno completo y avanzar el segundo si hay tiempo.','Al menos un ejercicio verde; segundo en progreso documentado.','mvn -pl b26_io test','SUM26-006',true,true),
    s(8,'2026-07-08','P0','AD','RA1','b16_xml','143','teoria/16_XML_y_Ficheros.md','media','XML DOM','Leer y construir XML con DOM.','AD RA1 suele pedir XML estructurado y validable.','Resolver e143 y comparar con CSV.','Test verde y notas DOM.','mvn -pl b16_xml test','SUM26-007',true,true),
    s(9,'2026-07-09','P0','AD','RA1','b16_xml','144-145','teoria/16_XML_y_Ficheros.md','alta','SAX/StAX','Procesar XML sin cargar todo en memoria.','Importante para ficheros grandes y rendimiento.','Resolver e144 y dejar e145 si el tiempo lo permite.','Test verde minimo y notas de streaming.','mvn -pl b16_xml test','SUM26-008',true,true),
    s(10,'2026-07-10','P0','AD','RA2','b11_jdbc','093','teoria/11_JDBC_Profundo.md','media','JDBC conexion y statement','Arrancar JDBC desde cero con conexion, recursos y SQL simple.','Es el nucleo de AD RA2 y base mental de JPA.','Leer teoria inicial y resolver e093.','Test verde y chuleta DriverManager/DataSource.','mvn -pl b11_jdbc test','SUM26-009',true,true),
    s(11,'2026-07-11','P0','AD','RA2','b11_jdbc','094','teoria/11_JDBC_Profundo.md','media','PreparedStatement','Usar parametros y evitar SQL injection.','Toda aplicacion de datos depende de consultas parametrizadas.','Resolver e094 con casos limite.','Tests verdes y notas de tipos SQL.','mvn -pl b11_jdbc test','SUM26-010',true,true),
    s(12,'2026-07-12','P0','AD','RA2','b11_jdbc','095','teoria/11_JDBC_Profundo.md','alta','ResultSet y mapeo','Mapear filas a objetos con control de nulls.','DAO manual entrena lo que luego automatiza JPA.','Resolver e095 completo.','Mapper probado y decisiones anotadas.','mvn -pl b11_jdbc test','SUM26-011',true,true),
    s(13,'2026-07-13','P0','AD','RA2','b11_jdbc','096','teoria/11_JDBC_Profundo.md','alta','Transacciones','Controlar commit/rollback y consistencia.','RA2 y empresa real dependen de atomicidad.','Resolver e096 y simular fallo intermedio.','Tests de rollback verdes.','mvn -pl b11_jdbc test','SUM26-012',true,true),
    s(14,'2026-07-14','P0','AD','RA2','b11_jdbc','097-098','teoria/11_JDBC_Profundo.md','media','DAO completo','Completar CRUD DAO y separar responsabilidades.','Prepara repositorios y arquitectura por capas.','Resolver un ejercicio y revisar el segundo como lectura guiada.','CRUD probado o plan claro de cierre.','mvn -pl b11_jdbc test','SUM26-013',true,true),
    s(15,'2026-07-15','P0','AD','RA2','b11_jdbc','099-102','teoria/11_JDBC_Profundo.md','alta','JDBC cierre RA2','Cerrar batch, metadata y repaso de puntos debiles.','Consolida JDBC profundo sin dedicar un bloque entero por dia.','Elegir 1-2 ejercicios mas relevantes y documentar descartes.','Tests verdes de lo elegido y backlog P2.','mvn -pl b11_jdbc test','SUM26-014',true,true),
    s(16,'2026-07-16','P0','AD','RA3','b12_jpa','103','teoria/12_Spring_Data_JPA.md','media','JPA entidad repositorio','Crear entidad y repositorio basico.','JPA es el centro practico de AD RA3.','Resolver e103 y entender convenciones.','Test verde y diagrama entidad-tabla.','mvn -pl b12_jpa test','SUM26-015',true,true),
    s(17,'2026-07-17','P0','AD','RA3','b12_jpa','104-105','teoria/12_Spring_Data_JPA.md','media','CRUD JPA','Practicar repositorios, ids y queries derivadas.','Acelera desarrollo sin olvidar SQL subyacente.','Resolver e104 completo y avanzar e105.','Tests verdes al menos e104.','mvn -pl b12_jpa test','SUM26-016',true,true),
    s(18,'2026-07-18','P0','AD','RA3','b13_rel','115','teoria/13_Relaciones_JPA.md','alta','Relaciones 1-N','Modelar relaciones y cascadas con cuidado.','Muchos errores DAM vienen de cardinalidades mal entendidas.','Resolver e115 con diagrama previo.','Test verde y diagrama cardinalidades.','mvn -pl b13_rel test','SUM26-017',true,true),
    s(19,'2026-07-19','P0','AD','RA3','b13_rel','116-117','teoria/13_Relaciones_JPA.md','alta','N-M y fetch','Practicar colecciones, join table y carga.','Evita N+1, ciclos y borrados peligrosos.','Resolver uno completo y revisar el otro.','Tests verdes y notas fetch/cascade.','mvn -pl b13_rel test','SUM26-018',true,true),
    s(20,'2026-07-20','P0','AD','RA3','b14_jpaadv','123','teoria/14_JPA_Avanzado.md','alta','Transacciones JPA','Entender contexto de persistencia y limites transaccionales.','Une JDBC RA2 con ORM RA3.','Resolver e123 con lectura pausada.','Test verde y notas persist/merge/flush.','mvn -pl b14_jpaadv test','SUM26-019',true,true),
    s(21,'2026-07-21','P0','AD','RA3','b14_jpaadv','124-125','teoria/14_JPA_Avanzado.md','alta','Locking y auditoria','Tocar concurrencia de datos y trazabilidad.','Importante para datos compartidos y apps reales.','Resolver uno completo; dejar ampliacion como P2.','Test verde y casos de conflicto anotados.','mvn -pl b14_jpaadv test','SUM26-020',true,true),
    s(22,'2026-07-22','P0','AD','RA3','b15_query','133','teoria/15_Consultas_Avanzadas.md','media','JPQL','Consultar por criterios de negocio.','RA3 exige consultas utiles, no solo CRUD.','Resolver e133 y comparar SQL/JPQL.','Test verde y consultas explicadas.','mvn -pl b15_query test','SUM26-021',true,true),
    s(23,'2026-07-23','P0','AD','RA3','b15_query','134-136','teoria/15_Consultas_Avanzadas.md','alta','Paginacion filtros specs','Practicar paginacion, orden y filtros.','Muy frecuente en APIs, backoffice y examen practico.','Resolver un ejercicio completo y mapear los otros.','Test verde y plantilla de filtros.','mvn -pl b15_query test','SUM26-022',true,true),
    s(24,'2026-07-24','P0','AD','RA4','b31_oodb','249-250','teoria/31_ObjetoRelacional_OO.md','media','Objeto-relacional RA2/RA4','Cubrir procedimientos y persistencia avanzada.','Cierra huecos BOE de AD con cobertura suficiente.','Resolver e249 y revisar e250 si hay tiempo.','Tests verdes o notas de bloqueo.','mvn -pl b31_oodb test','SUM26-023',true,true),
    s(25,'2026-07-25','P0','AD','RA4','b31_oodb','251-254','teoria/31_ObjetoRelacional_OO.md','media','OO DB cobertura','Tocar persistencia OO/objeto-relacional sin sobredimensionar.','Da contexto de tecnologias menos usadas pero oficiales.','Leer teoria y resolver el ejercicio mas representativo.','Resumen RA4 y ejercicio elegido probado.','mvn -pl b31_oodb test','SUM26-024',true,true),
    s(26,'2026-07-26','P0','AD','RA5','b17_nosql','149','teoria/17_NoSQL_MongoDB.md','media','MongoDB documentos','Entender modelo documental frente al relacional.','AD RA5 pide elegir tecnologia segun necesidad.','Resolver e149 y anotar diferencias con JPA.','Test verde y comparativa breve.','mvn -pl b17_nosql test','SUM26-025',true,true),
    s(27,'2026-07-27','P0','AD','RA5','b17_nosql','150-154','teoria/17_NoSQL_MongoDB.md','media','Consultas NoSQL','Practicar consultas, indices y documentos embebidos.','Completa vision de persistencia moderna.','Resolver 1-2 ejercicios clave y marcar resto P2.','Tests verdes de seleccion y backlog P2.','mvn -pl b17_nosql test','SUM26-026',true,true),
    s(28,'2026-07-28','P0','AD','RA6','b46_datacomp','351','teoria/46_Componentes_Datos.md','media','JavaBean datos','Entender componentes reutilizables de acceso a datos.','AD RA6 conecta persistencia con componentes mantenibles.','Resolver e351 con lectura de teoria.','Test verde y esquema del componente.','mvn -pl b46_datacomp test','SUM26-027',true,true),
    s(29,'2026-07-29','P0','AD','RA6','b46_datacomp','352','teoria/46_Componentes_Datos.md','media','Propiedades eventos','Aplicar propiedades y eventos en componente de datos.','Clave para componentes observables y reutilizables.','Resolver e352 completo.','Tests verdes y notas de eventos.','mvn -pl b46_datacomp test','SUM26-028',true,true),
    s(30,'2026-07-30','P0','AD','RA6','b46_datacomp','353-354','teoria/46_Componentes_Datos.md','alta','Componente DAO','Integrar DAO/configuracion en componente.','Cierra ciclo datos + reutilizacion.','Resolver un ejercicio fuerte y revisar el siguiente.','Test verde y decisiones de API.','mvn -pl b46_datacomp test','SUM26-029',true,true),
    s(31,'2026-07-31','P0','AD','RA6','b46_datacomp','355-356','teoria/46_Componentes_Datos.md','media','Cierre AD','Terminar cobertura AD y consolidar mapa RA.','Permite entrar en PSP sin deuda grave.','Resolver o revisar ejercicios finales; actualizar notas.','Checklist AD RA1-RA6 completo.','mvn -pl b46_datacomp test','SUM26-030',true,true),
    s(32,'2026-08-01','P0','PSP','RA1','b28_proc','227','teoria/28_Multiproceso_IPC.md','media','Procesos Java','Lanzar procesos externos y capturar salida.','PSP RA1 empieza por controlar procesos del SO.','Resolver e227 completo.','Test verde y notas ProcessBuilder.','mvn -pl b28_proc test','SUM26-031',true,true),
    s(33,'2026-08-02','P0','PSP','RA1','b28_proc','228-229','teoria/28_Multiproceso_IPC.md','media','IPC y streams','Comunicar procesos mediante entrada/salida.','Necesario para herramientas, workers y automatizacion.','Resolver uno completo y diseñar el otro.','Test verde minimo y diagrama flujo.','mvn -pl b28_proc test','SUM26-032',true,true),
    s(34,'2026-08-03','P0','PSP','RA1','b28_proc','230-232','teoria/28_Multiproceso_IPC.md','alta','Multiproceso cierre','Gestionar errores, timeouts y orquestacion.','Evita procesos colgados y fallos invisibles.','Resolver ejercicio representativo y backlog P2.','Test verde y politica timeout.','mvn -pl b28_proc test','SUM26-033',true,true),
    s(35,'2026-08-04','P0','PSP','RA2','b27_concur','215','teoria/27_Concurrencia_Multihilo.md','media','Thread Runnable','Arrancar concurrencia desde primitives basicas.','PSP RA2 es extenso y necesita sesiones graduales.','Resolver e215 y dibujar ciclo de vida.','Test verde y notas thread lifecycle.','mvn -pl b27_concur test','SUM26-034',true,true),
    s(36,'2026-08-05','P0','PSP','RA2','b27_concur','216','teoria/27_Concurrencia_Multihilo.md','media','Sincronizacion','Evitar race conditions con synchronized/locks.','Es el error central de concurrencia.','Resolver e216 con prueba que falle sin sincronizar.','Test verde y explicacion race condition.','mvn -pl b27_concur test','SUM26-035',true,true),
    s(37,'2026-08-06','P0','PSP','RA2','b27_concur','217','teoria/27_Concurrencia_Multihilo.md','alta','wait notify','Coordinar productores y consumidores basicos.','Entrena comunicacion entre hilos antes de pools.','Resolver e217 pausadamente.','Test verde y diagrama estados.','mvn -pl b27_concur test','SUM26-036',true,true),
    s(38,'2026-08-07','P0','PSP','RA2','b27_concur','218','teoria/27_Concurrencia_Multihilo.md','alta','ExecutorService','Usar pools y Futures correctamente.','Codigo profesional rara vez crea hilos a mano.','Resolver e218 y cerrar recursos.','Test verde y notas shutdown.','mvn -pl b27_concur test','SUM26-037',true,true),
    s(39,'2026-08-08','P0','PSP','RA2','b27_concur','219','teoria/27_Concurrencia_Multihilo.md','alta','Concurrent collections','Elegir estructuras thread-safe.','Reduce bloqueos manuales y bugs dificiles.','Resolver e219 y comparar colecciones.','Test verde y tabla de elecciones.','mvn -pl b27_concur test','SUM26-038',true,true),
    s(40,'2026-08-09','P0','PSP','RA2','b27_concur','220','teoria/27_Concurrencia_Multihilo.md','alta','CompletableFuture','Componer tareas asincronas.','Puente entre PSP, APIs y UI no bloqueante.','Resolver e220 con manejo de errores.','Test verde y flujo async dibujado.','mvn -pl b27_concur test','SUM26-039',true,true),
    s(41,'2026-08-10','P0','PSP','RA2','b27_concur','221-222','teoria/27_Concurrencia_Multihilo.md','alta','Problemas clasicos','Aplicar locks/semaforos a casos clasicos.','Prepara examen y entrevistas tecnicas.','Resolver uno completo; otro como lectura guiada.','Test verde y patron identificado.','mvn -pl b27_concur test','SUM26-040',true,true),
    s(42,'2026-08-11','P0','PSP','RA2','b27_concur','223-226','teoria/27_Concurrencia_Multihilo.md','alta','Cierre concurrencia','Consolidar cancelacion, timeouts y diagnostico.','Es el bloque mas pesado de PSP junto a sockets.','Resolver seleccion realista y dejar retos como P2.','Checklist RA2 y tests seleccionados verdes.','mvn -pl b27_concur test','SUM26-041',true,true),
    s(43,'2026-08-12','P0','PSP','RA3','b29_sockets','233','teoria/29_Sockets_Red.md','media','TCP cliente servidor','Crear cliente/servidor TCP basico.','PSP RA3 se entiende practicando protocolo desde cero.','Resolver e233 y probar conexion local.','Test verde y esquema puerto/socket.','mvn -pl b29_sockets test','SUM26-042',true,true),
    s(44,'2026-08-13','P0','PSP','RA3','b29_sockets','234','teoria/29_Sockets_Red.md','alta','Protocolo texto','Diseñar mensajes y parseo robusto.','Sin protocolo claro no hay red mantenible.','Resolver e234 con errores de formato.','Test verde y especificacion protocolo.','mvn -pl b29_sockets test','SUM26-043',true,true),
    s(45,'2026-08-14','P0','PSP','RA3','b29_sockets','235','teoria/29_Sockets_Red.md','alta','Servidor concurrente','Atender varios clientes sin bloquear todo.','Une sockets con concurrencia RA2.','Resolver e235 y revisar cierre de conexiones.','Test verde y politica de threads.','mvn -pl b29_sockets test','SUM26-044',true,true),
    s(46,'2026-08-15','P0','PSP','RA3','b29_sockets','236','teoria/29_Sockets_Red.md','alta','Timeouts y errores red','Gestionar desconexiones, timeouts y recursos.','La red falla por defecto; hay que codificarlo.','Resolver e236 con casos de fallo.','Test verde y checklist de fallos.','mvn -pl b29_sockets test','SUM26-045',true,true),
    s(47,'2026-08-16','P0','PSP','RA3','b29_sockets','237','teoria/29_Sockets_Red.md','media','UDP','Practicar datagramas y diferencias con TCP.','Cubre casos de PSP RA3 no orientados a conexion.','Resolver e237 y comparar garantias.','Test verde y tabla TCP/UDP.','mvn -pl b29_sockets test','SUM26-046',true,true),
    s(48,'2026-08-17','P0','PSP','RA3','b29_sockets','238-240','teoria/29_Sockets_Red.md','alta','Sockets cierre','Integrar protocolo, concurrencia y seguridad basica.','Cierra el bloque pesado de red con criterio profesional.','Resolver una pieza final y documentar resto P2.','Checklist RA3 y tests seleccionados verdes.','mvn -pl b29_sockets test','SUM26-047',true,true),
    s(49,'2026-08-18','P0','PSP','RA4','b00_http,b05_web','001,045','teoria/00_Fundamentos_HTTP_Web.md; teoria/05_Controllers_REST.md','media','HTTP servicios base','Repasar HTTP y controlador REST minimo.','PSP RA4 necesita servicios y comunicacion en red.','Leer teoria clave y resolver un ejercicio minimo de cada bloque si procede.','Resumen HTTP + endpoint probado.','mvn test','SUM26-048',true,true),
    s(50,'2026-08-19','P0','PSP','RA5','b30_crypto','241','teoria/30_Criptografia_Seguridad.md','media','Hash y encoding','Distinguir hash, cifrado y codificacion.','Seguridad PSP empieza evitando confusiones basicas.','Resolver e241 y anotar usos correctos.','Test verde y glosario breve.','mvn -pl b30_crypto test','SUM26-049',true,true),
    s(51,'2026-08-20','P0','PSP','RA5','b30_crypto,b18_sec','242-243,155','teoria/30_Criptografia_Seguridad.md; teoria/18_Seguridad_JWT.md','alta','Crypto y JWT minimo','Tocar cifrado, firmas/JWT y riesgos.','Cubre RA5 sin convertir verano en bloque de seguridad completo.','Resolver ejercicio crypto clave y leer JWT minimo.','Test verde y mapa amenazas basico.','mvn test','SUM26-050',true,true),
    s(52,'2026-08-21','P1','DI','Cobertura','b32_fxbase','255','teoria/32_JavaFX_Base.md','media','JavaFX base','Entrar en escena, Stage, Scene y layouts.','DI sera modulo tecnico relevante pero no prioridad maxima.','Leer teoria y resolver ejercicio base.','Ventana funcional/test verde si aplica.','mvn -pl b32_fxbase test','SUM26-051',true,true),
    s(53,'2026-08-22','P1','DI','Cobertura','b36_fxstyle','287','teoria/36_JavaFX_Estilo_Accesibilidad.md','media','CSS accesibilidad','Tocar estilos, temas y accesibilidad.','DI exige interfaces usables, no solo funcionales.','Resolver ejercicio representativo y checklist WCAG.','Checklist y app estilada.','mvn -pl b36_fxstyle test','SUM26-052',true,true),
    s(54,'2026-08-23','P1','DI','Cobertura','b37_fxcustom','293','teoria/37_JavaFX_Componentes_Canvas.md','media','Canvas componentes','Probar dibujo y componente propio.','Amplia DI hacia UI personalizada.','Resolver ejercicio base de Canvas/componente.','Demo funcional y notas.','mvn -pl b37_fxcustom test','SUM26-053',true,true),
    s(55,'2026-08-24','P1','DI','Cobertura','b38_fxreports','299','teoria/38_Informes_PDF.md','media','Informes PDF','Entender pipeline de informes/exportacion.','DI RA4 suele pedir documentacion e informes.','Leer teoria y resolver ejercicio minimo.','PDF/exportacion o resumen tecnico.','mvn -pl b38_fxreports test','SUM26-054',true,true),
    s(56,'2026-08-25','P1','DI','Cobertura','b39_fxdeploy,b44_nui,b47_pruebas','305,337,357','teoria/39_Distribucion_Instaladores.md; teoria/44_Interfaces_Naturales.md; teoria/47_Estrategia_Pruebas.md','media','DI cierre cobertura','Tocar distribucion, interfaces naturales y pruebas.','Da vision de todo DI sin absorber el verano.','Lectura guiada y un ejercicio representativo.','Mapa DI con huecos P2.','mvn test','SUM26-055',true,true),
    s(57,'2026-08-26','P1','PMDM','Cobertura','b40_media','311','teoria/40_Multimedia.md','media','Multimedia','Tocar imagen/audio/video desde Java.','PMDM requiere familiaridad con recursos multimedia.','Leer teoria y resolver ejercicio base.','Demo o test verde.','mvn -pl b40_media test','SUM26-056',true,true),
    s(58,'2026-08-27','P1','PMDM','Cobertura','b41_anim','319','teoria/41_Animacion_Juegos.md','media','Animacion game loop','Entender bucle de juego y animacion 2D.','Base de PMDM/juegos sin entrar a fondo.','Resolver ejercicio representativo.','Demo o test verde y diagrama loop.','mvn -pl b41_anim test','SUM26-057',true,true),
    s(59,'2026-08-28','P1','PMDM','Cobertura','b42_mobile,b45_juego3d','325,345','teoria/42_Movil_Android.md; teoria/45_Juegos3D_Motores.md','media','Movil y 3D vistazo','Tocar conceptos Android y 3D/motores.','Sirve para llegar a clase con vocabulario y mapa mental.','Lectura activa y ejercicio minimo de uno de los dos.','Mapa PMDM y backlog P2.','mvn test','SUM26-058',true,true),
    s(60,'2026-08-29','P1','SGE','Cobertura','b43_erp','331','teoria/43_SGE_Integracion.md','media','SGE integracion','Entender ERP/CRM, ETL e integracion.','SGE no es foco, pero conviene no llegar en blanco.','Leer teoria y resolver ejercicio representativo.','Resumen SGE de una pagina.','mvn -pl b43_erp test','SUM26-059',true,true),
    s(61,'2026-08-30','P0','AD/PSP','Repaso','b11,b27,b29,b46','seleccion','teoria/11_JDBC_Profundo.md; teoria/27_Concurrencia_Multihilo.md; teoria/29_Sockets_Red.md; teoria/46_Componentes_Datos.md','baja','Buffer repaso fuerte','Recuperar retrasos de bloques pesados o repetir puntos flojos.','El plan asume fallos y necesita amortiguacion real.','Elegir el mayor bloqueo AD/PSP y cerrarlo.','Bloqueo cerrado o replanificado con nota.','mvn test','',true,true),
    s(62,'2026-08-31','P2','Global','Opcional','retos extra','seleccion','SYLLABUS.md','baja','Cierre verano y P2','Convertir retos extra en backlog de septiembre.','Mantiene trazabilidad sin castigarte por no hacerlo todo.','Marcar completado/saltado/descartado y preparar primera semana de clase.','Dashboard limpio y backlog priorizado.','','SUM26-061',true,false)
  ];
}

function s(order, date, priority, module, ra, block, exercises, theory, energy, title, objective, why, activity, deliverable, testCommand, dependencies, allowReplan, replanNext) {
  return {
    session_id: 'SUM26-' + String(order).padStart(3, '0'),
    orden: order,
    fecha_planificada: date,
    fecha_actual: date,
    estado: 'pendiente',
    prioridad: priority,
    modulo: module,
    ra: ra,
    bloque: block,
    ejercicios: exercises,
    teoria: theory,
    duracion_min: priority === 'P2' ? 60 : 105,
    energia: energy,
    titulo: title,
    objetivo: objective,
    por_que_importa: why,
    actividad: activity,
    entregable: deliverable,
    comando_test: testCommand,
    dependencias: dependencies,
    permitir_replanificacion: allowReplan,
    reprogramar_siguientes: replanNext,
    notas_alumno: '',
    resultado_real: ''
  };
}

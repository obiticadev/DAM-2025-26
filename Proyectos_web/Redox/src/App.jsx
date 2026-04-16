import React, { useState } from 'react';

// ─── Tooltip metadata for complex keys ───────────────────────────────────────
// Keys with special behavior get a small callout describing their functions.
// Each entry maps a label → array of short descriptions (max ~3 words each).
const tooltipMap = {
  'SymTgl':   ['Toggle Símbolos'],
  'SymEsc':   ['Hold → Símbolos', 'Tap → Escape'],
  'SymMo':    ['Hold → Símbolos'],
  'Nav/Spc':  ['Hold → Nav', 'Tap → Espacio'],
  'RALT_TP':  ['Hold → AltGr', 'Doble Tap → AltGr'],
  'Meh':      ['Ctrl+Alt+Shift'],
  'BOOTLD':   ['Modo Flash'],
  'NumLk':    ['Bloq Numérico'],
  'Caps':     ['Bloq Mayúsculas'],
  'PrtScn':   ['Captura Pantalla'],
  '`':        ['Hold → Shift', 'Tap → `'],
  'Accent':   ['One-Shot Acentos'],
};

// ─── Format label for display ────────────────────────────────────────────────
const formatLabel = (label) => {
  if (!label) return '';
  const l = label.trim();
  if (l === 'TRANS') return <span className="trans-symbol">▽</span>;
  if (l === 'NONE') return '';

  if (l.includes(' ') && l.length > 5) {
    const parts = l.split(' ');
    return (
      <span className="keycap-split">
        <span>{parts[0]}</span>
        <span>{parts.slice(1).join(' ')}</span>
      </span>
    );
  }

  if (l.includes('/') && l.length > 5) {
    const parts = l.split('/');
    return (
      <span className="keycap-split">
        <span>{parts[0]}/</span>
        <span>{parts.slice(1).join('/')}</span>
      </span>
    );
  }

  return l;
};

// ─── Keyboard data ───────────────────────────────────────────────────────────
// Layers: q=QWERTY, s=SYMB, n=NAV, a=ACCENT, g=ALTGR
const keyboardData = {
  leftHalf: [
    [{ q:'Caps', s:'º', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'1', s:'!', n:'F1', a:'TRANS', g:'TRANS' }, { q:'2', s:'@', n:'F2', a:'TRANS', g:'TRANS' }, { q:'3', s:'#', n:'F3', a:'TRANS', g:'TRANS' }, { q:'4', s:'$', n:'F4', a:'TRANS', g:'TRANS' }, { q:'5', s:'%', n:'F5', a:'TRANS', g:'TRANS' }],
    [{ q:'Tab', s:'Bksp', n:'Del', a:'TRANS', g:'TRANS' }, { q:'Q', s:'Nump /', n:'Alt+F4', a:'TRANS', g:'TRANS' }, { q:'W', s:'Nump 7', n:'Mouse↑', a:'TRANS', g:'TRANS' }, { q:'E', s:'Nump 8', n:'Whl L', a:'é', g:'TRANS' }, { q:'R', s:'Nump 9', n:'Whl ↑', a:'TRANS', g:'TRANS' }, { q:'T', s:'Nump -', n:'Whl R', a:'TRANS', g:'TRANS' }, { q:'SymTgl', s:'TRANS', n:'BOOTLD', a:'TRANS', g:'TRANS' }],
    [{ q:'SymEsc', s:'TRANS', n:'TRANS', a:'TRANS', g:'TRANS' }, { q:'A', s:'Nump *', n:'Mouse←', a:'á', g:'TRANS' }, { q:'S', s:'Nump 4', n:'Mouse↓', a:'TRANS', g:'TRANS' }, { q:'D', s:'Nump 5', n:'Mouse→', a:'TRANS', g:'TRANS' }, { q:'F', s:'Nump 6', n:'Whl ↓', a:'TRANS', g:'TRANS' }, { q:'G', s:'+', n:'Play', a:'TRANS', g:'TRANS' }, { q:'[', s:',', n:'NONE', a:'TRANS', g:'TRANS' }],
    [{ q:'Shift', s:'Shift', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'Z', s:'↑', n:'Prev', a:'TRANS', g:'TRANS' }, { q:'X', s:'Nump 1', n:'Next', a:'TRANS', g:'TRANS' }, { q:'C', s:'Nump 2', n:'Vol -', a:'TRANS', g:'TRANS' }, { q:'V', s:'Nump 3', n:'Vol +', a:'TRANS', g:'TRANS' }, { q:'B', s:'Nump .', n:'Mute', a:'TRANS', g:'TRANS' }, { q:'¡', s:'PgUp', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'!', s:'PgDn', n:'NONE', a:'TRANS', g:'TRANS' }],
    [{ q:'Ctrl', s:'←', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'Win', s:'↓', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'Meh', s:'→', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'Alt', s:'Nump 0', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'Ctrl', s:'Tab', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'Space', s:'Nav/Spc', n:'TRANS', a:'TRANS', g:'TRANS' }, { q:'Enter', s:'Enter', n:'TRANS', a:'TRANS', g:'TRANS' }]
  ],
  rightHalf: [
    [{ q:'6', s:'^', n:'F6', a:'TRANS', g:'TRANS' }, { q:'7', s:'&', n:'F7', a:'TRANS', g:'TRANS' }, { q:'8', s:'_', n:'F8', a:'TRANS', g:'TRANS' }, { q:'9', s:'!', n:'F9', a:'TRANS', g:'TRANS' }, { q:'0', s:'?', n:'F10', a:'TRANS', g:'TRANS' }, { q:'NumLk', s:'TRANS', n:'F11', a:'TRANS', g:'TRANS' }],
    [{ q:'SymTgl', s:'TRANS', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'Y', s:'+', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'U', s:'[', n:'NONE', a:'ú', g:'TRANS' }, { q:'I', s:']', n:'NONE', a:'í', g:'TRANS' }, { q:'O', s:'/', n:'NONE', a:'ó', g:'TRANS' }, { q:'P', s:'\\', n:'PrtScn', a:'TRANS', g:'TRANS' }, { q:'=', s:'|', n:'F12', a:'TRANS', g:'TRANS' }],
    [{ q:']', s:'NONE', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'H', s:'-', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'J', s:'(', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'K', s:')', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'L', s:'=', n:'NONE', a:'TRANS', g:'TRANS' }, { q:';', s:'`', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'Accent', s:'"', n:'NONE', a:"'", g:'TRANS' }],
    [{ q:'¿', s:'Home', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'?', s:'End', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'N', s:'*', n:'NONE', a:'TRANS', g:'ñ' }, { q:'M', s:'{', n:'NONE', a:'TRANS', g:'TRANS' }, { q:',', s:'}', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'.', s:'<', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'/', s:'>', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'Shift', s:'~', n:'NONE', a:'TRANS', g:'TRANS' }],
    [{ q:'SymMo', s:'TRANS', n:'TRANS', a:'TRANS', g:'TRANS' }, { q:'Bksp', s:'Bksp', n:'TRANS', a:'TRANS', g:'TRANS' }, { q:'RALT_TP', s:'RALT_TP', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'←', s:'←', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'↓', s:'↓', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'↑', s:'↑', n:'NONE', a:'TRANS', g:'TRANS' }, { q:'→', s:'→', n:'NONE', a:'TRANS', g:'TRANS' }]
  ]
};

// ─── Layer definitions ───────────────────────────────────────────────────────
const layers = [
  {
    id: 'q', name: 'QWERTY',
    color: '#6b7280', accent: '#374151',
    activeBg: '#f9fafb', activeBorder: '#d1d5db',
    emptyBg: '#1f2024', emptyBorder: '#111214',
    activeText: '#1f2937', emptyText: '#4b5563',
    shadow: '#b0b5bd', tagBg: '#f3f4f6', tagText: '#374151', tagBorder: '#e5e7eb'
  },
  {
    id: 's', name: '_SYMB',
    color: '#3b82f6', accent: '#1d4ed8',
    activeBg: '#eff6ff', activeBorder: '#93c5fd',
    emptyBg: 'rgba(30,58,138,0.25)', emptyBorder: 'rgba(30,58,138,0.45)',
    activeText: '#1e3a8a', emptyText: 'rgba(96,165,250,0.5)',
    shadow: '#7cb8fd', tagBg: '#dbeafe', tagText: '#1e40af', tagBorder: '#93c5fd'
  },
  {
    id: 'n', name: '_NAV',
    color: '#10b981', accent: '#047857',
    activeBg: '#ecfdf5', activeBorder: '#6ee7b7',
    emptyBg: 'rgba(6,78,59,0.25)', emptyBorder: 'rgba(6,78,59,0.45)',
    activeText: '#064e3b', emptyText: 'rgba(52,211,153,0.5)',
    shadow: '#5ee0b1', tagBg: '#d1fae5', tagText: '#065f46', tagBorder: '#6ee7b7'
  },
  {
    id: 'a', name: '_ACCENT',
    color: '#8b5cf6', accent: '#6d28d9',
    activeBg: '#f5f3ff', activeBorder: '#c4b5fd',
    emptyBg: 'rgba(91,33,182,0.25)', emptyBorder: 'rgba(91,33,182,0.45)',
    activeText: '#4c1d95', emptyText: 'rgba(167,139,250,0.5)',
    shadow: '#c4b5fd', tagBg: '#ede9fe', tagText: '#5b21b6', tagBorder: '#c4b5fd'
  },
  {
    id: 'g', name: '_ALTGR',
    color: '#f59e0b', accent: '#b45309',
    activeBg: '#fffbeb', activeBorder: '#fcd34d',
    emptyBg: 'rgba(124,45,18,0.25)', emptyBorder: 'rgba(124,45,18,0.45)',
    activeText: '#78350f', emptyText: 'rgba(251,191,36,0.5)',
    shadow: '#f6c84d', tagBg: '#fef3c7', tagText: '#92400e', tagBorder: '#fcd34d'
  }
];

// ─── Physical positions ──────────────────────────────────────────────────────
const leftPositions = [
  { x: 0, y: 0.375, w: 1.25 }, { x: 1.25, y: 0.125 }, { x: 2.25, y: 0 }, { x: 3.25, y: 0.125 }, { x: 4.25, y: 0.375 }, { x: 5.25, y: 0.375 },
  { x: 0, y: 1.375, w: 1.25 }, { x: 1.25, y: 1.125 }, { x: 2.25, y: 1 }, { x: 3.25, y: 1.125 }, { x: 4.25, y: 1.375 }, { x: 5.25, y: 1.375 }, { x: 6.5, y: 0.75, h: 1.25 },
  { x: 0, y: 2.375, w: 1.25 }, { x: 1.25, y: 2.125 }, { x: 2.25, y: 2 }, { x: 3.25, y: 2.125 }, { x: 4.25, y: 2.375 }, { x: 5.25, y: 2.375 }, { x: 6.5, y: 2.0, h: 1.25 },
  { x: 0, y: 3.375, w: 1.25 }, { x: 1.25, y: 3.125 }, { x: 2.25, y: 3 }, { x: 3.25, y: 3.125 }, { x: 4.25, y: 3.375 }, { x: 5.25, y: 3.375 }, { x: 5.5, y: 4.5 }, { x: 6.5, y: 4.5 },
  { x: 0, y: 4.375, w: 1.25 }, { x: 1.25, y: 4.125 }, { x: 2.25, y: 4 }, { x: 3.25, y: 4.125 }, { x: 4.25, y: 4.375, w: 1.25 }, { x: 5.5, y: 5.5, w: 1.25, h: 1.25 }, { x: 6.75, y: 5.5, w: 1.25, h: 1.25 }
];

const rightPositions = [
  { x: 1.75, y: 0.375 }, { x: 2.75, y: 0.375 }, { x: 3.75, y: 0.125 }, { x: 4.75, y: 0 }, { x: 5.75, y: 0.125 }, { x: 6.75, y: 0.375, w: 1.25 },
  { x: 0.5, y: 0.75, h: 1.25 }, { x: 1.75, y: 1.375 }, { x: 2.75, y: 1.375 }, { x: 3.75, y: 1.125 }, { x: 4.75, y: 1 }, { x: 5.75, y: 1.125 }, { x: 6.75, y: 1.375, w: 1.25 },
  { x: 0.5, y: 2.0, h: 1.25 }, { x: 1.75, y: 2.375 }, { x: 2.75, y: 2.375 }, { x: 3.75, y: 2.125 }, { x: 4.75, y: 2 }, { x: 5.75, y: 2.125 }, { x: 6.75, y: 2.375, w: 1.25 },
  { x: 0.5, y: 4.5 }, { x: 1.5, y: 4.5 }, { x: 1.75, y: 3.375 }, { x: 2.75, y: 3.375 }, { x: 3.75, y: 3.125 }, { x: 4.75, y: 3 }, { x: 5.75, y: 3.125 }, { x: 6.75, y: 3.375, w: 1.25 },
  { x: 0, y: 5.5, w: 1.25, h: 1.25 }, { x: 1.25, y: 5.5, w: 1.25, h: 1.25 }, { x: 2.5, y: 4.375, w: 1.25 }, { x: 3.75, y: 4.125 }, { x: 4.75, y: 4 }, { x: 5.75, y: 4.125 }, { x: 6.75, y: 4.375, w: 1.25 }
];

// ─── Annotation slot positions (in keyboard unit coords) ─────────────────────
// Each slot defines where an annotation bubble sits in empty space around the keys.
const annotationSlots = {
  left: {
    0:  { ax: -2.0, ay: -0.8 },
    12: { ax: 7.5,  ay: -1.5 },
    13: { ax: -2.5, ay: 2.5 },
    30: { ax: -2.0, ay: 5.5 },
    33: { ax: 5.5,  ay: 8.5 },
  },
  right: {
    5:  { ax: 9.5,  ay: -0.8 },
    6:  { ax: 0.5,  ay: -1.5 },
    11: { ax: 9.5,  ay: 1.5 },
    18: { ax: 9.5,  ay: 3.5 },
    19: { ax: 9.5,  ay: 2.5 },
    28: { ax: -1.5, ay: 8.5 },
    30: { ax: 3.5,  ay: 8.5 },
  }
};

// Compute arrow start point on key EDGE (not center) facing the annotation
const keyEdgePoint = (kx, ky, kw, kh, ax, ay) => {
  const dx = ax - kx;
  const dy = ay - ky;
  if (dx === 0 && dy === 0) return { ex: kx, ey: ky };
  const hw = kw / 2;
  const hh = kh / 2;
  const sx = hw / (Math.abs(dx) || 0.001);
  const sy = hh / (Math.abs(dy) || 0.001);
  const s = Math.min(sx, sy);
  const len = Math.sqrt(dx * dx + dy * dy) || 1;
  const ux = dx / len;
  const uy = dy / len;
  return { ex: kx + dx * s + ux * 0.15, ey: ky + dy * s + uy * 0.15 };
};

// Compute quadratic bezier control point for organic curved arrow
const bezierControl = (sx, sy, ax, ay) => {
  const mx = (sx + ax) / 2;
  const my = (sy + ay) / 2;
  const dx = ax - sx;
  const dy = ay - sy;
  const len = Math.sqrt(dx * dx + dy * dy) || 1;
  const nx = -dy / len;
  const ny = dx / len;
  const curve = len * 0.25;
  return { cx: mx + nx * curve, cy: my + ny * curve };
};

// SVG overlay offsets (keyboard x=0 maps to SVG x=OX)
const SVG_OX = 4;
const SVG_OY = 2.5;
const SVG_W = 16;
const SVG_H = 13;

// ─── Annotation layer (SVG curves + HTML bubbles) ────────────────────────────
const AnnotationLayer = ({ keys, positions, layerData, side, showTooltips }) => {
  if (!showTooltips) return null;
  const slots = annotationSlots[side] || {};

  const annotations = [];
  keys.forEach((k, i) => {
    const label = k[layerData.id];
    if (!label || !tooltipMap[label] || !slots[i]) return;
    const pos = positions[i];
    const kw = pos.w || 1;
    const kh = pos.h || 1;
    const kx = pos.x + kw / 2;
    const ky = pos.y + kh / 2;
    const { ax, ay } = slots[i];
    const edge = keyEdgePoint(kx, ky, kw, kh, ax, ay);
    const ctrl = bezierControl(edge.ex, edge.ey, ax, ay);
    annotations.push({ label, tips: tooltipMap[label], ex: edge.ex, ey: edge.ey, ax, ay, cx: ctrl.cx, cy: ctrl.cy });
  });

  if (annotations.length === 0) return null;

  return (
    <>
      <svg
        className="annotation-svg"
        viewBox={`0 0 ${SVG_W} ${SVG_H}`}
        style={{
          position: 'absolute',
          left: `calc(${-SVG_OX} * var(--u))`,
          top: `calc(${-SVG_OY} * var(--u))`,
          width: `calc(${SVG_W} * var(--u))`,
          height: `calc(${SVG_H} * var(--u))`,
          pointerEvents: 'none',
          overflow: 'visible',
        }}
      >
        {annotations.map((a, i) => {
          const sx = a.ex + SVG_OX, sy = a.ey + SVG_OY;
          const eax = a.ax + SVG_OX, eay = a.ay + SVG_OY;
          const cx = a.cx + SVG_OX, cy = a.cy + SVG_OY;
          return (
            <g key={i}>
              <path
                d={`M${sx} ${sy} Q${cx} ${cy} ${eax} ${eay}`}
                stroke={layerData.activeBorder}
                strokeWidth="0.03"
                fill="none"
                strokeDasharray="0.1 0.07"
                opacity="0.45"
                strokeLinecap="round"
              />
              <circle cx={sx} cy={sy} r="0.07" fill={layerData.color} opacity="0.55" />
            </g>
          );
        })}
      </svg>

      {annotations.map((a, i) => (
        <div
          key={i}
          className="annotation-bubble"
          style={{
            left: `calc(${a.ax} * var(--u))`,
            top: `calc(${a.ay} * var(--u))`,
            background: layerData.tagBg,
            borderColor: layerData.tagBorder,
            color: layerData.tagText,
          }}
        >
          {a.tips.map((t, j) => <span key={j} className="annotation-line">{t}</span>)}
        </div>
      ))}
    </>
  );
};

// ─── Keycap component ────────────────────────────────────────────────────────
const Keycap = ({ label, layerData, pos }) => {
  const isEmpty = !label || label.trim() === 'NONE';
  const isTrans = label === 'TRANS';
  const hasContent = !isEmpty && !isTrans;
  const hasTooltip = hasContent && !!tooltipMap[label];

  const bgColor = hasContent ? layerData.activeBg : layerData.emptyBg;
  const borderColor = hasContent ? layerData.activeBorder : layerData.emptyBorder;
  const textColor = hasContent ? layerData.activeText : layerData.emptyText;
  const shadowColor = hasContent ? layerData.shadow : 'transparent';

  const style = {
    left: `calc(${pos.x} * var(--u))`,
    top: `calc(${pos.y} * var(--u))`,
    width: `calc(${(pos.w || 1)} * var(--u) - var(--gap))`,
    height: `calc(${(pos.h || 1)} * var(--u) - var(--gap))`,
    position: 'absolute',
  };

  return (
    <div style={style} className="keycap-wrapper">
      <div
        className={`keycap ${hasContent ? 'keycap-active' : 'keycap-empty'}`}
        style={{
          background: bgColor,
          borderColor: borderColor,
          color: textColor,
          '--shadow-color': shadowColor,
        }}
      >
        <span className={`keycap-label ${label && label.length > 5 ? 'keycap-label-sm' : ''}`}>
          {formatLabel(label)}
        </span>
        {hasTooltip && (
          <span className="keycap-dot" style={{ background: layerData.color }} />
        )}
      </div>
    </div>
  );
};

// ─── Keyboard half ───────────────────────────────────────────────────────────
const KeyboardHalf = ({ keys, positions, layerData, side, showTooltips }) => (
  <div className="keyboard-half" style={{ width: 'calc(8.0 * var(--u))', height: 'calc(6.75 * var(--u))' }}>
    {keys.map((k, i) => (
      <Keycap key={i} label={k[layerData.id]} layerData={layerData} pos={positions[i]} />
    ))}
    <AnnotationLayer keys={keys} positions={positions} layerData={layerData} side={side} showTooltips={showTooltips} />
  </div>
);

// ─── Full keyboard layout ────────────────────────────────────────────────────
const KeyboardLayout = ({ layerData, showTooltips }) => {
  const leftKeys = keyboardData.leftHalf.flat();
  const rightKeys = keyboardData.rightHalf.flat();

  return (
    <div className="keyboard-case">
      <KeyboardHalf keys={leftKeys} positions={leftPositions} layerData={layerData} side="left" showTooltips={showTooltips} />
      <KeyboardHalf keys={rightKeys} positions={rightPositions} layerData={layerData} side="right" showTooltips={showTooltips} />
    </div>
  );
};

// ─── Layer badge ─────────────────────────────────────────────────────────────
const LayerBadge = ({ layer }) => (
  <div className="layer-badge">
    <span className="layer-dot" style={{ background: layer.color }} />
    <h2 className="layer-title" style={{ color: layer.activeText }}>
      {layer.name}
    </h2>
  </div>
);

// ─── Legend ───────────────────────────────────────────────────────────────────
const Legend = () => (
  <div className="legend print-hidden">
    <div className="legend-item">
      <span className="legend-dot" style={{ background: '#6b7280' }} />
      <span>Función compleja (ver bocadillo)</span>
    </div>
    <div className="legend-item">
      <span className="trans-symbol" style={{ fontSize: 14, color: '#9ca3af' }}>▽</span>
      <span>Transparente (hereda capa inferior)</span>
    </div>
  </div>
);

// ─── App ─────────────────────────────────────────────────────────────────────
export default function App() {
  const [activeLayer, setActiveLayer] = useState('q');
  const [viewAll, setViewAll] = useState(false);
  const [showTooltips, setShowTooltips] = useState(true);

  return (
    <div className="app-root">
      <style>{globalStyles}</style>

      {/* ── Header / Control Panel ── */}
      <header className="control-panel print-hidden">
        <div className="panel-header">
          <div className="logo-row">
            <svg width="32" height="32" viewBox="0 0 32 32" fill="none">
              <rect x="2" y="6" width="12" height="20" rx="3" stroke="#374151" strokeWidth="2" fill="none" />
              <rect x="18" y="6" width="12" height="20" rx="3" stroke="#374151" strokeWidth="2" fill="none" />
              <circle cx="8" cy="13" r="1.5" fill="#6b7280" />
              <circle cx="8" cy="19" r="1.5" fill="#6b7280" />
              <circle cx="24" cy="13" r="1.5" fill="#6b7280" />
              <circle cx="24" cy="19" r="1.5" fill="#6b7280" />
            </svg>
            <h1 className="app-title">Redox Layout</h1>
          </div>
          <p className="app-subtitle">Visualizador de capas · Exporta a PDF</p>
        </div>

        {/* Layer selector */}
        <nav className="layer-nav">
          {layers.map(layer => (
            <button
              key={layer.id}
              onClick={() => { setActiveLayer(layer.id); setViewAll(false); }}
              className={`layer-btn ${(!viewAll && activeLayer === layer.id) ? 'layer-btn-active' : ''}`}
              style={(!viewAll && activeLayer === layer.id) ? {
                background: layer.activeBg,
                borderColor: layer.activeBorder,
                color: layer.activeText,
              } : {}}
            >
              <span className="layer-btn-dot" style={{ background: layer.color }} />
              {layer.name}
            </button>
          ))}
          <span className="nav-divider" />
          <button
            onClick={() => setViewAll(true)}
            className={`layer-btn ${viewAll ? 'layer-btn-active layer-btn-all' : ''}`}
          >
            Ver Todas
          </button>
        </nav>

        {/* Options row */}
        <div className="options-row">
          <label className="toggle-label">
            <input
              type="checkbox"
              checked={showTooltips}
              onChange={e => setShowTooltips(e.target.checked)}
              className="toggle-checkbox"
            />
            <span className="toggle-switch" />
            Mostrar bocadillos
          </label>

          <button onClick={() => { setViewAll(true); setTimeout(() => window.print(), 100); }} className="btn-print">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
              <path d="M17 17h2a2 2 0 002-2v-4a2 2 0 00-2-2H5a2 2 0 00-2 2v4a2 2 0 002 2h2" />
              <path d="M9 21h6a2 2 0 002-2v-4a2 2 0 00-2-2H9a2 2 0 00-2 2v4a2 2 0 002 2z" />
              <path d="M17 9V5a2 2 0 00-2-2H9a2 2 0 00-2 2v4" />
            </svg>
            Exportar PDF
          </button>
        </div>

        <p className="print-tip">
          Activa <strong>"Gráficos de fondo"</strong> en el diálogo de impresión para colores correctos.
        </p>
      </header>

      <Legend />

      {/* ── Keyboard renders ── */}
      <main className="keyboard-stage">
        {(viewAll ? layers : layers.filter(l => l.id === activeLayer)).map(layer => (
          <section key={`render-${layer.id}`} className="keyboard-section">
            <LayerBadge layer={layer} />
            <KeyboardLayout layerData={layer} showTooltips={showTooltips} />
          </section>
        ))}
      </main>

    </div>
  );
}

// ─── Global styles ───────────────────────────────────────────────────────────
const globalStyles = `
  :root {
    --u: 42px;
    --gap: 5px;
  }
  @media (min-width: 640px) {
    :root { --u: 48px; --gap: 6px; }
  }
  @media (min-width: 1024px) {
    :root { --u: 60px; --gap: 6px; }
  }

  /* ── App root ── */
  .app-root {
    min-height: 100vh;
    background: #0f1115;
    background-image:
      radial-gradient(ellipse at 20% 0%, rgba(59,130,246,0.06) 0%, transparent 60%),
      radial-gradient(ellipse at 80% 100%, rgba(16,185,129,0.05) 0%, transparent 60%);
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 2.5rem 1rem 6rem;
    font-family: 'Inter', system-ui, sans-serif;
    color: #e5e7eb;
  }

  /* ── Control panel ── */
  .control-panel {
    background: rgba(255,255,255,0.03);
    backdrop-filter: blur(24px);
    border: 1px solid rgba(255,255,255,0.06);
    border-radius: 1.5rem;
    padding: 2rem 2.5rem;
    margin-bottom: 2.5rem;
    width: 100%;
    max-width: 56rem;
  }
  .panel-header {
    text-align: center;
    margin-bottom: 1.5rem;
  }
  .logo-row {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 0.75rem;
    margin-bottom: 0.25rem;
  }
  .logo-row svg { opacity: 0.7; stroke: #9ca3af; }
  .app-title {
    font-size: 1.75rem;
    font-weight: 900;
    letter-spacing: -0.03em;
    color: #f9fafb;
  }
  .app-subtitle {
    font-size: 0.85rem;
    color: #6b7280;
    font-weight: 500;
  }

  /* ── Layer nav ── */
  .layer-nav {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    gap: 0.5rem;
    margin-bottom: 1.25rem;
    background: rgba(255,255,255,0.02);
    padding: 0.375rem;
    border-radius: 1rem;
    border: 1px solid rgba(255,255,255,0.04);
  }
  .layer-btn {
    display: inline-flex;
    align-items: center;
    gap: 0.4rem;
    padding: 0.5rem 1rem;
    border-radius: 0.625rem;
    font-size: 0.8rem;
    font-weight: 700;
    font-family: 'JetBrains Mono', monospace;
    border: 1.5px solid transparent;
    background: transparent;
    color: #6b7280;
    cursor: pointer;
    transition: all 0.2s;
  }
  .layer-btn:hover { background: rgba(255,255,255,0.05); color: #d1d5db; }
  .layer-btn-active {
    border-color: currentColor;
    box-shadow: 0 0 12px rgba(255,255,255,0.04);
  }
  .layer-btn-all.layer-btn-active {
    background: rgba(129,140,248,0.1);
    border-color: rgba(129,140,248,0.4);
    color: #a5b4fc;
  }
  .layer-btn-dot {
    width: 8px; height: 8px;
    border-radius: 50%;
    flex-shrink: 0;
  }
  .nav-divider {
    width: 1px;
    height: 1.5rem;
    background: rgba(255,255,255,0.08);
    margin: 0 0.25rem;
  }

  /* ── Options ── */
  .options-row {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 1.5rem;
    flex-wrap: wrap;
    margin-bottom: 0.75rem;
  }
  .toggle-label {
    display: flex;
    align-items: center;
    gap: 0.5rem;
    font-size: 0.8rem;
    color: #9ca3af;
    cursor: pointer;
    font-weight: 500;
    user-select: none;
  }
  .toggle-checkbox { display: none; }
  .toggle-switch {
    position: relative;
    width: 36px; height: 20px;
    background: rgba(255,255,255,0.1);
    border-radius: 10px;
    transition: background 0.2s;
    flex-shrink: 0;
  }
  .toggle-switch::after {
    content: '';
    position: absolute;
    top: 2px; left: 2px;
    width: 16px; height: 16px;
    background: #6b7280;
    border-radius: 50%;
    transition: all 0.2s;
  }
  .toggle-checkbox:checked + .toggle-switch {
    background: rgba(59,130,246,0.3);
  }
  .toggle-checkbox:checked + .toggle-switch::after {
    left: 18px;
    background: #60a5fa;
  }

  .btn-print {
    display: inline-flex;
    align-items: center;
    gap: 0.5rem;
    padding: 0.625rem 1.5rem;
    background: #f9fafb;
    color: #111827;
    font-weight: 700;
    font-size: 0.85rem;
    border: none;
    border-radius: 0.75rem;
    cursor: pointer;
    transition: all 0.2s;
    box-shadow: 0 2px 8px rgba(0,0,0,0.2);
  }
  .btn-print:hover {
    background: #fff;
    transform: translateY(-1px);
    box-shadow: 0 4px 16px rgba(0,0,0,0.3);
  }

  .print-tip {
    text-align: center;
    font-size: 0.72rem;
    color: #4b5563;
    background: rgba(255,255,255,0.02);
    padding: 0.4rem 0.75rem;
    border-radius: 0.5rem;
    border: 1px solid rgba(255,255,255,0.04);
  }
  .print-tip strong { color: #9ca3af; }

  /* ── Legend ── */
  .legend {
    display: flex;
    gap: 1.5rem;
    justify-content: center;
    margin-bottom: 1.5rem;
    flex-wrap: wrap;
  }
  .legend-item {
    display: flex;
    align-items: center;
    gap: 0.4rem;
    font-size: 0.72rem;
    color: #6b7280;
  }
  .legend-dot {
    width: 7px; height: 7px;
    border-radius: 50%;
    flex-shrink: 0;
  }

  /* ── Keyboard stage ── */
  .keyboard-stage {
    width: 100%;
    display: flex;
    flex-direction: column;
    gap: 3.5rem;
    align-items: center;
  }
  .keyboard-section {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  /* ── Layer badge ── */
  .layer-badge {
    display: flex;
    align-items: center;
    gap: 0.5rem;
    margin-bottom: 1rem;
  }
  .layer-dot {
    width: 10px; height: 10px;
    border-radius: 50%;
  }
  .layer-title {
    font-size: 1rem;
    font-weight: 800;
    letter-spacing: 0.15em;
    text-transform: uppercase;
    font-family: 'JetBrains Mono', monospace;
  }

  /* ── Keyboard case ── */
  .keyboard-case {
    display: flex;
    flex-direction: column;
    gap: 1.5rem;
    justify-content: center;
    align-items: center;
    background: #1a1b1f;
    padding: calc(2.5 * var(--u)) calc(4 * var(--u)) calc(3.5 * var(--u));
    border-radius: 1.5rem;
    border: 1px solid rgba(255,255,255,0.04);
    box-shadow:
      0 0 0 1px rgba(0,0,0,0.3),
      0 20px 60px rgba(0,0,0,0.5),
      inset 0 1px 0 rgba(255,255,255,0.03);
    position: relative;
    overflow: visible;
  }
  @media (min-width: 1024px) {
    .keyboard-case {
      flex-direction: row;
      gap: 3rem;
    }
  }

  /* ── Keyboard half ── */
  .keyboard-half {
    position: relative;
    overflow: visible;
  }

  /* ── Keycap ── */
  .keycap-wrapper {
    position: absolute;
    z-index: 1;
  }
  .keycap-wrapper:hover {
    z-index: 50;
  }
  .keycap {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 0.5rem;
    border-width: 1px 1px 3px 1px;
    border-style: solid;
    transition: all 0.15s ease;
    position: relative;
    box-shadow: 0 2px 0 var(--shadow-color, transparent);
    cursor: default;
  }
  .keycap-active:hover {
    filter: brightness(1.08);
    transform: translateY(-1px);
    box-shadow: 0 4px 8px rgba(0,0,0,0.3);
  }
  .keycap-empty {
    opacity: 0.6;
  }
  .keycap-label {
    font-weight: 700;
    font-family: 'JetBrains Mono', 'Inter', monospace;
    letter-spacing: -0.02em;
    padding: 0 3px;
    font-size: 0.7rem;
    line-height: 1.1;
    text-align: center;
    white-space: nowrap;
  }
  @media (min-width: 640px) {
    .keycap-label { font-size: 0.75rem; }
  }
  @media (min-width: 1024px) {
    .keycap-label { font-size: 0.85rem; }
  }
  .keycap-label-sm {
    font-size: 0.55rem !important;
  }
  @media (min-width: 640px) {
    .keycap-label-sm { font-size: 0.62rem !important; }
  }
  @media (min-width: 1024px) {
    .keycap-label-sm { font-size: 0.72rem !important; }
  }

  .keycap-split {
    display: flex;
    flex-direction: column;
    align-items: center;
    line-height: 1.15;
    gap: 1px;
  }

  .trans-symbol {
    font-size: 1.1rem;
    line-height: 1;
    opacity: 0.6;
  }

  /* ── Complex key dot ── */
  .keycap-dot {
    position: absolute;
    bottom: 3px;
    right: 3px;
    width: 5px;
    height: 5px;
    border-radius: 50%;
    opacity: 0.7;
  }

  /* ── Annotation bubbles ── */
  .annotation-bubble {
    position: absolute;
    transform: translate(-50%, -50%);
    padding: 4px 10px;
    border-radius: 7px;
    border: 1px solid;
    font-size: 0.65rem;
    font-weight: 600;
    font-family: 'Inter', sans-serif;
    line-height: 1.35;
    white-space: nowrap;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 1px;
    pointer-events: none;
    z-index: 100;
    box-shadow: 0 2px 8px rgba(0,0,0,0.12);
  }
  @media (min-width: 1024px) {
    .annotation-bubble { font-size: 0.72rem; padding: 5px 12px; }
  }
  .annotation-line { display: block; }
  .annotation-svg { z-index: 90; }

  /* ═══ PRINT STYLES ═══ */
  @media print {
    @page {
      size: A4 landscape;
      margin: 5mm;
    }
    body {
      -webkit-print-color-adjust: exact !important;
      print-color-adjust: exact !important;
      color-adjust: exact !important;
    }
    .print-hidden { display: none !important; }

    .app-root {
      background: white !important;
      background-image: none !important;
      padding: 0;
      color: #111;
    }

    .keyboard-stage {
      gap: 0;
    }

    .keyboard-section {
      page-break-after: always;
      page-break-inside: avoid;
      break-after: page;
      break-inside: avoid;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      min-height: 100vh;
      padding: 0;
    }
    .keyboard-section:last-child {
      page-break-after: auto;
      break-after: auto;
    }

    .keyboard-case {
      background: #f8f9fa !important;
      box-shadow: none !important;
      border: 1px solid #e5e7eb !important;
      flex-direction: row !important;
      gap: calc(2 * var(--u)) !important;
      padding: calc(2 * var(--u)) calc(3.5 * var(--u)) calc(3 * var(--u)) !important;
      border-radius: 0.75rem;
    }

    .layer-badge { margin-bottom: 0.5rem; }
    .layer-title { color: #111 !important; font-size: 0.9rem; }

    .keycap-active {
      box-shadow: 0 1px 0 var(--shadow-color, #ccc) !important;
    }
    .keycap-empty { opacity: 0.4; }

    .annotation-bubble {
      box-shadow: none;
    }
    .annotation-svg path { opacity: 0.4 !important; }

    :root { --u: 42px; --gap: 4px; }
  }
`;

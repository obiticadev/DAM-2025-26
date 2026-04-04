import React, { useState, useEffect } from 'react';

// Función avanzada para formatear las etiquetas y ajustarlas visualmente a la tecla
const formatLabel = (label) => {
    if (!label) return '';
    const l = label.trim();
    if (l === 'TRANS') return <span className="text-xl leading-none font-normal">▽</span>;
    if (l === 'NONE') return '';

    // Dividir textos largos por espacios (ej. "Nump /" -> "Nump" + "/")
    if (l.includes(' ') && l.length > 5) {
        const parts = l.split(' ');
        return (
            <div className="flex flex-col items-center justify-center leading-tight gap-0.5">
                <span>{parts[0]}</span>
                <span>{parts.slice(1).join(' ')}</span>
            </div>
        );
    }

    // Dividir textos largos por barra (ej. "Nav/Spc" -> "Nav/" + "Spc")
    if (l.includes('/') && l.length > 5) {
        const parts = l.split('/');
        return (
            <div className="flex flex-col items-center justify-center leading-tight gap-0.5">
                <span>{parts[0]}/</span>
                <span>{parts.slice(1).join('/')}</span>
            </div>
        );
    }

    return l;
};

// Datos estructurados del layout (con la corrección de SHFT/`)
const keyboardData = {
    leftHalf: [
        [{ q: 'Caps', s: 'º', n: '', e: '' }, { q: '1', s: '!', n: 'F1', e: '' }, { q: '2', s: '@', n: 'F2', e: '' }, { q: '3', s: '#', n: 'F3', e: '' }, { q: '4', s: '$', n: 'F4', e: '' }, { q: '5', s: '%', n: 'F5', e: '' }],
        [{ q: 'Tab', s: 'Bksp', n: 'Del', e: '' }, { q: 'Q', s: 'Nump /', n: 'Alt+F4', e: '' }, { q: 'W', s: 'Nump 7', n: 'Mouse↑', e: '' }, { q: 'E', s: 'Nump 8', n: 'Whl L', e: 'é' }, { q: 'R', s: 'Nump 9', n: 'Whl ↑', e: '' }, { q: 'T', s: 'Nump -', n: 'Whl R', e: '' }, { q: 'SymTgl', s: 'TRANS', n: 'BOOTLD', e: '' }],
        [{ q: 'SymEsc', s: '', n: '', e: '' }, { q: 'A', s: 'Nump *', n: 'Mouse←', e: 'á' }, { q: 'S', s: 'Nump 4', n: 'Mouse↓', e: '' }, { q: 'D', s: 'Nump 5', n: 'Mouse→', e: '' }, { q: 'F', s: 'Nump 6', n: 'Whl ↓', e: '' }, { q: 'G', s: '+', n: 'Play', e: '' }, { q: '[', s: ',', n: '', e: '' }],
        [{ q: 'Shift', s: 'Shift', n: '', e: '' }, { q: 'Z', s: '↑', n: 'Prev', e: '' }, { q: 'X', s: 'Nump 1', n: 'Next', e: '' }, { q: 'C', s: 'Nump 2', n: 'Vol -', e: '' }, { q: 'V', s: 'Nump 3', n: 'Vol +', e: '' }, { q: 'B', s: 'Nump .', n: 'Mute', e: '' }, { q: '¡', s: 'PgUp', n: '', e: '' }, { q: '!', s: 'PgDn', n: '', e: '' }],
        [{ q: 'Ctrl', s: '←', n: '', e: '' }, { q: 'Win', s: '↓', n: '', e: '' }, { q: 'Meh', s: '→', n: '', e: '' }, { q: 'Alt', s: 'Nump 0', n: '', e: '' }, { q: 'Ctrl', s: 'Tab', n: '', e: '' }, { q: 'Space', s: 'Nav/Spc', n: '', e: '' }, { q: 'Enter', s: 'Enter', n: 'TRANS', e: '' }]
    ],
    rightHalf: [
        [{ q: '6', s: '^', n: 'F6', e: '' }, { q: '7', s: '&', n: 'F7', e: '' }, { q: '8', s: '_', n: 'F8', e: '' }, { q: '9', s: '!', n: 'F9', e: '' }, { q: '0', s: '?', n: 'F10', e: '' }, { q: 'NumLk', s: 'TRANS', n: 'F11', e: '' }],
        [{ q: 'SymTgl', s: 'TRANS', n: '', e: '' }, { q: 'Y', s: '+', n: '', e: '' }, { q: 'U', s: '[', n: '', e: 'ú' }, { q: 'I', s: ']', n: '', e: 'í' }, { q: 'O', s: '/', n: '', e: 'ó' }, { q: 'P', s: '\\', n: '', e: '' }, { q: '=', s: '|', n: 'PrtScn', e: '' }],
        [{ q: ']', s: 'NONE', n: '', e: '' }, { q: 'H', s: '-', n: '', e: '' }, { q: 'J', s: '(', n: '', e: '' }, { q: 'K', s: ')', n: '', e: '' }, { q: 'L', s: '=', n: '', e: '' }, { q: ';', s: '`', n: '', e: '' }, { q: '\'', s: '"', n: '', e: '' }],
        [{ q: '¿', s: 'Home', n: '', e: '' }, { q: '?', s: 'End', n: '', e: '' }, { q: 'N', s: '*', n: '', e: 'ñ' }, { q: 'M', s: '{', n: '', e: '' }, { q: ',', s: '}', n: '', e: '' }, { q: '.', s: '<', n: '', e: '' }, { q: '/', s: '>', n: '', e: '' }, { q: 'Shift', s: '~', n: '', e: '' }],
        [{ q: 'SymMo', s: 'TRANS', n: 'TRANS', e: '' }, { q: 'Bksp', s: 'Bksp', n: 'TRANS', e: '' }, { q: 'RALT_TP', s: 'RALT_TP', n: '', e: '' }, { q: '←', s: '←', n: '', e: '' }, { q: '↓', s: '↓', n: '', e: '' }, { q: '↑', s: '↑', n: '', e: '' }, { q: '→', s: '→', n: '', e: '' }]
    ]
};

// Configuración visual profesional: Colores de fondo activos vs inactivos (transparentes)
const layers = [
    {
        id: 'q', name: 'QWERTY',
        activeText: 'text-gray-800', activeBg: 'bg-[#f8f9fa]', activeBorder: 'border-[#e5e7eb] shadow-[0_4px_0_#d1d5db]',
        emptyText: 'text-gray-600', emptyBg: 'bg-[#2b2d31]', emptyBorder: 'border-[#1e1f22] shadow-[0_4px_0_#111214]'
    },
    {
        id: 's', name: '_SYMB',
        activeText: 'text-blue-900', activeBg: 'bg-[#eff6ff]', activeBorder: 'border-[#bfdbfe] shadow-[0_4px_0_#93c5fd]',
        emptyText: 'text-blue-500/50', emptyBg: 'bg-[#1e3a8a]/30', emptyBorder: 'border-[#1e3a8a]/50 shadow-[0_4px_0_rgba(30,58,138,0.5)]'
    },
    {
        id: 'n', name: '_NAV',
        activeText: 'text-emerald-900', activeBg: 'bg-[#ecfdf5]', activeBorder: 'border-[#a7f3d0] shadow-[0_4px_0_#6ee7b7]',
        emptyText: 'text-emerald-500/50', emptyBg: 'bg-[#064e3b]/30', emptyBorder: 'border-[#064e3b]/50 shadow-[0_4px_0_rgba(6,78,59,0.5)]'
    },
    {
        id: 'e', name: '_EXTRA',
        activeText: 'text-orange-900', activeBg: 'bg-[#fff7ed]', activeBorder: 'border-[#fed7aa] shadow-[0_4px_0_#fdba74]',
        emptyText: 'text-orange-500/50', emptyBg: 'bg-[#7c2d12]/30', emptyBorder: 'border-[#7c2d12]/50 shadow-[0_4px_0_rgba(124,45,18,0.5)]'
    }
];

// Coordenadas absolutas físicas para Redox
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

const Keycap = ({ label, layerData, pos }) => {
    const isEmpty = !label || label.trim() === 'NONE';
    const isTrans = label === 'TRANS';
    const hasContent = !isEmpty && !isTrans;

    // Resolviendo clases según si la tecla tiene acción o es hueco de la rejilla
    const bgClass = hasContent ? layerData.activeBg : layerData.emptyBg;
    const borderClass = hasContent ? layerData.activeBorder : layerData.emptyBorder;
    const textClass = hasContent ? layerData.activeText : layerData.emptyText;

    const style = {
        left: `calc(${pos.x} * var(--u))`,
        top: `calc(${pos.y} * var(--u))`,
        width: `calc(${(pos.w || 1)} * var(--u) - var(--gap))`,
        height: `calc(${(pos.h || 1)} * var(--u) - var(--gap))`,
        position: 'absolute'
    };

    return (
        <div
            style={style}
            className={`flex items-center justify-center rounded-lg border-t border-l border-r border-b-[3px] transition-all duration-300
        ${bgClass} ${borderClass}
        ${!hasContent ? 'opacity-80' : 'hover:brightness-105 z-10'}
      `}
        >
            <span className={`font-bold tracking-tight px-1 drop-shadow-sm
        ${textClass}
        ${label && label.length > 5 ? 'text-[10px] sm:text-xs' : 'text-xs sm:text-sm lg:text-base'}
      `}>
                {formatLabel(label)}
            </span>
        </div>
    );
};

const KeyboardLayout = ({ layerData }) => {
    const leftKeys = keyboardData.leftHalf.flat();
    const rightKeys = keyboardData.rightHalf.flat();

    return (
        <div className="print-avoid-break flex flex-col lg:flex-row gap-6 lg:gap-12 justify-center items-center bg-[#313338] p-8 lg:p-12 rounded-[2rem] shadow-2xl w-full max-w-6xl relative overflow-hidden border border-gray-800">

            {/* Mitad Izquierda */}
            <div className="relative" style={{ width: 'calc(8.0 * var(--u))', height: 'calc(6.75 * var(--u))' }}>
                {leftKeys.map((k, i) => (
                    <Keycap key={`L${i}`} label={k[layerData.id]} pos={leftPositions[i]} layerData={layerData} />
                ))}
            </div>

            {/* Mitad Derecha */}
            <div className="relative" style={{ width: 'calc(8.0 * var(--u))', height: 'calc(6.75 * var(--u))' }}>
                {rightKeys.map((k, i) => (
                    <Keycap key={`R${i}`} label={k[layerData.id]} pos={rightPositions[i]} layerData={layerData} />
                ))}
            </div>

        </div>
    );
};

export default function App() {
    const [activeLayer, setActiveLayer] = useState('q');
    const [viewAll, setViewAll] = useState(false);

    return (
        <div className="min-h-screen bg-gray-100 flex flex-col items-center py-10 px-4 font-sans text-gray-800">

            <style>
                {`
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
          
          /* INSTRUCCIONES ESTRICTAS PARA GENERAR PDF PROFESIONAL */
          @media print {
            @page {
              size: auto;
              margin: 15mm;
            }
            body {
              background-color: white !important;
              -webkit-print-color-adjust: exact !important;
              print-color-adjust: exact !important;
              color-adjust: exact !important;
            }
            .print-hidden {
              display: none !important;
            }
            .print-avoid-break {
              page-break-inside: avoid;
              break-inside: avoid;
            }
            :root { --u: 50px; --gap: 5px; } /* Tamaño fijo perfecto para PDF (A4) */
          }
        `}
            </style>

            {/* Panel de Control (No se imprime) */}
            <div className="print-hidden bg-white p-6 md:p-8 rounded-3xl shadow-xl border border-gray-200 mb-12 text-center w-full max-w-4xl">
                <h1 className="text-3xl font-black text-gray-900 tracking-tight mb-2">Editor Visual Redox</h1>
                <p className="text-gray-500 mb-8 font-medium">Disposición exacta 1:1 lista para imprimir</p>

                <div className="flex flex-wrap justify-center items-center gap-3 mb-8 bg-gray-50 p-2 rounded-2xl border border-gray-100">
                    {layers.map(layer => (
                        <button
                            key={layer.id}
                            onClick={() => { setActiveLayer(layer.id); setViewAll(false); }}
                            className={`px-6 py-2.5 rounded-xl font-bold text-sm transition-all border-2 
                ${(!viewAll && activeLayer === layer.id)
                                    ? `${layer.activeBg} ${layer.activeBorder} ${layer.activeText} shadow-md scale-[1.02]`
                                    : 'bg-white border-transparent text-gray-500 hover:bg-gray-200'}`}
                        >
                            {layer.name}
                        </button>
                    ))}
                    <div className="w-px h-8 bg-gray-300 mx-2"></div>
                    <button
                        onClick={() => setViewAll(true)}
                        className={`px-6 py-2.5 rounded-xl font-bold text-sm transition-all border-2 
              ${viewAll ? 'bg-indigo-50 border-indigo-300 text-indigo-700 shadow-md scale-[1.02]' : 'bg-white border-transparent text-gray-500 hover:bg-gray-200'}`}
                    >
                        📋 Ver Todas (Póster)
                    </button>
                </div>

                <div className="flex flex-col items-center gap-3">
                    <button
                        onClick={() => window.print()}
                        className="px-8 py-3.5 bg-gray-900 text-white font-bold rounded-2xl shadow-lg hover:bg-black hover:shadow-xl hover:-translate-y-0.5 transition-all inline-flex items-center gap-3 text-lg"
                    >
                        <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M17 17h2a2 2 0 002-2v-4a2 2 0 00-2-2H5a2 2 0 00-2 2v4a2 2 0 002 2h2m2 4h6a2 2 0 002-2v-4a2 2 0 00-2-2H9a2 2 0 00-2 2v4a2 2 0 002 2zm8-12V5a2 2 0 00-2-2H9a2 2 0 00-2 2v4h10z"></path></svg>
                        Generar PDF / Imprimir
                    </button>
                    <p className="text-xs text-indigo-600/80 font-semibold bg-indigo-50 px-4 py-2 rounded-lg">
                        ⚠️ Importante: En la ventana de impresión, asegúrate de activar la casilla "Gráficos de fondo".
                    </p>
                </div>
            </div>

            {/* Renderizado del Teclado Físico */}
            <div className="w-full flex flex-col gap-16 print:gap-12 items-center pb-24">
                {(viewAll ? layers : layers.filter(l => l.id === activeLayer)).map(layer => (
                    <div key={`render-${layer.id}`} className="w-full flex flex-col items-center">
                        <div className="flex items-center gap-3 mb-6 print:mb-4">
                            <div className={`w-4 h-4 rounded-full border-2 ${layer.activeBorder} ${layer.activeBg}`}></div>
                            <h2 className={`text-2xl font-black tracking-widest uppercase ${viewAll ? 'text-gray-800' : layer.activeText}`}>
                                {layer.name}
                            </h2>
                        </div>
                        <KeyboardLayout layerData={layer} />
                    </div>
                ))}
            </div>

        </div>
    );
}
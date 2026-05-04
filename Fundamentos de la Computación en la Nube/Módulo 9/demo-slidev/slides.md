---
theme: default
background: https://images.unsplash.com/photo-1451187580459-43490279c0fa?q=80&w=2072&auto=format&fit=crop
class: text-white
highlighter: shiki
lineNumbers: false
info: |
  ## HireFlow AI
  Presentación Arquitectura Serverless AWS
drawings:
  persist: false
transition: slide-up
title: HireFlow AI - AWS Architecture
mdc: true
---

<style>
  h1, h2, h3, h4, h5, h6 {
    color: white !important;
  }
  .aws-border { border-color: #FF9900; }
  .aws-text { color: #FF9900; }
  .aws-bg { background-color: #FF9900; }
</style>

# HireFlow AI
<h3 class="aws-text mt-4">Plataforma de Contratación Automatizada con IA</h3>

<div class="mt-8 text-gray-300">
  Arquitectura Serverless evaluada bajo el <br>
  <span class="font-bold text-white">AWS Well-Architected Framework</span>
</div>

<div class="absolute bottom-10 left-14 text-sm text-gray-400">
  Fundamentos de la Computación en la Nube · Módulo 9 — Práctica grupal
</div>

<!--
Buenos días. Somos el equipo X y vamos a presentar HireFlow AI, una plataforma cloud-native que automatiza el proceso de contratación utilizando inteligencia artificial. Hemos diseñado la arquitectura íntegramente sobre AWS y la hemos evaluado contra los cinco pilares del Well-Architected Framework.
-->

---
layout: default
---

# El Problema que Resolvemos

<div class="grid grid-cols-2 gap-12 mt-12">

  <!-- Lado Izquierdo: El Problema -->
  <div class="flex flex-col gap-4">
    <div class="text-center mb-2">
      <span class="text-red-500 font-bold text-xl uppercase tracking-widest">Antes</span>
      <p class="text-gray-400 text-sm">Proceso Tradicional</p>
    </div>
    
    <div class="bg-red-900/20 border border-red-500/50 p-4 rounded-lg text-center" v-click>
      📄 Miles de CVs recibidos
    </div>
    <div class="text-center text-red-500" v-click>↓</div>
    <div class="bg-red-900/20 border border-red-500/50 p-4 rounded-lg text-center" v-click>
      👤 RRHH revisa manualmente
    </div>
    <div class="text-center text-red-500" v-click>↓</div>
    <div class="bg-red-900/40 border border-red-500 p-4 rounded-lg text-center text-red-300 font-bold shadow-[0_0_15px_rgba(239,68,68,0.3)]" v-click>
      ⏳ Semanas de retraso y sesgo
    </div>
  </div>

  <!-- Lado Derecho: La Solución -->
  <div class="flex flex-col gap-4">
    <div class="text-center mb-2">
      <span class="text-green-500 font-bold text-xl uppercase tracking-widest">Después</span>
      <p class="text-gray-400 text-sm">HireFlow AI</p>
    </div>

    <div class="bg-green-900/20 border border-green-500/50 p-4 rounded-lg text-center" v-click>
      📄 Miles de CVs recibidos
    </div>
    <div class="text-center text-green-500" v-click>↓</div>
    <div class="bg-green-900/20 border border-green-500/50 p-4 rounded-lg text-center" v-click>
      🤖 IA extrae y analiza al instante
    </div>
    <div class="text-center text-green-500" v-click>↓</div>
    <div class="bg-green-900/40 border border-green-500 p-4 rounded-lg text-center text-green-300 font-bold shadow-[0_0_15px_rgba(34,197,94,0.3)]" v-click>
      ⚡ Respuesta filtrada en < 1 hora
    </div>
  </div>

</div>

<!--
Imaginad una empresa que publica una oferta y recibe cinco mil currículums en dos días. Con un proceso manual, RRHH tarda semanas en revisarlos, muchos candidatos nunca reciben respuesta, y el sesgo humano afecta a la selección. HireFlow AI resuelve esto: el candidato sube su CV, nuestra IA lo analiza en segundos, y RRHH solo interactúa con talento ya filtrado.
-->

---
layout: default
---

# ¿Cómo funciona? — El Flujo Asíncrono

<div class="mt-12 flex flex-wrap justify-center gap-4">
  
  <div class="flex items-center gap-2" v-click>
    <div class="w-10 h-10 rounded-full aws-bg text-black flex items-center justify-center font-bold text-xl">1</div>
    <div class="bg-gray-800 border aws-border px-4 py-2 rounded-lg">Login Web</div>
    <span class="text-gray-500 mx-2">➔</span>
  </div>

  <div class="flex items-center gap-2" v-click>
    <div class="w-10 h-10 rounded-full aws-bg text-black flex items-center justify-center font-bold text-xl">2</div>
    <div class="bg-gray-800 border aws-border px-4 py-2 rounded-lg">Sube CV</div>
    <span class="text-gray-500 mx-2">➔</span>
  </div>

  <div class="flex items-center gap-2" v-click>
    <div class="w-10 h-10 rounded-full aws-bg text-black flex items-center justify-center font-bold text-xl">3</div>
    <div class="bg-gray-800 border aws-border px-4 py-2 rounded-lg">Cola SQS</div>
    <span class="text-gray-500 mx-2">➔</span>
  </div>

  <div class="flex items-center gap-2" v-click>
    <div class="w-10 h-10 rounded-full aws-bg text-black flex items-center justify-center font-bold text-xl">4</div>
    <div class="bg-gray-800 border aws-border px-4 py-2 rounded-lg">OCR + NLP</div>
    <span class="text-gray-500 mx-2">➔</span>
  </div>

  <div class="flex items-center gap-2" v-click>
    <div class="w-10 h-10 rounded-full aws-bg text-black flex items-center justify-center font-bold text-xl">5</div>
    <div class="bg-gray-800 border aws-border px-4 py-2 rounded-lg text-white font-bold">Puntuación</div>
  </div>

</div>

<div class="flex justify-end pr-20 mt-4 text-gray-500" v-click>
  <span class="rotate-90 text-2xl">➔</span>
</div>

<div class="grid grid-cols-2 gap-8 mt-4 px-10">
  <div class="bg-green-900/30 border border-green-500 p-4 rounded-xl flex items-center justify-center gap-4" v-click>
    <span class="text-3xl">✅</span>
    <div>
      <h4 class="m-0 text-green-400">Score ≥ 80%</h4>
      <p class="text-sm text-gray-300 m-0">Email de entrevista automático</p>
    </div>
  </div>

  <div class="bg-red-900/30 border border-red-500 p-4 rounded-xl flex items-center justify-center gap-4" v-click>
    <span class="text-3xl">❌</span>
    <div>
      <h4 class="m-0 text-red-400">Score < 80%</h4>
      <p class="text-sm text-gray-300 m-0">Descarte guardado en BBDD</p>
    </div>
  </div>
</div>

<!--
El flujo tiene cinco grandes pasos. El candidato se loguea y sube el CV. Ahí termina la espera para él. Todo lo demás es asíncrono: SQS encola, extraemos el texto y analizamos por IA. Finalmente la puntuación decide: si es mayor a 80%, se envía email para entrevista; si no, queda descartado.
-->

---
layout: default
---

# Arquitectura Cloud — Capas Serverless

<div class="mt-8 flex flex-col gap-6 w-4/5 mx-auto">

  <div class="bg-[#1A233A]/80 border border-blue-500/50 rounded-xl p-4 relative overflow-hidden" v-click>
    <div class="absolute left-0 top-0 bottom-0 w-2 bg-blue-500"></div>
    <h3 class="text-blue-400 m-0 text-lg">1. Capa Edge & Frontend</h3>
    <div class="flex gap-4 mt-2 text-sm">
      <span class="bg-blue-900/50 px-3 py-1 rounded">🛡️ WAF (Seguridad)</span>
      <span class="bg-blue-900/50 px-3 py-1 rounded">🌐 CloudFront (CDN)</span>
      <span class="bg-blue-900/50 px-3 py-1 rounded">🔑 Cognito (Auth)</span>
    </div>
  </div>

  <div class="bg-[#2A1F1A]/80 border aws-border rounded-xl p-4 relative overflow-hidden" v-click>
    <div class="absolute left-0 top-0 bottom-0 w-2 aws-bg"></div>
    <h3 class="aws-text m-0 text-lg">2. Capa Lógica & IA (Core Serverless)</h3>
    <div class="flex gap-4 mt-2 text-sm">
      <span class="bg-orange-900/50 px-3 py-1 rounded text-orange-200">⚙️ API Gateway + SQS</span>
      <span class="bg-orange-900/50 px-3 py-1 rounded text-orange-200">⚡ AWS Lambda</span>
      <span class="bg-orange-900/50 px-3 py-1 rounded text-orange-200">🧠 Textract & Comprehend</span>
    </div>
  </div>

  <div class="bg-[#1A2A22]/80 border border-green-500/50 rounded-xl p-4 relative overflow-hidden" v-click>
    <div class="absolute left-0 top-0 bottom-0 w-2 bg-green-500"></div>
    <h3 class="text-green-400 m-0 text-lg">3. Capa de Datos & Observabilidad</h3>
    <div class="flex gap-4 mt-2 text-sm">
      <span class="bg-green-900/50 px-3 py-1 rounded text-green-200">💾 S3 (CVs) + DynamoDB (Datos)</span>
      <span class="bg-purple-900/50 px-3 py-1 rounded text-purple-200 border border-purple-500">📊 CloudWatch & X-Ray</span>
    </div>
  </div>

</div>

<!--
En lugar de un diagrama confuso, nuestra arquitectura se divide en tres capas claras. La capa Edge sirve la web y asegura los accesos. La capa Lógica es el motor: recibe peticiones, encola y ejecuta los modelos de IA. Finalmente la capa de datos almacena la información, siempre monitorizada por nuestra capa transversal de observabilidad.
-->

---
layout: two-cols
---

# ¿Por qué Serverless?
<p class="text-gray-400">Decisión Arquitectónica Clave</p>

<div class="mt-8 mr-6">
  <div class="bg-gray-800/80 p-6 rounded-xl border border-gray-600 relative">
    <div class="absolute -top-4 -left-4 text-4xl">💡</div>
    <h3 class="text-white mt-0">¿Por qué no EC2?</h3>
    <p class="text-sm text-gray-300">
      El reclutamiento tiene picos impredecibles. Un EC2 encendido 24/7 procesando aire es dinero tirado.
    </p>
    <ul class="text-sm text-gray-400 mt-4 space-y-2">
      <li>❌ Pago fijo mensual (~2.000€)</li>
      <li>❌ Escalar requiere tiempo</li>
      <li>❌ Parches del SO manuales</li>
    </ul>
  </div>
</div>

::right::

<div class="mt-24 ml-6">
  <div class="bg-green-900/20 p-6 rounded-xl border aws-border relative shadow-[0_0_20px_rgba(255,153,0,0.1)]">
    <div class="absolute -top-4 -left-4 text-4xl">🚀</div>
    <h3 class="aws-text mt-0">La Ventaja Serverless</h3>
    <p class="text-sm text-gray-300">
      Cero gestión de infraestructura. Todo escala linealmente a la demanda.
    </p>
    
    <div class="grid grid-cols-2 gap-4 mt-4">
      <div class="bg-black/50 p-3 rounded border border-gray-700">
        <div class="text-xs text-gray-500">Coste inactividad</div>
        <div class="font-bold text-xl text-green-400">0 €</div>
      </div>
      <div class="bg-black/50 p-3 rounded border border-gray-700">
        <div class="text-xs text-gray-500">Escalado</div>
        <div class="font-bold text-lg text-white">Instantáneo</div>
      </div>
    </div>
  </div>
</div>

<!--
¿Por qué serverless? Porque el reclutamiento tiene picos brutales. Con EC2 pagaríamos miles de euros al mes aunque no procesemos nada. Con Lambda, el coste de inactividad es literalmente cero. Además, escala instantáneamente y AWS se encarga de los parches de seguridad.
-->

---
layout: center
---

# Evaluación: Los 5 Pilares de AWS

<div class="text-center text-gray-400 mb-8 max-w-2xl mx-auto">
  Hemos evaluado nuestra arquitectura contra las mejores prácticas de AWS Well-Architected Framework.
</div>

<div class="grid grid-cols-3 gap-6 w-full px-10">
  
  <div class="bg-gray-800/80 border border-gray-600 p-6 rounded-xl text-center transform transition hover:scale-105" v-click>
    <div class="text-4xl mb-3">🔧</div>
    <h4 class="text-white m-0">Operativa</h4>
    <div class="text-xs text-gray-400 mt-2">Automatización (IaC) y Observabilidad</div>
  </div>

  <div class="bg-gray-800/80 border border-blue-500/50 p-6 rounded-xl text-center shadow-[0_0_15px_rgba(59,130,246,0.2)] transform transition hover:scale-105" v-click>
    <div class="text-4xl mb-3">🔒</div>
    <h4 class="text-blue-400 m-0">Seguridad</h4>
    <div class="text-xs text-gray-400 mt-2">Mínimo privilegio y cifrado total</div>
  </div>

  <div class="bg-gray-800/80 border border-green-500/50 p-6 rounded-xl text-center shadow-[0_0_15px_rgba(34,197,94,0.2)] transform transition hover:scale-105" v-click>
    <div class="text-4xl mb-3">🔄</div>
    <h4 class="text-green-400 m-0">Fiabilidad</h4>
    <div class="text-xs text-gray-400 mt-2">Tolerancia a fallos y Backups</div>
  </div>

  <!-- Centered bottom row -->
  <div class="col-span-3 flex justify-center gap-6 mt-2">
    <div class="bg-gray-800/80 border border-yellow-500/50 p-6 rounded-xl text-center w-64 shadow-[0_0_15px_rgba(234,179,8,0.2)] transform transition hover:scale-105" v-click>
      <div class="text-4xl mb-3">⚡</div>
      <h4 class="text-yellow-400 m-0">Rendimiento</h4>
      <div class="text-xs text-gray-400 mt-2">Event-driven y respuestas en ms</div>
    </div>

    <div class="bg-gray-800/80 border aws-border p-6 rounded-xl text-center w-64 shadow-[0_0_15px_rgba(255,153,0,0.2)] transform transition hover:scale-105" v-click>
      <div class="text-4xl mb-3">💰</div>
      <h4 class="aws-text m-0">Costos</h4>
      <div class="text-xs text-gray-400 mt-2">Pagar solo lo que usas</div>
    </div>
  </div>

</div>

<!--
El AWS Well-Architected Framework define cinco pilares. No es una checklist teórica, es la base de nuestro diseño. Vamos a repasar muy rápido cómo nuestra plataforma Serverless cumple con cada uno de estos cinco pilares clave.
-->

---
layout: two-cols
---

# Pilares 1 y 2
<h3 class="text-gray-300">🔧 Operativa & 🔒 Seguridad</h3>

<div class="mr-6 mt-8">
  <div class="border-l-4 border-gray-500 pl-4 mb-8">
    <h4 class="m-0 text-white flex items-center gap-2"><div class="w-2 h-2 rounded-full bg-gray-500"></div> Excelencia Operativa</h4>
    <ul class="text-sm text-gray-400 mt-2 space-y-1">
      <li><strong>Despliegues:</strong> Todo es Código (IaC con SAM).</li>
      <li><strong>Visibilidad:</strong> Trazas con X-Ray.</li>
      <li><strong>Pruebas:</strong> Despliegues Canary progresivos.</li>
    </ul>
  </div>
</div>

::right::

<div class="ml-6 mt-20">
  <div class="bg-[#0A192F] p-5 rounded-xl border border-blue-500/30">
    <h4 class="m-0 text-blue-400 text-center mb-4 border-b border-blue-500/30 pb-2">Capas de Seguridad</h4>
    
    <div class="flex flex-col gap-2 text-sm">
      <div class="bg-blue-900/30 px-4 py-2 rounded flex justify-between" v-click>
        <span>👤 Identidad</span> <span class="text-gray-400">Cognito + IAM</span>
      </div>
      <div class="bg-blue-900/40 px-4 py-2 rounded flex justify-between" v-click>
        <span>🌐 Red</span> <span class="text-gray-400">WAF + TLS 1.2+</span>
      </div>
      <div class="bg-blue-900/50 px-4 py-2 rounded flex justify-between" v-click>
        <span>💾 Datos</span> <span class="text-gray-400">KMS (Cifrado)</span>
      </div>
      <div class="bg-blue-900/60 px-4 py-2 rounded flex justify-between border border-blue-500/50" v-click>
        <span>👁️ Auditoría</span> <span class="text-white font-bold">CloudTrail</span>
      </div>
    </div>
  </div>
</div>

<!--
Excelencia operativa: desplegamos la infraestructura como código con SAM y monitorizamos con X-Ray. En seguridad aplicamos defensa en profundidad: identidad con Cognito, red protegida por WAF, datos cifrados con KMS y auditoría constante con CloudTrail.
-->

---
layout: two-cols
---

# Pilares 3 y 4
<h3 class="text-gray-300">🔄 Fiabilidad & ⚡ Rendimiento</h3>

<div class="mr-6 mt-8 flex flex-col gap-4">
  <div class="bg-gray-800 p-4 rounded-xl border-l-4 border-green-500">
    <h4 class="m-0 text-white mb-2">Cero pérdida de datos</h4>
    <p class="text-sm text-gray-400 leading-tight">
      Si una Lambda falla, la cola <strong>SQS</strong> reintenta automáticamente. Si falla 3 veces, va a una <strong>Dead Letter Queue (DLQ)</strong> para revisión manual.
    </p>
  </div>

  <div class="bg-gray-800 p-4 rounded-xl border-l-4 border-green-500" v-click>
    <h4 class="m-0 text-white mb-2">Resiliencia</h4>
    <p class="text-sm text-gray-400 leading-tight">
      Servicios Multi-AZ por defecto. DynamoDB con <strong>Point-in-Time Recovery</strong> (35 días).
    </p>
  </div>
</div>

::right::

<div class="ml-6 mt-8">
  
  <div class="grid grid-cols-1 gap-4 mt-12">
    
    <div class="flex items-center gap-4 bg-black/40 p-4 rounded-lg border border-yellow-500/30" v-click>
      <div class="text-3xl text-yellow-500">⚡</div>
      <div>
        <div class="font-bold text-white">Event-Driven</div>
        <div class="text-xs text-gray-400">Respuesta web inmediata, trabajo en segundo plano.</div>
      </div>
    </div>

    <div class="flex items-center gap-4 bg-black/40 p-4 rounded-lg border border-yellow-500/30" v-click>
      <div class="text-3xl text-yellow-500">🚀</div>
      <div>
        <div class="font-bold text-white">Baja Latencia</div>
        <div class="text-xs text-gray-400">CloudFront distribuye el frontend globalmente en < 50ms.</div>
      </div>
    </div>

  </div>

</div>

<!--
Fiabilidad: si una Lambda falla, SQS lo reintenta automáticamente. Ningún CV se pierde. Tenemos backups automáticos. En rendimiento, la clave es la arquitectura event-driven: el candidato recibe respuesta instantánea y CloudFront garantiza latencias mínimas a nivel global.
-->

---
layout: default
---

# Optimización de Costos
<h3 class="aws-text">💰 Pilar 5: FinOps en la Nube</h3>

<div class="mt-10 grid grid-cols-4 gap-4">

  <div class="bg-gray-800 p-4 rounded-xl text-center" v-click>
    <div class="text-3xl mb-2">🏷️</div>
    <div class="font-bold text-white text-sm">Tagging Estricto</div>
    <div class="text-xs text-gray-500 mt-2">Project=HireFlow<br>Env=Prod</div>
  </div>

  <div class="bg-gray-800 p-4 rounded-xl text-center" v-click>
    <div class="text-3xl mb-2">🚨</div>
    <div class="font-bold text-white text-sm">AWS Budgets</div>
    <div class="text-xs text-gray-500 mt-2">Alertas automáticas de desvío de gasto</div>
  </div>

  <div class="bg-gray-800 p-4 rounded-xl text-center" v-click>
    <div class="text-3xl mb-2">🧊</div>
    <div class="font-bold text-white text-sm">S3 Intelligent-Tiering</div>
    <div class="text-xs text-gray-500 mt-2">CVs antiguos van a Glacier auto.</div>
  </div>

  <div class="bg-[#2A1F1A] border aws-border p-4 rounded-xl text-center transform scale-105 shadow-lg" v-click>
    <div class="text-3xl mb-2">⚡</div>
    <div class="font-bold text-white text-sm">Pago por uso</div>
    <div class="text-xs text-gray-400 mt-2">Serverless model</div>
  </div>

</div>

<div class="bg-black/50 border border-gray-700 rounded-xl p-6 mt-10 flex justify-around text-center" v-click>
  
  <div>
    <div class="text-sm text-gray-500 uppercase tracking-widest">Coste / CV procesado</div>
    <div class="text-3xl font-bold text-white mt-1">~0,02 €</div>
  </div>

  <div class="border-l border-gray-700"></div>

  <div>
    <div class="text-sm text-gray-500 uppercase tracking-widest">Ahorro Almacenamiento</div>
    <div class="text-3xl font-bold text-blue-400 mt-1">Hasta 95%</div>
  </div>

  <div class="border-l border-gray-700"></div>

  <div>
    <div class="text-sm text-gray-500 uppercase tracking-widest">Coste Servidor Inactivo</div>
    <div class="text-3xl font-bold text-green-400 mt-1">0 €</div>
  </div>

</div>

<!--
Quinto pilar: costos. Etiquetamos recursos, usamos AWS Budgets para evitar sorpresas, y con S3 Intelligent-Tiering los CVs antiguos van a Glacier automáticamente, ahorrando un 95%. Lo mejor: el coste de inactividad es cero. Solo pagamos dos céntimos cuando realmente procesamos un CV.
-->

---
layout: center
---

# Conclusión

<div class="bg-gray-800/60 p-8 rounded-2xl border border-gray-600 max-w-3xl mx-auto w-full">
  
  <h2 class="text-center aws-text m-0 mb-6">HireFlow AI (100% Serverless)</h2>

  <div class="grid grid-cols-2 gap-x-8 gap-y-4 text-gray-300 ml-8">
    <div class="flex items-center gap-3"><span class="text-green-500">✔</span> Excelencia Operativa garantizada</div>
    <div class="flex items-center gap-3"><span class="text-green-500">✔</span> Seguridad desde el diseño</div>
    <div class="flex items-center gap-3"><span class="text-green-500">✔</span> Resiliencia y Fiabilidad total</div>
    <div class="flex items-center gap-3"><span class="text-green-500">✔</span> Rendimiento asíncrono</div>
    <div class="flex items-center gap-3 col-span-2 justify-center mt-2"><span class="text-green-500">✔</span> Optimización de Costos FinOps</div>
  </div>

</div>

<blockquote class="text-2xl text-center border-none italic mt-12 text-white font-light" v-click>
  "Un buen arquitecto cloud no solo diseña para el día 1, <br><span class="aws-text font-bold">sino para el día 1000.</span>"
</blockquote>

<!--
Para cerrar: HireFlow AI cumple los cinco pilares. No es solo una arquitectura para el día 1, está preparada para evolucionar gracias a herramientas como AWS Trusted Advisor. El resultado es una plataforma segura, eficiente y que solo cuesta dinero cuando aporta valor. ¿Alguna pregunta?
-->

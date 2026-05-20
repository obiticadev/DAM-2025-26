# -*- coding: utf-8 -*-
"""Build MEMORIA.pdf from MEMORIA.md with TOC, pagination and macOS-style image frames."""
import os, re, subprocess, sys
from pathlib import Path
import markdown
from bs4 import BeautifulSoup

BASE = Path(__file__).parent.resolve()
MD_FILE = BASE / "MEMORIA.md"
HTML_FILE = BASE / "MEMORIA.html"
PDF_FILE = BASE / "MEMORIA.pdf"
EDGE = r"C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe"

# ---------- 1. Leer y preparar el Markdown ----------
md_src = MD_FILE.read_text(encoding="utf-8")

# Reemplaza el índice manual ("## Índice ... ---") por marcador [TOC]
md_src = re.sub(
    r"## Índice\s*\n.*?(?=\n---)",
    "## Índice\n\n[TOC]\n",
    md_src,
    count=1,
    flags=re.DOTALL,
)

# ---------- 2. Convertir a HTML ----------
md = markdown.Markdown(
    extensions=["extra", "tables", "toc", "fenced_code", "sane_lists"],
    extension_configs={"toc": {"title": "", "permalink": False}},
)
body_html = md.convert(md_src)

# ---------- 3. Post-procesar: marco macOS en imágenes y title page ----------
soup = BeautifulSoup(body_html, "html.parser")

# 3a. Construir bloque de portada con el primer h1 y los párrafos meta hasta el primer <hr>
title_block_items = []
first_h1 = soup.find("h1")
if first_h1:
    # Captura el h1 y todos los siblings hasta (sin incluir) el primer <hr>
    node = first_h1
    while node is not None:
        next_node = node.find_next_sibling()
        title_block_items.append(node.extract())
        if next_node and next_node.name == "hr":
            next_node.extract()  # quitar el separador, lo hace el page break
            break
        node = next_node

    title_div = soup.new_tag("div", attrs={"class": "title-page"})
    for it in title_block_items:
        title_div.append(it)
    soup.insert(0, title_div)

def build_figure(img, soup):
    figure = soup.new_tag("figure", attrs={"class": "mac-window"})
    content = soup.new_tag("div", attrs={"class": "mac-content"})
    alt = img.get("alt", "").strip()
    content.append(img.extract())
    figure.append(content)
    if alt:
        cap = soup.new_tag("figcaption")
        cap.string = alt
        figure.append(cap)
    return figure

# 3b. Para cada <p> que contenga imágenes, construir figuras y colocarlas
paragraphs_with_imgs = []
for img in soup.find_all("img"):
    p = img.find_parent("p")
    if p and p not in paragraphs_with_imgs:
        paragraphs_with_imgs.append(p)

for p in paragraphs_with_imgs:
    figs = [build_figure(img, soup) for img in list(p.find_all("img"))]
    if not p.get_text(strip=True):
        # p solo contenía imágenes: insertar figuras antes y eliminar p
        for fig in figs:
            p.insert_before(fig)
        p.decompose()
    else:
        # p tenía texto además de imágenes: insertar figuras después de p, en orden
        last = p
        for fig in figs:
            last.insert_after(fig)
            last = fig

# 3c. Mover cualquier figura que haya quedado dentro de un blockquote
for fig in list(soup.find_all("figure", class_="mac-window")):
    bq = fig.find_parent("blockquote")
    if bq:
        bq.insert_after(fig.extract())

# 3d. Eliminar los blockquotes "📷 Captura N — ..." (la info ya aparece como pie de imagen)
for bq in list(soup.find_all("blockquote")):
    txt = bq.get_text(strip=True)
    if txt.startswith("📷") or "Captura " in txt[:25]:
        bq.decompose()

# ---------- 4. CSS ----------
CSS = """
@page {
  size: A4;
  margin: 1.8cm 1.8cm 2cm 1.8cm;
  @bottom-right {
    content: counter(page) " / " counter(pages);
    font-size: 9pt;
    color: #6b7280;
    font-family: 'Segoe UI', system-ui, sans-serif;
  }
  @bottom-left {
    content: "InfoDistribución S.L. — Memoria UT8";
    font-size: 8.5pt;
    color: #9ca3af;
    font-family: 'Segoe UI', system-ui, sans-serif;
  }
}
@page :first { @bottom-left { content: none; } @bottom-right { content: none; } }

* { box-sizing: border-box; }

body {
  font-family: 'Segoe UI', 'Inter', system-ui, -apple-system, sans-serif;
  font-size: 10pt;
  line-height: 1.45;
  color: #1f2937;
  max-width: 100%;
  margin: 0;
  padding: 0;
}

h1, h2, h3, h4 {
  font-family: 'Segoe UI', system-ui, sans-serif;
  color: #1f4e78;
  line-height: 1.25;
  page-break-after: avoid;
  break-after: avoid;
}
h1 { font-size: 22pt; border-bottom: 3px solid #1f4e78; padding-bottom: 6px; margin-top: 0; }
h2 { font-size: 15pt; margin-top: 18px; margin-bottom: 8px; border-bottom: 1px solid #d1d5db; padding-bottom: 3px; }
h3 { font-size: 11.5pt; margin-top: 12px; margin-bottom: 4px; color: #2c5d8a; }
h4 { font-size: 10.5pt; color: #2c5d8a; }

p { margin: 5px 0; text-align: justify; }
strong { color: #0f172a; }

/* ---- Portada ---- */
.title-page {
  height: calc(100vh - 4.6cm);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  text-align: left;
  page-break-after: always;
  break-after: page;
}
.title-page h1 {
  font-size: 28pt;
  border: none;
  padding: 0 0 12px 0;
  margin-bottom: 24px;
  border-left: 6px solid #1f4e78;
  padding-left: 16px;
  line-height: 1.2;
}
.title-page p { font-size: 11pt; line-height: 2; margin: 2px 0; text-align: left; }
.title-page p strong { color: #1f4e78; min-width: 220px; display: inline-block; }
.title-page p:last-of-type { margin-top: 30px; padding-top: 14px; border-top: 1px solid #d1d5db; }

/* ---- TOC ---- */
.toc { background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 8px; padding: 14px 20px; margin: 12px 0 22px 0; }
.toc > ul { margin: 0; padding-left: 16px; list-style: none; }
.toc ul ul { padding-left: 18px; }
.toc li { margin: 4px 0; }
.toc a { text-decoration: none; color: #1f4e78; font-weight: 500; }
.toc a:hover { text-decoration: underline; }

/* ---- Tablas ---- */
table { border-collapse: collapse; width: 100%; font-size: 9pt; margin: 12px 0; page-break-inside: auto; }
th, td { border: 1px solid #d1d5db; padding: 6px 8px; text-align: left; vertical-align: top; }
th { background: #1f4e78; color: white; font-weight: 600; }
tr:nth-child(even) td { background: #f9fafb; }

/* ---- Listas ---- */
ul, ol { padding-left: 22px; margin: 8px 0; }
li { margin: 3px 0; }

/* ---- Code ---- */
code { background: #f3f4f6; padding: 1px 5px; border-radius: 3px; font-family: 'Consolas', 'SF Mono', monospace; font-size: 9.5pt; color: #b45309; }
pre code { background: transparent; color: inherit; padding: 0; }
pre { background: #1e293b; color: #f1f5f9; padding: 12px 14px; border-radius: 8px; font-size: 9pt; overflow: auto; page-break-inside: avoid; }

/* ---- Blockquote (notas/captura) ---- */
blockquote { border-left: 4px solid #1f4e78; background: #eff6ff; padding: 8px 14px; margin: 12px 0; color: #1e3a8a; font-style: normal; page-break-inside: avoid; }
blockquote p { margin: 4px 0; }
blockquote code { background: rgba(255,255,255,0.6); color: #1e3a8a; }

/* ---- Marco blanco redondeado tipo Liquid Glass (sin titlebar) ---- */
figure.mac-window {
  max-width: 58%;
  margin: 10px auto;
  border-radius: 18px;
  background: #fafafa;
  border: 1px solid #ececec;
  box-shadow:
    0 10px 28px rgba(15, 23, 42, 0.10),
    0 3px 10px rgba(15, 23, 42, 0.06),
    inset 0 1px 0 rgba(255, 255, 255, 0.9);
  overflow: hidden;
  page-break-inside: avoid;
  break-inside: avoid;
  padding: 6px;
}
figure.mac-window .mac-content {
  background: #ffffff;
  line-height: 0;
  overflow: hidden;
  border-radius: 12px;
  border: 1px solid #f0f0f0;
}
figure.mac-window img { width: 100%; height: auto; display: block; }
figure.mac-window figcaption {
  font-size: 9pt;
  color: #475569;
  text-align: center;
  padding: 8px 12px 4px 12px;
  background: transparent;
  font-style: italic;
  line-height: 1.35;
}

hr { border: none; border-top: 1px solid #e2e8f0; margin: 20px 0; }

a { color: #2563eb; text-decoration: none; }
a:hover { text-decoration: underline; }

/* Mantener encabezados con su contenido siguiente, sin forzar página nueva */
h2 { page-break-before: auto; break-before: auto; }
h2 + p, h2 + figure, h3 + p, h3 + figure { page-break-before: avoid; }
"""

# ---------- 5. HTML final ----------
html_out = f"""<!doctype html>
<html lang="es">
<head>
<meta charset="utf-8">
<title>Memoria UT8 — InfoDistribución S.L.</title>
<style>
{CSS}
</style>
</head>
<body>
{str(soup)}
</body>
</html>
"""

HTML_FILE.write_text(html_out, encoding="utf-8")
print(f"HTML generado: {HTML_FILE}")

# ---------- 6. Edge headless → PDF ----------
url = HTML_FILE.as_uri()
cmd = [
    EDGE,
    "--headless=new",
    "--disable-gpu",
    "--no-sandbox",
    "--no-pdf-header-footer",
    f"--print-to-pdf={PDF_FILE}",
    "--virtual-time-budget=5000",
    url,
]
res = subprocess.run(cmd, capture_output=True, text=True, encoding="utf-8", errors="replace")
if PDF_FILE.exists():
    print(f"PDF generado: {PDF_FILE} ({PDF_FILE.stat().st_size // 1024} KB)")
else:
    print("ERROR generando PDF")
    print("STDOUT:", res.stdout[:500])
    print("STDERR:", res.stderr[:500])
    sys.exit(1)

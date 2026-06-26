#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Construye el EPUB maestro de The Beginning After The End (TBATE) a partir
de los .txt por capítulo, agrupando por volúmenes oficiales (12 + spin-off
Amongst the Fallen), con portada por volumen, TOC jerárquico y metadatos."""
import os, re, json, html, uuid, zipfile, datetime

CHAPDIR = "/home/oliwheel/Documentos/Java/DAM-2025-26/Proyectos_web/Scraper_Novela/La vida después de la muerte - Todos los capítulos"
HERE = os.path.dirname(os.path.abspath(__file__))
OUT = os.path.join(HERE, "The Beginning After The End - Saga Completa [ES].epub")

m = json.load(open('/tmp/tbate_map.json'))
bynum = {int(k): v for k, v in m['bynum'].items()}

# orden final de secciones: Vol1-8, Spin 8.5, Vol9-12  (con su portada)
vols = m['vols']                       # [[name,[nums]], ...] en orden 1..12
spin = m['spin']                       # [name,[nums]]  (Vol 8.5, va tras el Vol 8)
order = []
for i, (name, chaps) in enumerate(vols, 1):
    order.append((name, chaps, f"v{i:02d}.jpg"))
    if i == 8 and spin:
        order.append((spin[0], spin[1], "v085.jpg"))

LANG = "es"
TITLE = "The Beginning After The End — Saga Completa"
SUBTITLE = "La vida después de la muerte · Volúmenes 1–12 + Amongst the Fallen"
AUTHOR = "TurtleMe"
PUBLISHER = "TurtleMe / Tapas Media"
SUBJECTS = ["Fantasía", "Acción", "Aventura", "Reencarnación", "Isekai", "Magia",
            "LitRPG", "Web Novel", "Fantasía oscura", "Romance"]
SYNOPSIS = [
 "El rey más fuerte, Grey, lo tenía todo: poder, posición y riqueza incomparables en un mundo regido por la habilidad marcial. Sin embargo, la soledad acompaña de cerca a quienes ostentan un gran poder.",
 "Tras una vida vacía y sin propósito, el Rey Grey renace como Arthur Leywin en un mundo nuevo rebosante de magia y monstruos. Con una segunda oportunidad, está decidido a no repetir los errores de su vida pasada.",
 "Reviviendo en este nuevo mundo, Arthur tendrá que enfrentarse a las consecuencias de la fuerza, encontrar su lugar y descubrir la verdad que se oculta tras su reencarnación.",
 "Una épica de fantasía completa, desde sus primeros años hasta el final definitivo."]

CSS = """@charset "utf-8";
body{font-family:serif;line-height:1.55;margin:5%;text-align:justify;}
h1,h2{text-align:center;font-weight:bold;page-break-before:always;}
h1{font-size:1.6em;margin:1.5em 0 1em;}
h2{font-size:1.3em;margin:1em 0 .6em;}
p{margin:0 0 .7em;text-indent:1.2em;}
p.first{text-indent:0;}
.cover,.vol{margin:0;padding:0;text-align:center;}
.cover img,.vol img{max-width:100%;height:auto;}
.vol h1{page-break-before:avoid;margin-top:1em;}
.subt{font-style:italic;color:#555;text-align:center;text-indent:0;}
.volnum{text-align:center;font-size:1.1em;letter-spacing:.2em;text-transform:uppercase;color:#444;text-indent:0;}
"""


def esc(s): return html.escape(s, quote=False)


def read_chapter(num):
    """Devuelve (titulo, [parrafos]) de un .txt."""
    path = os.path.join(CHAPDIR, bynum[num])
    with open(path, encoding='utf-8') as f:
        raw = f.read().replace('\r\n', '\n').replace('\r', '\n')
    lines = raw.split('\n')
    # título desde el nombre de archivo (limpio)
    ft = re.sub(r'^Capítulo \d+\s+TBATE\s+', '', bynum[num]).replace('.txt', '').strip()
    # quitar 1ª línea si es la cabecera del propio txt
    body = lines[1:] if lines and 'TBATE' in lines[0] else lines
    paras = [ln.strip() for ln in body if ln.strip()]
    return ft, paras


def chapter_xhtml(num, disp_title, paras):
    head = f"Capítulo {num}: {disp_title}" if disp_title else f"Capítulo {num}"
    disp_title = head
    ps = []
    for i, p in enumerate(paras):
        cls = ' class="first"' if i == 0 else ''
        ps.append(f'<p{cls}>{esc(p)}</p>')
    body = '\n'.join(ps)
    return (f'<?xml version="1.0" encoding="utf-8"?>\n<!DOCTYPE html>\n'
            f'<html xmlns="http://www.w3.org/1999/xhtml" xmlns:epub="http://www.idpf.org/2007/ops" '
            f'xml:lang="{LANG}" lang="{LANG}">\n<head><meta charset="utf-8"/>'
            f'<title>{esc(disp_title)}</title>'
            f'<link rel="stylesheet" href="../css/style.css" type="text/css"/></head>\n'
            f'<body epub:type="bodymatter"><section epub:type="chapter">\n'
            f'<h1>{esc(disp_title)}</h1>\n{body}\n</section></body></html>\n')


def volume_divider(vol_name, cover_href):
    # nombre tipo "Volume 8.5: Amongst the Fallen (POV de Ellie)"
    mnum = re.match(r'Volume\s+([\d.]+):?\s*(.*)', vol_name)
    num = mnum.group(1) if mnum else ''
    rest = mnum.group(2) if mnum else vol_name
    return (f'<?xml version="1.0" encoding="utf-8"?>\n<!DOCTYPE html>\n'
            f'<html xmlns="http://www.w3.org/1999/xhtml" xmlns:epub="http://www.idpf.org/2007/ops" '
            f'xml:lang="{LANG}" lang="{LANG}">\n<head><meta charset="utf-8"/>'
            f'<title>{esc(vol_name)}</title>'
            f'<link rel="stylesheet" href="../css/style.css" type="text/css"/></head>\n'
            f'<body epub:type="bodymatter"><section epub:type="part" class="vol">\n'
            f'<figure class="vol"><img src="../img/{cover_href}" alt="{esc(vol_name)}"/></figure>\n'
            f'<p class="volnum">Volumen {esc(num)}</p>\n<h1>{esc(rest)}</h1>\n'
            f'</section></body></html>\n')


def main():
    book_id = 'urn:uuid:' + str(uuid.uuid4())
    now = datetime.datetime.now(datetime.timezone.utc).strftime('%Y-%m-%dT%H:%M:%SZ')
    manifest, spine_items, nav_items = [], [], []
    Z = zipfile.ZipFile(OUT, 'w', zipfile.ZIP_DEFLATED)
    zi = zipfile.ZipInfo('mimetype'); zi.compress_type = zipfile.ZIP_STORED
    Z.writestr(zi, 'application/epub+zip')
    Z.writestr('META-INF/container.xml',
        '<?xml version="1.0" encoding="utf-8"?>\n<container version="1.0" '
        'xmlns="urn:oasis:names:tc:opendocument:xmlns:container"><rootfiles>'
        '<rootfile full-path="OEBPS/content.opf" media-type="application/oebps-package+xml"/>'
        '</rootfiles></container>\n')
    Z.writestr('OEBPS/css/style.css', CSS)

    # portadas (img) -> manifest
    covdir = os.path.join(HERE, 'covers')
    for c in sorted(os.listdir(covdir)):
        data = open(os.path.join(covdir, c), 'rb').read()
        Z.writestr(f'OEBPS/img/{c}', data)
        if c != 'master.jpg':          # master se declara aparte como cover-image
            manifest.append(f'<item id="img-{c}" href="img/{c}" media-type="image/jpeg"/>')

    # cubierta maestra (Vol 1)
    Z.writestr('OEBPS/text/cover.xhtml',
        f'<?xml version="1.0" encoding="utf-8"?>\n<!DOCTYPE html>\n<html '
        f'xmlns="http://www.w3.org/1999/xhtml" xmlns:epub="http://www.idpf.org/2007/ops" '
        f'xml:lang="{LANG}"><head><meta charset="utf-8"/><title>Cubierta</title>'
        f'<link rel="stylesheet" href="../css/style.css" type="text/css"/></head>'
        f'<body><section epub:type="cover" class="cover">'
        f'<img src="../img/master.jpg" alt="Cubierta"/></section></body></html>\n')
    manifest.append('<item id="cover" href="text/cover.xhtml" media-type="application/xhtml+xml"/>')
    manifest.append('<item id="cover-image" href="img/master.jpg" media-type="image/jpeg" properties="cover-image"/>')
    spine_items.append('<itemref idref="cover"/>')

    # sinopsis
    sp = '\n'.join(f'<p>{esc(s)}</p>' for s in SYNOPSIS)
    Z.writestr('OEBPS/text/sinopsis.xhtml',
        f'<?xml version="1.0" encoding="utf-8"?>\n<!DOCTYPE html>\n<html '
        f'xmlns="http://www.w3.org/1999/xhtml" xml:lang="{LANG}"><head><meta charset="utf-8"/>'
        f'<title>Sinopsis</title><link rel="stylesheet" href="../css/style.css" type="text/css"/>'
        f'</head><body><h1>{esc(TITLE)}</h1><p class="subt">{esc(SUBTITLE)}</p>{sp}</body></html>\n')
    manifest.append('<item id="sinopsis" href="text/sinopsis.xhtml" media-type="application/xhtml+xml"/>')
    spine_items.append('<itemref idref="sinopsis"/>')
    nav_items.append(('Sinopsis', 'text/sinopsis.xhtml', None))

    # volúmenes + capítulos
    cidx = 0
    for vi, (vol_name, chaps, cover) in enumerate(order):
        vid = f'vol{vi:02d}'
        Z.writestr(f'OEBPS/text/{vid}.xhtml', volume_divider(vol_name, cover))
        manifest.append(f'<item id="{vid}" href="text/{vid}.xhtml" media-type="application/xhtml+xml"/>')
        spine_items.append(f'<itemref idref="{vid}"/>')
        children = []
        for num in chaps:
            cidx += 1
            ftitle, paras = read_chapter(num)
            disp = f"Capítulo {num}: {ftitle}" if ftitle else f"Capítulo {num}"
            fid = f'c{cidx:04d}'
            Z.writestr(f'OEBPS/text/{fid}.xhtml', chapter_xhtml(num, ftitle, paras))
            manifest.append(f'<item id="{fid}" href="text/{fid}.xhtml" media-type="application/xhtml+xml"/>')
            spine_items.append(f'<itemref idref="{fid}"/>')
            children.append((disp, f'text/{fid}.xhtml'))
        nav_items.append((vol_name, f'text/{vid}.xhtml', children))

    # nav.xhtml jerárquico
    def render_nav():
        lis = []
        for label, href, children in nav_items:
            if children is None:
                lis.append(f'<li><a href="{href}">{esc(label)}</a></li>')
            else:
                sub = '\n'.join(f'<li><a href="{h}">{esc(t)}</a></li>' for t, h in children)
                lis.append(f'<li><a href="{href}">{esc(label)}</a><ol>\n{sub}\n</ol></li>')
        return '\n'.join(lis)
    nav = (f'<?xml version="1.0" encoding="utf-8"?>\n<!DOCTYPE html>\n<html '
           f'xmlns="http://www.w3.org/1999/xhtml" xmlns:epub="http://www.idpf.org/2007/ops" '
           f'xml:lang="{LANG}"><head><meta charset="utf-8"/><title>Índice</title></head><body>'
           f'<nav epub:type="toc" id="toc"><h1>Índice</h1><ol>\n{render_nav()}\n</ol></nav>'
           f'<nav epub:type="landmarks" hidden=""><ol>'
           f'<li><a epub:type="cover" href="text/cover.xhtml">Cubierta</a></li>'
           f'<li><a epub:type="bodymatter" href="text/sinopsis.xhtml">Inicio</a></li>'
           f'</ol></nav></body></html>\n')
    Z.writestr('OEBPS/nav.xhtml', nav)
    manifest.append('<item id="nav" href="nav.xhtml" properties="nav" media-type="application/xhtml+xml"/>')

    subj = '\n'.join(f'<dc:subject>{esc(s)}</dc:subject>' for s in SUBJECTS)
    desc = '<br/>'.join(esc(s) for s in SYNOPSIS)
    opf = (f'<?xml version="1.0" encoding="utf-8"?>\n<package xmlns="http://www.idpf.org/2007/opf" '
           f'version="3.0" unique-identifier="BookId" xml:lang="{LANG}">\n<metadata '
           f'xmlns:dc="http://purl.org/dc/elements/1.1/">\n'
           f'<dc:identifier id="BookId">{book_id}</dc:identifier>\n'
           f'<dc:title>{esc(TITLE)}</dc:title>\n'
           f'<dc:language>{LANG}</dc:language>\n'
           f'<dc:creator id="aut">{esc(AUTHOR)}</dc:creator>\n'
           f'<meta refines="#aut" property="role" scheme="marc:relators">aut</meta>\n'
           f'<dc:publisher>{esc(PUBLISHER)}</dc:publisher>\n'
           f'<dc:description>{desc}</dc:description>\n{subj}\n'
           f'<dc:date>2025-11-07</dc:date>\n'
           f'<meta property="belongs-to-collection" id="col">The Beginning After The End</meta>\n'
           f'<meta refines="#col" property="collection-type">series</meta>\n'
           f'<meta name="calibre:series" content="The Beginning After The End"/>\n'
           f'<meta property="dcterms:modified">{now}</meta>\n'
           f'<meta name="cover" content="cover-image"/>\n</metadata>\n<manifest>\n'
           + '\n'.join(manifest) + '\n</manifest>\n<spine>\n'
           + '\n'.join(spine_items) + '\n</spine>\n</package>\n')
    Z.writestr('OEBPS/content.opf', opf)
    Z.close()
    print(f"OK -> {OUT}")
    print(f"secciones: {len(order)} (12 vols + spin-off) | capítulos: {cidx}")


if __name__ == '__main__':
    main()

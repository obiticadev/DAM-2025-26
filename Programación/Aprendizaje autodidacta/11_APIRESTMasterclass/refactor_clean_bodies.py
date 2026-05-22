# -*- coding: utf-8 -*-
import os
import re

base_dir = r"C:\Users\obitica\Java\DAM-2025-26\Programación\Aprendizaje autodidacta\11_APIRESTMasterclass\src\main\java\com\masterclass\api"

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    modified = False
    
    # We find Javadoc comments containing 'Reto Extra' or 'RETO EXTRA' or 'pasoExtra'
    # Pattern to match Javadoc blocks
    javadoc_pattern = re.compile(r'/\*\*[\s\S]*?(?:reto\s+extra|paso\s*extra|pasoextra)[\s\S]*?\*/', re.IGNORECASE)
    
    pos = 0
    new_content = ""
    
    matches = list(javadoc_pattern.finditer(content))
    
    if not matches:
        return False
        
    for i, match in enumerate(matches):
        start_javadoc, end_javadoc = match.span()
        javadoc_text = match.group(0)
        
        # Add everything from last position to start of Javadoc
        new_content += content[pos:start_javadoc]
        
        # Extract the descriptive part of Javadoc for the TODO
        # e.g., strip /*, */, * and find the main description line
        lines = [l.strip().lstrip('*').strip() for l in javadoc_text.split('\n')]
        description = "Implementar el reto extra correspondiente."
        for line in lines:
            if "Reto Extra" in line or "RETO EXTRA" in line:
                description = line
                break
        
        # Now find the first opening brace '{' after the Javadoc
        open_brace_idx = content.find('{', end_javadoc)
        if open_brace_idx == -1:
            # No opening brace found, just copy the match and proceed
            new_content += javadoc_text
            pos = end_javadoc
            continue
            
        # Find the method name from the signature between end of Javadoc and '{'
        sig_text = content[end_javadoc:open_brace_idx]
        paren_idx = sig_text.find('(')
        method_name = "retoExtra"
        if paren_idx != -1:
            pre_paren = sig_text[:paren_idx].strip()
            name_match = re.search(r'\b(\w+)\s*$', pre_paren)
            if name_match:
                method_name = name_match.group(1)
                
        # Find matching closing brace
        brace_count = 1
        close_brace_idx = -1
        for j in range(open_brace_idx + 1, len(content)):
            if content[j] == '{':
                brace_count += 1
            elif content[j] == '}':
                brace_count -= 1
                if brace_count == 0:
                    close_brace_idx = j
                    break
                    
        if close_brace_idx == -1:
            new_content += javadoc_text
            pos = end_javadoc
            continue
            
        # We got the body! Let's construct the new body with TODOs and throw exception
        indent = "        " # standard indent
        
        new_body = f"\n{indent}// TODO extra: {description}\n"
        new_body += f"{indent}// 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.\n"
        new_body += f"{indent}// 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.\n"
        new_body += f"{indent}// 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.\n"
        new_body += f"{indent}// 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.\n"
        new_body += f"{indent}throw new UnsupportedOperationException(\"TODO: Implementar la lógica del reto extra para {method_name}\");\n{indent.replace('    ', '', 1)}"
        
        # Add Javadoc + signature + open brace + new body + close brace
        new_content += javadoc_text + sig_text + '{' + new_body + '}'
        
        pos = close_brace_idx + 1
        modified = True
        
    new_content += content[pos:]
    
    if modified:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(new_content)
        return True
    return False

# Scan and run
count = 0
for root, dirs, files in os.walk(base_dir):
    for file in files:
        if file.endswith('.java'):
            filepath = os.path.join(root, file)
            if process_file(filepath):
                print(f"Refactored: {file}")
                count += 1

print(f"Total production files clean-refactored: {count}")

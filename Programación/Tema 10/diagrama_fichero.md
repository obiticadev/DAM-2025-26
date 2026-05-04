graph TD
    classDef abstract fill:#444,stroke:#fff,stroke-width:2px,color:#fff;
    classDef binary fill:#0a4069,stroke:#66b3ff,stroke-width:2px,color:#fff;
    classDef text fill:#0f592f,stroke:#4db879,stroke-width:2px,color:#fff;

    %% Raíz
    IO[java.io] --> Binario[Mundo Binario / Bytes]
    IO --> Texto[Mundo Texto / Caracteres]

    %% MUNDO BINARIO
    Binario --> IS[InputStream]:::abstract
    Binario --> OS[OutputStream]:::abstract

    %% Nivel Físico (Node)
    IS --> FIS[FileInputStream<br/>(Conexión lectura bruta)]:::binary
    OS --> FOS[FileOutputStream<br/>(Conexión escritura bruta)]:::binary

    %% Nivel Abstracción (Wrapper)
    IS --> FilterIS[FilterInputStream]:::abstract
    OS --> FilterOS[FilterOutputStream]:::abstract

    FilterIS --> BIS[BufferedInputStream<br/>(Tanque RAM)]:::binary
    FilterIS --> DIS[DataInputStream<br/>(Traduce a int, boolean...)]:::binary

    FilterOS --> BOS[BufferedOutputStream<br/>(Tanque RAM)]:::binary
    FilterOS --> DOS[DataOutputStream<br/>(Traduce int, boolean a bytes)]:::binary

    %% MUNDO TEXTO
    Texto --> R[Reader]:::abstract
    Texto --> W[Writer]:::abstract

    %% Nivel Puente
    R --> ISR[InputStreamReader<br/>(Puente Bytes->Texto)]:::text
    W --> OSW[OutputStreamWriter<br/>(Puente Texto->Bytes)]:::text

    %% Nivel Físico (Node)
    ISR --> FR[FileReader<br/>(Lee chars del disco)]:::text
    OSW --> FW[FileWriter<br/>(Escribe chars al disco)]:::text

    %% Nivel Abstracción (Wrapper)
    R --> BR[BufferedReader<br/>(Método readLine)]:::text
    W --> BW[BufferedWriter<br/>(Acumula texto en RAM)]:::text
    W --> PW[PrintWriter<br/>(Métodos println fáciles)]:::text



graph TD
    classDef io fill:#6b1d1d,stroke:#ff6b6b,stroke-width:2px,color:#fff;
    classDef nio fill:#1d4c6b,stroke:#66b3ff,stroke-width:2px,color:#fff;
    classDef nio2 fill:#1d6b41,stroke:#4db879,stroke-width:2px,color:#fff;
    classDef buffer fill:#4f3d7a,stroke:#b380ff,stroke-width:2px,color:#fff;

    %% Raíz
    Java[Ecosistema Java I/O] --> IO[java.io <br/> Clásico / Bloqueante]:::io
    Java --> NIO[java.nio <br/> Moderno / No Bloqueante]:::nio

    %% RAMA IO (CLÁSICA)
    IO --> IO_Stream[Orientado a Streams <br/> Flujo continuo]:::io
    IO --> IO_File[Clase File <br/> Metadatos Básicos]:::io
    
    IO_Stream --> IO_In[InputStream / Reader <br/> Solo Lectura]:::io
    IO_Stream --> IO_Out[OutputStream / Writer <br/> Solo Escritura]:::io

    %% RAMA NIO (CORE)
    NIO --> NIO_Core[NIO Core <br/> Alta Concurrencia]:::nio
    NIO --> NIO_2[NIO.2 <br/> File System API]:::nio2

    %% NIO CORE CONCEPTOS
    NIO_Core --> Channels[Channels <br/> Bidireccionales]:::nio
    NIO_Core --> Selectors[Selectors <br/> Multiplexación]:::nio
    NIO_Core --> Buffers[Buffers <br/> Bloques de Memoria]:::buffer

    %% Relaciones NIO
    Channels -. "Leen/Escriben hacia" .-> Buffers
    Selectors -. "Monitorean múltiples" .-> Channels

    %% Tipos de Channels y Buffers
    Channels --> FC[FileChannel <br/> Archivos]:::nio
    Channels --> SC[SocketChannel <br/> Redes/TCP]:::nio
    
    Buffers --> BB[ByteBuffer]:::buffer
    Buffers --> CB[CharBuffer]:::buffer

    %% NIO.2 (FILES)
    NIO_2 --> Path[Interfaz Path <br/> Reemplazo de File]:::nio2
    NIO_2 --> Files[Clase Files <br/> Utilidades masivas]:::nio2
    
    %% Comparativa File vs Path
    IO_File -. "Sustituido por" .-> Path
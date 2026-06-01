// API del Boss Final. Sin dependencias externas (solo stdlib): habla con Redis
// por su protocolo RESP a pelo para incrementar un contador de visitas.
//
//	GET /health -> "ok"          (lo usa el HEALTHCHECK y el readiness de k8s)
//	GET /        -> "MENSAJE=<config> visitas=<n>"   (config viene de env)
package main

import (
	"bufio"
	"fmt"
	"log"
	"net"
	"net/http"
	"os"
	"strings"
)

func incrVisitas(addr string) (string, error) {
	conn, err := net.Dial("tcp", addr)
	if err != nil {
		return "", err
	}
	defer conn.Close()
	if _, err := fmt.Fprint(conn, "INCR visitas\r\n"); err != nil {
		return "", err
	}
	line, err := bufio.NewReader(conn).ReadString('\n')
	if err != nil {
		return "", err
	}
	line = strings.TrimSpace(line)
	if strings.HasPrefix(line, ":") { // respuesta entero RESP -> ":<n>"
		return line[1:], nil
	}
	return "", fmt.Errorf("respuesta inesperada de redis: %q", line)
}

func main() {
	mensaje := os.Getenv("MENSAJE")
	if mensaje == "" {
		mensaje = "(sin MENSAJE)"
	}
	redis := os.Getenv("REDIS_HOST")
	if redis == "" {
		redis = "cache:6379"
	}

	http.HandleFunc("/health", func(w http.ResponseWriter, _ *http.Request) {
		fmt.Fprint(w, "ok")
	})
	http.HandleFunc("/", func(w http.ResponseWriter, _ *http.Request) {
		v, err := incrVisitas(redis)
		if err != nil {
			w.WriteHeader(http.StatusInternalServerError)
			fmt.Fprintf(w, "ERROR cache: %v", err)
			return
		}
		fmt.Fprintf(w, "MENSAJE=%s visitas=%s", mensaje, v)
	})

	log.Println("API escuchando en :8080")
	if err := http.ListenAndServe(":8080", nil); err != nil {
		log.Fatal(err)
	}
}

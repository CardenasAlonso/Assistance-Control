# School Management System — I.E. Centro de Varones, Cañete

## Servicios

| Servicio | Puerto | Descripción |
|---|---|---|
| school-management-system | :8085 | Backend principal |
| minedu-chatbot-service | :8086 | Agente IA MINEDU |

## Requisitos
- Java 21
- Oracle Database (con Wallet configurado)
- ChromaDB (Docker): `docker run -p 8000:8000 chromadb/chroma`
- Variable de entorno: `ANTHROPIC_API_KEY=tu-api-key`

## Ejecutar

```bash
# 1. Backend principal
cd school-management-system
mvn spring-boot:run

# 2. ChromaDB
docker run -d -p 8000:8000 chromadb/chroma

# 3. Chatbot
cd minedu-chatbot-service
export ANTHROPIC_API_KEY=tu-api-key-aqui
mvn spring-boot:run
```

## Endpoints principales

### Auth
- POST /api/auth/login → { email, password }

### Blockchain
- GET  /api/blockchain/verify → verifica integridad de la cadena
- GET  /api/blockchain/student/{id} → historial de bloques de un alumno

### Chatbot (puerto 8086)
- POST /api/chat → { sessionId, userId, userRole, message }
- GET  /api/chat/history/{sessionId}
- POST /api/documents/index → subir PDF de MINEDU (multipart)

## Documentos MINEDU a indexar primero
1. Currículo Nacional de la Educación Básica (CNEB)
2. Programa Curricular de Educación Secundaria
3. Resolución Ministerial año escolar vigente
4. Orientaciones para Evaluación Formativa

## Arquitectura hexagonal — capas
```
domain/       → modelos puros, enums, excepciones (sin dependencias)
application/  → ports/in (interfaces de entrada), ports/out (salida), usecases, DTOs
infrastructure/ → controllers, entities, mappers, config, adapters
```

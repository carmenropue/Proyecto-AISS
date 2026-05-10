# Proyecto-AISS

Proyecto de integración desarrollado con Spring Boot para obtener información desde PeerTubeMiner y DailyMotionMiner y almacenarla en VideoMiner.

## Repositorio GitHub

https://github.com/carmenropue/Proyecto-AISS

## Servicios y Puertos

| Servicio | Puerto |
|---|---|
| VideoMiner | 8080 |
| PeerTubeMiner | 8081 |
| DailyMotionMiner | 8082 |

## Funcionalidades implementadas

- Obtención de canales desde PeerTubeMiner
- Obtención de vídeos desde PeerTubeMiner
- Obtención de canales desde DailyMotionMiner
- Obtención de vídeos desde DailyMotionMiner
- Envío de información a VideoMiner
- Persistencia en base de datos H2
- Mappers entre modelos externos y modelos VideoMiner
- Soporte para comments
- Soporte para captions
- Generación automática de IDs en VideoMiner para DailyMotionMiner

## Documentación

DailyMotion Miner: localhost:8082/swagger-ui/index.html

Peertube Miner: localhost:8081/swagger-ui/index.html

VideoMiner: localhost:8080/swagger-ui/index.html

## Tecnologías

- Java
- Spring Boot
- Maven
- H2 Database
- Postman

## Endpoints principales

### PeerTubeMiner

Obtiene un canal de PeerTube.

GET: http://localhost:8081/peertubeminer/v1/{channelHandle}?maxVideos=5&maxComments=2

Ejemplo: http://localhost:8081/peertubeminer/v1/transport_evolved_main?maxVideos=5&maxComments=2


Obtiene un vídeo concreto de PeerTube.

GET: http://localhost:8081/peertubeminer/v1/video/{id}?maxComments=2

Ejemplo: http://localhost:8081/peertubeminer/v1/video/21856?maxComments=2


Envía el canal de PeerTube a VideoMiner.

POST: http://localhost:8081/peertubeminer/v1/{id}?maxVideos=5&maxComments=2

Ejemplo: http://localhost:8081/peertubeminer/v1/transport_evolved_main?maxVideos=5&maxComments=2

### DailyMotionMiner

Obtiene un canal de DailyMotion.

GET: http://localhost:8082/dailymotion/v1/{id}?maxVideos=10&maxPages=2

Ejemplo: http://localhost:8082/dailymotion/v1/x43py0?maxVideos=10&maxPages=2


Envía el canal de DailyMotion a VideoMiner.

POST: http://localhost:8082/dailymotion/v1/{id}?maxVideos=10&maxPages=2

Ejemplo: http://localhost:8082/dailymotion/v1/x43py0?maxVideos=10&maxPages=2


Obtiene un vídeo concreto de DailyMotion.

GET: http://localhost:8082/dailymotion/v1/video/{id}

Ejemplo: http://localhost:8082/dailymotion/v1/video/x89abc

### VideoMiner

#### VideoMiner - Captions

Obtiene todas las captions almacenadas.

GET:
http://localhost:8080/videominer/v1/captions

Obtiene una caption por ID.

GET:
http://localhost:8080/videominer/v1/captions/{id}

Obtiene las captions de un vídeo concreto.

GET:
http://localhost:8080/videominer/v1/videos/{videoId}/captions

Crea una nueva caption para un vídeo.

POST:
http://localhost:8080/videominer/v1/videos/{videoId}/captions

Elimina una caption.

DELETE:
http://localhost:8080/videominer/v1/captions/{id}

#### VideoMiner - Channels

Obtiene todos los canales almacenados.

GET:
http://localhost:8080/videominer/v1/channels

Obtiene un canal por ID.

GET:
http://localhost:8080/videominer/v1/channels/{id}

Crea un nuevo canal.

POST:
http://localhost:8080/videominer/v1/channels

Actualiza un canal existente.

PUT:
http://localhost:8080/videominer/v1/channels/{id}

Elimina un canal.

DELETE:
http://localhost:8080/videominer/v1/channels/{id}

#### VideoMiner - Comments

Obtiene todos los comentarios almacenados.

GET:
http://localhost:8080/videominer/v1/comments

Obtiene un comentario por ID.

GET:
http://localhost:8080/videominer/v1/comments/{id}

Obtiene los comentarios de un vídeo concreto.

GET:
http://localhost:8080/videominer/v1/videos/{videoId}/comments

Crea un nuevo comentario para un vídeo.

POST:
http://localhost:8080/videominer/v1/videos/{videoId}/comments

Elimina un comentario.

DELETE:
http://localhost:8080/videominer/v1/comments/{id}

#### VideoMiner - Videos

Obtiene todos los vídeos almacenados.

GET:
http://localhost:8080/videominer/v1/videos

Obtiene un vídeo por ID.

GET:
http://localhost:8080/videominer/v1/videos/{id}

Obtiene todos los vídeos de un canal concreto.

GET:
http://localhost:8080/videominer/v1/channels/{channelId}/videos

Crea un nuevo vídeo en un canal.

POST:
http://localhost:8080/videominer/v1/channels/{channelId}/videos

Actualiza un vídeo existente.

PUT:
http://localhost:8080/videominer/v1/videos/{id}

Elimina un vídeo.

DELETE:
http://localhost:8080/videominer/v1/videos/{id}

Obtiene el usuario de un vídeo.

GET:
http://localhost:8080/videominer/v1/videos/{id}/user

Actualiza el usuario de un vídeo.

PUT:
http://localhost:8080/videominer/v1/videos/{id}/user


## Notas

- Algunos vídeos de DailyMotion pueden no tener captions disponibles y devolver una lista vacía.


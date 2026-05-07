# Proyecto-AISS

Proyecto de integración desarrollado con Spring Boot para obtener información desde PeerTubeMiner y DailyMotionMiner y almacenarla en VideoMiner.

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

## Tecnologías

- Java
- Spring Boot
- Maven
- H2 Database
- Postman

## Endpoints principales

### PeerTubeMiner

GET:
http://localhost:8081/peertubeminer/api/v1/transport_evolved_main?maxVideos=5

### DailyMotionMiner

GET:
http://localhost:8082/dailymotion/x43py0?maxVideos=5&maxPages=1

### VideoMiner

GET:
http://localhost:8080/videominer/v1/channels

GET:
http://localhost:8080/videominer/v1/videos

GET:
http://localhost:8080/videominer/v1/comments

GET:
http://localhost:8080/videominer/v1/captions



To do:

- Arreglar DailyMotion, no salen los captions(hay que hacer un token) y los comments tampoco salen

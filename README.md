## Запуск

```bash
mvn spring-boot:run
```

или сборка и запуск jar-файла:

```bash
mvn clean package
java -jar target/mock-service.jar
```

Приложение стартует на порту `8080`.

## Особенности

Оба эндпоинта имитируют время отклика реального сервиса — перед ответом добавляется
случайная задержка от 1 до 2 секунд (`Thread.sleep` со случайным значением в контроллере).

## Эндпоинты

### GET /api/status

Возвращает статичный json:

```bash
curl http://localhost:8080/api/status
```

Ответ:

```json
{"login":"Login","status":"ok"}
```

### POST /api/login

Принимает login и password, возвращает их обратно вместе с текущей датой.

```bash
curl -X POST http://localhost:8080/api/login \
  -H "Content-Type: application/json" \
  -d '{"login":"myLogin","password":"myPassword"}'
```

Ответ:

```json
{"login":"myLogin","password":"myPassword","date":"2026-07-16 14:32:05"}
```

## Тестовое задание.

Стек технологий:

- spring boot
- gradle
- postgres
- mongo
- docker-compose
- flyway

1) Для запуска приложений надо выполнить команду git clone https://github.com/artem-kulakovich/hash-translator-service.git
2) Открыть проект и в терминале запустить команду docker compose up
3) Если во время запуска возникли проблемы с наименованием контейнеров (дупликаты), удалите их с помощью docker rm container-id или же удалите все контейнеры с помощью docker container prune

Для тестирования приложения я прикрепил коллекцию в папке usefull/postman, которой можно воспользоваться.
Для тестирования приложения была создана тест дата: пользователь (admin), который может создавать других пользователей, а также обычные пользователи.
Креды для админа: email - admin@mail.ru, password - admin; user@mail.ru - useruser. Все креды можно посмотреть в файле миграции.


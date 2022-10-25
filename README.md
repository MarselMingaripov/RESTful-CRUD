# RESTful-CRUD-API
## Цель - реализовация RESTful CRUD API на языке Java:
### Необходимо реализовать публичный API для этих операций
- создание пользователя
- представление всех пользователей
- представление пользователя
- обновление пользователя
- удаление пользователя

### Роуты работы с пользователями:
POST {url}/users - создание пользователя с именем 'admin'
Request json:
- "username": "admin",
- "password": "password",
- "name": "example",
- "email": "example@example.com"

GET {url}/users - представление всех пользователей

GET {url}/users/{username} - представление пользователя с именем 'admin'
- {username} - 'admin'

PUT	{url}/users/{username} - обновление пользователя с именем 'admin'
- {username} - 'admin'

DELETE	{url}/users/{username} - удаление пользователя с именем 'admin'
- {username} - 'admin'

---
### Задание реализовал за 1 день
+ подключил Hibernate для автоматической генерации таблиц по сущности
+ настроил SpringSecurity
---

# update task:
## 
# RESTful-CRUD-API
## Цель - реализовация RESTful CRUD API на языке Java:
### Необходимо реализовать публичный API для этих операций
- создание пользователя
- представление всех пользователей
- представление пользователя
- обновление пользователя
- удаление пользователя

### Роуты работы с пользователями:
POST {url}/users - создание пользователя c присвоением роли
Request json:
- "username": "admin",
- "password": "password",
- "name": "example",
- "email": "example@example.com"
- role

GET {url}/get-all-users - представление всех пользователей толкьо админ

GET {url}/users/{id} - представление пользователя admin все, юзер - только свой
- {id} - 1

PUT	{url}/users/{username} - обновление пользователя admin все, юзер - только свой
- {username} - 'admin'

DELETE	{url}/users/{username} - удаление пользователя только админ
- {username} - 'admin'

---
### Задание реализовал за 1 день
+ подключил Hibernate для автоматической генерации таблиц по сущности
+ настроил SpringSecurity
---

# update task:
## 
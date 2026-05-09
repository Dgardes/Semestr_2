# Task Manager API

Лабораторна робота №8: Робота з PostgreSQL та міграціями в NestJS.

## Як запустити

1. **Встановіть залежності**:
  ```bash
  npm install

2. **Налаштуйте середовище**:
   - Створіть файл `.env` (використовуйте `.env.example` як зразок).
   - Створіть порожню БД у PostgreSQL.

3. **Запустіть міграції** :
   ```bash
   npm run migration:run

4. **Запустіть проект**:
   ```bash
  npm run start:dev

## Змінні середовища .env

| Ключ | Приклад |
| :--- | :--- |
| `DB_HOST` | `localhost` |
| `DB_PORT` | `5432` |
| `DB_USERNAME` | `postgres` |
| `DB_PASSWORD` | `ваш_пароль` |
| `DB_NAME` | `nest_lab8` |

---

## Основні Ендпоінти

### Tasks
- `GET /tasks` — Всі задачі з тегами.
- `POST /tasks` — Створення задачі (можна додати `"tagIds": [1, 2]`).
- `GET /tasks/search?status=pending` — Пошук за статусом.
- `DELETE /tasks/:id` — Видалення задачі.

### Tags
- `GET /tags` — Список усіх тегів.
- `POST /tags` — Створення тегу.
- `PATCH /tags/:id` — Редагування тегу.
- `DELETE /tags/:id` — Видалення тегу (каскадно).

---

## Команди міграцій
- `npm run migration:generate src/migrations/Initial` — Створити міграцію.
- `npm run migration:run` — Застосувати зміни до БД.
- `npm run migration:revert` — Відкотити останню міграцію.
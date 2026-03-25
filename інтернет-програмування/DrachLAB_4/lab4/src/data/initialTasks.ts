import type { Task } from "../types/task";

export const INITIAL_TASKS: Task[] = [
  {
    id: "task-1",
    title: "Налаштування Docker середовища",
    description: "Підготувати Dockerfile та docker-compose для локальної розробки (Node.js + PostgreSQL).",
    status: "done",
    priority: "high",
    createdAt: new Date("2026-03-01"),
  },
  {
    id: "task-2",
    title: "Розробка REST API для авторизації",
    description: "Реалізувати реєстрацію та вхід користувачів з використанням JWT-токенів.",
    status: "in-progress",
    priority: "high",
    createdAt: new Date("2026-03-15"),
  },
  {
    id: "task-3",
    title: "Рефакторинг сервісу повідомлень",
    description: "Оптимізувати код відправки email та додати обробку черг через RabbitMQ.",
    status: "todo",
    priority: "medium",
    createdAt: new Date("2026-03-20"),
  },
  {
    id: "task-4",
    title: "Документування API в Swagger",
    description: "Описати всі ендпоінти модуля користувачів згідно зі стандартом OpenAPI 3.0.",
    status: "todo",
    priority: "low",
    createdAt: new Date("2026-03-24"),
  },
];
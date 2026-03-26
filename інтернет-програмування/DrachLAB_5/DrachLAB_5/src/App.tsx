import { useState } from "react";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { todosApi } from "./api/todos";
import "./App.css";

function App() {
  const queryClient = useQueryClient();
  const [newTodoTitle, setNewTodoTitle] = useState("");

  //отримання списку
  const { data: todos, isLoading, isError } = useQuery({
    queryKey: ["todos"],
    queryFn: todosApi.getAll,
  });

  //мутація для створення
  const createMutation = useMutation({
    mutationFn: (title: string) => todosApi.create({ title, completed: false }),
    onSuccess: () => {
      //інвалідація кешу змушує юзКуері перекачати список
      queryClient.invalidateQueries({ queryKey: ["todos"] });
      //очищення поля
      setNewTodoTitle("");
    },
  });

  const handleAddTodo = (e: React.FormEvent) => {
    e.preventDefault();
    if (newTodoTitle.trim().length < 3) return;
    
    //виклик мутації
    createMutation.mutate(newTodoTitle);
  };

  if (isLoading) return <div>Завантаження</div>;
  if (isError) return <div>Сталася помилка при завантаженні</div>;

  return (
    <div className="container">
      <h1>Todo App</h1>

      <form onSubmit={handleAddTodo} className="add-form">
        <input
          type="text"
          value={newTodoTitle}
          onChange={(e) => setNewTodoTitle(e.target.value)}
          placeholder="ваші плани"
        />
        <button 
          type="submit" 
          disabled={createMutation.isPending || newTodoTitle.trim().length < 3}
        >
          {createMutation.isPending ? "Додавання" : "Додати"}
        </button>
      </form>

      <div className="todo-list">
        {todos?.map((todo) => (
          <div key={todo.id} className={`todo-item ${todo.completed ? "completed" : ""}`}>
            <span>{todo.title}</span>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
import TaskCard from "../../components/TaskCard/TaskCard";
// TODO (Варіант А): імпортуйте тип Task для оголошення інтерфейсу TasksPageProps
import type { Task } from "../../types/task";

// TODO (Варіант А): оголосіть інтерфейс TasksPageProps: { tasks: Task[]; onDelete: (id: string) => void }
//                   та додайте деструктуризацію props у сигнатуру функції
interface TasksPageProps {
    tasks: Task[];
    onDelete: (id: string) => void;
}

export default function TasksPage({ tasks, onDelete } : TasksPageProps) {


  return (
    <div>
      <h2>📋 Задачі ({tasks.length})</h2>

      {/* TODO: якщо tasks.length === 0, відобразіть:
               <p>Задач поки немає. Створіть першу!</p>*/}
        {tasks.length === 0 && (
        <p style={{ marginTop: "1rem", color: "#64748b" }}>
        Задач поки немає. Створіть першу!
        </p>)}       

      <div
        style={{
          display: "flex",
          flexDirection: "column",
          gap: "0.75rem",
          marginTop: "1rem",
        }}
      >
        {/* TODO: відрендеріть tasks.map(...) — для кожної задачі <TaskCard>
                  з атрибутами key={task.id} та task={task}
                  Варіант А: onDelete={onDelete} */}

        {tasks.map((task) => (
            <TaskCard 
                key={task.id} 
                task={task} 
                onDelete={onDelete} 
            />
        ))}
      </div>
    </div>
  );
}
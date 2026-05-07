import { Injectable } from '@nestjs/common';
import { CreateTaskDto } from './dto/create-task.dto';
import { UpdateTaskDto } from './dto/update-task.dto';
import { Task } from './entities/task.entity';

@Injectable()
export class TasksService {

  private Tasks :Task[] = 
  [
    {
    id: '1',
    title: 'Task_1',
    description: 'task-1 description adsadas',
    status: 'pending',
    priority: 'medium',
    createdAt: new Date('2025-01-01T10:00:00.000Z'),
  },
  {
    id: '2',
    title: 'Task_2',
    description: 'task-2 description kkofdgk',
    status: 'in-progress',
    priority: 'high',
    createdAt: new Date('2025-02-01T10:00:00.000Z'),
  },
  {
    id: '3',
    title: 'Task_3',
    description: 'task-3 description vknbxlcn',
    status: 'done',
    priority: 'low',
    createdAt: new Date('2025-03-01T10:00:00.000Z'),
  },
  ];

  create(createTaskDto: CreateTaskDto) 
  {
    const newTask: Task =
    {
      id: Date.now().toString(),
      title: createTaskDto.title,
      description: createTaskDto.description ?? "",
      status: "pending",
      priority: createTaskDto.priority,
      createdAt: new Date()
    }
    
    this.Tasks.push(newTask);
    return newTask;
  }

  findAll() {
    return this.Tasks;
  }

  findByStatus(status: string): Task[] {
  return this.Tasks.filter(task => task.status === status);
  }

  findOne(id: string) {
    const foundTask = this.Tasks.find(task => task.id === id);
    if (!foundTask)
    {
      return null; 
    }
    return foundTask;
  }

  update(id: string, updateTaskDto: UpdateTaskDto) {
    const foundTask = this.Tasks.find(task => task.id === id);
    if (!foundTask)
    {
      return null;
    }
    return Object.assign(foundTask, updateTaskDto);
  }

  remove(id: string): boolean {
    const foundTask = this.Tasks.find(task => task.id === id);
    if (!foundTask)
    {
      return false;
    }
    this.Tasks = this.Tasks.filter(task => task.id !== id)
    return true;
  }
}

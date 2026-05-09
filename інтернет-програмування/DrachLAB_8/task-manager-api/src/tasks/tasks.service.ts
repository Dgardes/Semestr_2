import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository, In } from 'typeorm';
import { Task } from './entities/task.entity';
import { Tag } from '../tags/entities/tag.entity';
import { CreateTaskDto } from './dto/create-task.dto';
import { UpdateTaskDto } from './dto/update-task.dto';

@Injectable()
export class TasksService {
  constructor(
    @InjectRepository(Task)
    private readonly taskRepo: Repository<Task>,
    @InjectRepository(Tag)
    private readonly tagRepo: Repository<Tag>,
  ) {}

  async findAll(): Promise<Task[]> {
    return await this.taskRepo.find({ relations: ['tags'] });
  }

  async findByStatus(status: any): Promise<Task[]> {
    return await this.taskRepo.find({ 
      where: { status }, 
      relations: ['tags'] 
    });
  }

  async findOne(id: number): Promise<Task> {
    const task = await this.taskRepo.findOne({ 
      where: { id }, 
      relations: ['tags'] 
    });
    if (!task) throw new NotFoundException(`Task with ID ${id} not found`);
    return task;
  }

  async create(createTaskDto: CreateTaskDto & { tagIds?: number[] }): Promise<Task> {
    const { tagIds, ...taskData } = createTaskDto;
    
    const task = this.taskRepo.create(taskData);
    
    if (tagIds && tagIds.length > 0) {
      task.tags = await this.tagRepo.findBy({ id: In(tagIds) });
    }
    
    return await this.taskRepo.save(task);
  }

  async update(id: number, updateTaskDto: UpdateTaskDto): Promise<Task> {
    const task = await this.findOne(id);
    const updatedTask = Object.assign(task, updateTaskDto);
    return await this.taskRepo.save(updatedTask);
  }

  async remove(id: number): Promise<boolean> {
    const result = await this.taskRepo.delete(id);
    return (result.affected ?? 0) > 0;
  }
}
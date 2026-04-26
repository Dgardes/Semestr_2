import { Controller, Get, Post, Body, Patch, Param, Delete, NotFoundException, Query } from '@nestjs/common';
import { TasksService } from './tasks.service';
import { CreateTaskDto } from './dto/create-task.dto';
import { UpdateTaskDto } from './dto/update-task.dto';
import { Task } from './entities/task.entity';

@Controller('tasks')
export class TasksController {
  constructor(private readonly tasksService: TasksService) {}

  @Post()
  create(@Body() createTaskDto: CreateTaskDto) {
    return this.tasksService.create(createTaskDto);
  }

  @Get()
  findAll() {
    return this.tasksService.findAll();
  }

  @Get('search')
  findByStatus(@Query('status') status: string) {
    return this.tasksService.findByStatus(status);
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    const foundTask = this.tasksService.findOne(id); 
    if( foundTask === null)
    {
      throw new NotFoundException("Задачу не знайдено.");
    };
    return foundTask;
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() updateTaskDto: UpdateTaskDto) {
    const foundTask = this.tasksService.update(id, updateTaskDto); 
    if( foundTask === null)
    {
      throw new NotFoundException("Задачу не знайдено.");
    };
    
    return foundTask;
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    const isDeleted = this.tasksService.remove(id);
    if(!isDeleted)
    {
      throw new NotFoundException("Задачу не знайдено.");
    };
    return {message: "Задачу видалено."}
  }
}

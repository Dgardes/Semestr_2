import { 
  Controller, 
  Get, 
  Post, 
  Body, 
  Patch, 
  Param, 
  Delete, 
  NotFoundException, 
  Query,
  HttpCode 
} from '@nestjs/common';
import { TasksService } from './tasks.service';
import { CreateTaskDto } from './dto/create-task.dto';
import { UpdateTaskDto } from './dto/update-task.dto';

@Controller('tasks')
export class TasksController {
  constructor(private readonly tasksService: TasksService) {}

  @Post()
  async create(@Body() createTaskDto: CreateTaskDto & { tagIds?: number[] }) {
    return await this.tasksService.create(createTaskDto);
  }

  @Get()
  async findAll() {
    return await this.tasksService.findAll();
  }

  @Get('search')
  async findByStatus(@Query('status') status: string) {
    return await this.tasksService.findByStatus(status);
  }

  @Get(':id')
  async findOne(@Param('id') id: string) {
    const foundTask = await this.tasksService.findOne(+id);
    if (!foundTask) {
      throw new NotFoundException("Задачу не знайдено.");
    }
    return foundTask;
  }

  @Patch(':id')
  async update(@Param('id') id: string, @Body() updateTaskDto: UpdateTaskDto) {
    const updatedTask = await this.tasksService.update(+id, updateTaskDto);
    if (!updatedTask) {
      throw new NotFoundException("Задачу не знайдено.");
    }
    return updatedTask;
  }

  @Delete(':id')
  @HttpCode(204) 
  async remove(@Param('id') id: string) {
    const isDeleted = await this.tasksService.remove(+id);
    if (!isDeleted) {
      throw new NotFoundException("Задачу не знайдено.");
    }
  }
}
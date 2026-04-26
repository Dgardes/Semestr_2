import type { TaskPriority } from "../entities/task.entity";

import {
  IsString,
  IsNotEmpty,
  IsInt,
  Min,
  Max,
  MaxLength,
  IsOptional,
  IsIn,
} from "class-validator";

export class CreateTaskDto 
{
    @IsString({ message: "Назва має бути рядком" })
    @IsNotEmpty({ message: "Назва не може бути порожньою" })
    @MaxLength(200, { message: "Назва не може перевищувати 200 символів" })
    title: string;
    
    @IsString()
    @IsOptional() //якщо поле відсутнє — валідація пропускається
    @MaxLength(1000)
    description?: string;

    @IsIn
    (
        ["low", "medium", "high"],
        {message: "Пріоритет має бути low/medium/high"}
    )
    priority: TaskPriority;
    
}

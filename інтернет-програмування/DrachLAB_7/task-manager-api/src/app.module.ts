import { Module } from '@nestjs/common';
import { ConfigModule } from '@nestjs/config'; //
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { TasksModule } from './tasks/tasks.module';

@Module({
  imports: [
    //додаємо в масив imports
    ConfigModule.forRoot({
      isGlobal: true, //це робить конфігурацію доступною скрізь
    }),
    TasksModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
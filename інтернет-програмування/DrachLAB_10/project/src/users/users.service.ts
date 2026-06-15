import { Injectable, ConflictException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { User } from './user.entity';

@Injectable()
export class UsersService {
  constructor(
    @InjectRepository(User)
    private usersRepository: Repository<User>,
  ) {}

  async findByEmail(email: string): Promise<User | null> {
    return this.usersRepository.findOne({ where: { email } });
  }

  async create(userData: any): Promise<any> {
    const existing = await this.findByEmail(userData.email);
    if (existing) {
      throw new ConflictException('Користувач з таким email вже існує');
    }

    const user = this.usersRepository.create(userData);
    return this.usersRepository.save(user);
  }
}
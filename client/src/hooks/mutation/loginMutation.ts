import { User } from '../../entity/User.ts';

export type LoginUserForm = Omit<User, 'userDesc' | 'id' | 'createdAt' | 'profileUrl' | 'userName'>;

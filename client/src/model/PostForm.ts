import { Posts } from '../entity/Posts.ts';

export type PostForm = Pick<Posts, 'userId' | 'title' | 'content'>;
import { Posts } from '../entity/Posts.ts';
import { PostForm } from '../model/PostForm.ts';

export default interface PostsApi {
	writePost:(data:PostForm)=>Promise<Posts>

}
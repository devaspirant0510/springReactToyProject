import UserApi from './UserApi.ts';
import { User } from '../entity/User.ts';
import axios from 'axios';
import { RegisterUserForm } from '../hooks/mutation/registerMutation.ts';
import { LoginUserForm } from '../hooks/mutation/loginMutation.ts';
import PostsApi from './PostsApi.ts';
import { Posts } from '../entity/Posts.ts';
import { PostForm } from '../model/PostForm.ts';

axios.defaults.withCredentials = true;
axios.defaults.baseURL = 'http://127.0.0.1:8080';

class ApiClient implements UserApi,PostsApi {
	async registerUser(data: RegisterUserForm): Promise<User> {
		const { userId, userName, password } = data;
		const result = await axios.post<User>('http://127.0.0.1:8080/api/user/join', {
			userId,
			userName,
			password,
		});
		return result.data as User;
	}

	async login(data: LoginUserForm): Promise<User> {
		const {userId,password} = data;
		const result = await axios.post<User>('api/user/login', {
			userId,
			password,
		});
		return result.data as User;
	}

	async writePost(data: PostForm): Promise<Posts> {
		const result = await axios.post<Posts>('api/post',data);
		return result.data as Posts;
	}

}

export default new ApiClient();
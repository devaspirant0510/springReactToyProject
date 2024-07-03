import UserApi from './UserApi.ts';
import { User } from '../entity/User.ts';
import axios from 'axios';
import { RegisterUserForm } from '../hooks/mutation/registerMutation.ts';
import { LoginUserForm } from '../hooks/mutation/loginMutation.ts';

axios.defaults.withCredentials = true;
// axios.defaults.baseURL = 'http://127.0.0.1:8080';

class ApiClient implements UserApi {
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
		const result = await axios.post<User>('http://127.0.0.1:8080/api/user/login', {
			userId,
			password,
		});
		return result.data as User;
	}

}

export default new ApiClient();
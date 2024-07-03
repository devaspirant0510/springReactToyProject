import { User } from '../entity/User.ts';
import { RegisterUserForm } from '../hooks/mutation/registerMutation.ts';
import { LoginUserForm } from '../hooks/mutation/loginMutation.ts';

export default interface UserApi {
	registerUser:(data:RegisterUserForm)=>Promise<User>;
	login:(data:LoginUserForm)=>Promise<User>;


}
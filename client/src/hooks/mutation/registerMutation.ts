import { useMutation } from '@tanstack/react-query';
import { User } from '../../entity/User.ts';
import apiClient from '../../api/ApiClient.ts';
export type RegisterUserForm = Omit<User,'userDesc'|'id'|'createdAt'|'profileUrl'>;


const useMutationRegister = ()=>{

}
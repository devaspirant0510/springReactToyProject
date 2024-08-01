import { useMutation, useQueryClient } from '@tanstack/react-query';
import apiClient from '../../api/ApiClient.ts';
import { PostForm } from '../../model/PostForm.ts';
import { Posts } from '../../entity/Posts.ts';
import { toast } from 'react-toastify';

export const useMutateWritePost = () => {
	const queryClient = useQueryClient();
	const { isPending,isSuccess,data,mutateAsync} = useMutation({
		mutationFn: async (postForm:PostForm) => {
			return await apiClient.writePost(postForm)
		},
		onSuccess:(data:Posts)=>{
			queryClient.setQueryData<Posts[]>(['api/posts'],(oldData)=>{
				if(!oldData){
					return [ data];
				}
				return [data,...oldData];
			})

		},
		onError:()=>{
			toast.error("게시글 작성 실패")
		}
	});
	return {
		pendingWritePost: isPending,
		isSuccessWritePost: isSuccess,
		writePostData:data,
		mutateWritePost: mutateAsync,
	}
};
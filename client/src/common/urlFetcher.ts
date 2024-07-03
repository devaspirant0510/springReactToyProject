import { QueryFunctionContext } from '@tanstack/react-query';
import axios from 'axios';

export const urlFetcher = async <T>(queryFnCtx:QueryFunctionContext)=>{
	const requestUrl = queryFnCtx.queryKey.join('/');
	const result = await axios.get<T>(`/${requestUrl}`)
	return result.data as T;
}
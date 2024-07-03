import { http, HttpResponse } from 'msw';
import { Posts } from '../entity/Posts.ts';
import { User } from '../entity/User.ts';

let userDummyData: User[] = [
	{
		id: 1,
		createdAt: new Date(),
		userId: 'lsh0510',
		password: '1234',
		userDesc: 'hello world',
		userName: 'seungho',
		profileUrl: 'https://d2u3dcdbebyaiu.cloudfront.net/uploads/atch_img/773/8548051fcfce80a09534231685454ad4_res.jpeg',
	},

];
let postDummyData: Posts[] = [
	{
		id: 1,
		content: 'tet',
		title: 't',
		createdAt: new Date(),
		updatedAt: new Date(),
		userId: 1,
	},
	{
		id: 2,
		content: 'tet',
		title: 't',
		createdAt: new Date(),
		updatedAt: new Date(),
		userId: 1,
	},
	{
		id: 3,
		content: 'tet',
		title: 't',
		createdAt: new Date(),
		updatedAt: new Date(),
		userId: 1,
	},
	{
		id: 4,
		content: 'tet',
		title: 't',
		createdAt: new Date(),
		updatedAt: new Date(),
		userId: 1,
	},
];
export const handlers = [
	// An example handler
	http.get('/posts', () => {
		return HttpResponse.json(postDummyData);
	}),
	http.post('/posts', async ({ request }) => {
		const postForm = await request.json() as Posts;
		postDummyData = [...postDummyData, postForm];
		return HttpResponse.json(postForm);
	}),
	http.get('/posts/:id', async ({params}) => {
		return HttpResponse.json(postDummyData.filter(value => value.id !== params.id));
	}),
	http.post('/user/join',async ({request})=>{
		const userForm = await request.json() as User;
		userDummyData = [...userDummyData,userForm];
		return HttpResponse.json(userDummyData);
	}),
	http.post('/user/login',async({request})=>{
		const userForm = await request.json() as User;
		const loginedUser = userDummyData.filter(user=>user.userId===userForm.userId && user.password===userForm.password);
		return HttpResponse.json(loginedUser[0]);

	})
];
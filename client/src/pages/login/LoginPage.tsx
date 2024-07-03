import MainLayout from '../../components/templates/MainLayout.tsx';
import { Button, Flex, FormControl, FormLabel, Heading, Input } from '@chakra-ui/react';
import useInput from '../../hooks/useInput.ts';
import { Link, useNavigate } from 'react-router-dom';
import { useCallback, useLayoutEffect } from 'react';
import { useMutation } from '@tanstack/react-query';
import { RegisterUserForm } from '../../hooks/mutation/registerMutation.ts';
import apiClient from '../../api/ApiClient.ts';
import { toast } from 'react-toastify';
import { LoginUserForm } from '../../hooks/mutation/loginMutation.ts';
import { User } from '../../entity/User.ts';

const LoginPage = () => {
	const navigate = useNavigate();
	const [inputId, onChangeId, setId] = useInput('');
	const [inputPwd, onChangePwd, setPwd] = useInput('');
	const { mutate, isPending } = useMutation({
		mutationFn: async (data: LoginUserForm) => {
			return await apiClient.login(data);
		},
		onSuccess: (user:User) => {
			toast.success('로그인 성공');
			navigate('/');
			localStorage.setItem("user", JSON.stringify(user));
		},
		onError: () => {
			toast.error('로그인 실패');

		},
	});
	useLayoutEffect(() => {
		if (localStorage.getItem('user')) {
			navigate('/');
		}
	}, []);
	const onClickLogin = useCallback(() => {
		mutate({
			userId: inputId,
			password: inputPwd,
		});
	}, [inputId, inputPwd]);
	return (
		<MainLayout>
			<Flex direction="column">
				<Heading>로그인</Heading>
				<FormControl>
					<FormLabel></FormLabel>
					<Input value={inputId} onChange={onChangeId} />
					<FormLabel></FormLabel>
					<Input value={inputPwd} onChange={onChangePwd} />
					<Button onClick={onClickLogin} isLoading={isPending} >
						로그인
					</Button>
				</FormControl>
				<Link to={'/register'}>회원가입하러가기</Link>
			</Flex>
		</MainLayout>
	);
};

export default LoginPage;
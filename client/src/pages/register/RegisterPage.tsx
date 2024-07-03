import { useMutation } from '@tanstack/react-query';
import { RegisterUserForm } from '../../hooks/mutation/registerMutation.ts';
import apiClient from '../../api/ApiClient.ts';
import useInput from '../../hooks/useInput.ts';
import { Button, Flex, FormControl, FormLabel, Heading, Input } from '@chakra-ui/react';
import MainLayout from '../../components/templates/MainLayout.tsx';
import { useCallback } from 'react';
import { Link, redirect, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { User } from '../../entity/User.ts';

const RegisterPage = () => {
	const [inputId, onChangeId, setInputId] = useInput('');
	const [inputName, onChangeName, setInputName] = useInput('');
	const [inputPwd, onChangePwd, setInputPwd] = useInput('');
	const navigate = useNavigate();


	const { mutate, isPending} = useMutation({
		mutationFn: async (data: RegisterUserForm) => {
			console.log('mutate user/join');
			return await apiClient.registerUser(data);
		},
		onSuccess: (data:User) => {
			setInputId('');
			setInputName('');
			setInputPwd('');
			navigate("/login");
			toast.success(`${data.userName}님 가입에 성공하셨습니다.`);
		},
		onError: () => {
			setInputId('');
			setInputName('');
			setInputPwd('');
			toast.error(`이미 가입한 아이디입니다.`,);
		},
	});
	const onClickRegister = useCallback(() => {
		mutate({
			userId: inputId,
			password: inputPwd,
			userName: inputName,
		});

	}, [inputId, inputName, inputPwd]);
	return (
		<MainLayout>
			<Flex direction="column" justifyContent="center" alignItems="center">
				<Heading>회원가입</Heading>
				<FormControl >
					<FormLabel>아이디</FormLabel>
					<Input type="text" value={inputId} onChange={onChangeId} />
					<FormLabel>비밀번호</FormLabel>
					<Input type="password" value={inputPwd} onChange={onChangePwd} />
					<FormLabel>닉네임</FormLabel>
					<Input type="text" value={inputName} onChange={onChangeName} />
					<Button isLoading={isPending} type={'submit'} onClick={onClickRegister} >
						가입하기
					</Button>
				</FormControl>
				<Link to={'/login'}>로그인하러가기</Link>
			</Flex>
		</MainLayout>
	);
};
export default RegisterPage;
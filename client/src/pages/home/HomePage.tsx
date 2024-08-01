import { useCallback, useLayoutEffect, useState } from 'react';
import {
	Avatar,
	Box,
	Button,
	ButtonGroup,
	Card,
	CardHeader,
	Text,
	Flex,
	Heading,
	CardBody,
	CardFooter, useDisclosure, Modal, ModalOverlay, ModalContent,
} from '@chakra-ui/react';
import GuestInfoPage from './components/GuestInfoPage.tsx';
import { User } from '../../entity/User.ts';
import ProfileCard from './components/ProfileCard.tsx';
import PostListPage from './components/PostListPage.tsx';
import { useNavigate } from 'react-router-dom';
import PostFormModal from './components/PostFormModal.tsx';


const HomePage = () => {
	const [isLogin, setLogin] = useState(false);
	const [loginUser, setUser] = useState<User | null>(null);
	const navigate = useNavigate();
	const { isOpen, onOpen, onClose } = useDisclosure();

	useLayoutEffect(() => {
		if (localStorage.getItem('user')) {
			setUser(JSON.parse(localStorage.getItem('user')));
			setLogin(true);
		}
	}, []);
	const onClickLogout = useCallback(() => {
		localStorage.removeItem('user');
		navigate('/login');
	}, []);
	// 로그인이 안되있는 상태면 로그인/회원가입 페이지로 안내
	if (!isLogin) {
		return <GuestInfoPage />;
	}
	// 로그인이 되있는 상태면 홈화면 표시
	return (
		<Flex gap={2}>
			<Box flex={1}>
				<Card>
					<CardHeader>
						<ProfileCard loginUser={loginUser} />
					</CardHeader>
					<CardBody>
						<Text>
							{loginUser?.userDesc ? loginUser.userDesc : '설명이 없습니다.추후에 추가될기능입니다.'}
						</Text>
					</CardBody>
					<CardFooter>
						<Button onClick={onClickLogout} colorScheme={'red'}>로그아웃</Button>
					</CardFooter>
				</Card>
			</Box>
			<Box flex={2}>
				<Button onClick={onOpen}>작성하기</Button>
				<PostFormModal isOpen={isOpen} onClose={onClose}/>
				<PostListPage />
			</Box>
		</Flex>
	);
};

export default HomePage;
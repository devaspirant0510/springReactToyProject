import MainLayout from '../../../components/templates/MainLayout.tsx';
import { Box, Button, ButtonGroup, Center, Flex, Heading } from '@chakra-ui/react';
import { Link } from 'react-router-dom';

const GuestInfoPage = () => {
	return <MainLayout>
		<Flex direction="column" alignItems="center">
			<Heading  size={'lg'}>저희 서비스를 이용하시려면 회원가입 또는 로그인을 해야합니다.</Heading>
			<Box h={14} />
			<Center>
				<ButtonGroup gap={8}>
					<Link to={'/login'}>
						<Button colorScheme={'gray'}> 로그인</Button>
					</Link>
					<Link to={'/register'}>
						<Button colorScheme={'gray'}>회원가입</Button>
					</Link>
				</ButtonGroup>
			</Center>
		</Flex>;

	</MainLayout>
		;
};

export default GuestInfoPage;
import { Avatar, Box, Flex, Heading, Text } from '@chakra-ui/react';
import { User } from '../../../entity/User.ts';
import { FC } from 'react';

type Props = {
	loginUser:User|null
}
const ProfileCard :FC<Props>= ({loginUser}) => {
	return (
		<Flex gap={'4'}>
			<Avatar src={loginUser?.profileUrl}/>
			<Box>
				<Heading size={'sm'}>{loginUser?.userName}</Heading>
				<Text >{loginUser?.userId}</Text>
			</Box>
		</Flex>
	)
}
export default ProfileCard;
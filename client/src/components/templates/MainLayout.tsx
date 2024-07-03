import React, { FC } from 'react';
import { Box, Center, Flex } from '@chakra-ui/react';

type Props = {
	children: React.ReactNode;
}

const MainLayout: FC<Props> = ({ children }) => {
	return (
		<Flex>
			<Box flex={2} />
			<Box flex={20}>
				<Center height={'100vh'}>
					{children}
				</Center>
			</Box>
			<Box flex={2} />
		</Flex>
	);
};

export default MainLayout;
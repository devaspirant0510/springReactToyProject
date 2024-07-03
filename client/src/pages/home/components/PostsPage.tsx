import { useQuery } from '@tanstack/react-query';
import { urlFetcher } from '../../../common/urlFetcher.ts';
import { Posts } from '../../../entity/Posts.ts';
import { Card, CardBody, CardHeader, Flex, Heading,Text } from '@chakra-ui/react';
import { convertDate } from '../../../libs/dateLibs.ts';

const PostsPage = () => {
	const { isLoading, data } = useQuery({
		queryKey: ['posts'],
		queryFn: urlFetcher<Posts[]>,
	});
	console.log(data);
	if (isLoading) {
		return <>
			로딩중이다 개이들아
		</>;
	}
	return (
		<>

			{data?.map(((item, key) => {
				return <Card key={key} marginBottom={4}>
					<CardHeader>
						<Flex justifyContent={'space-between'} alignItems={'center'} gap={2}>
							<Heading size={'sm'}>
								{item.title}
							</Heading>
							<Text>
								{convertDate(item.createdAt)}
							</Text>
						</Flex>
					</CardHeader>
					<CardBody>
						{item.content}
					</CardBody>
				</Card>;
			}))}
		</>
	);
};
export default PostsPage;
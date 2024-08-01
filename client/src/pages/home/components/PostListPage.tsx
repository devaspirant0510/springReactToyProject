import { useQuery } from '@tanstack/react-query';
import { urlFetcher } from '../../../common/urlFetcher.ts';
import { Posts } from '../../../entity/Posts.ts';
import { Card, CardBody, CardHeader, Flex, Heading, Text } from '@chakra-ui/react';
import { convertDate } from '../../../libs/dateLibs.ts';
import { useNavigate } from 'react-router-dom';

const PostListPage = () => {
	const navigate = useNavigate();
	const { isLoading, data } = useQuery({
		queryKey: ['api/posts'],
		queryFn: urlFetcher<Posts[]>,
	});
	console.log(data);
	if (isLoading) {
		return <>
			로딩중
		</>;
	}
	return (
		<>
			{data?.map(((item, key) => {
				console.log(item);
				return <Card key={key} marginBottom={4} onClick={()=>{
					navigate("/post/"+item.id);

				}}>
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
export default PostListPage;
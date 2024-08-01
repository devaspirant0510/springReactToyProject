import { useNavigate, useParams } from 'react-router-dom';
import { useQuery } from '@tanstack/react-query';
import { urlFetcher } from '../../common/urlFetcher.ts';
import { Posts } from '../../entity/Posts.ts';
import { Button, Card, CardBody, CardHeader, Flex } from '@chakra-ui/react';
import { convertDate } from '../../libs/dateLibs.ts';

const PostPage = () => {
	const { id } = useParams();
	const navigate = useNavigate();
	const { isLoading, error, data } = useQuery({
		queryKey: [`api/post/${id!}`],
		queryFn: urlFetcher<Posts>,
	});
	if (!id) {
		return <>
			error
		</>;
	}
	if(isLoading){
		return <>로딩중</>
	}
	if(error){
		return  <>{error.toString()}</>
	}
	if(data===undefined){
		return <>nodata</>
	}
	return (
		<Card>
			<CardHeader>
				<Flex justifyContent={'space-between'} alignItems={'center'}>
					<Button onClick={()=>{
						navigate(-1);
					}}>뒤로가기</Button>
					<h1>{data.title}</h1>
					<div>{convertDate(data.createdAt)}</div>
				</Flex>
			</CardHeader>
			<CardBody>
				<div>
					{data.content}
				</div>
			</CardBody>

		</Card>
	);
};
export default PostPage;
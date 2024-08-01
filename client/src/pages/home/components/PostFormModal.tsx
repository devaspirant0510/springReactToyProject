import React, { FC, useCallback } from 'react';
import {
	Button,
	FormControl,
	Input,
	Modal,
	ModalBody,
	ModalContent,
	ModalFooter,
	ModalHeader,
	ModalOverlay,
} from '@chakra-ui/react';
import useInput from '../../../hooks/useInput.ts';
import { useMutateWritePost } from '../../../hooks/mutation/postMutation.ts';
import { User } from '../../../entity/User.ts';

type Props = {
	isOpen: boolean;
	onClose: () => void;
}
const PostFormModal:FC<Props> = ({isOpen,onClose}) => {
	const [inputTitle,onChangeTitle] = useInput('')
	const [inputContent,onChangeContent] = useInput('')
	const {
		mutateWritePost,
		isSuccessWritePost,
		pendingWritePost,
	} = useMutateWritePost();
	const onSubmitPost = useCallback(async (e:React.FormEvent)=>{
		e.preventDefault();
		const user = JSON.parse(localStorage.getItem("user")) as User | null;
		console.log(user);
		if (user==null){
			return;
		}

		if(inputTitle!=="" && inputContent!==""){
			await mutateWritePost({
				title:inputTitle,
				content:inputContent,
				userId:user.id

			})
			onClose();
		}

	},[inputTitle,inputContent]);
	return <Modal isOpen={isOpen} onClose={onClose}>
		<ModalOverlay/>
		<ModalContent>
			<ModalHeader>글 작성하기</ModalHeader>
			<ModalBody>
				<FormControl>
					<Input value={inputTitle} onChange={onChangeTitle}/>
					<Input value={inputContent} onChange={onChangeContent}/>
				</FormControl>
			</ModalBody>
			<ModalFooter>
				<Button onClick={onClose}>취소</Button>
				<Button onClick={onSubmitPost} type={"submit"} isLoading={pendingWritePost}>작성</Button>
			</ModalFooter>
		</ModalContent>
	</Modal>;
};
export default PostFormModal;
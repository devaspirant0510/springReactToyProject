import { useCallback, useEffect } from 'react';
import axios from 'axios';
const HomePage = () => {
	const onClick = useCallback(() => {
		axios.get("http://127.0.0.1:8080/post").then(r=>{
			console.log(r.data);
		})

	}, []);
	return <>
		<h1>hello</h1>
		<button onClick={onClick}>sd</button>
	</>;
};

export default HomePage;
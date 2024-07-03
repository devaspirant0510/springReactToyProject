import React from 'react';
import ReactDOM from 'react-dom/client';
import HomePage from './pages/home/HomePage.tsx';
import { ChakraProvider } from '@chakra-ui/react';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';

async function enableMocking() {
	if (process.env.NODE_ENV !== 'development') {
		return;
	}

	const { worker } = await import('./mocks/browser');

	// `worker.start()` returns a Promise that resolves
	// once the Service Worker is up and ready to intercept requests.
	return worker.start();
}

const router = createBrowserRouter([
	{
		path:'/',
		element:<HomePage/>

	}
])
const queryClient = new QueryClient();

enableMocking().then(()=>{
	ReactDOM.createRoot(document.getElementById('root')!).render(
		<ChakraProvider>
			<QueryClientProvider client={queryClient}>
				<RouterProvider router={router}/>
			</QueryClientProvider>
		</ChakraProvider>
	);
})

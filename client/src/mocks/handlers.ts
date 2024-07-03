import { http, HttpResponse } from 'msw';

export const handlers = [
	// An example handler
	http.get('http://127.0.0.1:8080/post', () => {
		return HttpResponse.json({ name: 'John Maverick' });
	}),
];
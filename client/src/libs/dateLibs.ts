import dayjs from "dayjs";
export const convertDate =(date:Date):string=>{
	return dayjs(date).format('YYYY-MM-DD HH:mm');
}
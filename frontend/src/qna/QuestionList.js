import React, {useEffect, useState} from 'react';
import oomeApi from "../common/CommonApi";

const QuestionList = () => {
    const [data, setData] = useState([]);

    useEffect(() => {
        oomeApi.fetchData(oomeApi.QNA.getUrl("/list"), {
            method: 'GET'
        })
            .then((data) => {
                setData(data);
            })
    }, [])
    return (
        <div>
            <table>
                <thead>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                </thead>
                <tbody>
                {data.map((item) => (
                    <tr key={item.title}>
                        <td>{item.title}</td>
                        <td>{item.createrNickname}</td>
                        <td>{item.createdDate}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default QuestionList;
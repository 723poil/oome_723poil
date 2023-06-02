import React, {useEffect, useState} from 'react';
import axios from "axios";

const Main = () => {
    const [hello, setHello] = useState('')

    useEffect(() => {
        axios.get('/api/v1/common/hello')
            .then(response => setHello(response.data))
            .catch(error => console.log(error))
    }, []);

    const handleGetUser = (e) => {
        axios.get('/api/v1/common/auth/authcheck')
            .then((response) => {
                console.log(response);
            });
    }

    return (
        <div>
            <div>
                {hello}
                <button type={"button"} onClick={handleGetUser}>USER GET</button>
            </div>
        </div>
    );
};

export default Main;
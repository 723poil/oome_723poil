import React, {useEffect, useState} from 'react';
import axios from "axios";

const Main = () => {
    const [hello, setHello] = useState('')

    useEffect(() => {
        axios.get('/api/v1/common/hello')
            .then(response => setHello(response.data))
            .catch(error => console.log(error))
    }, []);

    return (
        <div>
            <div>
                {hello}
            </div>
        </div>
    );
};

export default Main;
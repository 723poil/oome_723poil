import React, {useEffect, useState} from 'react';
import oomeApi from "./common/CommonApi";

const Main = () => {
    const [hello, setHello] = useState('')

    useEffect(() => {
        oomeApi.fetchData(oomeApi.COMMON.getUrl('/hello'))
            .then(data => setHello(data))
            .catch(error => console.log(error))
    }, []);

    const handleGetUser = (e) => {
        oomeApi.fetchData(oomeApi.COMMON.getUrl('/auth/authcheck'))
            .then((data) => {
                console.log(data);
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
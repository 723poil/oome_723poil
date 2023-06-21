import React from 'react';
import oomeApi from "./common/CommonApi";

const Main = () => {

    const handleGetUser = (e) => {
        oomeApi.fetchData(oomeApi.COMMON.getUrl('/auth/authcheck'))
            .then((data) => {
                console.log(data);
            });
    }

    return (
        <div>
            <div>
                <button type={"button"} onClick={handleGetUser}>USER GET</button>
            </div>
        </div>
    );
};

export default Main;
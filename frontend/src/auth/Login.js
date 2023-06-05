import React, {useState} from 'react';
import axios from "axios";
import {useNavigate} from 'react-router-dom'
import oomeApi from '../common/CommonApi'

const Login = () => {
    const navigator = useNavigate();
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    const handleLogin = (e) => {

        console.log('Username:', username);
        console.log('Password:', password);

        const data = {
            username: username,
            password: password
        }

        oomeApi.fetchData(oomeApi.COMMON.getUrl('/auth/authorize'), {
            method: 'POST',
            data: data
        }).then((data) => {
            const token = data.accessToken;
            if (token !== null) {
                localStorage.setItem('isLoggedIn', 'true');
                axios.defaults.headers.common['Authorization'] = `Bearer ${token}`; // 토큰을 헤더에 추가
                navigator('/');
            } else {
                localStorage.setItem('isLoggedIn', 'false');
            }


        }).catch(e => console.error(e));


    };

    return (
        <div>
            <h2>Login</h2>
            <label>
                Username:
                <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
            </label>
            <br />
            <label>
                Password:
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
            </label>
            <br />
            <button type="button" onClick={handleLogin}>Login</button>
        </div>
    );
};

export default Login;

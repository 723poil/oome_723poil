import React, {useState} from 'react';
import axios from "axios";

const Login = () => {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    const handleLogin = (e) => {
        // Add your login logic here
        console.log('Username:', username);
        console.log('Password:', password);
        // Reset form fields
        const data = {
            username: username,
            password: password
        }

        axios.post('/api/v1/common/auth/authorize', data, {
            headers: {
                'Content-Type': "application/json"
            }
        })
            .then((response) => {
                console.log(response.data);
            })
            .catch(e => console.error(e))
    };

    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleLogin}>
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
                <button type="submit">Login</button>
            </form>
        </div>
    );
};

export default Login;
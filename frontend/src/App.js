import React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from './auth/Login'
import Main from './Main'
import NotFound from './NotFound'

function App() {
  return (
      <div className='App'>
          <BrowserRouter>
              <Routes>
                  <Route path="/" element={<Main />}></Route>
                  <Route path="/login" element={<Login />}></Route>
                  <Route path="*" element={<NotFound />}></Route>
              </Routes>
          </BrowserRouter>
      </div>
  );
}

export default App;
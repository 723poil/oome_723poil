import React from 'react';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import Login from './Login'
import Main from './Main'

function App() {
  return (
      <div className='App'>
          <BrowserRouter>
              <Routes>
                  <Route path="/" element={<Main />}></Route>
                  <Route path="/login" element={<Login />}></Route>
              </Routes>
          </BrowserRouter>
      </div>
  );
}

export default App;
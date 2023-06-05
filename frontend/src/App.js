import React from 'react';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from './auth/Login'
import Main from './Main'
import NotFound from './NotFound'
import Regist from './member/Regist'
import QuestionList from "./qna/QuestionList";

function App() {
  return (
      <div className='App'>
          <BrowserRouter>
              <Routes>
                  {/* 기본 Index */}
                  <Route path="/" element={<Main />}></Route>
                  <Route path="/login" element={<Login />}></Route>

                  {/* 404 NotFound */}
                  <Route path="*" element={<NotFound />}></Route>

                  {/* qna 관련 라우팅 */}
                  <Route path="/qna/list" element={<QuestionList />}></Route>

                  {/* member 관련 라우팅 */}
                  <Route path="/member/regist" element={<Regist/>}></Route>
              </Routes>
          </BrowserRouter>
      </div>
  );
}

export default App;
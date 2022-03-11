import React, {Suspense} from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import reportWebVitals from './reportWebVitals';
import './i18n';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import ToDoList from "./ToDoList/ToDoList";
import ToDoDetails from "./ToDoDetails";

ReactDOM.render(
    <React.StrictMode>
        <Suspense fallback="Still loading...">
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<App/>}/>
            <Route path='ToDoList' element={<ToDoList/>}/>
            <Route path='ToDoList/:id' element={<ToDoDetails/>}/>
            <Route path='*' element={<ToDoList/>}/>
            </Routes>
            </BrowserRouter>
        </Suspense>
    </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

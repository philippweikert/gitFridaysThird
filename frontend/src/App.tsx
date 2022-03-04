import React from 'react';
import ToDoList from "./ToDoList/ToDoList";
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import ToDoDetails from "./ToDoDetails";
import ToDoElement from "./ToDoElement/ToDoElement";


function App() {


    return (
        <div>
            <h1>Was haben wir schon wieder alles vergessen?</h1>
            <Link to="/ToDoDetails">...</Link>
        </div>
    );
}

export default App;

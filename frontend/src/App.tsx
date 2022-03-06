import React from 'react';
import {Link} from "react-router-dom";
import {useTranslation} from "react-i18next";


function App() {

    const {t} = useTranslation();


    return (
        <div>
            <h1>{t('headline')}</h1>
            <Link to="/ToDoDetails">{t('detail-link')}</Link>
        </div>
    );
}

export default App;

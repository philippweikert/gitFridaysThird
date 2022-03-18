import React from 'react';
import {Link,Outlet} from "react-router-dom";
import {useTranslation} from "react-i18next";


function App() {

    const {t} = useTranslation();


    return (
        <div>
            <h1>{t('headline')}</h1>
            <Link to="/LoginElement">{t('detail-link')}</Link>
            <div><Outlet/></div>
        </div>
    );
}

export default App;

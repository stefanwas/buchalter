import React from 'react';

import Header from './Header.jsx';
import Selector from './Selector.jsx';
import Report from './Report.jsx';

export default class Book extends React.Component {

    render() {
        return (
            <div>
                <Header/>
                <div className="row">
                    <Selector />
                    <Report />
                </div>
            </div>
        );
    }
}
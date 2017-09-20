import React from 'react';
import ReactDOM from 'react-dom';
import jQuery from 'jquery';

import * as AppAction from './js/AppAction.js';

import Book from './components/Book.jsx';

ReactDOM.render(<Book />, document.getElementById('book'));


console.log('Loading all reports...');
AppAction.loadAllReports();
AppAction.loadYReport('2016');




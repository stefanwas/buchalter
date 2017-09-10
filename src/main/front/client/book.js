import React from 'react';
import ReactDOM from 'react-dom';
import $ from 'jquery';

import Reports from './components/Reports.jsx';
import * as AppAction from './js/AppAction.js';



ReactDOM.render(<Reports />, document.getElementById('reports'));


console.log('Loading all reports...');
AppAction.loadAllReports();




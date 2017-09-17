import React from 'react';
import ReactDOM from 'react-dom';
import jQuery from 'jquery';

import * as AppAction from './js/AppAction.js';


import Header from './components/Header.jsx';
import ReportSelector from './components/ReportSelector.jsx';
import IncomeTable from './components/record/IncomeTable.jsx';


ReactDOM.render(<Header />, document.getElementById('reports'));
ReactDOM.render(<ReportSelector />, document.getElementById('nav-selector'));
ReactDOM.render(<IncomeTable />, document.getElementById('table'));


console.log('Loading all reports...');
AppAction.loadAllReports();
AppAction.loadYReport('2016');




import $ from 'jquery';

import AppActionTypes from './AppActionTypes';
import AppDispatcher from './AppDispatcher';


//const Actions = {
//  addToDo(text) {
//    AppDispatcher.dispatch({
//      type: 'CREATE_TODO',
//      text,
//    });
//  },
//};
//
//export default Actions;




export function loadAllReports() {
    $.ajax({url: "http://localhost:8000/buchalter/report/all"}).then(function(data) {
        AppDispatcher.dispatch({
            type: "LOAD_ALL_REPORTS",
            content: data,
        });
    });
}

export function loadYReport(year) {
        $.ajax({url: "http://localhost:8000/buchalter/report/year/"+year}).then(function(data) {
            AppDispatcher.dispatch({
                type: "LOAD_YEAR_REPORT",
                content: data,
            });
        });
}
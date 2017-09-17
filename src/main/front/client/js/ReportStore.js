import {EventEmitter} from 'events';


import AppDispatcher from './AppDispatcher.js'
//import AppActionTypes from './AppActionTypes';
//import AppDispatcher from './AppDispatcher';

class ReportStore extends EventEmitter {
  constructor() {
    super();
    this.reports = [];
    this.yReportCodes = [];

//    $.ajax({
//        url: "http://localhost:8000/buchalter/report/all"
//    }).then(function(data) {
//        console.log("DATA=" + data);
//       setReports(data);
////       this.emit("change");
//    }).fail(function() {
//      this.reports = [];
//  });
//    this.reports = [
//      { id : 'R2015', name: 'buy milk'},
//      { id : 'R2016', name: 'learn react'},
//      { id : 'R2017', name: 'call mike'},
//    ];
  }
    setReports(reports) {
        console.log ("NEW REPORTS="+reports);
        this.reports = reports;
        this.emit("change");
    }

    setYearReport(yReport) {
        console.log ("SET Y REPORT="+yReport);
        this.yReport = yReport;
        this.emit("change");
    }

    getAll() {
        return this.reports;
    }

    getAllYReports() {
        return this.yReportCodes;
    }

    getYReport() {
        return this.yReport;
    }

    createReport(id) {
        this.reports.push( {
            id: id,
            name: 'New Report',
        });
        this.emit("change");
    }

    handleAction(action) {
        console.log("Action =", action)
        switch(action.type) {
            case "CREATE_TODO": {
                this.createReport(action.text);
                break;
            }
            case "LOAD_ALL_REPORTS": {
                console.log("LOAD_ALL_REPORTS ACTION RECEIVED. CONTENT=" + action.content);
                this.setReports(action.content);
                break;
            }
            case "LOAD_YEAR_REPORT": {
                this.setYearReport(action.content);
                break;
            }

        }
    }

}

var reportStore = new ReportStore();

AppDispatcher.register(reportStore.handleAction.bind(reportStore)); // what is this???

export default reportStore;

window.ReportStore = reportStore;
window.AppDispatcher = AppDispatcher;
import {EventEmitter} from 'events';

import AppDispatcher from './AppDispatcher.js'
//import AppActionTypes from './AppActionTypes';

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

  }
    setReports(reports) {
        console.log ("NEW REPORTS="+reports);
        this.reports = reports;
        this.emit("REPORT_LIST_LOADED");
    }

    setYearReport(yReport) {
        console.log ("SET Y REPORT="+yReport);
        this.yReport = yReport;
        this.emit("YREPORT_LOADED");
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

    getMReport() {
        return this.yReport.qreports[0].mreports[0];
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
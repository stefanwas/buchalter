import React from 'react';

import ReportStore from '../js/ReportStore.js';

export default class ReportSelector extends React.Component {

    constructor() {
        super();
        this.state = {
            yReport: ReportStore.getYReport()
        };
    }

    componentWillMount() {
        ReportStore.on("YREPORT_LOADED", () => {
            this.setState({
                yReport: ReportStore.getYReport()
            });
        });
    }

    createMItem(mReport) {
        return (
            <li key={mReport.code} className={"nav-item"}>
                <a className="nav-link" href="#">{mReport.code.substring(7)}</a>
            </li>
        )
    }

    createQItem(qReport) {
        var months = [];
        if (qReport) {
            qReport.mreports.forEach(mReport => months.push(this.createMItem(mReport)));
        }

        return (
            <li key={qReport.code} className="nav-item">
                <a href={"#" + qReport.code + "-sub"} id={qReport.code} className="nav-link collapsed"
                    data-toggle="collapse" data-target={"#" + qReport.code + "-sub"}>{qReport.code}</a>
                <div id={qReport.code + "-sub"} className="collapse small" aria-expanded="true">
                    <ul className="navbar-nav nav-stacked collapse pl-3" id={qReport.code + "-list"}>{months}</ul>
                </div>
            </li>
//            <li key={yReportCode} className={"nav-item " + active}>
//                <a className="nav-link" href="#" onClick={() => this.openReport(yReportCode)}>{yReportCode}</a>
//            </li>
        )
    }

    render() {
        console.log("RENDER SELECTOR...");

        var quarters = [];
        if (this.state.yReport) {
            this.state.yReport.qreports.forEach(qReport => quarters.push(this.createQItem(qReport)));
        }

        return (
            <div id="selector" className="border rounded w-10 mr-1">
                <nav className="navbar navbar-light bg-inverse">
                    <ul className="navbar-nav nav-stacked ">{quarters}</ul>
                </nav>
            </div>
        );
    }

}


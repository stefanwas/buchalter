import React from 'react';

import ReportStore from '../js/ReportStore.js';
import * as AppAction from '../js/AppAction.js';


export default class Header extends React.Component {
    constructor() {
        super();

        this.state = {
            reports: [],
            activeReport: null
        };
    }

    componentWillMount() {
        ReportStore.on("change", () => {
            var allYReportCodes = ReportStore.getAll();

            this.setState({
                reports: allYReportCodes,
                activeReport: this.state.activeReport
            });
        });
    }

    createYItem(yReportCode) {
        var active = (yReportCode == this.state.activeReport) ? "active" : "";
        return (
            <li key={yReportCode} className={"nav-item " + active}>
                <a className="nav-link" href="#" onClick={() => this.openReport(yReportCode)}>{yReportCode}</a>
            </li>
        )
    }

    openReport(yReportCode) {

        //TODO
        console.log("CLick="+yReportCode);

        this.setState({
            reports: this.state.reports,
            activeReport: yReportCode
        });
        this.state.activeReport = yReportCode;
        AppAction.loadYReport(yReportCode.substring(1));
    }

    render() {
        console.log("RENDER HEADER...");

        var years = [];
        this.state.reports.forEach(report => years.push(this.createYItem(report)));

        return (
            <nav id="header" className="navbar navbar-expand navbar-dark bg-dark row">
                <a className="navbar-brand" href="#">Buchalter</a>
                <ul className="navbar-nav mr-auto">{years}</ul>
            </nav>
        );
    }
}


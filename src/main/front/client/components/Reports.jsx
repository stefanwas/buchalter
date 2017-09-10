import React from 'react';
import _ from 'lodash';

import ReportStore from '../js/ReportStore.js';


export default class Reports extends React.Component {

  constructor() {
    super();
    this.state = {
      reports: ReportStore.getAll()
    };
  }

  componentWillMount() {
    ReportStore.on("change", () => {
        this.setState({
            reports: ReportStore.getAll(),
        })
    });
  }


//  renderItem(item) {
//    return (<ToDoItem text={item.name} key={item.id} />)
//  }

//  createTodo() {
//    AppAction.addToDo(Date.now());
//  }

//  render() {
////  const {items} = this.state;
//    var rows = this.state.items.map(item => this.renderItem(item));
//    return (
//        <div>
//            <h3>Todos:</h3>
//            <button onClick={this.createTodo.bind(this)}>Create TODO</button>
//            <ul> {rows} </ul>
//        </div>
//    );
//  }

    openReport(report) {
        console.log("CLick"+report);
    }

    render() {
        var rows = [];

        rows.push(<li key="R0" className="active"><a href="none.asp">Raporty Roczne:</a></li>);
        this.state.reports.forEach(report => rows.push(<li key={report}><a href="#" onClick={() => this.openReport(report)}>{report}</a></li>))

        console.log("RENDERED")
        return (<ul>{rows}</ul>);
    }
}

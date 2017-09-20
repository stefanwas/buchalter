import React from 'react';

import TableHeader from './TableHeader.jsx';
import TableFooter from './TableFooter.jsx';
import TableBody from './TableBody.jsx';

export default class Table extends React.Component {

    render() {

        var labels = this.props.labels;
        var records = this.props.records;

        return (
            <table className="table table-sm table-bordered table-hover small">
                <TableHeader labels={labels} />
                <TableBody records={records} />
                <TableFooter />

            </table>
        );
    }
}


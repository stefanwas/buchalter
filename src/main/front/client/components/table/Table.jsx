import React from 'react';
import _ from 'lodash';

import TableHeader from './TableHeader.jsx';
import TableFooter from './TableFooter.jsx';
import TableBody from './TableBody.jsx';

export default class Table extends React.Component {

    calculateSummary(records) {
        var summary = {};

        summary.netValue = _.sumBy(records, r => parseFloat(r.netValue));
        summary.vatValue = _.sumBy(records, r => parseFloat(r.vatValue));
        summary.grossValue = _.sumBy(records, r => parseFloat(r.grossValue));
        summary.pitValue = _.sumBy(records, r => parseFloat(r.pitValue));
        summary.vatDeductionValue = _.sumBy(records, r => parseFloat(r.vatDeductionValue));

        return summary;
    }

    render() {

        var labels = this.props.labels;
        var records = this.props.records;
        var summary = this.calculateSummary(records);

        return (
            <table className="table table-sm table-bordered table-hover small">
                <TableHeader labels={labels} />
                <TableBody records={records} />
                <TableFooter summary={summary} />
            </table>
        );
    }
}


import React from 'react';
import TableHeader from './TableHeader.jsx';
import TableFooter from './TableFooter.jsx';
import TableRow from './TableRow.jsx';

export default class IncomeTable extends React.Component {

    render() {

        var labels = ["Przychody", "Data", "Netto", "Vat %", "Vat", "Brutto", "Dochód", "VAT+ %", "VAT+", ""];
        var rows = [];
        if (this.props.records) {
            this.props.records.forEach(record => rows.push(<TableRow record={record} />));
        }

        return (
            <table className="table table-sm table-bordered table-hover small">
                <thead className="thead-inverse">
                    <TableHeader labels={labels} />
                </thead>
                <tbody>
                    {rows}
                </tbody>
                <tfoot>
                    <TableFooter />
                </tfoot>
            </table>
        );
    }
}


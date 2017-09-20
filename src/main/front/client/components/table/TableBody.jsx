import React from 'react';

import * as AppAction from '../../js/AppAction.js';


export default class TableRow extends React.Component {

    createRow(record) {
        return (
            <tr key={record.id}>
                <td>{record.title}</td>
                <td>{record.date}</td>
                <td className="text-right">{record.type == 'VAT' ? record.netValue + " zł" : ""}</td>
                <td className="text-right">{record.type == 'VAT' ? 100 * record.vatRate + " %" : ""}</td>
                <td className="text-right">{record.type == 'VAT' ? record.vatValue + " zł" : ""}</td>
                <td className="text-right">{record.type == 'VAT' ? record.grossValue + " zł" : ""}</td>
                <td className="text-right">{record.pitValue + " zł"}</td>
                <td className="text-right">{record.type == 'VAT' ? 100 * record.vatDeductionRate + " %" : ""}</td>
                <td className="text-right">{record.type == 'VAT' ? record.vatDeductionValue + " zł" : ""}</td>
                <td className="text-right"></td>
            </tr>
        );
    }

    render() {
        var rows = [];

        if (this.props.records) {
            this.props.records.forEach(record => rows.push(this.createRow(record)));
        }

        return (
           <tbody>
                {rows}
            </tbody>
        );
    }

}
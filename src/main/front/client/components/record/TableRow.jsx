import React from 'react';

import * as AppAction from '../../js/AppAction.js';


export default class TableRow extends React.Component {

    render() {
        var record = this.props.record;
        return (
            <tr>
                <td>{record.title}</td>
                <td>{record.date}</td>
                <td className="text-right">{record.netValue + " zł"}</td>
                <td className="text-right">{record.vatRate + " %"}</td>
                <td className="text-right">{record.vatValue + " zł"}</td>
                <td className="text-right">{record.grossValue + " zł"}</td>
                <td className="text-right">{record.pitValue + " zł"}</td>
                <td className="text-right">{record.vatDeductionRate + " %"}</td>
                <td className="text-right">{record.vatDeductionValue + " zł"}</td>
                <td className="text-right"></td>
            </tr>
        );
    }

}
import React from 'react';

export default class TableFooter extends React.Component {

    render() {

        var summary = this.props.summary;

        return (
            <tfoot>
                <tr>
                    <th colSpan="2">RAZEM</th>
                    <th className="text-right">{summary.netValue.toFixed(2) } zł</th>
                    <th></th>
                    <th className="text-right">{summary.vatValue.toFixed(2) } zł</th>
                    <th className="text-right">{summary.grossValue.toFixed(2) } zł</th>
                    <th className="text-right">{summary.pitValue.toFixed(2) } zł</th>
                    <th></th>
                    <th className="text-right">{summary.vatDeductionValue.toFixed(2) } zł</th>
                </tr>
            </tfoot>
        );
    }
}
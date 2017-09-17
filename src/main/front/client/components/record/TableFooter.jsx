import React from 'react';

export default class TableFooter extends React.Component {

    render() {
    //TODO
        return (
            <tr>
                <th colSpan="2">RAZEM</th>
                <th className="text-right">20000,00 zł</th>
                <th></th>
                <th className="text-right">800,30 zł</th>
                <th className="text-right">10000,30 zł</th>
                <th className="text-right">8200,00 zł</th>
                <th></th>
                <th className="text-right">2300,00 zł</th>
            </tr>
        );
    }
}
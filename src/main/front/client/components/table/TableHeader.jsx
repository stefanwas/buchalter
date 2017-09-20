import React from 'react';

export default class TableHeader extends React.Component {

    render() {
        var labels = this.props.labels;
        return (
            <thead className="thead-inverse">
                <tr>
                    <th style={{width: '28%'}}>{labels[0]}</th>
                    <th style={{width: '9%'}} >{labels[1]}</th>
                    <th style={{width: '9%'}} >{labels[2]}</th>
                    <th style={{width: '6%'}} >{labels[3]}</th>
                    <th style={{width: '9%'}} >{labels[4]}</th>
                    <th style={{width: '9%'}} >{labels[5]}</th>
                    <th style={{width: '9%'}} >{labels[6]}</th>
                    <th style={{width: '7%'}} >{labels[7]}</th>
                    <th style={{width: '9%'}} >{labels[8]}</th>
                    <th style={{width: '5%'}} ></th>
                </tr>
            </thead>
        );
    }

}
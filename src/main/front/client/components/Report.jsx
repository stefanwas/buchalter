import React from 'react';

import Table from './table/Table.jsx';

export default class Report extends React.Component {

    constructor() {
        super();
        this.state = {
            report: {}
        };
    }

    componentWillMount() {
        ReportStore.on("YREPORT_LOADED", () => {
            this.setState({
                report: ReportStore.getMReport()
            });
        });
    }

    button(label) {
        return (
            <div className="row mb-4">
                <div className="col-2  px-1">
                    <button type="button" className="btn btn-outline-success float-left btn-block btn-sm standard-btn" value="add">{label}</button>
                </div>
            </div>
        );
    }

    render() {
        console.log("RENDER REPROT...");

        var incomeRecords = this.state.report.incomeRecords; //TODO change to income
        var expenseRecords = this.state.report.expenseRecords; //TODO change to income
        var incomeLabels = ["Przychody", "Data", "Netto", "Vat %", "Vat", "Brutto", "Dochód", "VAT+ %", "VAT+", ""];
        var expenseLabels = ["Koszty", "Data", "Netto", "Vat %", "Vat", "Brutto", "Koszt", "VAT+ %", "VAT+", ""];

        return (
            <div id="records" className="border rounded col-9 mr-1">
                <div id="income-records">
                    <div className="row mt-3 mb-2">
                        <span className="col-12 text-center">{this.state.report.code}</span>
                    </div>
                    <div className="row">
                        <Table labels={incomeLabels} records={incomeRecords} />
                    </div>
                </div>

                {this.button('Dodaj')}

                <div id="expense-records">
                    <div className="row">
                        <Table labels={expenseLabels} records={expenseRecords} />
                    </div>
                </div>

                {this.button('Dodaj')}

            </div>
        );
    }

}


//<div id="records" class="border rounded col-9 mr-1">
//            <div id="income-records">
//                <div class="row mt-3 mb-2">
//                    <span class="col-12 text-center">R2017Q3 M8 - Sie</span>
//                </div>
//                <div class="row">
//                    <div class="col-12 px-1">
//                    <table class="table table-sm table-bordered table-hover small">
//                        <thead class="thead-inverse">
//                        <tr>
//                            <th style="width:28%">Przychody</th>
//                            <th style="width:9%">Data</th>
//                            <th style="width:9%">Netto</th>
//                            <th style="width:6%">Vat %</th>
//                            <th style="width:9%">Vat</th>
//                            <th style="width:9%">Brutto</th>
//                            <th style="width:9%">Dochód</th>
//                            <th style="width:7%">VAT+ %</th>
//                            <th style="width:9%">VAT+</th>
//                            <th style="width:5%"></th>
//                        </tr>
//                        </thead>
//                        <tbody>
//                        <tr>
//                            <td>FV/2017/08/1</td>
//                            <td>2017.08.01</td>
//                            <td class="text-right">10000,00 zł</td>
//                            <td class="text-right">23 %</td>
//                            <td class="text-right">2300,00 zł</td>
//                            <td class="text-right">12300,00 zł</td>
//                            <td class="text-right">10000,00 zł</td>
//                            <td class="text-right">100 %</td>
//                            <td class="text-right">2300,00 zł</td>
//                            <td class="text-right"></td>
//                        </tr>
//                        </tbody>
//                        <tfoot>
//                        <tr>
//                            <th colspan="2">RAZEM</th>
//                            <th class="text-right">20000,00 zł</th>
//                            <th></th>
//                            <th class="text-right">800,30 zł</th>
//                            <th class="text-right">10000,30 zł</th>
//                            <th class="text-right">8200,00 zł</th>
//                            <th></th>
//                            <th class="text-right">2300,00 zł</th>
//                        </tr>
//                        </tfoot>
//                    </table>
//                    </div>
//                </div>
//
//
//                <div class="row mb-4">
//                    <div class="col-2  px-1">
//                        <button type="button" class="btn btn-outline-success float-left btn-block btn-sm standard-btn" value="add">Dodaj</button>
//                    </div>
//                </div>
//            </div>


//                <div class="row">
//                    <div class="col-4 px-1">
//                    <table class="table table-sm table-bordered table-hover small">
//                        <thead class="thead-inverse">
//                        <tr>
//                            <th colspan="2" style="width:60%">Zestawienie PIT</th>
//                        </tr>
//                        </thead>
//                        <tbody>
//                            <tr>
//                                <td>Przychód</td>
//                                <td>8000.00</td>
//                            </tr>
//                            <tr>
//                                <td>Koszty</td>
//                                <td>2000.00</td>
//                            </tr>
//                            <tr>
//                                <td>P - K</td>
//                                <td>6000.00</td>
//                            </tr>
//                            <tr>
//                                <td>PIT 19%</td>
//                                <td>1500.00</td>
//                            </tr>
//                            <tr>
//                                <td>PIT do zapłaty</td>
//                                <td>1200.00</td>
//                            </tr>
//                        </tbody>
//                    </table>
//                    </div>
//                    <div class="col-4 px-1">
//                        <table class="table table-sm table-bordered table-hover small">
//                            <thead class="thead-inverse">
//                            <tr>
//                                <th colspan="2" >Zestawienie VAT</th>
//                            </tr>
//                            </thead>
//                            <tbody>
//                            <tr>
//                                <td style="width:60%">VAT pobrany</td>
//                                <td>2300.00</td>
//                            </tr>
//                            <tr>
//                                <td>VAT zapłacony</td>
//                                <td>2000.00</td>
//                            </tr>
//                            <tr>
//                                <td>VAT odliczony</td>
//                                <td>6000.00</td>
//                            </tr>
//                            <tr>
//                                <td>VAT do zapłaty</td>
//                                <td>1500.00</td>
//                            </tr>
//                            </tbody>
//                        </table>
//                    </div>
//
//                    <div class="col-4 px-1">
//                        <table class="table table-sm table-bordered table-hover small">
//                            <thead class="thead-inverse">
//                            <tr>
//                                <th colspan="2" >Podsumowanie</th>
//                            </tr>
//                            </thead>
//                            <tbody>
//                            <tr>
//                                <td style="width:60%">Total Brutto</td>
//                                <td>1300.00</td>
//                            </tr>
//                            <tr>
//                                <td>Total Netto</td>
//                                <td>1300.00</td>
//                            </tr>
//                            <tr>
//                                <td>N / B</td>
//                                <td>65%</td>
//                            </tr>
//                            </tbody>
//                        </table>
//                    </div>
//                </div>
//
//            </div>
//        </div>
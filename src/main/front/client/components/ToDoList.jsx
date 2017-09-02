import React from 'react';
import _ from 'lodash';

import TodoStore from "../js/TodoStore.js"
import AppAction from "../js/AppAction.js"

 class ToDoItem extends React.Component {

//  constructor() {
//    super();
//    this.state = {
//      squares: Array(9).fill(null),
//      xIsNext: true,
//    };
//  }

  render() {
    const toDoElement = this.props.text;

    return (<li> {toDoElement} </li>);

  }
}

export default class ToDoList extends React.Component {

  constructor() {
    super();
    this.state = {
      items: TodoStore.getAll()
    };
  }

  componentWillMount() {
    TodoStore.on("change", () => {
        this.setState( {
            items: TodoStore.getAll(),
        })
    });
  }



  renderItem(item) {
    return (<ToDoItem text={item.name} key={item.id} />)
  }

  createTodo() {
    AppAction.addToDo(Date.now());
  }

  render() {
//  const {items} = this.state;
    var rows = this.state.items.map(item => this.renderItem(item));
    return (
        <div>
            <h3>Todos:</h3>
            <button onClick={this.createTodo.bind(this)}>Create TODO</button>
            <ul> {rows} </ul>
        </div>
    );
  }

}
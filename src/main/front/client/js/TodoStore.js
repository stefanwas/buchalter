import {EventEmitter} from 'events';
import AppDispatcher from './AppDispatcher.js'
//import AppActionTypes from './AppActionTypes';
//import AppDispatcher from './AppDispatcher';

class TodoStore extends EventEmitter {
  constructor() {
    super();
    this.todos = [
      { id : '1', name: 'buy milk'},
      { id : '2', name: 'learn react'},
      { id : '3', name: 'call mike'},
    ];
  }

    getAll() {
        return this.todos;
    }

    createToDo(text) {
        this.todos.push( {
            id: Date.now(),
            name: text,
        });
        this.emit("change");
    }

    handleAction(action) {
        console.log("Action =", action)
        switch(action.type) {
            case "CREATE_TODO": {
//                console.log("TODO ACTION RECEIVED.");
                this.createToDo(action.text);
            }
        }
    }

}

var toDoStore = new TodoStore();
AppDispatcher.register(toDoStore.handleAction.bind(toDoStore)); // what is this???

export default toDoStore;

window.TodoStore = toDoStore;
window.AppDispatcher = AppDispatcher;
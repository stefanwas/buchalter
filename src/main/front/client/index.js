import React from 'react';
import ReactDOM from 'react-dom';



import HelloWorldApp from './components/HelloWorld.jsx';
import ToDoList from './components/ToDoList.jsx';

ReactDOM.render(<HelloWorldApp />, document.getElementById('hello-world'));
ReactDOM.render(<ToDoList />, document.getElementById('todo-list'));





console.log('Test');

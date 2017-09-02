import AppActionTypes from './AppActionTypes';
import AppDispatcher from './AppDispatcher';

const Actions = {
  addToDo(text) {
    AppDispatcher.dispatch({
      type: 'CREATE_TODO',
      text,
    });
  },
};

export default Actions;
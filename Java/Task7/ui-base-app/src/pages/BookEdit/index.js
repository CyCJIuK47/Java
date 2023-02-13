import React from 'react';
import { Provider } from 'react-redux';
import { applyMiddleware, createStore, combineReducers } from 'redux';
import thunkMiddleware from 'redux-thunk';
import BookEdit from './containers/BookEdit';
import withAuthorities from 'decorators/withAuthorities';
import { SnackbarProvider } from "notistack";

import {
    getBookReducer,
    createBookReducer,
    updateBookReducer,
 } from 'app/reducers/book';
import { getAuthorsReducer } from 'app/reducers/author';


const rootReducer = combineReducers({
  getBook: getBookReducer,
  updateBook: updateBookReducer,
  createBook: createBookReducer,
  authors: getAuthorsReducer,
});

const store = createStore(
  rootReducer,
  applyMiddleware(thunkMiddleware),
);

export default withAuthorities(props => (
  <Provider store={store}>
    <SnackbarProvider maxSnack={4}>
        <BookEdit {...props} />
    </SnackbarProvider>
  </Provider>
));
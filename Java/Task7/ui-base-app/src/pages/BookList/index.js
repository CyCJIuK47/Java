import React from 'react';
import { Provider } from 'react-redux';
import { applyMiddleware, createStore, combineReducers } from 'redux';
import thunkMiddleware from 'redux-thunk';
import BookList from './containers/BookList';
import withAuthorities from 'decorators/withAuthorities';
import { getBooksReducer, deleteBookReducer } from 'app/reducers/book';
import { getAuthorsReducer } from 'app/reducers/author';
import { SnackbarProvider } from "notistack";


const rootReducer = combineReducers({
  books: getBooksReducer,
  deleteBook: deleteBookReducer,
  authors: getAuthorsReducer,
});

const store = createStore(
  rootReducer,
  applyMiddleware(thunkMiddleware),
);

export default withAuthorities(props => (
  <Provider store={store}>
    <SnackbarProvider maxSnack={4}>
        <BookList {...props} />
    </SnackbarProvider>
  </Provider>
));
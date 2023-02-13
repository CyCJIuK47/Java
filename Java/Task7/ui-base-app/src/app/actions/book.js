import {
  getJson,
  postJson,
  putJson,
  deleteEntity,
  mapRequestErrorsToString,
} from 'requests';

import {
    REMOVE_BOOK,
    REQUEST_BOOKS,
    RECEIVE_BOOKS,
    ERROR_RECEIVE_BOOKS,
    RESET_BOOKS,
    REQUEST_BOOK,
    RECEIVE_BOOK,
    ERROR_RECEIVE_BOOK,
    RESET_BOOK,
    REQUEST_BOOK_CREATE,
    SUCCESS_BOOK_CREATE,
    ERROR_BOOK_CREATE,
    RESET_BOOK_CREATE,
    REQUEST_BOOK_UPDATE,
    SUCCESS_BOOK_UPDATE,
    ERROR_BOOK_UPDATE,
    RESET_BOOK_UPDATE,
    REQUEST_BOOK_DELETE,
    SUCCESS_BOOK_DELETE,
    ERROR_BOOK_DELETE,
    RESET_BOOK_DELETE,
} from '../constants/actionTypes';

import config from 'config';


export const resetBooks = () => ({
    type: RESET_BOOKS,
});

export const removeBookFromList = ({ id }) => (dispatch) => {
  dispatch(removeBook(id));
};

const removeBook = (id) => ({
    type: REMOVE_BOOK,
    payload: id,
});

const requestBook = () => ({
  type: REQUEST_BOOK,
});

const receiveBook = (book) => ({
  type: RECEIVE_BOOK,
  payload: book,
});

const errorReceiveBook = (errorMessage) => ({
  type: ERROR_RECEIVE_BOOK,
  payload: errorMessage,
});

export const resetBook = () => ({
    type: RESET_BOOK,
});

const getBook = (id) => {
  const {
    BASE_URL,
    USERS_SERVICE,
  } = config;

  return getJson({
    url: `${BASE_URL}${USERS_SERVICE}/api/books/${id}`,
  })
};

export const fetchBook = ({ id }) => (dispatch) => {
    dispatch(requestBook());
    return getBook(id)
        .then(book => dispatch(receiveBook(book)))
        .catch(error => dispatch(errorReceiveBook("")));
};

export const removeBookFromPage = ({ id }) => (dispatch) => {
    dispatch(requestBookDelete());
    return deleteBook(id)
        .then(() => {dispatch(removeBook(id))})
        .then(() => dispatch(successBookDelete()))
        .catch(error => {
            mapRequestErrorsToString(error)
            .then(errorMessage => dispatch(errorBookDelete(errorMessage)));
        });
};

const requestBooks = () => ({
  type: REQUEST_BOOKS,
});

const receiveBooks = (books) => ({
  type: RECEIVE_BOOKS,
  payload: books,
});

const errorReceiveBooks = (errorMessage) => ({
  type: ERROR_RECEIVE_BOOKS,
  payload: errorMessage,
});

const getBooks = () => {
  const {
    BASE_URL,
    USERS_SERVICE,
  } = config;

  return getJson({
    url: `${BASE_URL}${USERS_SERVICE}/api/books`,
  })
};


export const fetchBooks = () => (dispatch) => {
    dispatch(requestBooks());
    return getBooks()
        .then(books => dispatch(receiveBooks(books)))
        .catch(error => dispatch(errorReceiveBooks("")));
};


const requestBookCreate = () => ({
  type: REQUEST_BOOK_CREATE,
});

const successBookCreate = (id) => ({
  type: SUCCESS_BOOK_CREATE,
  payload: id,
});

const errorBookCreate = (errorMessage) => ({
  type: ERROR_BOOK_CREATE,
  payload: errorMessage,
});

export const resetBookCreate = () => ({
    type: RESET_BOOK_CREATE,
});

const createBook = (book) => {
  const {
    BASE_URL,
    USERS_SERVICE,
  } = config;

  const {
    title,
    year,
    pages,
    authorId,
  } = book;

  return postJson({
      body: {
        title,
        year: Number(year),
        pages: Number(pages),
        authorId: Number(authorId),
      },
      url: `${BASE_URL}${USERS_SERVICE}/api/books`,
    })
};


export const fetchBookCreate = (book) => (dispatch) => {
    dispatch(requestBookCreate());
    return createBook(book)
        .then(id => dispatch(successBookCreate(id)))
        .catch(error => {
             mapRequestErrorsToString(error)
                .then(errorMessage => dispatch(errorBookCreate(errorMessage)));
         });
};


//

const requestBookUpdate = () => ({
  type: REQUEST_BOOK_UPDATE,
});

const successBookUpdate = () => ({
  type: SUCCESS_BOOK_UPDATE,
});

const errorBookUpdate = (errorMessage) => ({
  type: ERROR_BOOK_UPDATE,
  payload: errorMessage,
});

export const resetBookUpdate = () => ({
    type: RESET_BOOK_UPDATE,
});

const updateBook = (book) => {
  const {
    BASE_URL,
    USERS_SERVICE,
  } = config;

  const {
    id,
    title,
    year,
    pages,
    authorId,
  } = book;

  return putJson({
      body: {
        title,
        year,
        pages,
        authorId,
      },
      url: `${BASE_URL}${USERS_SERVICE}/api/books/${id}`,
    })
};


export const fetchBookUpdate = (book) => (dispatch) => {
    dispatch(requestBookUpdate());
    return updateBook(book)
        .then(() => dispatch(successBookUpdate()))
        .catch(error => {
            mapRequestErrorsToString(error)
            .then(errorMessage => dispatch(errorBookUpdate(errorMessage)));
        });
};



const requestBookDelete = () => ({
  type: REQUEST_BOOK_DELETE,
});

const successBookDelete = () => ({
  type: SUCCESS_BOOK_DELETE,
});

const errorBookDelete = (errorMessage) => ({
  type: ERROR_BOOK_DELETE,
  payload: errorMessage,
});

export const resetBookDelete = () => ({
    type: RESET_BOOK_DELETE,
});

const deleteBook = (id) => {
  const {
    BASE_URL,
    USERS_SERVICE,
  } = config;

  return deleteEntity({
      url: `${BASE_URL}${USERS_SERVICE}/api/books/${id}`,
    })
};


export const fetchBookDelete = (id) => (dispatch) => {
    dispatch(requestBookDelete());
    return deleteBook(id)
        .then(() => dispatch(successBookDelete()))
        .catch(error => {
            mapRequestErrorsToString(error)
            .then(errorMessage => dispatch(errorBookDelete(errorMessage)));
        });
};
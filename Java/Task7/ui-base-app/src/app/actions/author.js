import {
  getJson,
  postJson,
  putJson,
  deleteEntity,
} from 'requests';

import {
    REQUEST_AUTHORS,
    RECEIVE_AUTHORS,
    ERROR_RECEIVE_AUTHORS,
    REQUEST_AUTHOR,
    RECEIVE_AUTHOR,
    ERROR_RECEIVE_AUTHOR,
    RESET_AUTHORS,
    RESET_AUTHOR,
} from '../constants/actionTypes';

import config from 'config';


export const resetAuthors = () => ({
    type: RESET_AUTHORS,
});

export const resetAuthor = () => ({
    type: RESET_AUTHOR,
});

const requestAuthors = () => ({
  type: REQUEST_AUTHORS,
});

const receiveAuthors = (authors) => ({
  type: RECEIVE_AUTHORS,
  payload: authors,
});

const errorReceiveAuthors = (errorMessage) => ({
  type: ERROR_RECEIVE_AUTHORS,
  payload: errorMessage,
});

const getAuthors = () => {
  const {
    BASE_URL,
    USERS_SERVICE,
  } = config;

  return getJson({
    url: `${BASE_URL}${USERS_SERVICE}/api/authors`,
  })
};


export const fetchAuthors = () => (dispatch) => {
    dispatch(requestAuthors());
    return getAuthors()
        .then(authors => dispatch(receiveAuthors(authors)))
        .catch(error => dispatch(errorReceiveAuthors("")));
};


const requestAuthor = () => ({
  type: REQUEST_AUTHOR,
});

const receiveAuthor = (author) => ({
  type: RECEIVE_AUTHOR,
  payload: author,
});

const errorReceiveAuthor = (errorMessage) => ({
  type: ERROR_RECEIVE_AUTHOR,
  payload: errorMessage,
});

const getAuthor = (id) => {
  const {
    BASE_URL,
    USERS_SERVICE,
  } = config;

  return getJson({
    url: `${BASE_URL}${USERS_SERVICE}/api/authors/${id}`,
  })
};




export const fetchAuthor = ({ id }) => (dispatch) => {
    dispatch(requestAuthor());
    return getAuthor()
        .then(author => dispatch(receiveAuthor(author)))
        .catch(error => dispatch(errorReceiveAuthor("")));
};
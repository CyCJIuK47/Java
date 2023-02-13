import {
    REQUEST_AUTHORS,
    RECEIVE_AUTHORS,
    ERROR_RECEIVE_AUTHORS,
    REQUEST_AUTHOR,
    RECEIVE_AUTHOR,
    ERROR_RECEIVE_AUTHOR,
} from '../constants/actionTypes';


const getAuthorsInitialState = {
    list: [],
    isFetching: false,
    isFailed: false,
    errorMessage: null
};

const getAuthorsReducer = (state = getAuthorsInitialState, action) => {
  switch (action.type) {
    case REQUEST_AUTHORS: {
        return {
            ...getAuthorsInitialState,
            isFetching: true,
        };
    }
    case RECEIVE_AUTHORS: {
        return {
            ...getAuthorsInitialState,
            list: action.payload,
        };
    }
    case ERROR_RECEIVE_AUTHORS: {
        return {
            ...getAuthorsInitialState,
            isFailed: true,
            errorMessage: action.payload,
        };
    }
    default: return state;
  }
}

const getAuthorInitialState = {
    item: null,
    isFetching: false,
    isFailed: false,
    errorMessage: null
};

const getAuthorReducer = (state = getAuthorInitialState, action) => {
  switch (action.type) {
    case REQUEST_AUTHOR: {
        return {
            ...getAuthorInitialState,
            isFetching: true,
        };
    }
    case RECEIVE_AUTHOR: {
        return {
            ...getAuthorInitialState,
            item: action.payload,
        };
    }
    case ERROR_RECEIVE_AUTHOR: {
        return {
            ...getAuthorInitialState,
            isFailed: true,
            errorMessage: action.payload,
        };
    }
    default: return state;
  }
}

export {
    getAuthorsReducer,
    getAuthorReducer,
};
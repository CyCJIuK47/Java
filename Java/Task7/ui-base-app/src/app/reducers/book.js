import {
    REQUEST_BOOKS,
    RECEIVE_BOOKS,
    ERROR_RECEIVE_BOOKS,
    RESET_BOOKS,
    REMOVE_BOOK,
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


const getBooksInitialState = {
    list: [],
    isFetching: false,
    isFailed: false,
    errorMessage: null
};

const getBooksReducer = (state = getBooksInitialState, action) => {
  switch (action.type) {
    case REQUEST_BOOKS: {
        return {
            ...getBooksInitialState,
            isFetching: true,
        };
    }
    case RECEIVE_BOOKS: {
        return {
            ...getBooksInitialState,
            list: action.payload,
        };
    }
    case ERROR_RECEIVE_BOOKS: {
        return {
            ...getBooksInitialState,
            isFailed: true,
            errorMessage: action.payload,
        };
    }
    case REMOVE_BOOK: {
        return {
            ...state,
            list: state.list.filter(book => book.id !== action.payload),
        };
    }
    case RESET_BOOKS: {
        return getBooksInitialState;
    }
    default: return state;
  }
}


const updateBookInitialState = {
    isFetching: false,
    isFailed: false,
    isUpdated: false,
    errorMessage: null,
};

const updateBookReducer = (state = updateBookInitialState, action) => {
  switch (action.type) {
    case REQUEST_BOOK_UPDATE: {
        return {
            ...updateBookInitialState,
            isFetching: true,
        };
    }
    case SUCCESS_BOOK_UPDATE: {
        return {
            ...updateBookInitialState,
            isUpdated: true,
        };
    }
    case ERROR_BOOK_UPDATE: {
        return {
            ...updateBookInitialState,
            isFailed: true,
            errorMessage: action.payload
        };
    }
    case RESET_BOOK_UPDATE: {
        return updateBookInitialState;
    }
    default: return state;
  }
}


const deleteBookInitialState = {
    isFetching: false,
    isFailed: false,
    isDeleted: false,
    errorMessage: null
};

const deleteBookReducer = (state = deleteBookInitialState, action) => {
  switch (action.type) {
    case REQUEST_BOOK_DELETE: {
        return {
            ...deleteBookInitialState,
            isFetching: true,
        };
    }
    case SUCCESS_BOOK_DELETE: {
        return {
            ...deleteBookInitialState,
            isDeleted: true,
        };
    }
    case ERROR_BOOK_DELETE: {
        return {
            ...deleteBookInitialState,
            isFailed: true,
            errorMessage: action.payload
        };
    }
    case RESET_BOOK_DELETE: {
        return deleteBookInitialState;
    }
    default: return state;
  }
}


const createBookInitialState = {
    isFetching: false,
    isFailed: false,
    id: null,
    errorMessage: null
};

const createBookReducer = (state = createBookInitialState, action) => {
  switch (action.type) {
    case REQUEST_BOOK_CREATE: {
        return {
            ...createBookInitialState,
            isFetching: true,
        };
    }
    case SUCCESS_BOOK_CREATE: {
        return {
            ...createBookInitialState,
            id: action.payload,
        };
    }
    case ERROR_BOOK_CREATE: {
        return {
            ...createBookInitialState,
            isFailed: true,
            errorMessage: action.payload
        };
    }
    case RESET_BOOK_CREATE: {
        return createBookInitialState;
    }
    default: return state;

  }
}


const getBookInitialState = {
    isFetching: false,
    isFailed: false,
    item: null,
    errorMessage: null,
};

const getBookReducer = (state = getBookInitialState, action) => {
  switch (action.type) {
    case REQUEST_BOOK: {
        return {
            ...getBookInitialState,
            isFetching: true,

        };
    }
    case RECEIVE_BOOK: {
        return {
            ...getBookInitialState,
            item: action.payload,
        };
    }
    case ERROR_RECEIVE_BOOK: {
        return {
            ...getBookInitialState,
            isFailed: true,
            errorMessage: action.payload
        };
    }
    case RESET_BOOK: {
        return getBookInitialState;
    }
    default: return state;
  }
}

export {
    getBooksReducer,
    getBookReducer,
    createBookReducer,
    updateBookReducer,
    deleteBookReducer,
};
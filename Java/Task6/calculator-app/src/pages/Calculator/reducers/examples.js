import examplesConstants from '../constants/examples';

const {
    RECEIVE_EXAMPLES,
    REQUEST_EXAMPLES,
    ERROR_RECEIVE_EXAMPLES,
    PROCESS_EXAMPLES
} = examplesConstants;


const initialState = {
    isLoading: false,
    isError: false,
    list: [],
};

export default (state = initialState, action) => {
    switch(action.type) {
        case ERROR_RECEIVE_EXAMPLES: {
            return {
                ...state,
                isLoading: false,
                isError: true,
            }
        }
        case REQUEST_EXAMPLES: {
            return {
                ...state,
                isLoading: true,
                isError: false,
            }
        }
        case RECEIVE_EXAMPLES: {
            return {
                ...state,
                isError: false,
                isLoading: false,
                list: action.list,
            }
        }

        case PROCESS_EXAMPLES: {
            return {
                ...state,
                isError: false,
                isLoading: false,
                list: [],
            }
        }

        default: return state;
    }
};
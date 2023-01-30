import calculatorActions from './calculator';
import examplesConstants from '../constants/examples';

const {
    actionCalculateBatch,
} = calculatorActions;

const {
    RECEIVE_EXAMPLES,
    REQUEST_EXAMPLES,
    ERROR_RECEIVE_EXAMPLES,
    PROCESS_EXAMPLES
} = examplesConstants;

const getExamples = (count) => {

    return fetch('http://localhost:8080/math/examples?count=' + count,
        {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .catch(error => console.log(error));
};

const errorReceiveExamples = () => ({
    type: ERROR_RECEIVE_EXAMPLES,
});

const requestExamples = () => ({
    type: REQUEST_EXAMPLES,
});

const processExamples = () => ({
    type: PROCESS_EXAMPLES,
});

const receiveExamples = (examples) => ({
    type: RECEIVE_EXAMPLES,
    list: examples
});

const fetchExamples = ({count}) => (dispatch) => {
    dispatch(requestExamples());

    return getExamples(count)
        .then(examples => {
            dispatch(receiveExamples(examples));
            dispatch(processExamples(examples));
            dispatch(actionCalculateBatch(examples));
        })
        .catch((error) => {
            dispatch(errorReceiveExamples());
            console.log(error)
        });
};

export default {
    actionFetchExamples: fetchExamples,
};
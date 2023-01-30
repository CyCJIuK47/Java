import calculatorConstants from '../constants/calculator';

const {
    ADD_DIGIT,
    SET_OPERATOR,
    EVALUATE,
    CALCULATE_BATCH
} = calculatorConstants;


const addDigit = (digit) => ({
    type: ADD_DIGIT,
    payload: digit
});

const setOperator = (operator) => ({
    type: SET_OPERATOR,
    payload: operator
});

const evaluate = () => ({
    type: EVALUATE
});

const calculateBatch = (exampleList) => ({
    type: CALCULATE_BATCH,
    exampleList: exampleList
});


export default {
    actionAddDigit: addDigit,
    actionSetOperator: setOperator,
    actionEvaluate: evaluate,
    actionCalculateBatch: calculateBatch
};

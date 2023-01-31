import calculatorConstants from '../constants/calculator';

const {
    ADD_DIGIT,
    SET_OPERATOR,
    EVALUATE,
    CALCULATE_BATCH
} = calculatorConstants;


const initialState = {
    leftOperand: 0,
    rightOperand: null,
    operator: null,
    history: [],
};

const DIVISION_BY_ZERO = "Error division by zero";

const calculate = (leftOperand, rightOperand, operator) => {
    switch (operator) {
        case '+' :
            return leftOperand + rightOperand;
        case '-' :
            return leftOperand - rightOperand;
        case '*' :
            return leftOperand * rightOperand;
        case '/' :
            if (rightOperand === 0) {
                return DIVISION_BY_ZERO;
            }
            return leftOperand / rightOperand;
    }
}

const parseExample = (example) => {
    const items = example.split(' ');
    return {
        leftOperand: items[0],
        rightOperand: items[2],
        operator: items[1],
    };
}

const exampleToString = ({leftOperand, rightOperand, operator, result}) => {
    return `${leftOperand} ${operator} ${rightOperand} = ${result}`;
}

export default (state = initialState, action) => {

    const {
        leftOperand,
        rightOperand,
        operator,
        history
    } = state;

    switch (action.type) {
        case ADD_DIGIT: {
            const newState = {...state};
            if (operator === null) {
                newState.leftOperand = leftOperand * 10 + action.payload;
            } else {
                newState.rightOperand = rightOperand * 10 + action.payload;
            }
            return newState;
        }

        case SET_OPERATOR: {
            if (leftOperand === null) {
                return state;
            }

            if (leftOperand !== null && rightOperand === null) {
                return {
                    ...state,
                    operator: action.payload
                }
            }

            const result = calculate(leftOperand, rightOperand, operator);
            return {
                leftOperand: result === DIVISION_BY_ZERO ? 0 : result,
                rightOperand: null,
                operator: action.payload,
                history: history.concat(exampleToString({
                    leftOperand,
                    rightOperand,
                    operator,
                    result
                }))
            }
        }
        case EVALUATE: {
            // if expression is not completed
            if (leftOperand === null || operator === null || rightOperand === null) {
                return state;
            }
            const result = calculate(leftOperand, rightOperand, operator);
            return {...initialState,
                    leftOperand: result === DIVISION_BY_ZERO ? 0 : result,
                    history: history.concat(exampleToString({
                        leftOperand,
                        rightOperand,
                        operator,
                        result
                    }))
            };
        }

        case CALCULATE_BATCH: {
            const newState = {...state};
            const calculatedExamples = action.exampleList.map(item=>{
                const example = parseExample(item);

                return item + " = " + calculate(example.leftOperand,
                                                example.rightOperand,
                                                example.operator);
            });
            newState.history = history.concat(calculatedExamples);
            return newState;
        }
        default: return state;
    }
}
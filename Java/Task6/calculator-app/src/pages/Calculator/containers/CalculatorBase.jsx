import React from 'react';
import { connect } from 'react-redux';

import Grid from '@material-ui/core/Grid';

import calculatorActions from '../actions/calculator';
import CalculatorButton from '../components/CalculatorButton';


const {
    actionAddDigit,
    actionSetOperator,
    actionEvaluate,
} = calculatorActions;

class CalculatorBase extends React.Component {
    render() {

        const {
            dispatch
        } = this.props;


        return (
            <Grid container spacing={1}>
                <Grid item container xs={12}>
                   <CalculatorButton
                   onClick={()=>dispatch(actionAddDigit(7))}
                   text="7"
                   />
                   <CalculatorButton
                   onClick={()=>dispatch(actionAddDigit(8))}
                   text="8"
                   />
                   <CalculatorButton
                   onClick={()=>dispatch(actionAddDigit(9))}
                   text="9"
                   />
                   <CalculatorButton
                   onClick={()=>dispatch(actionSetOperator("+"))}
                   text="+"
                   />
                </Grid>
                <Grid item container xs={12}>
                   <CalculatorButton
                   onClick={()=>dispatch(actionAddDigit(4))}
                   text="4"
                   />
                   <CalculatorButton
                   onClick={()=>dispatch(actionAddDigit(5))}
                   text="5"
                   />
                   <CalculatorButton
                   onClick={()=>dispatch(actionAddDigit(6))}
                   text="6"
                   />
                   <CalculatorButton
                   onClick={()=>dispatch(actionSetOperator("-"))}
                   text="-"
                   />
                </Grid>
                <Grid item container xs={12}>
                   <CalculatorButton
                   onClick={()=>dispatch(actionAddDigit(1))}
                   text="1"
                   />
                   <CalculatorButton
                   onClick={()=>dispatch(actionAddDigit(2))}
                   text="2"
                   />
                   <CalculatorButton
                   onClick={()=>dispatch(actionAddDigit(3))}
                   text="3"
                   />
                   <CalculatorButton
                   onClick={()=>dispatch(actionSetOperator("*"))}
                   text="*"
                   />
                </Grid>
                <Grid item container xs={12}>
                   <CalculatorButton
                   onClick={()=>dispatch(actionAddDigit(0))}
                   text="0"
                   />
                   <CalculatorButton
                   onClick={()=>dispatch(actionEvaluate())}
                   text="="
                   xs={6}
                   />
                   <CalculatorButton
                   onClick={()=>dispatch(actionSetOperator("/"))}
                   text="/"
                   />
                </Grid>
            </Grid>
        )
    }
}

const mapReduxStateToProps = reduxState => ({

});

const mapDispatchToProps = dispatch => ({
    dispatch,
});

export default connect(mapReduxStateToProps, mapDispatchToProps)(CalculatorBase);
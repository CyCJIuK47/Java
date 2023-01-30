import React from 'react';
import { connect } from 'react-redux';

import Box from '@material-ui/core/Box';
import Button from '@material-ui/core/Button';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';

import calculatorActions from '../actions/calculator';
import CalculationHistory from '../components/CalculationHistory';
import CalculatorTabloid from '../components/CalculatorTabloid';
import CalculatorButton from '../components/CalculatorButton';
import examplesActions from '../actions/examples';
import ExamplesGenerator from './ExamplesGenerator';


const {
    actionAddDigit,
    actionSetOperator,
    actionEvaluate,
} = calculatorActions;

class Calculator extends React.Component {
    render() {
        const {
            leftOperand,
            rightOperand,
            operator,
            history
        } = this.props.calculator;

        const {
            dispatch
        } = this.props;


        return (
        <Container maxWidth='xs'>
            <Paper elevation={3}>
                <Box sx={{ flexGrow: 1 }}>
                    <Grid container spacing={1} justifyContent="center">
                        <Grid container item xs={9} spacing={1}>
                            <Grid item xs={12}>
                                <CalculationHistory
                                list={history}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <CalculatorTabloid>
                                    {leftOperand} {operator} {rightOperand}
                                </CalculatorTabloid>
                            </Grid>

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
                            <Grid item container>
                                <ExamplesGenerator/>
                            </Grid>
                        </Grid>
                    </Grid>
                </Box>
            </Paper>
        </Container>
        )
    }
}

const mapReduxStateToProps = reduxState => ({
    calculator: reduxState.calculatorReducer,
});

const mapDispatchToProps = dispatch => ({
    dispatch,
});

export default connect(mapReduxStateToProps, mapDispatchToProps)(Calculator);
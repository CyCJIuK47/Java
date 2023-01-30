import React from 'react';
import { connect } from 'react-redux';

import Box from '@material-ui/core/Box';
import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';

import CalculationHistory from '../components/CalculationHistory';
import CalculatorTabloid from '../components/CalculatorTabloid';
import CalculatorBase from './CalculatorBase';
import ExamplesGenerator from './ExamplesGenerator';


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
                            <Grid item container>
                                <CalculatorBase/>
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
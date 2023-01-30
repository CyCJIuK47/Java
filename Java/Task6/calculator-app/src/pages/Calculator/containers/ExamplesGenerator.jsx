import React from 'react';
import { connect } from 'react-redux';

import Box from '@material-ui/core/Box';
import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';

import examplesActions from '../actions/examples';


class ExamplesGenerator extends React.Component {

    render() {
        const {
            isError,
            isLoading,
            list,
        } = this.props.examples;

        const {
            dispatch,
        } = this.props;


        return (
            <Grid item container spacing={1}>
             <Grid item container xs={12}>
                 <Button
                 fullWidth
                 variant="contained"
                 onClick={()=>examplesActions.actionFetchExamples({count: 5})(dispatch)}
                 >
                     Request & Solve examples
                 </Button>
            </Grid>
            <Grid item xs={12}>
                {isLoading &&
                <Paper elevation={0}>
                    Requesting examples...
                </Paper>}
                {isError &&
                <Paper elevation={0}>
                    Something went wrong...
                </Paper>}
            </Grid>
        </Grid>
        )
    }
}

const mapReduxStateToProps = reduxState => ({
    examples: reduxState.examplesReducer,
});

const mapDispatchToProps = dispatch => ({
    dispatch,
});

export default connect(mapReduxStateToProps, mapDispatchToProps)(ExamplesGenerator);
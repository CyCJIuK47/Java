import React from 'react';

import Grid from '@material-ui/core/Grid';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Paper from '@material-ui/core/Paper';


const CalculationHistory = ({ list }) => {

    return (
        <Grid item xs={12}>
            <Paper elevation={0}>
                Calculation History:
            </Paper>
            <Paper
            key="calculationHistory"
            maxHeight="200px"
            style={{maxHeight: '200px', overflow: 'auto', }}
            >
                <List
                dense={true}
                >
                    {list.map((item, index) => {
                        const lastItemHighlighting = (index === list.length - 2);
                        return (
                            <ListItem
                            key={index}
                            divider={lastItemHighlighting}>
                                <Paper elevation={0}>
                                    {item}
                                </Paper>
                            </ListItem>
                        );
                        }
                    )}
                </List>
            </Paper>
        </Grid>
)};

export default CalculationHistory;
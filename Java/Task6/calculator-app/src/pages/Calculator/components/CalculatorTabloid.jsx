import Paper from '@material-ui/core/Paper';
import {styled} from '@material-ui/core';

const CalculatorTabloid = styled(Paper)(({theme}) => ({
    textAlign: 'right',
    fontSize: '2em',
    padding: theme.spacing(2),
    height: 'auto',
    overflow: 'auto'
}));

export default CalculatorTabloid;


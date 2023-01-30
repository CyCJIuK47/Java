import Button from '@material-ui/core/Button';
import Grid from '@material-ui/core/Grid';


const CalculatorButton = ({text, onClick, xs=3}) => {
    return (
        <Grid item xs={xs}>
            <Button
            fullWidth
            onClick={onClick}
            variant="outlined"
            >
                {text}
            </Button>
        </Grid>
    );
};


export default CalculatorButton;




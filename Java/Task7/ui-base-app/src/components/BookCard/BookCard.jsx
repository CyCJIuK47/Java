import React from 'react';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import CardActions from '@material-ui/core/CardActions';
import Button from 'components/Button';
import Grid from '@material-ui/core/Grid';
import CardHeader from '@material-ui/core/CardHeader';
import Typography from 'components/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import FolderIcon from '@material-ui/icons/Folder';
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import DescriptionIcon from '@material-ui/icons/Description';
import DateRangeIcon from '@material-ui/icons/DateRange';
import PermContactCalendar from '@material-ui/icons/PermContactCalendar';
import * as PAGES from 'constants/pages';
import useChangePage from 'hooks/useChangePage';
import { useDispatch } from 'react-redux';
import { fetchBookDelete } from '../../app/actions/book';
import { makeStyles } from '@material-ui/core/styles';
import { shadows } from '@material-ui/system';
import useAccessValidate from 'hooks/useAccessValidate';
import { useIntl } from 'react-intl';

const getClasses = makeStyles(() => ({
  movieCardContent: {
    opacity: 0,
    paddingTop: 0,
    marginLeft: 5,
  },

  movieCard: {
    '&:hover': {
        opacity: 1,
        //explicit boxShadow: 12,
        boxShadow: '0px 7px 8px -4px rgb(0 0 0 / 20%), 0px 12px 17px 2px rgb(0 0 0 / 14%), 0px 5px 22px 4px rgb(0 0 0 / 12%)',
        "& $movieCardContent": {
          opacity: 1,
        }
    },

  }
}));



const BookCard = ({ book, author, onEdit, onDelete, canEditCard }) => {

    const {
        id,
        title,
        authorId,
        year,
        pages,
    } = book;

    const {
        name,
        surname,
    } = author;


    const { formatMessage } = useIntl();

    const classes = getClasses();

    return (
        <Card className={classes.movieCard}>
             <CardContent >
                    <Grid container xs={12} direction="row" justify="space-between" alignItems="center"
                     columnSpacing={2}>
                    <Grid item >
                    <Typography gutterBottom variant="h5" component="div">
                      {title}
                    </Typography>
                    </Grid>

                    {canEditCard && (<Grid item >
                      <CardActions className={classes.movieCardContent} justify="flex-start"  >
                      <IconButton aria-label="delete" size="small"
                        onClick={onEdit}>
                      <EditIcon fontSize="small" />
                      </IconButton>
                      <IconButton aria-label="edit" size="small"
                      onClick={onDelete}>
                          <DeleteIcon fontSize="small" />
                      </IconButton>
                    </CardActions>
                    </Grid>)}
                    </Grid>
                    <Divider/>
                    <List dense={true}>
                        <ListItem>
                          <ListItemIcon>
                            <AccountCircleIcon/>
                          </ListItemIcon>
                          <ListItemText
                            primary={`${name} ${surname}`}
                          />
                        </ListItem>
                        <ListItem>
                          <ListItemIcon>
                            <DateRangeIcon/>
                          </ListItemIcon>
                          <ListItemText
                            primary={`${year} ${formatMessage({
                                        id: 'book.card.year',
                                      })}`}
                          />
                        </ListItem>
                        <ListItem>
                          <ListItemIcon>
                            <DescriptionIcon/>
                          </ListItemIcon>
                          <ListItemText
                            primary={`${pages} ${formatMessage({
                                             id: 'book.card.pages',
                                      })}`}
                          />
                        </ListItem>

                    </List>
                  </CardContent>
        </Card>
    )
};

export default BookCard;
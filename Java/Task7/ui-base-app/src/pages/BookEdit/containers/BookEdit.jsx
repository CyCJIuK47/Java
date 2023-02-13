import React, { useState, useEffect, useCallback } from "react";
import { useParams } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import Grid from '@material-ui/core/Grid';
import useChangePage from 'hooks/useChangePage';
import Container from '@material-ui/core/Container';
import Button from '@material-ui/core/Button';
import Divider from '@material-ui/core/Divider';
import TextField from '@material-ui/core/TextField';
import MenuItem from '@material-ui/core/MenuItem';
import { fetchBook, fetchBookCreate, fetchBookUpdate, resetBook, resetBookUpdate, resetBookCreate } from 'app/actions/book';
import { fetchAuthors, resetAuthors } from 'app/actions/author';
import * as PAGES from 'constants/pages';
import { useSnackbar } from 'notistack';
import useAccessValidate from 'hooks/useAccessValidate';
import { useIntl } from 'react-intl';


const initialState = {
  title: '',
  year: '',
  pages: '',
  authorId: 0,
};


const UPDATE_MODE = "UPDATE_MODE";
const CREATE_MODE = "CREATE_MODE";

const BookEdit = ({ authorities }) => {

    const canSee = useAccessValidate({
        ownedAuthorities: authorities,
        neededAuthorities: ['READ'],
    });

    const canEdit = useAccessValidate({
        ownedAuthorities: authorities,
        neededAuthorities: ['EDIT'],
    });

    const { formatMessage } = useIntl();

    const { enqueueSnackbar, closeSnackbar } = useSnackbar();

    const handleClick = () => {
            enqueueSnackbar(`Successful.`, { variant: "success" });
        };

    const [state, setState] = useState(initialState);

    const getBook = useSelector(({ getBook }) => getBook);
    const createBook = useSelector(({ createBook }) => createBook);
    const updateBook = useSelector(({ updateBook }) => updateBook);


    const authors = useSelector(({ authors }) => authors);

    const dispatch = useDispatch();

    const handleInputChange = (e) => {
      const { name, value} = e.target;
      setState({
        ...state,
        [name]: value,
      });
    };

    const params = useParams();
    const editMode = (params.id === undefined) ? CREATE_MODE : UPDATE_MODE;

    useEffect(() => {
        if(!canEdit) {
            return;
        }
        if (editMode === UPDATE_MODE) {
            dispatch(fetchBook({id: params.id}));
        }
        dispatch(fetchAuthors());

        return () => {
            dispatch(resetBook());
            dispatch(resetBookCreate());
            dispatch(resetBookUpdate());
            dispatch(resetAuthors());
        }
    }, []);

    useEffect(() => {
        if (getBook.item !== null) {
            setState(getBook.item);
        }
    }, [getBook.item]);

    const [authorsMap, setAuthorsMap] = useState({});

    const changePage = useChangePage();

    useEffect(() => {
        if (authors.list.length) {
            const map = {};
            authors.list.forEach(author => map[author.id] = author);
            setAuthorsMap(map);
        }

    }, [authors.list]);


    useEffect(() => {
        if (createBook.id !== null) {
            enqueueSnackbar(formatMessage({
                                id: 'book.create.success',
                            }), { variant: "success" });
        }
    }, [createBook.id]);

    useEffect(() => {
        if (createBook.isFailed) {
            enqueueSnackbar(`${formatMessage({
                            id: 'book.create.failed',
                        })} \n ${createBook.errorMessage}`,
                        { variant: "error", style: { whiteSpace: 'pre-line' } });
        }
    }, [createBook.isFailed]);

    useEffect(() => {
        if (updateBook.isUpdated) {
            enqueueSnackbar(formatMessage({
                                id: 'book.update.success',
                            }), { variant: "success" });
        }
    }, [updateBook.isUpdated, enqueueSnackbar]);

    useEffect(() => {
        if (updateBook.isFailed) {
           enqueueSnackbar(`${formatMessage({
                               id: 'book.update.failed',
                           })} \n ${updateBook.errorMessage}`,
                           { variant: "error", style: { whiteSpace: 'pre-line' } });
        }
    }, [updateBook.isFailed]);

    const handleEditClick = () => {
        (editMode === CREATE_MODE) ?
            dispatch(fetchBookCreate(state)) :
            dispatch(fetchBookUpdate({ ...state, id: params.id }));
    };



    return !canEdit ? null : (
        <Container maxWidth='md'>
            <Grid container spacing={2} size="md">
                <Grid item xs={12}>
                    <TextField
                      id="title-input"
                      name="title"
                      label={formatMessage({
                        id: 'title',
                      })}
                      type="text"
                      value={state.title}
                      variant="outlined"
                      onChange={handleInputChange}
                      fullWidth
                      inputProps="[0-9]{1,15}"
                    />
                </Grid>
                <Grid item container spacing={2}>
                    <Grid item container xs={6}>
                        <TextField
                          id="year-input"
                          name="year"
                          label={formatMessage({
                              id: 'year',
                            })}
                          type="number"
                          value={state.year}
                          variant="outlined"
                          onChange={handleInputChange}
                          fullWidth
                        />
                    </Grid>
                    <Grid item container xs={6}>
                        <TextField
                          id="pages-input"
                          name="pages"
                          label={formatMessage({
                              id: 'pages',
                          })}
                          type="number"
                          value={state.pages}
                          variant="outlined"
                          onChange={handleInputChange}
                          fullWidth
                        />
                    </Grid>
                </Grid>
                <Grid item xs={12}>
                    <TextField
                      id="outlined-select-currency"
                      select
                      name="authorId"
                      label={formatMessage({
                        id: 'author',
                      })}
                      defaultValue=""
                      variant="outlined"
                      value={state.authorId}
                      fullWidth
                      onChange={handleInputChange}
                    >
                      {
                        Object.keys(authorsMap).map((authorId) =>
                        {
                            const author = authorsMap[authorId];
                            return (
                                <MenuItem key={authorId} value={authorId}>
                                    {`${author.name} ${author.surname}`}
                                </MenuItem>
                        )})
                      }
                    </TextField>
                </Grid>
                <Grid item xs={12} >
                     <Divider
                       orientation="horizontal"
                     />
                </Grid>
                <Grid item container spacing={2}>
                    <Grid item xs={6}>
                        <Button
                        color="secondary"
                          variant="outlined"
                          fullWidth
                          onClick={() => changePage({ path: `/${PAGES.BOOK_LIST}` })}
                        >
                         {formatMessage({
                             id: 'back',
                           })}
                         </Button>
                    </Grid>
                    <Grid item xs={6}>
                        <Button
                          color="primary"
                          variant="outlined"
                          fullWidth
                          onClick={() => handleEditClick()}
                        >
                         {formatMessage({
                              id: editMode == CREATE_MODE ? 'create' : 'update'
                            })}
                         </Button>
                    </Grid>
                </Grid>
            </Grid>
        </Container>
    )
};

export default BookEdit;
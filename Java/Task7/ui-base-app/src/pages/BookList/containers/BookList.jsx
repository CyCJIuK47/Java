import React from 'react';
import { useState, useEffect, useRef } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import useChangePage from 'hooks/useChangePage';
import useLocationSearch from 'hooks/useLocationSearch';
import BookCard from 'components/BookCard';
import Button from 'components/Button';
import { useIntl } from 'react-intl';
import Grid from 'components/Grid';
import { fetchBooks, fetchBookDelete, removeBookFromPage, resetBookDelete, resetBooks } from 'app/actions/book';
import { fetchAuthors, resetAuthors } from 'app/actions/author';
import * as PAGES from 'constants/pages';
import { useSnackbar } from 'notistack';
import useAccessValidate from 'hooks/useAccessValidate';


const BookList = ({ authorities }) => {

    const canSeeList = useAccessValidate({
        ownedAuthorities: authorities,
        neededAuthorities: ['READ'],
      });

      const canEditList = useAccessValidate({
          ownedAuthorities: authorities,
          neededAuthorities: ['EDIT'],
        });

    const books = useSelector(({ books }) => books);
    const deleteBook = useSelector(({ deleteBook }) => deleteBook);
    const authors = useSelector(({ authors }) => authors);

    const { enqueueSnackbar, closeSnackbar } = useSnackbar();

    const dispatch = useDispatch();
    const locationSearch = useLocationSearch();
    const changePage = useChangePage();

    const [authorsMap, setAuthorsMap] = useState({});


    useEffect(() => {
        dispatch(fetchBooks());
        dispatch(fetchAuthors());

       return ()=> {
            dispatch(resetBooks());
            dispatch(resetBookDelete());
            dispatch(resetAuthors());
        }

    }, []);

    const authorsToDict = () => {
        const map = {};
        authors.list.forEach(author => map[author.id] = author);
        return map;
    };

    useEffect(()=>{
        if(authors.list.length){
            setAuthorsMap(authorsToDict());
        }
    }, [authors.list]);




    useEffect(() => {
        if (deleteBook.isDeleted) {
            enqueueSnackbar(formatMessage({
                                id: 'book.delete.success',
                            }), { variant: "success" });
        }
    }, [deleteBook.isDeleted]);

    useEffect(() => {
        if (deleteBook.isFailed) {
            enqueueSnackbar(`${formatMessage({
                               id: 'book.delete.failed',
                           })} \n ${deleteBook.errorMessage}`,
                           { variant: "error", style: { whiteSpace: 'pre-line' } });
        }
    }, [deleteBook.isFailed]);


    const { formatMessage } = useIntl();

    return (
    <>
        { Object.keys(authorsMap).length !==0 &&
        (<Grid container justify="space-around" spacing={2} >
            <Grid container item xs={12} spacing={2}>

                <Grid item xs={12}>
                    <Button
                    fullWidth
                    color="secondary"
                    variant="outlined"
                    onClick={() => changePage({ locationSearch: locationSearch,
                                                path: `/${PAGES.INITIAL}` })}
                    >
                        {formatMessage({
                              id: 'back.to.main.page',
                        })}
                    </Button>
                </Grid>
                <Grid item xs={12}>
                    {canEditList && (<Button
                    variant="outlined"
                    fullWidth
                    color="primary"
                    onClick={() => changePage({ locationSearch: locationSearch,
                                                path: `/${PAGES.BOOK_EDIT}` })}
                    > {formatMessage({
                          id: 'book.create',
                      })} </Button>)}
                </Grid>
            </Grid>
            {
                books.list.map((item)=>{
                    const author = authorsMap[Number(item.authorId)];
                    return (
                        <Grid item >
                            <BookCard book={item} author={author}
                                onEdit={() => changePage({ path: `/${PAGES.BOOK_EDIT}/${item.id}` })}
                                onDelete={ () => dispatch(removeBookFromPage({id: item.id}))}
                                canEditCard={canEditList}
                            />
                        </Grid>
                    );
                })
            }
        </Grid>)}
    </>
    )
};

export default BookList;

import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { useSelector } from 'react-redux';
import { useIntl } from 'react-intl';
import useLocationSearch from 'hooks/useLocationSearch';
import useChangePage from 'hooks/useChangePage';
import Link from 'components/Link';
import Typography from 'components/Typography';
import useAccessValidate from 'hooks/useAccessValidate';
import Button from 'components/Button';
import config from 'config';
import * as PAGES from 'constants/pages';


const getClasses = makeStyles(() => ({
  container: {
    display: 'flex',
    flexDirection: 'column',
  },
}));

const {
    BASE_URL,
    USERS_SERVICE,
} = config;

const Initial = ({
  authorities,
}) => {
  const classes = getClasses();
  const {
    availableItems,
  } = useSelector(({ reducer })=> reducer);
  const canSeeList = useAccessValidate({
    ownedAuthorities: authorities,
    neededAuthorities: ['READ'],
  });

  const locationSearch = useLocationSearch();
  const changePage = useChangePage();

  const { formatMessage } = useIntl();

  return (
    <div className={classes.container}>
      {canSeeList && availableItems.map((item, index) => (
        <Link
          href={index % 2 === 0
            ? `https://www.google.com.ua/search?q=${item}&hl=en`
            : undefined}
          to={index % 2 !== 0
            ? (location => ({
              ...location,
              pathname: `/${item}`,
              search: `${location.search}&newProp=42`,
            }))
            : undefined}
        >
          <Typography>
            {item}
          </Typography>
        </Link>
      ))
      }

      {
        <Button
        variant="outlined"
        onClick={() => changePage({locationSearch: locationSearch,  path: `/${PAGES.BOOK_LIST}` })}
        >
            {formatMessage({
                id: 'book.list',
            })}
        </Button>
      }

      {!canSeeList && (
        <Typography>
           {formatMessage({
                 id: 'content.not.available',
            })}
        </Typography>
      )}
    </div>
  )
};

export default Initial;

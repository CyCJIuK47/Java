import React from 'react';
import PageAccessValidator from 'components/PageAccessValidator';
import BookListPage from 'pages/BookList';
import PageContainer from 'components/PageContainer';

const BookList = () => (
  <PageAccessValidator>
    <PageContainer>
      <BookListPage />
    </PageContainer>
  </PageAccessValidator>
);

export default BookList;
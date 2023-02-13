import React from 'react';
import PageAccessValidator from 'components/PageAccessValidator';
import BookEditPage from 'pages/BookEdit';
import PageContainer from 'components/PageContainer';

const BookEdit = () => (
  <PageAccessValidator>
    <PageContainer>
      <BookEditPage />
    </PageContainer>
  </PageAccessValidator>
);

export default BookEdit;
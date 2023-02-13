import {
  getToken,
} from 'token';

const getHeaders = () => ({
  Accept: 'application/json',
  Authorization: `Bearer ${getToken()}`,
  'Content-Type': 'application/json',
});

const fetchGet = ({ params = {}, url }) => {
  url = new URL(url);
  url.search = new URLSearchParams(params).toString();
  return fetch(
    url,
    {
      headers: getHeaders(),
      method: 'GET',
    }
  );
};

const SERVER_IS_NOT_RESPONDING = "Server is not responding.";
const DEFAULT_ERROR_MESSAGE = "Ooops... something went wrong."


const fetchPost = ({ body, params = {}, url }) => {
  url = new URL(url);
  url.search = new URLSearchParams(params).toString();

  return fetch(
    url,
    {
      body: JSON.stringify(body),
      headers: getHeaders(),
      method: 'POST',
    }
  );
};

const fetchPut = ({ body, params = {}, url }) => {
  url = new URL(url);
  url.search = new URLSearchParams(params).toString();

  return fetch(
    url,
    {
      body: JSON.stringify(body),
      headers: getHeaders(),
      method: 'PUT',
    }
  );
};

const fetchDeleteEntity = ({ params = {}, url }) => {
  url = new URL(url);
  url.search = new URLSearchParams(params).toString();
  return fetch(
    url,
    {
      headers: getHeaders(),
      method: 'DELETE',
    }
  );
};

export const getJson = ({
  params,
  url,
}) => {
  return fetchGet({
    params,
    url,
  }).then((response) => {
    if (response.ok) {
        return response.json();
    }
    throw response;
  });
};

export const postJson = ({
  body,
  params,
  url,
}) => {
  return fetchPost({
    body,
    params,
    url,
  }).then((response) => {
    if (response.ok) {
        return response.json();
    }
    throw response;
  });
};

export const putJson = ({
  body,
  params,
  url,
}) => {
  return fetchPut({
    body,
    params,
    url,
  }).then((response) => {
    if (response.ok) {
        return response.json();
    }
    throw response;
  });
};

export const deleteEntity = ({
  params,
  url,
}) => {
  return fetchDeleteEntity({
    params,
    url,
  }).then((response) => {
    if (response.ok) {
        return response.json();
    }
    throw response;
  });
};

export const mapRequestErrorsToString = (response) => {

    try {
       return response.json()
          .then((res) => {
              return res.errors === undefined ? [res.message] :
              res.errors.map((error) => error.defaultMessage)
          })
          .then((errorArray) => errorArray.join("\n"));

    } catch (error) {

      if (response.status === undefined) {
          return Promise.resolve(SERVER_IS_NOT_RESPONDING);
      }

      if (response.status >= 400 && response.status < 500) {
          return Promise.resolve(response.message || DEFAULT_ERROR_MESSAGE);
      }
    }

};


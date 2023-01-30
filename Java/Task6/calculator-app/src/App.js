import React from 'react';
import Calculator from './pages/Calculator';
import { createStore, combineReducers } from 'redux';
import { Provider } from 'react-redux';
import calculatorReducer from './pages/Calculator/reducers/calculator';
import examplesReducer from './pages/Calculator/reducers/examples';

const combineReducer = combineReducers({
    calculatorReducer,
    examplesReducer,
});

const store = createStore(combineReducer);

function App() {
  return (
    <div>
        <Provider store={store}>
            <Calculator/>
        </Provider>
    </div>
  );
}

export default App;

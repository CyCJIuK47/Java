const initialState = {
  availableItems: [
    'how+to+learn+js',
    'somePath',
    'Картинка',
    'anotherPath',
  ],
};

export default (state = initialState, {type, payload}) => {
  switch (type) {

    default: return state;
  }
}

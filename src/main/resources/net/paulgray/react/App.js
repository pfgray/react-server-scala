import React from 'react';
import ReactDOMServer from 'react-dom/server';

console.log('huh');

const App = () => (
  <h1>WUT</h1>
);

console.log(ReactDOMServer.renderToString(<App/>));
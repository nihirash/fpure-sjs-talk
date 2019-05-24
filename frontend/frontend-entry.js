const React = require('react')
const ReactDOM = require('react-dom')

const fastOpt = require("./frontend-fastopt.js");

const { Component } = fastOpt

ReactDOM.render(React.createElement(Component, {element: {"id":"first","isDone":false,"text":"Начать проект"}, upd: (i) => alert(i) }), root)

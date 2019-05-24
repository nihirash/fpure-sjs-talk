var path = require("path");
var merge = require('webpack-merge');
var core = require('./webpack-core.config.js')
var webpack = require("webpack");

module.exports = merge(core, {
  mode: "production",
  devtool: "source-map",
  entry: {
    "frontend-opt": [ path.resolve(__dirname, "./opt-launcher.js")]
  },
  output: {
    path: path.resolve(__dirname, "../../../../build")
  },
  plugins: [
    new webpack.DefinePlugin({
      'process.env': {
        NODE_ENV: JSON.stringify('production')
      }
    })
  ]
})

var path = require("path");
var CopyWebpackPlugin = require('copy-webpack-plugin');
var HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  mode: "development",
  devServer: {
      port: 9001,
      proxy: {
        '/api': 'http://localhost:8080/'
      }
  },
  resolve: {
    alias: {
      "./resources": path.resolve(__dirname, "../../../../src/main/resources")
    }
  },
  module: {
    rules: [
      {
        test: /\.css$/,
        use: [
          {
            loader:'style-loader'
          },
          {
            loader: 'css-loader',
            options: {
              importLoaders: 1,
              sourceMap: false,
              modules: true
            }
          }
        ]
      },
      // "file" loader for svg
      {
        test: /\.svg$/,
        use: [
          {
            loader: 'file-loader',
            query: {
              name: 'static/media/[name].[hash:8].[ext]'
            }
          }
        ]
      }
    ]
  },
  plugins: [
    new CopyWebpackPlugin([
      { from: path.resolve(__dirname, "../../../../public") }
    ]),
    new HtmlWebpackPlugin({
      template: path.resolve(__dirname, "../../../../public/index.html")
    })
  ],
  output: {
    "filename": "[name]-bundle.js"
  }
}

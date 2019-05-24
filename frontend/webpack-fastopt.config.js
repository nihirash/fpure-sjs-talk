var merge = require('webpack-merge');
var core = require('./webpack-core.config.js')
var HtmlWebpackPlugin = require('html-webpack-plugin');
var path = require("path");

var generatedConfig = require("./scalajs.webpack.config.js")

module.exports = merge(core, {
        devtool: "cheap-module-eval-source-map",
        entry: {
            "dependencies": ["./frontend-fastopt-entrypoint.js"],
            "frontend-fastopt": ["./frontend-entry.js"]
        },
        output: {
            path: __dirname,
            filename: "[name]-library.js",
            library: "appLibrary",
            libraryTarget: "var"
        },
    plugins: [
        new HtmlWebpackPlugin({
            template: path.resolve(__dirname, "../../../../public/index.html"),
            inject: false
        })
    ]
    }
)

/*
    ./webpack.config.js
*/
const path = require('path');

const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    entry: {
        'index': './client/index.js',
        'book': './client/book.js',
    },
    output: {
        path: path.resolve('dist'),
        filename: '[name].js'
    },
    module: {
        loaders: [
            { test: /\.js$/, loader: 'babel-loader', exclude: /node_modules/ },
            { test: /\.jsx$/, loader: 'babel-loader', exclude: /node_modules/ }
        ]
    },
    plugins: [
      new HtmlWebpackPlugin({
        inject: 'body',
        chunks: ['index'],
        filename: 'index.html',
        template: './client/index.html',
      }),
      new HtmlWebpackPlugin({
        inject: 'body',
        chunks: ['book'],
        filename: 'book.html',
        template: './client/book.html',
      })
    ]
}


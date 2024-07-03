const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    mode: 'development',
    devtool: 'inline-source-map',
    devServer: {
        static: './dist',
        port: 8888
    },
    entry: {
        index: './src/index.js'
    },
    output: {
        filename: 'bundle.js',
        path: path.resolve(__dirname, 'dist'),
        clean: true //czyszczenie katalogu /dist przed budowaniem
    },

    module: {
        rules: [
            //   Dodane w celu ładowania css w plikach js
            {
                test: /\.css$/i,
                use: ['style-loader', 'css-loader'],
            },
            //   Dodane w celu ładowania obrazków w plkach js
            {
                test: /\.(png|svg|jpg|jpeg|gif)$/i,
                type: 'asset/resource',
            },
            //   Dodane w celu ładowania danych xml i csv
            {
                test: /\.(csv|tsv)$/i,
                use: ['csv-loader'],
            },
            {
                test: /\.xml$/i,
                use: ['xml-loader'],
            },
        ],
    },
    // Dodany, eby automatycznie aktualizować index.html i dołączać do niego pliki js, m.in z entries
    plugins: [
        new HtmlWebpackPlugin({
            title: 'Output Management'
        }),
    ],
};
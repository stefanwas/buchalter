var rootElement =
    React.createElement('div', {},
        React.createElement('input', {
            type: 'text',
            placeholder: 'Name (required)',
            value: "Some value",
            onChange: function () {
            }

        }),

        // If your `children` is an array, you'll need to give each one a unique `key`
        // prop. I'll explain why a little later.
        React.createElement('h4', {}, "Some text")
    )

ReactDOM.render(rootElement, document.getElementById('react-app'))
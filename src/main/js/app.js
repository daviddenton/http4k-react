'use strict';

const React = require('react');
const ReactDOM = require('react-dom')
const client = require('./client');
const EmployeeList = require('./components/EmployeeList');

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {employees: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/employees'}).then(response => {
            this.setState({employees: response.entity});
        });
    }

    render() {
        return (
            <EmployeeList employees={this.state.employees}/>
        )
    }
}

ReactDOM.render(<App />, document.getElementById('root'))
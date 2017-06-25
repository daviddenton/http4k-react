'use strict';

const React = require('react');
const ReactDOM = require('react-dom')
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {employees: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/employees'}).then(response => {
            this.setState({employees: response.entity.employees});
        });
    }

    render() {
        return (
            <EmployeeList employees={this.state.employees}/>
        )
    }
}

class EmployeeList extends React.Component {
    render() {
        const employees = this.props.employees.map(employee => <Employee key={employee.id} employee={employee}/>);
        return (
            <table>
                <tbody>
                <tr>
                    <th>First Name</th>
                </tr>
                {employees}
                </tbody>
            </table>
        )
    }
}

class Employee extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.employee.firstName}</td>
            </tr>
        )
    }
}

ReactDOM.render(<App />, document.getElementById('root'))
const React = require('react');

class Employee extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.employee.firstName}</td>
            </tr>
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

module.exports = EmployeeList;

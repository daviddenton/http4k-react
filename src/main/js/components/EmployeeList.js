const React = require('react');
const ajax = require('superagent');

class AddForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {name: ''};
        this.changeName = this.changeName.bind(this);
        this.handleAdd = this.handleAdd.bind(this);
    }

    handleAdd() {
        const c = this;
        ajax.post('/api/employee')
            .send({firstName: this.state.name})
            .set('Accept', 'application/json')
            .end(function (err, res) {
                c.setState({name: ''});
            });
    }

    changeName(e) {
        this.setState({name: e.target.value});
    }

    render() {
        return (
            <div>
                <input type="text" onChange={this.changeName} value={this.state.name}/>
                <button disabled={!this.state.name} onClick={this.handleAdd}>Add</button>
            </div>
        )
    }
}

class DeleteButton extends React.Component {
    constructor(props) {
        super(props);
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick(event) {
        this.setState({value: event.target.value});
    }

    render() {
        return (
            <button onClick={this.handleClick}>Delete</button>
        )
    }
}

class Employee extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.employee.id}</td>
                <td>{this.props.employee.firstName}</td>
                <td>
                    <DeleteButton />
                </td>
            </tr>
        )
    }
}

class EmployeeList extends React.Component {
    render() {
        const employees = this.props.employees.map(employee => <Employee key={employee.id} employee={employee}/>);
        return <div>
            <table>
                <tbody>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                </tr>
                {employees}
                </tbody>
            </table>
            <AddForm />
        </div>
    }
}

module.exports = EmployeeList;

'use strict';

const React = require('react');
const ReactDOM = require('react-dom')
const ajax = require('superagent');
const EmployeeList = require('./components/EmployeeList');

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {employees: []};
    }

    componentDidMount() {
        const c = this;
        ajax.get('/api/employee')
            .end(function (err, res) {
                c.setState({employees: JSON.parse(res.text)});
            });
    }

    render() {
        return (
            <EmployeeList employees={this.state.employees}/>
        )
    }
}

ReactDOM.render(<App />, document.getElementById('root'))
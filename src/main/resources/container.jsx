class Container extends React.Component {
    render() {
        return <div>
            <nav className="navbar navbar-inverse navbar-fixed-top">
                <div className="container">
                    Hello
                </div>
            </nav>
            <div id="content" className="container theme-showcase" role="main">
                {this.props.children}
            </div>
        </div>
    }
}
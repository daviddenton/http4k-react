const {Router, Route, IndexRoute, hashHistory} = ReactRouter;

ReactDOM.render(
    <Router history={hashHistory}>
        <Route path="/" component={Container}>
            <IndexRoute component={HomePage}/>
            <IndexRoute component={HomePage2}/>
        </Route>
    </Router>,
    document.getElementById('root')
);
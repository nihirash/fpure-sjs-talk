import React from 'react'
import AddItem from './todo/addNew'
import { Component } from 'nihirash-sjs-talk'

class App extends React.Component {
    state = {downloaded: false, data: []}

    refetchData() {
        fetch("/api")
            .then(r => {
                if (r.status === 200) r.json().then(x => this.setState({downloaded: true, data: x}))
            })

    }

    pushNew(item) {
        if (item.text) {
            item.id = Math.random().toString(36)
            fetch("/api", {method: 'POST', body: JSON.stringify(item)})
                .then(r =>
                    setTimeout(this.refetchData(), 1000)
                )
        }
    }

    render() {
        const {downloaded, data} = this.state

        if (!downloaded)
            this.refetchData()

        return <>
            <h1>Todo list</h1>
            <AddItem newItem={(item) => this.pushNew(item)}/>
            {data.map(r => <Component element={r} upd={(u) => this.pushNew(u)}/>)}
        </>
    }
}

export default App;

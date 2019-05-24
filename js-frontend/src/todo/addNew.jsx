import React from 'react'
import TodoCreator from './todoCreator'

class addItem extends React.Component {
    state = {show: false}

    hide() {
        this.setState({show: false})
    }

    render() {
        const {show} = this.state
        const {newItem} = this.props

        return <>
            <div className="button">
                <button onClick={() => this.setState({show: !show})}>Add</button>
            </div>
            {show && <TodoCreator submit={(i) => {this.hide(); newItem(i);}}/>}
            </>
    }
}


export default addItem
